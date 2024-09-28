import java.awt.Color;
import java.awt.event.*;
import java.lang.reflect.*;
import javax.swing.*;

public class QueensRadio extends QueensCheckBox implements ActionListener{
    private JRadioButton[] radio = new JRadioButton[10];
    protected ButtonGroup bg1;
    
    public QueensRadio(){
        super();
        bg1 = new ButtonGroup();

        for (int i = 0; i < 10; i++) {
            colPan[i] = new JPanel();
            radio[i] = new JRadioButton();
            radio[i].addActionListener(this);
            radio[i].setOpaque(false);
            bg1.add(radio[i]);
            colPan[i].setBounds(25,175+37*i, 150, 25);
            colPan[i].setBorder(BorderFactory.createLineBorder(Color.black));
            colPan[i].setBackground(Color.white);
            JLabel j = new JLabel(colours[i]);
            colPan[i].add(j);
            colPan[i].add(radio[i]);
            colPan[i].setVisible(false);
        }
    }

    public void add(JPanel p){
        super.add(p);
        for (int i = 0; i < 10; i++) {
            p.add(colPan[i]);
        }
    }

    public void actionPerformed(ActionEvent a){
        if (a.getSource() instanceof JRadioButton){
            for(int i = 0; i<size; i++){
                if(radio[i].isSelected()){
                    selectedCol = colours[i];
                    currentColorIndex = i;
                    Color color;
                    try {
                        Field field = Class.forName("java.awt.Color").getField(colours[i]);
                        color = (Color)field.get(null);
                    } catch (Exception e) {
                        color = null;
                    }
                    currentColor = color; 
                    setVisible(true);
                }
            }
        }
        else{
            super.actionPerformed(a);
        }
    }
}