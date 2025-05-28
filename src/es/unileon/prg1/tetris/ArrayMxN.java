package es.unileon.prg1.tetris;

public class ArrayMxN {
    protected int[][] array;

    
    public ArrayMxN(int rows, int columns){
        array = new int [rows][columns];
    }


    public int rows(){
        return array.length;
    }

    public int columns() {
        if (array.length > 0) {
            return array[0].length;
        } else {
            return 0; 
        }
    }

    public int get(int row, int column) {
        if (row < 0 || column < 0) {
            return Integer.MIN_VALUE;
        }

        if (row >= rows() || column >= columns()) {
            return Integer.MIN_VALUE;
        }

        else {
            return array[row][column];
        }
        
    }

    public boolean set(int row, int column, int value) {
        if (row >= 0 && column >= 0 && row < rows() && column < columns()) {
            array[row][column] = value;
            return true;
            }

        else {
            return false;
        }
    }

    public String toString(char replacement) {
        StringBuilder cadena;
        cadena = new StringBuilder();

        for (int i = 0; i < this.rows(); i++) {
            for (int j=0; j < this.columns(); j++) {
                if (array[i][j] == 1){
                    cadena.append(" ");
                    cadena.append(array[i][j]);
                }else{
                    cadena.append("  ");
                }
        }   
            cadena.append(" "); // Edición del toString
            cadena.append("\n");
        }
      
        return cadena.toString().replace('1', replacement);
        
    }
}
