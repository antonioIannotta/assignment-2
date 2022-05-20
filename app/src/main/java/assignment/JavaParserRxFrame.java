package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Function;

public class JavaParserRxFrame extends JavaParserFrame {

    protected JavaParserPanel<JavaParserRxImpl> parserPanel;
    protected JavaParserRxImpl parserRx;

    public JavaParserRxFrame(JavaParserRxImpl parserRx){
        super();
        this.parserRx = parserRx;
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
            parserPanel = new JavaParserPanel<>(this.parserRx, this.filePath);
            newFrame.add(parserPanel);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setVisible(true);
            this.setVisible(false);
        }
    }

    public class JavaParserPanel<T extends JavaParserApi> extends JavaParserFrame.JavaParserPanel<T> {


        public JavaParserPanel(T parser, String filePath) {
            super(parser, filePath);
            this.parser.analyzeProject(filePath, (Function<String, String>) s -> {
                JavaParserPanel.super.addLabel(s);
                return s;
            });
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.stopButton) {
                this.parser.stopAnalyzeProject();
            }
        }
    }
}
