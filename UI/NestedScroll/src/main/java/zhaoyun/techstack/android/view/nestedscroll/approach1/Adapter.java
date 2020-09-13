package zhaoyun.techstack.android.view.nestedscroll.approach1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhaoyun.techstack.android.view.nestedscroll.R;

/**
 * @author zhaoyun
 * @version 2020/4/22
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;

    Adapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String comment = "评论 " + (position + 1) + " : 这是一条价值5毛的评论";
        holder.mTextView.setText(comment);
    }

    @Override
    public int getItemCount() {
        return 1000000;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
