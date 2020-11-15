package zhaoyun.techstack.android.transform;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Status;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author zhaoyun
 * @version 2020/11/15
 */
class CustomTransform extends Transform {
    @Override
    public String getName() {
        return "CustomTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return true;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws IOException {
        System.out.println("Transforming..");
        System.out.println("isIncremental = " + transformInvocation.isIncremental());

        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        if (!transformInvocation.isIncremental()) {
            for (TransformInput transformInput : transformInvocation.getInputs()) {
                System.out.println("TransformInput " + transformInput);

                for (DirectoryInput directoryInput : transformInput.getDirectoryInputs()) {
                    System.out.println("directoryInput " + directoryInput);

                    File output = outputProvider.getContentLocation(
                            directoryInput.getName(),
                            directoryInput.getContentTypes(),
                            directoryInput.getScopes(),
                            Format.DIRECTORY
                    );

                    System.out.println("copy file from " + directoryInput.getFile().getAbsolutePath() + " to " + output.getAbsolutePath());
                    FileUtils.copyDirectory(directoryInput.getFile(), output);
                }

                for (JarInput jarInput : transformInput.getJarInputs()) {
                    System.out.println("jarInput " + jarInput);

                    File output = outputProvider.getContentLocation(
                            jarInput.getName(),
                            jarInput.getContentTypes(),
                            jarInput.getScopes(),
                            Format.JAR
                    );
                    System.out.println("copy jar from " + jarInput.getFile().getAbsolutePath() + " to " + output.getAbsolutePath());
                    try {
                        FileUtils.copyFile(jarInput.getFile(), output);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            for (TransformInput transformInput : transformInvocation.getInputs()) {
                System.out.println("TransformInput " + transformInput);

                for (DirectoryInput directoryInput : transformInput.getDirectoryInputs()) {
                    System.out.println("directoryInput " + directoryInput);

                    File output = outputProvider.getContentLocation(
                            directoryInput.getName(),
                            directoryInput.getContentTypes(),
                            directoryInput.getScopes(),
                            Format.DIRECTORY
                    );

                    Map<File, Status> changedFiles = directoryInput.getChangedFiles();
                    for (File file : changedFiles.keySet()) {
                        Status status = changedFiles.get(file);
                        System.out.println(file.getAbsolutePath() + " " + status);

                        String outputFilePath = file.getAbsolutePath().replace(directoryInput.getFile().getAbsolutePath(), output.getAbsolutePath());
                        File outputFile = new File(outputFilePath);
                        System.out.println("outputFilePath = " + outputFilePath);

                        switch (status) {
                            case NOTCHANGED:
                                break;
                            case ADDED:
                            case CHANGED:
                                if (!outputFile.getParentFile().exists()) {
                                    FileUtils.mkdirs(outputFile.getParentFile());
                                }
                                System.out.println("copy file from " + file.getAbsolutePath() + " to " + outputFile.getAbsolutePath());
                                FileUtils.copyFile(file, outputFile);
                                break;
                            case REMOVED:
                                System.out.println("delete file " + outputFile.getAbsolutePath());
                                FileUtils.delete(outputFile);
                                break;
                        }
                    }
                }

                for (JarInput jarInput : transformInput.getJarInputs()) {
                    System.out.println("jarInput " + jarInput);

                    File output = outputProvider.getContentLocation(
                            jarInput.getName(),
                            jarInput.getContentTypes(),
                            jarInput.getScopes(),
                            Format.JAR
                    );

                    switch (jarInput.getStatus()) {
                        case NOTCHANGED:
                            break;
                        case ADDED:
                        case CHANGED:
                            System.out.println("copy jar from " + jarInput.getFile().getAbsolutePath() + " to " + output.getAbsolutePath());
                            FileUtils.copyFile(jarInput.getFile(), output);
                            break;
                        case REMOVED:
                            System.out.println("delete jar " + output.getAbsolutePath());
                            FileUtils.deleteRecursivelyIfExists(output);
                            break;
                    }
                }
            }
        }
    }
}
