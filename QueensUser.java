import java.awt.event.*;
import javax.swing.*;


public class QueensUser implements WindowListener{
    private Queens qu = new QueensButton();

    public static void main(String[] args){
		QueensUser quu = new QueensUser();
    }

    public QueensUser(){
        JFrame frame = new JFrame("Queens solver");
		frame.addWindowListener(this);
		frame.setResizable(false);
		frame.setBounds(450,250,800,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JPanel p = new JPanel();
		p.setLayout(null);
		qu.add(p);

		frame.setContentPane(p);
    }

    public void exit() {
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Queen's Solver", JOptionPane.YES_NO_OPTION);
		if(option==0) {
			System.exit(0);
		}
	}
    
    public void windowOpened(WindowEvent w) {
	}
	
	public void windowClosed(WindowEvent w) {
	}
	
	public void windowClosing(WindowEvent w) {
		exit();
	}
	
	public void windowActivated(WindowEvent w) {
	}
	
	public void windowDeactivated(WindowEvent w) {
	}
	
	public void windowIconified(WindowEvent w) {
	}
	
	public void windowDeiconified(WindowEvent w) {

	}
}