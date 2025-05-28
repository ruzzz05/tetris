package es.unileon.prg1.tetris;


public class ArrayNxN extends ArrayMxN{

    int size;

    public ArrayNxN(int size) {
        super(size,size);
    }

    public ArrayNxN(ArrayMxN initial) {
        this(Math.max(initial.rows(), initial.columns()));
        for (int i = 0; i < initial.rows(); i++) {
            for (int j = 0; j < initial.columns(); j++) {
                array [i] [j] = initial.get(i, j);
                
            }
        }
    }


    private int length() {
        return array.length;
    }

    private boolean isEmptyRow(int m) {
        for (int i = 0; i< this.length(); i++) {
            if (this.get(m, i) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyColumn(int n) {
        for (int i = 0; i< this.length(); i++) {
            if (this.get(i, n) != 0) {
                return false;
            }
        }
        return true;
    }

    public int getEmptyColumnsLeft() {
        for (int i = 0; i < this.length(); i++) {
            if (!this.isEmptyColumn(i)) {
                return i;
            }
        }
        return this.length();
    }

    public int getEmptyColumnsRight() {
        int emptyColumnsRight = 0;
        for (int i = this.length() -1; i >= 0; i--) {
            if (!this.isEmptyColumn(i)) {
                    return emptyColumnsRight;
            }
            else {
                emptyColumnsRight++;
            }
        }
        return emptyColumnsRight;
    }

    private int getEmptyRowsDown() {
        int emptyRowsDown = 0;
        for (int i = this.length() -1; i >= 0; i--) {
            if (!this.isEmptyRow(i)) {
                return emptyRowsDown;
            }
            else {
                emptyRowsDown++;
            }
        }
        return emptyRowsDown;
    }

    private int getEmptyRowsUp() {
        for (int i = 0; i < this.length(); i++) {
            if (!this.isEmptyRow(i)) {
                return i;
            }
        }
        return this.length();
    }

    public ArrayMxN getMinArray() {
        int minRows = rows() - this.getEmptyRowsDown() - this.getEmptyRowsUp();
        int minColumns = columns() - this.getEmptyColumnsLeft() - this.getEmptyColumnsRight();
        
        if (minRows <= 0) {
            return null;
        } 
        else {
            ArrayMxN minArray = new ArrayMxN(minRows, minColumns);

            for (int i = 0; i < minRows; i++) {
                for (int j = 0; j < minColumns; j++) {
                    minArray.set(i, j, this.get(i + this.getEmptyRowsUp(), j + this.getEmptyColumnsLeft()));
                }
            }
            return minArray;

        }
    }

    public ArrayNxN getIdentity() {
        ArrayNxN identity = new ArrayNxN(this.length());
        for (int i = 0; i < identity.rows(); i++) {
            for (int j = 0; j < identity.rows(); j++) {
                if (i ==j ) {
                    identity.set(i, j, 1);
                }
            }
        }     
        return identity;
    }

    public ArrayNxN mirrorV() {
        ArrayNxN arrayV = new ArrayNxN(this.length());
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.rows(); j++) {
                arrayV.set(i, j, this.get(i, this.rows()-1-j));
            }
        }
        return arrayV;
    }

    public ArrayNxN mirrorH() {
        ArrayNxN arrayH = new ArrayNxN(this.length());
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                arrayH.set(i, j, this.get(this.rows()-1-i, j));
            }
        }
        return arrayH;
    }

    public ArrayNxN transpose() {
        ArrayNxN arrayTraspose = new ArrayNxN(this.length());
        for (int i = 0; i <= this.length(); i++) {
            for (int j = 0; j <= this.length(); j++) {
                arrayTraspose.set(i, j, this.get(j, i));
            }
        }
        return arrayTraspose;
    }

    public ArrayNxN multiply(ArrayNxN another) {
        if (columns() != another.rows()) {
            return null;
        }

        ArrayNxN arrayMultiply = new ArrayNxN(this.rows());
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < arrayMultiply.rows(); j++) {
                int valorMultiplicado = 0;
                for (int k = 0; k < arrayMultiply.rows(); k++) {
                    valorMultiplicado += get(i,k) * another.get(k, j);
                }
                arrayMultiply.set(i, j, valorMultiplicado);
            }
        }
        return arrayMultiply;
    }

    public ArrayNxN spinRight() {
        ArrayNxN spinnedArray;
        spinnedArray = this.transpose().multiply(this.getIdentity().mirrorV());
        return spinnedArray;
    }

    public ArrayNxN spinLeft() {
        ArrayNxN spinnedArray;
        spinnedArray = this.transpose().multiply(this.getIdentity()).mirrorH();
        return spinnedArray;
    }

    public String toString() {
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
            cadena.append(" ");
            cadena.append("\n");
        }
      
        return cadena.toString();
    }
}
