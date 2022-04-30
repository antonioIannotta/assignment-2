package assignment;

import java.io.File;

public class PackageReport {
    
    private String packagePath;
    private File folder;
    
    public PackageReport(String packagePath){
        this.packagePath = packagePath;
        this.folder = new File(packagePath);
    }

    public void getReport(){
        File[] fileInPackage = folder.listFiles();
        
        System.out.println("Package -> " + this.packagePath);
        for(File file: fileInPackage){
            if(file.isFile()){
                ClassReport classReport = new ClassReport(file.getAbsolutePath());
                classReport.getReport();
                InterfaceReport interfaceReport = new InterfaceReport(file.getAbsolutePath());
                interfaceReport.getReport();
            }
        }
    }
}
