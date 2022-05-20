package assignment;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JavaParserVertxFrame extends JavaParserFrame{
    protected JavaParserPanel<JavaParserVertxImpl> parserPanel;

    protected Vertx vertx;

    public JavaParserVertxFrame(){
        super();
        vertx = Vertx.vertx();
        this.startButton.addActionListener(this);

        Container c = this.getContentPane();
        this.setResizable(false);
        this.setSize(400,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startButton){
            JFrame newFrame = new JFrame();
            newFrame.setSize(1080,700);
            parserPanel = new JavaParserPanel<>(new JavaParserVertxImpl(this.vertx),this.filePath);
            newFrame.add(parserPanel);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setVisible(true);
            this.setVisible(false);
        }
    }

    public class JavaParserPanel<T extends JavaParserApi> extends JavaParserFrame.JavaParserPanel<T> {

        EventBus eb;
        public JavaParserPanel(T parser, String filePath) {
            super(parser, filePath);
            this.eb =  vertx.eventBus();
            vertx.deployVerticle(new JavaParserVertxGuiPrinter(this), res -> this.parser.analyzeProject(filePath,""));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == this.stopButton){
                this.parser.stopAnalyzeProject();
                this.eb.publish("general_element", "Stopped analysis of project.");
            }
        }
    }

   /* public class JavaParserPanel{
        JavaParserVertxImpl parser;

        JButton stopButton;
        JScrollPane scrollPane;
        JPanel interiorPanel;

        public JavaParserPanel(Vertx vertx,String file) {
            this.parser = new JavaParserVertxImpl(vertx);
            this.eb =  vertx.eventBus();
            this.stopButton = new JButton("Stop project analysis");
            this.interiorPanel = new JPanel();
            this.interiorPanel.setLayout(new BoxLayout(this.interiorPanel, BoxLayout.PAGE_AXIS));


            this.scrollPane = new JScrollPane(this.interiorPanel);
            this.scrollPane.setPreferredSize(new Dimension(1000, 600));

            this.add(this.scrollPane);
            this.add(this.stopButton);

            vertx.deployVerticle(new JavaParserVertxGuiPrinter(this), res -> this.parser.analyzeProject(file,""));
            this.setAutoscrolls(true);
            this.scrollPane.setVisible(true);
            this.setVisible(true);
        }

        public void addLabel(String text){
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new CardLayout());
            JLabel label = new JLabel();
            label.setText("<html><br/>" + text.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "<br/></html>");
            labelPanel.add(label);
            this.interiorPanel.add(labelPanel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == this.stopButton){
                this.parser.stopAnalyzeProject();
                this.eb.publish("general_element", "Stopped analysis of project.");
            }
        }
    }*/
}
