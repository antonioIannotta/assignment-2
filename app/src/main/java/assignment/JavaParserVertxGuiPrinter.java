package assignment;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;

public class JavaParserVertxGuiPrinter extends AbstractVerticle {

    JavaParserVertxFrame.JavaParserPanel panel;

    public JavaParserVertxGuiPrinter(JavaParserVertxFrame.JavaParserPanel javaParserPanel){
        this.panel = javaParserPanel;
    }

    public void start(Promise<Void> startPromise) {
        EventBus eb = this.getVertx().eventBus();
        eb.consumer("general_element", message -> {
            panel.addLabel(message.body().toString());
           log(message.body().toString());
        });
        startPromise.complete();
    }

    private void log(String msg) {
        System.out.println("[REACTIVE AGENT #2/ JavaParserGuiPrinter] " + msg);
    }
}
