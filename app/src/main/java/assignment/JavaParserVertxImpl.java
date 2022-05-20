package assignment;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

import java.util.function.Function;

public class JavaParserVertxImpl implements JavaParserApi {
    private Vertx vertx;
    private EventBus eb;
    private String deploymentId;

    public JavaParserVertxImpl(Vertx vertx){
        this.vertx = vertx;
        this.eb =  vertx.eventBus();
    }

    @Override
    public void getInterfaceReport(String srcInterfacePath){
        vertx.deployVerticle(new JavaParserVertxPrinter(), res -> {
            /* deploy the second verticle only when the first has completed */
            vertx.deployVerticle(new InterfaceReport(srcInterfacePath));
        });
    }

    @Override
    public void getClassReport(String srcClassPath){
        vertx.deployVerticle(new JavaParserVertxPrinter(), res -> {
            /* deploy the second verticle only when the first has completed */
            vertx.deployVerticle(new ClassReport(srcClassPath));
        });
    }

    @Override
    public void getPackageReport(String srcPackageFolderPath){
        vertx.deployVerticle(new JavaParserVertxPrinter(), res -> {
            /* deploy the second verticle only when the first has completed */
            vertx.deployVerticle(new PackageReport(srcPackageFolderPath));
        });
    }

    @Override
    public void getProjectReport(String srcProjectFolderPath){
        vertx.deployVerticle(new JavaParserVertxPrinter(), res -> {
            /* deploy the second verticle only when the first has completed */
            vertx.deployVerticle(new PackageReport(srcProjectFolderPath));
        });
    }

    @Override
    public void analyzeProject(String srcProjectFolderPath, String topic){
        this.eb.consumer("general_element", message -> this.eb.publish(topic, message.body()));
        vertx.deployVerticle(new ProjectReport(srcProjectFolderPath), res -> {
            deploymentId = res.result();
        });
    }

    @Override
    public void analyzeProject(String srcProjectFolderPath, Function callback) {
        System.out.println("Not implemented.");
    }

    @Override
    public void stopAnalyzeProject(){
        vertx.undeploy(deploymentId, res -> {
            if (res.succeeded()) {
                System.out.println("Undeployed ok");
            } else {
                System.out.println("Undeploy failed!");
            }
        });
    }
}
