package assignment;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ClassCollector extends VoidVisitorAdapter<List<String>> {
    @Override
    public void visit(ClassOrInterfaceDeclaration cl, List<String> collector) {
        if(!cl.isInterface()) {
            super.visit(cl, collector);
            collector.add(cl.getNameAsString());
        }
    }
}

