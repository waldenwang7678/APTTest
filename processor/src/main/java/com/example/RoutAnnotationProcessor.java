package com.example;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * Created by wangjt on 2017/8/15.
 * 注解处理器
 */
@SupportedAnnotationTypes("com.example.RoutAnnotation")  //注解类路径(要处理的注解)
@SupportedSourceVersion(SourceVersion.RELEASE_7)//java版本
public class RoutAnnotationProcessor extends AbstractProcessor {
    //process 方法会在编译的时候被执行,
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        StringBuilder builder = new StringBuilder()
                .append("package com.wangjt.apttest.generate;\n\n")           //包名
                .append("public class GeneratedClass {\n\n") // open class
                .append("\tpublic static String getMessage() {\n") // open method
                .append("\t\treturn \"");

        //遍历所有 有这个注解的元素
        for (Element element : roundEnvironment.getElementsAnnotatedWith(RoutAnnotation.class)) {
            String objectType = element.getSimpleName().toString();
            builder.append(objectType).append(" says hello!\\n");
        }
        builder.append("\";\n")   // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class

        try { // write the file
            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.wangjt.apttest.generate.GeneratedClass");
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }

        return true;
    }
}
