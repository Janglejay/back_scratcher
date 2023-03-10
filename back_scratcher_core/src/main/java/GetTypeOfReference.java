import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class GetTypeOfReference {

    private static final String FILE_PATH = "/Users/fufangjie/MyProjects/java_project/back_scratcher/back_scratcher_core/src/main/java/GetTypeOfReference.java";

    private static void parseCode() {
        String code = "int i = 0";
        String code = "int i = 0";
//        String code = """
//                    public static void main(String[] args) throws FileNotFoundException {
//                        TypeSolver typeSolver = new CombinedTypeSolver();
//
//                        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
//                        JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
//
//                        CompilationUnit cu = JavaParser.parse(new File(FILE_PATH));
//
//                        cu.findAll(AssignExpr.class).forEach(ae -> {
//                            ResolvedType resolvedType = ae.calculateResolvedType();
//                            System.out.println(ae.toString() + " is a: " + resolvedType);
//                        });
//                    }
//                """;
        CompilationUnit cu = StaticJavaParser.parse(code);
//        compilationUnit.getImports().forEach(System.out::println);
        VoidVisitor<?> methodNameVisitor = new MethodNamePrinter();
        methodNameVisitor.visit(cu, null);

        List<String> methodNames = new ArrayList<>();
        VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
        methodNameCollector.visit(cu, methodNames);
        methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));

    }

    private static class MethodNamePrinter extends VoidVisitorAdapter<Void> {

        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
            System.out.println("Method Name Printed: " + md.getName());
        }
    }

    private static class MethodNameCollector extends VoidVisitorAdapter<List<String>> {

        @Override
        public void visit(MethodDeclaration md, List<String> collector) {
            super.visit(md, collector);
            collector.add(md.getNameAsString());
        }
    }

    public static void main(String[] args) throws Exception {
        parseCode();
    }
}
