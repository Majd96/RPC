package package1.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    public JPanel rootJpanel;
    private JPanel fisrtRow;
    public JTextField numberstextField;
    public JLabel averageResultLabel;
    private JPanel secondRow;
    public JButton findTheAverageButton;
    public JButton findTheMaxButton;
    public JLabel maxLabel;


    private void createUIComponents() {
        rootJpanel=new JPanel();
        fisrtRow=new JPanel();
        secondRow=new JPanel();
        rootJpanel.add(fisrtRow);
        rootJpanel.add(secondRow);
        numberstextField=new JTextField();
        averageResultLabel=new JLabel();
        findTheAverageButton=new JButton();
        maxLabel=new JLabel();
        findTheMaxButton=new JButton();

        // TODO: place custom component creation code here
    }
}
