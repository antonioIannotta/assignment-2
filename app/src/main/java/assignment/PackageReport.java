package assignment;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

import java.io.File;

public class PackageReport extends AbstractVerticle {
    
    private String packagePath;
    private File folder;
    private EventBus eb;
    
    public PackageReport(String packagePath){
        this.packagePath = packagePath;
        this.folder = new File(packagePath);

    }

    public void start() {
        this.eb = this.getVertx().eventBus();
        File[] fileInPackage = folder.listFiles();
        eb.publish("general_element", "Package found at: "+packagePath);
        eb.publish("package", packagePath);
        for(File file: fileInPackage){
            if(file.isFile()){
                this.vertx.deployVerticle(new ClassReport(file.getAbsolutePath()));
                this.vertx.deployVerticle(new InterfaceReport(file.getAbsolutePath()));
            }
        }
    }

    public String getReport(){
        File[] fileInPackage = folder.listFiles();
        System.out.println("Package -> " + this.packagePath);
        String report = "";
        for(File file: fileInPackage){
            if(file.isFile()){
                InterfaceReport interfaceReport = new InterfaceReport(file.getAbsolutePath());
                report += "\n" + interfaceReport.getReport() + "\n";
                ClassReport classReport = new ClassReport(file.getAbsolutePath());
                report += "\n" + classReport.getReport() + "\n";
            }
            if(file.isDirectory()) {
                PackageReport packageReport = new PackageReport(file.getAbsolutePath());
                report += "\n" + packageReport.getReport() + "\n";
            }
        }
        return report;
    }
}
