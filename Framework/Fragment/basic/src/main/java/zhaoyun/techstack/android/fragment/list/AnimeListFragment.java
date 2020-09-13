package zhaoyun.techstack.android.fragment.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import zhaoyun.techstack.android.fragment.LogFragment;
import zhaoyun.techstack.android.fragment.R;
import zhaoyun.techstack.android.fragment.model.Anime;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class AnimeListFragment extends LogFragment {

    public interface OnAnimeClickedListener {
        void onAnimeClicked(Anime anime);
    }

    private OnAnimeClickedListener mOnAnimeClickedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnAnimeClickedListener) {
            mOnAnimeClickedListener = (OnAnimeClickedListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.fragment_anime_list, container, false);
        AnimeRecyclerView animeRecyclerView = fragmentView.findViewById(R.id.recyclerView_anime);
        animeRecyclerView.setOnAnimeClickedListener(mOnAnimeClickedListener);
        return fragmentView;
    }
}
