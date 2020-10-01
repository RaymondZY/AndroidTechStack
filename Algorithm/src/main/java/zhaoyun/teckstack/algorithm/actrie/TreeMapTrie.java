package zhaoyun.teckstack.algorithm.actrie;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhaoyun
 * @version 2018/9/5
 *
 * 状态转移使用{@link TreeMap}实现的字典树。
 */
public class TreeMapTrie {

    /* ---------- State ---------- */

    /**
     * Trie本质上是一个有限状态机(DFA)。
     * 每一个状态代表一个单词(RootState代表空串)。
     * 每一个状态有目标状态和非目标状态的区别。
     * 目标状态表示此状态对应的单词在词典中出现过。
     *
     * 状态的转移：
     *
     * "he" + "y" = "hey"
     *
     * 现有状态："he"
     * 输入："y"
     * 新的状态："hey"
     */
    public static class State {

        /**
         * 状态转移Map。
         *
         * Key为状态转移输入，Value为目标状态。
         */
        private Map<Character, State> mTransitionMap;

        /**
         * 此状态是否为目标状态。
         */
        private boolean mAcceptable = false;

        /**
         * 如果此状态为目标状态，IndexInDict存储对应的单词在初始化词典中的Index。
         */
        private int mIndexInDict;

        /**
         * 构造函数。
         */
        private State() {
            mTransitionMap = new TreeMap<>();
        }

        /**
         * 获取状态转移Map。
         * @return 状态转移Map。
         */
        public Map<Character, State> getTransitionMap() {
            return mTransitionMap;
        }

        /**
         * 创建新的状态转移。
         * 当前状态接受输入{@param input}，转移到{@return}状态。
         * @param input 接受的输入。
         * @return 转移到的状态。
         */
        public State createNextTransition(char input) {
            State newState = new State();
            mTransitionMap.put(input, newState);
            return newState;
        }

        /**
         * 状态转移函数。
         * @param input 输入。
         * @return 如果此状态接受输入，则返回目标状态，否则返回{@code null}。
         */
        public State transit(char input) {
            return mTransitionMap.get(input);
        }

        /**
         * 设置此状态为目标状态。
         * 代表此状态对应的单词在词典中出现过。
         * @param index 此状态对应的单词在词典中的Index。
         */
        public void setAcceptable(int index) {
            mAcceptable = true;
            mIndexInDict = index;
        }

        /**
         * 判断此状态是否为目标状态。
         * @return 是否为目标状态。
         */
        public boolean isAcceptable() {
            return mAcceptable;
        }

        /**
         * 获取此状态对应的单词在词典中的Index。
         * @return 在词典中的Index。
         */
        public int getIndexInDict() {
            return mIndexInDict;
        }
    }

    /* ----------------------------- */

    /**
     * 根节点的状态。
     */
    private State mRootState;

    /**
     * 构造函数。
     */
    public TreeMapTrie() {
        mRootState = new State();
    }

    /**
     * 获取Trie的根节点。
     * @return 根节点。
     */
    public State getRootState() {
        return mRootState;
    }

    /**
     * 构造Trie。
     * @param wordList 词典中所有的词。
     */
    public void build(List<String> wordList) {
        for (int i = 0; i < wordList.size(); i++) {
            insert(wordList.get(i), i);
        }
    }

    /**
     * 检查一个单词是否在词典中出现。
     * @param word 输入单词。
     * @return 是否在词典中出现。
     */
    public boolean check(String word) {
        State currentState = mRootState;
        for (char character : word.toCharArray()) {
            currentState = currentState.transit(character);
            if (currentState == null) {
                return false;
            }
        }
        return currentState.isAcceptable();
    }

    /**
     * 插入单词。
     * @param word 单词。
     * @param indexInDict 单词在词典中的Index。
     */
    private void insert(String word, int indexInDict) {
        State currentState = mRootState;
        for (char character : word.toCharArray()) {
            State nextStatue = currentState.transit(character);
            if (nextStatue == null) {
                nextStatue = currentState.createNextTransition(character);
            }
            currentState = nextStatue;
        }
        currentState.setAcceptable(indexInDict);
    }
}
