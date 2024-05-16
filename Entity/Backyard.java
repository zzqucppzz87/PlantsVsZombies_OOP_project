package Entity;

import java.awt.event.MouseEvent;

public class Backyard {
    private int[] rows;
    private int[] columns;
    private int[][] availablePositions = new int[5][10];

    public Backyard(){
        setRowsCoordinates();
        setColumnsCoordinates();
        setAvailableCoordinate(availablePositions);
    }

    private void setRowsCoordinates(){
        rows = new int[5];
        for(int i = 0; i < 5; i++){
            rows[i] = 130 + i*100;
        }
    }

    private void setColumnsCoordinates(){
        columns = new int[10];
        for(int i = 0; i < 10; i++){
            columns[i] = 210 + i*80;
        }        
    }

    public void setAvailableCoordinate(int [][] a){
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 10;j++){
                a[i][j] = 0;
            }
    }

    public int[][] getAvailableCoordinate(){
        return availablePositions;
    }

    public int[] qualifiedPositionBackyard(MouseEvent e){
        int[] a = {0,0,0,0,0};
        for(int i = 0; i < 5;i++)
            for(int j = 1; j < 10;j++)
                // set a circle area in the rectangle are qualified to place plant
                if ((e.getX()-columns[j])*(e.getX()-columns[j]) + (e.getY()-rows[i])*(e.getY()-rows[i]) <= 40*40){
                    a[0] = columns[j];
                    a[1] = rows[i];
                    a[2] = 1;
                    a[3] = i;
                    a[4] = j;
                    return a;
                }
        return a;
    }

    public void reset(){
        setAvailableCoordinate(availablePositions);
    }

    public int[] getRows(){
        return rows;
    }

    public int[] getColumns(){
        return columns;
    }
}