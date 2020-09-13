package zhaoyun.techstack.android.coordinatorlayout.custom.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import zhaoyun.techstack.android.coordinatorlayout.custom.R;

/**
 * @author zhaoyun
 * @version 2020/5/27
 */
public class RecyclerViewBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    public RecyclerViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        return dependency.getId() == R.id.header;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        int dependencyBottom = dependency.getBottom();
        child.layout(0, dependencyBottom, parent.getWidth(), dependencyBottom + child.getHeight());
        return true;
    }

    @Override
    public boolean onMeasureChild(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        List<View> dependencyList = parent.getDependencies(child);
        View dependency = findFirstDependency(dependencyList);
        int parentHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
        if (dependency != null) {
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);
            parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
            return true;
        }
        return false;
    }

    private View findFirstDependency(List<View> dependencyList) {
        for (View dependency : dependencyList) {
            if (dependency.getId() == R.id.header) {
                return dependency;
            }
        }
        return null;
    }
}
