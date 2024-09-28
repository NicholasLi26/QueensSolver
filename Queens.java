import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Queens implements ActionListener{
    protected JPanel main, calculatePanel;
	protected JLabel title, test;
	protected JPanel[][] panels;
	protected String[][] grid;
	protected String[] colours = {"red","orange","yellow","green","blue","magenta","pink","white","black","gray"};
	protected JPanel[] colPan = new JPanel[10];
	protected String selectedCol;
	protected Color currentColor;
	protected String mainArr[][];
	protected Boolean madeChange = true;

	protected Colours colorArr[];

    protected int size, currentColorIndex;
    protected Font myFont, myFont2, myFont3;

	public Queens(){
		

		main = makePanel(200, 150, 400, 400);
		main.setBackground(Color.white);
		
		size = 0;
		myFont = new Font("Ariel", Font.PLAIN, 10);
		myFont2 = new Font("Ariel", Font.BOLD, 12);
		myFont3 = new Font("Calibri", Font.PLAIN, 25);

		title = new JLabel("Queens Solver");
		title.setBounds(150, 0,500,50);	
		title.setFont(myFont3);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBorder(BorderFactory.createEmptyBorder());

		test = new JLabel(String.valueOf(size));
		test.setBounds(500, 0,500,50);
		test.setHorizontalAlignment(JLabel.CENTER);
		test.setBorder(BorderFactory.createEmptyBorder());

		
	}

	public void add(JPanel panel) {
		panel.setBackground(Color.white);
		panel.add(title);
		panel.add(test);
		panel.add(main);
	}

    public JPanel makePanel(int x1, int y1,int x,int y) {
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBounds(x1,y1,x,y);
		return p;
	}

    public JLabel makeLabel(int x, int y, int sx, int sy, String message, Border b, int a) {
		JLabel l = new JLabel(message);
		l.setBounds(x,y,sx,sy);
		l.setBorder(b);
		l.setHorizontalAlignment(a);
		return l;
	}

	public void update(){
		test.setText(String.valueOf(size));
		panels = new JPanel[size][size];
		int offsetV, offsetH = 0;
		int step = 400/size;
		grid = new String[size][size];
		
		coloursConstructor();
		
		mainArr = new String[size][size];

		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				panels[i][j] = new JPanel();
				panels[i][j].setBounds(j*step, i*step, step, step);
				panels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				main.add(panels[i][j]);
				main.updateUI();

				mainArr[i][j] = null;
			}
		}
	}

	public boolean arrayIsValid(){
		boolean isValid = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (mainArr[i][j]==null){
					isValid = false;
					break;
				}
			}
		}
		return isValid;
		
	}

	public void calculate(){

		// String s = "";
		// for (int i = 0; i < size; i++) {
		// 	for (int j = 0; j < size; j++) {
		// 		s+= mainArr[i][j]+"	";
		// 	}
		// 	s+="\n";
		// }
		// JOptionPane.showMessageDialog(null, s );
		// int[] j = { (colorArr[0].coords.get(0).charAt(0))- '0', (colorArr[0].coords.get(0).charAt(1))-'0'};
		// System.out.println(j[0] +", "+ j[1]);
		// for(int i = 0; i<size; i++){
		// 	System.out.println(colorArr[i].checkHeight());
		// }
		// for (int i = 0; i < colorArr[0].size; i++) {
		// 	System.out.println(colorArr[0].coordsArr[i][0] +" ,"+colorArr[0].coordsArr[i][1]);
		// }
		while(!checkDone()&&madeChange){
			madeChange = false;
			colorCheck();
			print();
			rowCheck();
			print();
			columnCheck();
			print();
			pairsCheck();
		}
		//addQueen(new int[] {2,3});
		print();
		//twosNthrees(colorArr[0]);

	}

	public void coloursConstructor(){
		colorArr = new Colours[size];
		for (int i = 0; i < size; i++) {
			colorArr[i] = new Colours();
			colorArr[i].colour = colours[i];
		}
	}

	public int colorIndex(String col){
		for (int i = 0; i < size; i++) {
			if(col.equals(colours[i])) return i;
		}
		return -1;
	}

	public void removeSquare(int[] coords){
		int tempI = colorIndex(mainArr[coords[0]][coords[1]]);
		//System.out.println(tempI + mainArr[coords[0]][coords[1]]);
		//colorArr[tempI].print();
		if(!mainArr[coords[0]][coords[1]].equals("X") && tempI !=-1){
			colorArr[tempI].removeElem(coords);
			mainArr[coords[0]][coords[1]]="X";
			madeChange = true;
		}

	}

	public void addQueen(int[] coords){
		int indx = colorIndex(mainArr[coords[0]][coords[1]]);
		colorArr[indx].completed();
			for(int i = 0; i<size; i++){
				if(i!= coords[0])
				removeSquare(new int[] {i, coords[1]});
				if(i!= coords[1])
				removeSquare(new int[] {coords[0], i});
			}//removing in a cross surrounding queen

			for(int i = -1; i<=1; i++){
				for(int j = -1; j <=1; j++){
					if(coords[0]+i >= 0 && coords[0]+i<size && coords[1]+j >=0 && coords[1]+j <size){
						if(coords[0]+i != coords[0] && coords[1]+j != coords[1]){
							removeSquare(new int[] {coords[0] +i, coords[1] +j});
						}
					}
				}
			}//removing 3x3 area around queen


			for (int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++){
					if( mainArr[i][j].equals(colorArr[indx].colour)){
						removeSquare(new int[] {i,j});
					}
				}
			}//removing all of the same colour

			mainArr[coords[0]][coords[1]] = "Queen";
		
	}

	public void colorCheck(){
		for (int i = 0; i < size; i++) {
			if(colorArr[i].complete != true){
				if(colorArr[i].size == 1){
					int[] j = { (colorArr[i].coords.get(0).charAt(0))- '0', (colorArr[i].coords.get(0).charAt(1))-'0'};
					addQueen(j);
				}//case where there is only one
				
				else if(colorArr[i].size == 2 || colorArr[i].size ==3){
					twosNthrees(colorArr[i]);
				}//removing the squares that cannot be processed 
				
				if(colorArr[i].checkHeight() == 1){
					int range[] = colorArr[i].rangeOfW();
					for (int j = 0; j < size; j++) {
						if(j > range[0] || j < range[1]){
							removeSquare(new int[] {colorArr[i].coordsArr[0][0], j});
						}	
					}
				}//if colour is horizontal line

				if(colorArr[i].checkWidth() ==1){
					int range[] = colorArr[i].rangeOfH();
					for (int j = 0; j < size; j++) {
						if(j > range[0] || j < range[1]){
							removeSquare(new int[] {j,colorArr[i].coordsArr[0][1]});
						}	
					}
				}//if colour is vertical line
			}
		}
	}

	public void rowCheck(){
		for (int i = 0; i < size; i++) {
			String[] tempRow = mainArr[i];
			String last = tempRow[0];
			int k = 0;
			Boolean done = false;
			while(tempRow[k].equals("X")&& k<size-1){
				k++;
				if(tempRow[k].equals("Queen")){
					done = true;
					break;
				}
			}
			last = tempRow[k];

			if(!done){
				for (int j = 0; j < size; j++) {
					if(!tempRow[j].equals(last) && !tempRow[j].equals("X")){
						last=tempRow[j];
					}
				}
				if(last.equals(tempRow[k])){
					clearColourR(i, last);
				}
			}

		}
	}

	public void clearColourR(int row, String color){
		for (int i = 0; i < size; i++) {
			if(i!=row){
				for (int j = 0; j < size; j++) {
					if(mainArr[i][j].equals(color)){
						removeSquare(new int[] {i,j});
					}
				}
			}
			
		}
	}

	public void columnCheck(){
		for (int i = 0; i < size; i++) {
			String[] tempCol = new String[size];
			for (int j = 0; j < size; j++) {
				tempCol[j] = mainArr[j][i];	
			}
			String last = tempCol[0];
			Boolean done = false;
			int k = 0;
			while(tempCol[k].equals("X")&& k<size-1){
				k++;
				if(tempCol[k].equals("Queen")){
					done = true;
					break;
				}
			}
			if (!done){
				for (int j = 0; j < size; j++) {
					if(!tempCol[j].equals(last) && !tempCol[j].equals("X")){
						last=tempCol[j];
					}
				}
				if(last.equals(tempCol[k])){
					clearColourC(i, last);
				}

			}
			
		}
	}

	public void clearColourC(int column, String color){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(j!=column){
					if(mainArr[i][j].equals(color)){
						removeSquare(new int[] {i,j});
					}
				}
			}
		}
	}

	public void pairsCheck(){
		pairsCheckH();
		pairsCheckW();
	}

	public void pairsCheckH(){
		int numOfPairs;

		for (int i = 0; i < size-1; i++) {
			//colorArr[1]
			numOfPairs = 1;
			int b[] = colorArr[i].rangeOfH();
			int top, bottom;
			top = b[0];
			bottom = b[1];
			String[] tempCol = new String[size-i];
			tempCol[0] = colorArr[i].colour;
			
			for (int j = i+1; j < size; j++) {
				int[] a = colorArr[j].rangeOfH();
				int top1, bottom1;
				top1 = a[0];
				bottom1 = a[1];
				if(top1 == top && bottom1 == bottom){
					tempCol[numOfPairs] = colorArr[j].colour;
					numOfPairs++;
				}
			}
			if(numOfPairs == colorArr[i].checkHeight()){
				for(int j = 0; j<size; j++){
					for(int k = 0; k < colorArr[i].checkHeight(); k++){
						if(!isIn(mainArr[colorArr[i].getMinH()[0] + k][j], tempCol)){
							removeSquare(new int[] {colorArr[i].getMinH()[0] + k,j});
						}
					}
				}
			}	
		}
	}//checking for groups along the Y axis

	public void pairsCheckW(){
		int numOfPairs;

		for (int i = 0; i < size-1; i++) {
			//colorArr[1]
			numOfPairs = 1;
			int b[] = colorArr[i].rangeOfW();
			int left, right;
			right = b[0];
			left = b[1];
			String[] tempCol = new String[size-i];
			tempCol[0] = colorArr[i].colour;
			
			for (int j = i+1; j < size; j++) {
				int[] a = colorArr[j].rangeOfW();
				int right1, left1;
				right1 = a[0];
				left1 = a[1];
				if(right1 == right && left1 == left){
					tempCol[numOfPairs] = colorArr[j].colour;
					numOfPairs++;
				}
			}
			if(numOfPairs == colorArr[i].checkWidth()){
				for(int j = 0; j<size; j++){
					for(int k = 0; k < colorArr[i].checkWidth(); k++){
						if(!isIn(mainArr[j][colorArr[i].getMinW()[1] + k], tempCol)){
							removeSquare(new int[] {j,colorArr[i].getMinW()[1] + k});
						}
					}
				}
			}
		}
	}//checking for groups along the Y axis


	public Boolean isIn(String col, String[] arr){
        for(int i = 0; i < arr.length; i++) {
			if(arr[i] == null) break;
            if (arr[i].equals(col)) {
                return true;
            }
        }
		return false;
	}

	public void twosNthrees(Colours c){
		if(c.size == 2){
			int[][] temp = c.convertAll();
			if(c.checkHeight()==2){
				if(temp[0][1]+1 < size){
					removeSquare(new int[] {temp[0][0],temp[0][1]+1});
					removeSquare(new int[] {temp[1][0],temp[1][1]+1});
				}//removing right 2
				if(temp[0][1]-1>=0){
					removeSquare(new int[] {temp[0][0],temp[0][1]-1});
					removeSquare(new int[] {temp[1][0],temp[1][1]-1});
				}//removing left 2
			}//Vertical
			else{
				if(temp[0][0]+1 < size){
					removeSquare(new int[] {temp[0][0]+1,temp[0][1]});
					removeSquare(new int[] {temp[1][0]+1, temp[1][1]});

				}//removing right 2
				if(temp[0][0]-1>=0){
					removeSquare(new int[] {temp[0][0]-1,temp[0][1]});
					removeSquare(new int[] {temp[1][0]-1,temp[1][1]});
					
				}//removing left 2
			}//Horizontal
		}
		else{
			int[][] temp = c.convertAll();
			if(c.checkHeight()==2){
				int xSpot[] = new int[2];
				if(temp[0][1] == temp[1][1]){
					xSpot[0]=temp[0][0];
					xSpot[1]=temp[2][1];
				}
				else if (temp[0][1]==temp[2][1]){
					if(temp[0][0]==temp[1][0]){
						xSpot[0]=temp[1][1];
						xSpot[1]=temp[2][0];
					}
					else{
						xSpot[0]=temp[0][0];
						xSpot[1]=temp[1][1];
					}
				}
				else{
					xSpot[0]=temp[2][0];
					xSpot[1]=temp[0][1];
				}
				removeSquare(new int[] {xSpot[0],xSpot[1]});

			}//L shape
			else if(c.checkHeight()==3){
				int tempH = temp[1][0];
				if((temp[1][1]-1) >=0){
					removeSquare(new int[] {tempH, temp[1][1]-1});
				}
				if((temp[1][1]+1) <size){
					removeSquare(new int[] {tempH,temp[1][1]+1});
				}
			}//vertical
			else{
				int tempW = temp[1][1];
				if((temp[1][0]-1) >=0){
					removeSquare(new int[] {temp[1][0]-1,tempW});
				}
				if((temp[1][0]+1) < size){
					removeSquare(new int[] {temp[1][0]+1,tempW});
				}
			}//horizontal
		}
	}

	public boolean checkDone(){
		boolean done = true;
		for (int i = 0; i < size; i++) {	
			if(colorArr[i].complete != true ){
				done = false;
				break;
			}		
		}
		return done;
	}

	public void print(){
		for(int i = 0; i<size; i++){
			for (int j = 0; j < size; j++) {
				System.out.print(mainArr[i][j]+"    ");
			}
			System.out.println("");
		}
	}

    public void actionPerformed(ActionEvent a) {
		
	}
}