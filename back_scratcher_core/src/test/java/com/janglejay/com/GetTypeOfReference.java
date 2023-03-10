package com.janglejay.com;

import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileNotFoundException;

public class GetTypeOfReference {

    private static final String FILE_PATH = "/Users/fufangjie/MyProjects/java_project/back_scratcher/back_scratcher_core/src/main/java/com/janglejay/handler/MockerHandler.java";

    public static void main(String[] args) throws Exception {
        TypeSolver typeSolver = new ReflectionTypeSolver();

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
                .getConfiguration()
                .setSymbolResolver(symbolSolver);

        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

        cu.findAll(MethodCallExpr.class).forEach(mce ->
                System.out.println(mce.resolve().getQualifiedSignature()));
    }
//
//    public static void main(String[] args) throws FileNotFoundException {
//        TypeSolver typeSolver = new CombinedTypeSolver();
//
//        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
//        JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
//
//        CompilationUnit cu = JavaParser.parse(new File(FILE_PATH));
//
//        cu.findAll(AssignExpr.class).forEach(ae -> {
//            ResolvedType resolvedType = ae.calculateResolvedType();
//            System.out.println(ae.toString() + " is a: " + resolvedType);
//        });
//    }
}
