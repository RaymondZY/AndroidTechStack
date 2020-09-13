package zhaoyun.techstack.android.fragment.list;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhaoyun.techstack.android.fragment.model.AnimeRepository;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class AnimeRecyclerView extends RecyclerView {

    private AnimeAdapter mAnimeAdapter;

    public AnimeRecyclerView(@NonNull Context context) {
        super(context);
    }

    public AnimeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnAnimeClickedListener(AnimeListFragment.OnAnimeClickedListener listener) {
        mAnimeAdapter.setOnAnimeClickedListener(listener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initView();
    }

    private void initView() {
        mAnimeAdapter = new AnimeAdapter();
        mAnimeAdapter.updateModel(AnimeRepository.createAnimeList());
        setAdapter(mAnimeAdapter);
        setLayoutManager(new LinearLayoutManager(getContext()));
        addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}
