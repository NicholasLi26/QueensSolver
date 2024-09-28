import java.util.*;

public class Colours{
    public ArrayList<String> coords;
    public int size; 
    public String colour;
    public boolean complete;
    int[][] coordsArr;
    
    public Colours(){
        coords = new ArrayList<String>();
        size = 0;
        complete = false;
    }

    public void setArray(ArrayList<String> arr){
        coords = arr;
    }

    public int checkHeight(){
        int height;
        convertAll();
        int max = -1;
        int min = Integer.MAX_VALUE;
        for(int i =0; i<size; i++){
            if(coordsArr[i][0]>max) max = coordsArr[i][0];
            if (coordsArr[i][0]<min) min = coordsArr[i][0];
            //System.out.println(coordsArr[i][0] +", "+ coordsArr[i][1]);
        }
        height = max-min+1;
        //System.out.println(max+" + "+min);
        return height;
    }

    public int checkWidth(){
        int width;
        convertAll();
        int max = -1;
        int min = Integer.MAX_VALUE;
        for(int i =0; i<size; i++){
            if(coordsArr[i][1]>max) max = coordsArr[i][1];
            if (coordsArr[i][1]<min) min = coordsArr[i][1];
        }
        width = max-min+1;

        return width;
    }

    public void removeElem(int[] remove){
        coords.remove(String.valueOf(remove[0])+String.valueOf(remove[1]));
        Collections.sort(coords);
        size--;
        convertAll();
    }

    public void addElem(int[] add){
        coords.add((String.valueOf(add[0])+String.valueOf(add[1])));
        Collections.sort(coords);
        size++;
        convertAll();
    }

    public int[][] convertAll(){
        coordsArr = new int[size][2];
        for (int i = 0; i < size; i++) {
            coordsArr[i][0] = coords.get(i).charAt(0)-'0';
            coordsArr[i][1] = coords.get(i).charAt(1)-'0';
        }
        return coordsArr;
    }

    public int[] getMinH(){
        convertAll();
        int min = Integer.MAX_VALUE;
        int[] coordsMin = new int[2];
        for (int i = 0; i < size; i++) {
            if(coordsArr[i][0] < min){
                min = coordsArr[i][0];
                coordsMin = coordsArr[i];
            }   
        }
        return coordsMin;
    }

    public int[] getMaxH(){
        convertAll();
        int max = Integer.MIN_VALUE;
        int[] coordsMax = new int[2];
        for (int i = 0; i < size; i++) {
            if(coordsArr[i][0] > max){
                max = coordsArr[i][0];
                coordsMax = coordsArr[i];
            }
        }
        return coordsMax;
    }

    public int[] rangeOfH(){
        /*
        Returns max and min height values of the colour in an array
        [max, min]
         */ 
        int[] temp1 = getMaxH();
        int[] temp2 = getMinH();
        int[] range = {temp1[0], temp2[0]};
        return range;
    }

    public int[] getMinW(){
        convertAll();
        int min = Integer.MAX_VALUE;
        int[] coordsMin = new int[2];
        for (int i = 0; i < size; i++) {
            if(coordsArr[i][1] < min){
                min = coordsArr[i][1];
                coordsMin = coordsArr[i];
            }   
        }
        return coordsMin;
    }

    public int[] getMaxW(){
        convertAll();
        int max = Integer.MIN_VALUE;
        int[] coordsMax = new int[2];
        for (int i = 0; i < size; i++) {
            if(coordsArr[i][1] > max){
                max = coordsArr[i][1];
                coordsMax = coordsArr[i];
            }   
        }
        return coordsMax;
    }

    public int[] rangeOfW(){
        int[] temp1 = getMaxW();
        int[] temp2 = getMinW();
        int[] range = {temp1[1], temp2[1]};
        return range;
    }

    public void print(){
        for (int i = 0; i < size; i++) {
            System.out.println(coordsArr[i][0]+", "+coordsArr[i][1]);
        }
    }

    public void completed(){
        complete = true;
    }
}