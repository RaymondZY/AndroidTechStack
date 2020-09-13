package zhaoyun.techstack.android.fragment.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhaoyun.techstack.android.fragment.R;
import zhaoyun.techstack.android.fragment.model.Anime;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {

    private List<Anime> mAnimeList;
    private AnimeListFragment.OnAnimeClickedListener mOnAnimeClickedListener;

    public void updateModel(List<Anime> animeList) {
        mAnimeList = animeList;
        notifyDataSetChanged();
    }

    public void setOnAnimeClickedListener(AnimeListFragment.OnAnimeClickedListener listener) {
        mOnAnimeClickedListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AnimeItemView animeItemView = (AnimeItemView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.anime_item_view,
                parent,
                false
        );
        ViewHolder viewHolder = new ViewHolder(animeItemView);
        animeItemView.setOnClickListener(
                view -> {
                    if (mOnAnimeClickedListener != null) {
                        mOnAnimeClickedListener.onAnimeClicked(
                                mAnimeList.get(viewHolder.getAdapterPosition())
                        );
                    }
                }
        );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updateModel(mAnimeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAnimeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private AnimeItemView mAnimeItemView;

        public ViewHolder(@NonNull AnimeItemView itemView) {
            super(itemView);

            mAnimeItemView = itemView;
        }

        public void updateModel(Anime anime) {
            mAnimeItemView.updateModel(anime);
        }
    }
}
