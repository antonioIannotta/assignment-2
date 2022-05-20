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
import java.util.List;

public class InterfaceReport extends AbstractVerticle {

    private String filePath;
    private EventBus eb;

    public InterfaceReport(String filePath) {
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
                eb.publish("interface", rep);
            }
            else {
                promise.fail("No interfaces in file");
                System.out.println("No interfaces in file");
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
            eb.publish("method", method);
            report += method + "\n";
        }

        return report;
    }
}
