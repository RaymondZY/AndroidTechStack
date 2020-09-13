package zhaoyun.techstack.android.viewpager.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author zhaoyun
 * @version 2020/5/19
 */
public class ViewAdapter extends PagerAdapter {

    private static final int N = 10;

    private LayoutInflater mLayoutInflater;

    ViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_view_number, container, false);
        container.addView(itemView);
        itemView.setTag(position);
        TextView textView = itemView.findViewById(R.id.textView);
        textView.setText(String.valueOf(position));
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View itemView = container.findViewWithTag(position);
        container.removeView(itemView);
    }

    @Override
    public int getCount() {
        return N;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
