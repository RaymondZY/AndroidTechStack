package zhaoyun.teckstack.android.modular.router.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import zhaoyun.teckstack.android.modular.router.annotation.RouteBean;
import zhaoyun.teckstack.android.modular.router.annotation.RouteMap;
import zhaoyun.teckstack.android.modular.router.annotation.Router;

/**
 * @author zhaoyun
 * @version 2020/6/29
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("zhaoyun.teckstack.android.modular.router.annotation.Router")
@SupportedOptions("module")
public class RouterProcessor extends AbstractProcessor {

    private String mModule;

    private Elements mElementUtils;
    private Messager mMessager;
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();

        mModule = processingEnv.getOptions().get("module");
        mMessager.printMessage(Diagnostic.Kind.NOTE, "module = " + mModule);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Router.class);
        if (elements.size() > 0) {
            mMessager.printMessage(Diagnostic.Kind.NOTE, "RouterProcessor.process()");

            // public Map<String, RouteBean> getRouteBeanMap() {
            MethodSpec.Builder methodBuilder = MethodSpec
                    .methodBuilder("getRouteBeanMap")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(ParameterizedTypeName.get(Map.class, String.class, RouteBean.class));

            // Map<String, RouteBean> routeBeanMap = new HashMap<>();
            methodBuilder.addStatement(
                    "$T<$T, $T> $N = new $T<>()",
                    ClassName.get(Map.class),
                    ClassName.get(String.class),
                    ClassName.get(RouteBean.class),
                    "routeBeanMap",
                    ClassName.get(HashMap.class)
            );

            for (Element element : elements) {
                String packageName = mElementUtils.getPackageOf(element).getQualifiedName().toString();
                String className = element.getSimpleName().toString();

                mMessager.printMessage(Diagnostic.Kind.NOTE, "processing : " + packageName + "." + className);

                Router router = element.getAnnotation(Router.class);
                String path = router.path();
                checkPath(path);
                String group = path.substring(1, path.lastIndexOf("/"));

                // routeBeanMap.put("/app/MainActivity", new RouteBean("app", "/app/MainActivity", MainActivity.class));
                methodBuilder.addStatement(
                        "$N.put($S, new $T($S, $S, $T.class))",
                        "routeBeanMap",
                        path,
                        ClassName.get(RouteBean.class),
                        group,
                        path,
                        ClassName.get((TypeElement) element)
                );
            }

            // return routeBeanMap;
            methodBuilder.addStatement(
                    "return $N",
                    "routeBeanMap"
            );

            // public class Router$$app implements RouteMap
            try {
                JavaFile.builder(
                        "zhaoyun.teckstack.android.modular.apt",
                        TypeSpec.classBuilder("Router$$" + mModule)
                                .addSuperinterface(ClassName.get(RouteMap.class))
                                .addModifiers(Modifier.PUBLIC)
                                .addMethod(methodBuilder.build())
                                .build()
                ).build().writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
        return false;
    }

    private void checkPath(String path) {
        if (path == null || path.length() == 0) {
            mMessager.printMessage(Diagnostic.Kind.ERROR, "path should not be empty");
        } else if (!path.startsWith("/")) {
            mMessager.printMessage(Diagnostic.Kind.ERROR, "path should be like : /app/MainActivity");
        } else if (path.lastIndexOf("/") == 0) {
            mMessager.printMessage(Diagnostic.Kind.ERROR, "path should be like : /app/MainActivity");
        } else {
            String group = path.substring(1, path.lastIndexOf("/"));
            if (group.contains("/")) {
                mMessager.printMessage(Diagnostic.Kind.ERROR, "path should be like : /app/MainActivity");
            } else if (!group.equals(mModule)) {
                mMessager.printMessage(Diagnostic.Kind.ERROR, "path should be like : /app/MainActivity");
                mMessager.printMessage(Diagnostic.Kind.ERROR, "first segment should equal to module name");
            }
        }
    }
}
