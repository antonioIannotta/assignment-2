package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class InterfaceReport {
    
    private String filePath;

    public InterfaceReport(String filePath) {
        this.filePath = filePath;
    }

    public void getReport() {
        System.out.println("Report of " + this.filePath);
        try {
            System.out.println(this.makeReport());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    private String makeReport() throws FileNotFoundException {
        List<String> interfaces = new ArrayList<>();
        List<String> methods = new ArrayList<>();
        String report = "";

        VoidVisitor<List<String>> interfaceCollectorVisitor = new InterfaceCollector();
        VoidVisitor<List<String>> methodCollectorVisitor = new MethodCollector();

        CompilationUnit compilationUnit = StaticJavaParser.parse(new File(this.filePath));
        interfaceCollectorVisitor.visit(compilationUnit, interfaces);
        if(interfaces.isEmpty()){
            System.out.println("No interfaces found");
            return report;
        }
        methodCollectorVisitor.visit(compilationUnit, methods);
        report += "Interface -> " + interfaces + "\n";
        report += "SourcePath -> " + this.filePath + "\n";
        report += "Methods: \n";
        for(String method: methods) {
            report += method + "\n";
        }

        return report;
    }
}
