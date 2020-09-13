package zhaoyun.techstack.android.view.nestedscroll.approach1;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhaoyun.techstack.android.view.nestedscroll.R;

/**
 * @author zhaoyun
 * @version 2020/4/23
 */
public class ContainerView extends ConsecutiveScrollView {

    public ContainerView(Context context) {
        this(context, null);
    }

    public ContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("https://github.com/RaymondZY?tab=repositories");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new Adapter(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
