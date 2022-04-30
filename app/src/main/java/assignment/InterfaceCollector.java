package assignment;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class InterfaceCollector extends VoidVisitorAdapter<List<String>> {
    @Override
    public void visit(ClassOrInterfaceDeclaration cl, List<String> collector) { 
        if(cl.isInterface()) {
            super.visit(cl, collector);
            collector.add(cl.getNameAsString());
        }
    }
}
