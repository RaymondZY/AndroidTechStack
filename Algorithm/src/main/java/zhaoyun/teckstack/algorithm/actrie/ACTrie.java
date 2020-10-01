package zhaoyun.teckstack.algorithm.actrie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhaoyun
 * @version 2018/9/5
 *
 * Aho–Corasick自动机（AC自动机）的双数组压缩实现。
 * AC自动机主要是为了解决多模式匹配问题。
 *
 * 如：
 * 利用词典构建AC自动机。
 * 输入一个句子，判断句子中出现了词典中的哪些单词。
 * 时间复杂度为O(length)。
 *
 * 使用双数组实现是因为，AC的构造方法复杂，需要消耗的时间较高。
 * 双数组实现的AC，只需要base、check、fail三个数组就能够完成查询。
 * 因此，如果在词典固定的情况下，可以先离线初始化AC自动机。
 * 在线处理时读取离线的base、check、fail数组就可以工作。
 */
public class ACTrie {

    /**
     * AC自动机初始化Builder。
     *
     * 初始化过程较复杂，使用Builder构建后，回收构建时的所有对象。
     */
    private class Builder {

        /**
         * 字典里的所有单词。
         */
        private List<String> mWordList;

        /**
         * 为了辅助构建AC自动机创建的Trie。
         *
         */
        private TreeMapTrie mTreeMapTrie;

        /**
         * 标记base值是否使用过。
         *
         * 每一个状态的base值需要保持不同。
         * 虽然两个状态使用同一个base并不一定会产生状态转移冲突，
         * 但是检查状态转移 S + c = T 时需要判断check[base[S] + c] = base[S]，base相同会混淆这个判断。
         */
        private boolean[] mBaseUsed;

        /**
         * 记录最大的状态值，这个值是为了配合数组裁剪大小使用的。
         */
        private int mMaxState;

        /**
         * 每次寻找Base时的起始值，这个值是为了配合启发式使用的。
         */
        private int mStartBase;

        /**
         * 启发式寻找Base值时的压缩比。
         */
        private double mCompressLevel;

        /**
         * 构造函数。
         * @param wordList 词典。
         */
        private Builder(List<String> wordList) {
            mWordList = wordList;
        }

        /**
         * 构建ACTrie。
         * @param compressLevel 启发式寻找Base值时的压缩比例参数。
         */
        private void build(double compressLevel) {
            mCompressLevel = compressLevel;

            mBase = new int[DEFAULT_STATE_SIZE];
            mCheck = new int[DEFAULT_STATE_SIZE];
            mBaseUsed = new boolean[DEFAULT_STATE_SIZE];
            mMaxState = ROOT_STATE;
            mStartBase = ROOT_STATE + 1;

            buildTrie();
            buildBaseCheck(ROOT_STATE, mTreeMapTrie.getRootState());
            resize(mMaxState + 1);
            buildFail(mTreeMapTrie.getRootState());
        }

        /**
         * 构建Trie辅助AC自动机的构建。
         */
        private void buildTrie() {
            mTreeMapTrie = new TreeMapTrie();
            mTreeMapTrie.build(mWordList);
        }

        /**
         * 构建Base和Check数组。
         * @param currentState 当前构建的状态数值。
         * @param trieState Trie中对应的状态节点。
         */
        private void buildBaseCheck(int currentState, TreeMapTrie.State trieState) {
            int[] inputs = calcInputs(trieState);
            int base = mStartBase;
            int nonBlankCount = 0;
            boolean found = false;
            while (!found) {
                boolean allBlank = true;
                for (int i = 0; i < inputs.length; i++) {
                    int input = inputs[i];
                    int nextState = input + base;
                    if (nextState >= mBase.length) {
                        resize(mBase.length * 2);
                    }
                    if (mBaseUsed[base] || mCheck[nextState] != 0) {
                        if (i == 0) {
                            nonBlankCount++;
                        }
                        allBlank = false;
                        break;
                    }
                }

                if (allBlank) {
                    found = true;
                    if (1.0 * nonBlankCount / (base - mStartBase + 1) >= mCompressLevel) {
                        mStartBase = base;
                    }
                    mBaseUsed[base] = true;
                    mMaxState = Math.max(mMaxState, base + inputs[inputs.length - 1]);
                    mBase[currentState] = base;
                    if (trieState.isAcceptable()) {
                        mBase[base] = -(trieState.getIndexInDict() + 1);
                    }
                    for (int input : inputs) {
                        mCheck[base + input] = base;
                    }
                    for (Character input : trieState.getTransitionMap().keySet()) {
                        int nextState = base + input + 1;
                        buildBaseCheck(nextState, trieState.getTransitionMap().get(input));
                    }
                } else {
                    base++;
                }
            }
        }

        /**
         * 构建Fail数组。
         * @param rootTrieState 根节点状态。
         */
        private void buildFail(TreeMapTrie.State rootTrieState) {
            mFail = new int[mBase.length];
            mFail[ROOT_STATE] = ROOT_STATE;

            Queue<Integer> stateQueue = new ArrayDeque<>();
            Queue<TreeMapTrie.State> trieStateQueue = new ArrayDeque<>();
            stateQueue.add(ROOT_STATE);
            trieStateQueue.add(rootTrieState);

            while (!stateQueue.isEmpty()) {
                int state = stateQueue.remove();
                TreeMapTrie.State trieState = trieStateQueue.remove();
                int[] inputs = calcInputs(trieState);
                for (int input : inputs) {
                    int nextState = mBase[state] + input;

                    int fallbackState = state;
                    mFail[nextState] = ROOT_STATE;
                    while (fallbackState != ROOT_STATE) {
                        fallbackState = mFail[fallbackState];
                        if (mCheck[mBase[fallbackState] + input] == mBase[fallbackState]) {
                            mFail[nextState] = mBase[fallbackState] + input;
                            break;
                        }
                    }

                    if (input != END_INPUT) {
                        stateQueue.add(nextState);
                    }
                }

                trieStateQueue.addAll(trieState.getTransitionMap().values());
            }
        }

        /**
         * 计算当前状态的状态转移input数组。
         *
         * 分两种情况：
         * 1.如果当前状态是不是目标状态，
         * input数组每一位的值为Trie中的状态转移值+1(+1是因为0被用于表示句子结束，整体后移1)。
         *
         *                                   "h"
         *                                 /    \
         *      106 = 105 + 1 = 'i' + 1  /       \ "e" + 1 = 101 + 1 = 102
         *                             /          \
         *                          "hi"         "he"
         *
         *      input = [106, 102]

         *
         * 2. 如果当前状态是目标状态，
         * input数组在1.的基础上首位补上0。
         *
         *                                   "he"
         *                                 /     \
         *            用于标记单词结尾 0   /        \ "r" + 1 = 114 + 1 = 115
         *                             /            \
         *                       "he"在词典中       "her"
         *
         *      input = [0, 115]
         *
         * @param trieState 当前TrieState。
         * @return input数组。
         */
        private int[] calcInputs(TreeMapTrie.State trieState) {
            int[] inputs;
            int nextStateCount = trieState.getTransitionMap().size();
            int nextStateIndex = 0;
            if (trieState.isAcceptable()) {
                inputs = new int[1 + nextStateCount];
                inputs[nextStateIndex++] = END_INPUT;
            } else {
                inputs = new int[nextStateCount];
            }
            for (Character input : trieState.getTransitionMap().keySet()) {
                inputs[nextStateIndex++] = input + 1;
            }
            return inputs;
        }

        /**
         * 重新调整各个数组大小。
         * @param newSize 新的大小。
         */
        private void resize(int newSize) {
            int[] newBase = new int[newSize];
            int[] newCheck = new int[newSize];
            boolean[] newUsed = new boolean[newSize];

            System.arraycopy(mBase, 0, newBase, 0, Math.min(mBase.length, newSize));
            System.arraycopy(mCheck, 0, newCheck, 0,  Math.min(mCheck.length, newSize));
            System.arraycopy(mBaseUsed, 0, newUsed, 0,  Math.min(mBaseUsed.length, newSize));

            mBase = newBase;
            mCheck = newCheck;
            mBaseUsed = newUsed;
        }
    }

    private static final int ROOT_STATE = 0;
    private static final int END_INPUT = 0;
    private static final int DEFAULT_STATE_SIZE = 65535 * 32;
    private static final double DEFAULT_COMPRESS_LEVEL = 0.5;

    private int mBase[];
    private int mCheck[];
    private int mFail[];

    /* ---------- Getter ---------- */

    public int[] getBase() {
        return mBase;
    }

    public int[] getCheck() {
        return mCheck;
    }

    public int[] getFail() {
        return mFail;
    }

    /* ----------------------------- */

    /**
     * 构造函数。
     * @param base base数组。
     * @param check check数组。
     * @param fail fail数组。
     */
    public void build(int[] base, int[] check, int[] fail) {
        mBase = base;
        mCheck = check;
        mFail = fail;
    }

    /**
     * 构造函数。
     * @param wordList 词典。
     */
    public void build(List<String> wordList) {
        this.build(wordList, DEFAULT_COMPRESS_LEVEL);
    }

    /**
     * 构造函数。
     * @param wordList 词典。
     * @param compressLevel 启发式寻找base的压缩比。
     */
    public void build(List<String> wordList, double compressLevel) {
        compressLevel = Math.min(compressLevel, 1);
        compressLevel = Math.max(compressLevel, 0);
        new Builder(wordList).build(compressLevel);
    }

    /**
     * 判断单词是否在词典中。
     * @param word 单词。
     * @return 是否在词典中。
     */
    public boolean check(String word) {
        int currentState = 0;
        for (char character : word.toCharArray()) {
            int nextState = mBase[currentState] + character + 1;
            if (nextState < mBase.length && mCheck[nextState] == mBase[currentState]) {
                currentState = nextState;
            } else {
                return false;
            }
        }
        int endState = mBase[currentState] + END_INPUT;
        return endState < mBase.length && mCheck[endState] == mBase[currentState] && mBase[endState] < 0;
    }

    /**
     * 判断文本中出现了哪些词典中的词。
     * @param text 文本。
     * @return 出现了的词的数组。数组的值代表词典中的Index。
     */
    public List<Integer> checkHits(String text) {
        List<Integer> hitIndexList = new ArrayList<>();

        int currentState = ROOT_STATE;
        for (char character : text.toCharArray()) {
            while (true) {
                int nextState = mBase[currentState] + character + 1;
                if (nextState < mBase.length && mCheck[nextState] == mBase[currentState]) {
                    currentState = nextState;
                    break;
                } else {
                    if (currentState == ROOT_STATE) {
                        break;
                    } else {
                        currentState = mFail[currentState];
                    }
                }
            }
            int state = currentState;
            while (state != ROOT_STATE) {
                int endState = mBase[state] + END_INPUT;
                if (mCheck[endState] == mBase[state] && mBase[endState] < 0) {
                    int wordIndex = -mBase[endState] - 1;
                    hitIndexList.add(wordIndex);
                }
                state = mFail[state];
            }
        }

        return hitIndexList;
    }

    public int getSize() {
        return mBase.length;
    }
}