import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class QueensCombo extends QueensRadio implements ActionListener{
    private JComboBox<Integer> size1;
    private Integer[] numOpt = {1,2,3,4,5,6,7,8,9,10};
    public JPanel panelC;

    protected QueensCombo(){
        super();

        size1 = new JComboBox<Integer>(numOpt);
        size1.addActionListener(this);
        panelC = new JPanel();
        panelC.setOpaque(false);
        panelC.setBorder(BorderFactory.createTitledBorder("Size"));
        panelC.setBackground(Color.black);
        panelC.setBounds(200,75,200,60);
        panelC.add(size1);
    }

    public void actionPerformed(ActionEvent a){
        if (a.getSource() instanceof JComboBox){
            size = size1.getSelectedIndex()+1;
            panelC.setVisible(false);
            update();
        }
        else{
            super.actionPerformed(a);
        }
    }

    public void add(JPanel p){
        super.add(p);
        p.add(panelC);

    }

}