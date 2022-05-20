package assignment;

import io.vertx.core.AbstractVerticle;

import java.io.File;

public class ProjectReport extends AbstractVerticle {
    
    private String folderPath;

    public ProjectReport(String folderPath){
        this.folderPath = folderPath;
    }

    public void start() {
        File[] listFiles = new File(folderPath).listFiles();
        for(File file : listFiles){
            if(file.isDirectory()) {
               this.vertx.deployVerticle(new PackageReport(file.getAbsolutePath()));
            }
            else {
                this.vertx.deployVerticle(new InterfaceReport(file.getAbsolutePath()));
                this.vertx.deployVerticle(new ClassReport(file.getAbsolutePath()));
            }
        }
    }

    public String getReport(){
        File[] listFiles = new File(this.folderPath).listFiles();
        String report = "";
        for(File file : listFiles){
            if(file.isDirectory()) {
                PackageReport packageReport = new PackageReport(file.getAbsolutePath());
                report += "\n" + packageReport.getReport() + "\n";
            }
            if(file.isFile()){
                InterfaceReport interfaceReport = new InterfaceReport(file.getAbsolutePath());
                report += "\n" + interfaceReport.getReport() + "\n";
                ClassReport classReport = new ClassReport(file.getAbsolutePath());
                report += "\n" + classReport.getReport() + "\n";
            }
        }
        return report;
    }
}

/**
 * Ho immaginato un progetto java come una serie di directory,
 * ogni directory è un package. 
 */
