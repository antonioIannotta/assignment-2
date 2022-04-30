package assignment;

import java.util.List;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class PackageCollector extends VoidVisitorAdapter<List<String>> {
    @Override
    public void visit(PackageDeclaration pkg, List<String> collector) {
        super.visit(pkg, collector);
        collector.add(pkg.getNameAsString());
    }
}
