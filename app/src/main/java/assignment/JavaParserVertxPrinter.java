package assignment;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;

public class JavaParserVertxPrinter extends AbstractVerticle {

    public void start(Promise<Void> startPromise) {
        log("started printer.");
        EventBus eb = this.getVertx().eventBus();
        eb.consumer("general_element", message -> {
            log("new message: " + message.body());
        });
        log("Ready.");
        startPromise.complete();
    }

    private void log(String msg) {
        System.out.println("[REACTIVE AGENT #1/ JavaParserPrinter] " + msg);
    }
}
