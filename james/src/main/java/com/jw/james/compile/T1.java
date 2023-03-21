package com.jw.james.compile;

import org.junit.jupiter.api.Test;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;

/**
 * Description: guoyy
 * com.jw.james.compile.T1
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2022/6/26 23:24
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2022 JW All rights reserved.
 */
public class T1 {

    @Test
    public void test() {
        // 定义一个StringWriter类，用于写Java程序
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        // 开始写Java程序
        out.println("public class HelloWorld {");
        out.println(" public static void main(String args[]) {");
        out.println(" System.out.println(\"Hello, World\");");
        out.println(" }");
        out.println("}");
        out.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector diagnostics = new DiagnosticCollector();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        // 为这段代码取个名子：HelloWorld
        SimpleJavaFileObject file = (new JavaSourceFromString("HelloWorld", writer.toString()));
        Iterable compilationUnits = Arrays.asList(file);
        // options命令行选项 // 指定的路径一定要存在，javac不会自己创建文件夹
        Iterable<String> options = Arrays.asList("-d", "/Users/james/IdeaProjects/guoyy/james/target/classes");
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);

        boolean success = task.call();
        System.out.println((success) ? "编译成功" : "编译失败");

    }

    private class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    public static void main(String[] args) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int run = compiler.run(null, null, null, "-version");
        System.out.println(run);
        // 建立DiagnosticCollector对象
        DiagnosticCollector<Object> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        // 建立源文件对象，每个文件被保存在一个从JavaFileObject继承的类中
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays
                .asList("/Users/james/IdeaProjects/guoyy/james/src/main/java/com/jw/james/dto/Foo.java"));

        // options命令行选项  // 指定的路径一定要存在，javac不会自己创建文件夹
        Iterable<String> options = Arrays.asList("-d", "/Users/james/IdeaProjects/guoyy/james/target/classes");
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);

        // 编译源程序
        boolean success = task.call();
        fileManager.close();
        System.out.println((success) ? "编译成功" : "编译失败");

        // 打印信息
        for (Object object : diagnostics.getDiagnostics()) {
            Diagnostic diagnostic = (Diagnostic)object;
            System.out.printf("Code: %s%n" + "Kind: %s%n" + "Position: %s%n" + "Start Position: %s%n"
                            + "End Position: %s%n" + "Source: %s%n" + "Message: %s%n", diagnostic.getCode(),
                    diagnostic.getKind(), diagnostic.getPosition(), diagnostic.getStartPosition(),
                    diagnostic.getEndPosition(), diagnostic.getSource(), diagnostic.getMessage(null));
        }

    }

}
