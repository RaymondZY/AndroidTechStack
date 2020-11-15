package zhaoyun.techstack.android.transform;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author zhaoyun
 * @version 2020/11/15
 */
class TransformPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        appExtension.registerTransform(new CustomTransform());
    }
}