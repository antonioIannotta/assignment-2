package assignment;

import java.util.function.Function;

public interface JavaParserApi {
    void getInterfaceReport(String srcInterfacePath);

    void getClassReport(String srcClassPath);

    void getPackageReport(String srcPackageFolderPath);

    void getProjectReport(String srcProjectFolderPath);

    void analyzeProject(String srcProjectFolderPath, String topic);

    void analyzeProject(String srcProjectFolderPath, Function callback);


    void stopAnalyzeProject();
}
