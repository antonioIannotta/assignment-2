package assignment;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class MethodCollector extends VoidVisitorAdapter<List<String>> {
    @Override
    public void visit(MethodDeclaration md, List<String> collector) {
        super.visit(md, collector);
        
        collector.add("Name: " + md.getNameAsString());
        collector.add("Modifier" + md.getModifiers());
        collector.add("Type: " + md.getTypeAsString());
        collector.add("Start/End: " + md.getBegin().get() + ", " + md.getEnd().get());
        collector.add("Fields: " + md.getParameters().toString());
        
    }
}
