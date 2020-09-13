package zhaoyun.techstack.android.viewpager.reuse;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author zhaoyun
 * @version 2020/5/19
 */
public class ReuseAdapter extends PagerAdapter {

    private static final int N = 10;

    private LayoutInflater mLayoutInflater;
    private Queue<View> mViewCacheQueue;

    ReuseAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mViewCacheQueue = new LinkedList<>();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View convertView = mViewCacheQueue.poll();
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mLayoutInflater.inflate(R.layout.item_view_number, container, false);
            TextView textView = convertView.findViewById(R.id.textView);
            viewHolder = new ViewHolder(textView);
            convertView.setTag(viewHolder);
        }
        viewHolder.mTextView.setText(String.valueOf(position));
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View itemView = (View) object;
        container.removeView(itemView);
        mViewCacheQueue.add(itemView);
    }

    @Override
    public int getCount() {
        return N;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    private static class ViewHolder {

        private TextView mTextView;

        private ViewHolder(TextView textView) {
            mTextView = textView;
        }
    }
}
