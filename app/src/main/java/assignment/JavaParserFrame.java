package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class JavaParserFrame extends JFrame implements ActionListener {
    protected ChooseFilePanel panel;
    protected JButton startButton;
    protected String filePath;

    public JavaParserFrame() {
        panel = new ChooseFilePanel(this);
        this.startButton = new JButton("Start analyzing");
        this.filePath = "";
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public JButton getButton() {
        return this.startButton;
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);

    public abstract class JavaParserPanel<T extends JavaParserApi> extends JPanel implements ActionListener {
        T parser;
        String filePath;
        JButton stopButton;
        JScrollPane scrollPane;
        JPanel interiorPanel;
        public JavaParserPanel(T parser, String filePath) {
            this.parser = parser;
            this.filePath = filePath;

            this.stopButton = new JButton("Stop project analysis");
            this.interiorPanel = new JPanel();
            this.interiorPanel.setLayout(new BoxLayout(this.interiorPanel, BoxLayout.PAGE_AXIS));

            this.scrollPane = new JScrollPane(this.interiorPanel);
            this.scrollPane.setPreferredSize(new Dimension(1000, 600));

            this.add(this.scrollPane);
            this.add(this.stopButton);
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
        public abstract void actionPerformed(ActionEvent e);
    }
}
