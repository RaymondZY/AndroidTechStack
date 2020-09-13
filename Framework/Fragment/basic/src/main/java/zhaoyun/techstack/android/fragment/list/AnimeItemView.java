package zhaoyun.techstack.android.fragment.list;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import zhaoyun.techstack.android.fragment.R;
import zhaoyun.techstack.android.fragment.model.Anime;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class AnimeItemView extends ConstraintLayout {

    private TextView mNameTextView;

    public AnimeItemView(Context context) {
        super(context);
    }

    public AnimeItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mNameTextView = findViewById(R.id.textView_name);
    }

    public void updateModel(Anime anime) {
        mNameTextView.setText(anime.getName());
    }
}
