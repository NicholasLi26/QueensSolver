import java.awt.event.*;
import javax.swing.*;

public class QueensCheckBox extends Queens implements ActionListener{
    private JCheckBox[][] checkbox;

    public QueensCheckBox(){
        super();
    }

    public void add(JPanel p){
        super.add(p);
    }


    public void update(){
        super.update();
        checkbox = new JCheckBox[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                checkbox[i][j] = new JCheckBox();
                checkbox[i][j].addActionListener(this);
                checkbox[i][j].setVisible(false);
                panels[i][j].add(checkbox[i][j]);
            }
            colPan[i].setVisible(true);
        }
    }
    
    public void setVisible(boolean visible){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                checkbox[i][j].setVisible(visible);            
            }
        }
    }



    public void actionPerformed(ActionEvent a){
        if (a.getSource() instanceof JCheckBox){
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(checkbox[i][j].isSelected()){
                        if(mainArr[i][j]==null){
                            panels[i][j].setBackground(currentColor);
                            checkbox[i][j].setSelected(false);
                            checkbox[i][j].setVisible(false);
                            int[] temp = {i,j};
                            colorArr[currentColorIndex].addElem(temp);
                            mainArr[i][j] = selectedCol;
                        }
                        else{
                            int k;
                            for(k = 0; k<size; k++){
                                if (colours[k].equals(mainArr[i][j])){
                                    colorArr[k].coords.remove((String.valueOf(i)+String.valueOf(j)));
                                    colorArr[k].size--;
                                }
                            }
                            panels[i][j].setBackground(currentColor);
                            checkbox[i][j].setSelected(false);
                            checkbox[i][j].setVisible(false);
                            int[] temp = {i,j};
                            colorArr[currentColorIndex].addElem(temp);
                            mainArr[i][j] = selectedCol;
                        }
                        

                    }
                }
            }
            if (arrayIsValid()){
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        checkbox[i][j].setSelected(false);
                        checkbox[i][j].setVisible(false);
                    }
                    colPan[i].setVisible(false);
                } 
                calculatePanel.setVisible(true);
            }
        }

        else{
            super.actionPerformed(a);
        }
    }
}