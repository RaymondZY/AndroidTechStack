package zhaoyun.techstack.android.fragment.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import zhaoyun.techstack.android.fragment.LogFragment;
import zhaoyun.techstack.android.fragment.R;
import zhaoyun.techstack.android.fragment.model.Anime;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class AnimeDetailFragment extends LogFragment {

    private static final String ARGUMENT_KEY_ANIME = "anime";

    public static AnimeDetailFragment newInstance(Anime anime) {
        Bundle argument = new Bundle();
        argument.putParcelable(ARGUMENT_KEY_ANIME, anime);
        AnimeDetailFragment fragment = new AnimeDetailFragment();
        fragment.setArguments(argument);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.fragment_anime_detail, container, false);

        Anime anime = null;
        if (getArguments() != null) {
            anime = getArguments().getParcelable(ARGUMENT_KEY_ANIME);
        }
        initView(fragmentView, anime);
        return fragmentView;
    }

    private void initView(View fragmentView, Anime anime) {
        TextView nameTextView = fragmentView.findViewById(R.id.textView_name);
        TextView likeCountTextView = fragmentView.findViewById(R.id.textView_likeCount);
        TextView watchedTextView = fragmentView.findViewById(R.id.textView_watched);

        if (anime != null) {
            nameTextView.setText(String.format("名称 = %s", anime.getName()));
            likeCountTextView.setText(String.format(Locale.getDefault(), "点赞 = %d", anime.getLikeCount()));
            watchedTextView.setText(String.format("看过 = %b", anime.isWatched()));
        }
    }
}
