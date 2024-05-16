package Entity;

import java.awt.event.MouseEvent;

public class Card {
    private int[] columns;
    private int[] rows = {5,75};
    
    public Card(){
        setColumnsCoordinates();
    }

    private void setColumnsCoordinates(){
        columns = new int[10];
        for(int i = 0; i < 10; i++){
            if (i == 8){
                columns[i] = 730;
            }
            else if (i == 9){
                columns[i] = 730 + 52;
            }
            else
                columns[i] = 80 + i*52;
        }        
    }    

    public int[] qualifiedPositionCard(MouseEvent e){
        int a[] = {0,0};
        if (rows[0] <= e.getY() && e.getY() <= rows[1]){
            for(int i = 0;i < 10;i++){
                if ((columns[i] - e.getX()) > 0 && (columns[i] - e.getX()) < 52){
                    a[0] = i-1;
                    a[1] = 1;
                    return a;
                }
            }
        }
        return a;
    }
}