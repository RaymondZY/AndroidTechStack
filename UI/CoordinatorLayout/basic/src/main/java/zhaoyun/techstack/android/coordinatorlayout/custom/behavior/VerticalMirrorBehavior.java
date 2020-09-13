package zhaoyun.techstack.android.coordinatorlayout.custom.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import zhaoyun.techstack.android.coordinatorlayout.basic.R;

/**
 * @author zhaoyun
 * @version 2020/5/26
 */
public class VerticalMirrorBehavior extends CoordinatorLayout.Behavior<View> {

    public VerticalMirrorBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency.getId() == R.id.textView_base && child.getId() == R.id.textView_verticalMirror;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        float dependencyX = dependency.getX();
        float dependencyY = dependency.getY();
        child.setX(parent.getWidth() - dependencyX - child.getWidth());
        child.setY(dependencyY);
        return true;
    }
}
