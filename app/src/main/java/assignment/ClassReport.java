package assignment;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassReport extends AbstractVerticle {
    
    private String filePath;
    private EventBus eb;

    public ClassReport(String filePath){
        this.filePath = filePath;
    }

    public void start() {
        this.eb = this.getVertx().eventBus();
        Promise<String> promise = Promise.promise();

        try {
            String rep = makeReport();
            if (!rep.equals(""))
            {
                promise.complete(rep);
                eb.publish("general_element", rep);
                eb.publish("class", rep);
            }
            else {
                promise.fail("No classes in file");
                System.out.println("No classes in file");
            }
        }
        catch (FileNotFoundException exception){
            promise.fail("File not found!");
            System.out.println("File not found!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printReport() {
        System.out.println("Report of " + this.filePath);
        try {
            System.out.println(this.makeReport());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public String getReport(){
        try{
            return this.makeReport();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return "File not found!";
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
            if(eb != null) eb.publish("method", method);
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
