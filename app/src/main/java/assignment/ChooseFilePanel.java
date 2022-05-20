package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseFilePanel extends JPanel implements ActionListener {
    JButton button;
    JLabel label;
    JFileChooser fileChooser;
    JavaParserFrame parserFrame;

    public ChooseFilePanel(JavaParserFrame parserFrame){
        this.button = new JButton("Choose file/directory");
        this.label = new JLabel("Choose file to parse:");
        this.fileChooser = new JFileChooser();
        this.parserFrame = parserFrame;

        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.add(this.label);
        this.add(this.button);

        this.button.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.button){
            int returnVal = this.fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = this.fileChooser.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println("Opening: " + file.getAbsolutePath() + ".");
                this.parserFrame.setFilePath(file.getAbsolutePath());
                this.button.setVisible(false);
                this.label.setText("File path: " + file.getAbsolutePath());
                this.add(this.parserFrame.getButton());
            } else {
                System.out.println("Open command cancelled by user.");
            }
        }
    }
}
