import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class QueensButton extends QueensCombo implements ActionListener{
    private JButton calculate;

    public QueensButton(){
        super();
        calculate = new JButton("Calculate");
        calculate.addActionListener(this);
        calculate.setBounds(0,0,100, 50);
        calculatePanel = new JPanel();
        calculatePanel.setBounds(200,100,400,40);
        calculatePanel.add(calculate);
        calculatePanel.setBackground(Color.white);
        calculatePanel.setVisible(false);
    }

    public void actionPerformed(ActionEvent a){
        if (a.getSource() instanceof JButton){
            calculate.setVisible(false);
            calculate();
        }
        else{
            super.actionPerformed(a);
        }
    }

    public void buttonSetVisable(){
        calculatePanel.setVisible(true);
    }

    public void add(JPanel p){
        super.add(p);
        p.add(calculatePanel);
    }

}
