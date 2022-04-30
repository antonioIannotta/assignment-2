package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class ClassReport {
    
    private String filePath;

    public ClassReport(String filePath){
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
        
        List<String> classes = new ArrayList<>();
        List <String> methods = new ArrayList<>();
        Map<String, List<String>> methodProperties = new HashMap<>();
        VoidVisitor<List<String>> classCollectorVisitor = new ClassCollector();
        VoidVisitor<List<String>> methodCollectorVisitor = new MethodCollector();
        String report = "";

        CompilationUnit compilationUnit = StaticJavaParser.parse(new File(this.filePath));

        classCollectorVisitor.visit(compilationUnit, classes);
        if(classes.isEmpty()){
            System.out.println("No classes found");
            return report;
        }
        methodCollectorVisitor.visit(compilationUnit, methods);
        
        fillMap(methods, methodProperties);
        report += "Class -> " + classes + "\n";
        report += "SourcePath -> " + this.filePath + "\n";
        for(String method : methodProperties.keySet()){
            report += "Method: " + method + "\nInformation: " + methodProperties.get(method) + "\n";
            if(method.equals("main")){
                report += "This is the main class\n";
            }
        }

        return report;
    }

    private void fillMap(List<String> methods, Map<String, List<String>> methodProperties) {
        int cnt = 0;
        while(cnt < methods.size()) {
            methodProperties.put(nameMethod(methods.get(cnt)), methods.subList(cnt+1, cnt+5));
            cnt += 5;
        }
    }

    private String nameMethod(String method){
        String name = method.split(": ")[1];
        return name;
    }
}
