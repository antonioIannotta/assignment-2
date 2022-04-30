package assignment;

import java.io.File;

public class ProjectReport {
    
    private String folderPath;

    public ProjectReport(String folderPath){
        this.folderPath = folderPath;
    }

    public void getReport(){
        File[] listFiles = new File(folderPath).listFiles();
        for(File file : listFiles){
            if(file.isDirectory()) {
                PackageReport packageReport = new PackageReport(file.getAbsolutePath());
                packageReport.getReport();
            }
        }
    }
}

/**
 * Ho immaginato un progetto java come una serie di directory,
 * ogni directory è un package. 
 */
