package es.unileon.prg1.tetris;
/**
 * El método MainTetris, es el que se inicia al ejecutar el juego, este comprueba que los valores
 * introducidos al ejecutarlo son válidos.
 * @author Miguel Ruz García
*/
public class MainTetris {

    public static void main(String[] args) throws TetrisException{     
        Tetris tetris;
        TetrisTextUI ui;        

        if (args.length != 3) {
            System.out.println("Uso: MainTetris [filas] [columnas] [modo_color]");
            System.exit(1);
        }
        
        try {
            int rows = Integer.parseInt(args[0]);
            int columns = Integer.parseInt(args[1]);
            String color = args[2];

            // Validar los límites para filas y columnas
            if (rows < 5 || rows > 20 || columns < 5 || columns > 10) {
                System.out.println("Las filas y columnas deben estar entre 5 y 10 inclusive.");
                System.exit(1);
            }

            // Validar el modo de color
            if (!color.equals("nocolor") && !color.equals("color") ){
                System.out.println("El modo de color debe ser 'nocolor' o 'color'.");
                System.exit(1);

            
            }
            tetris = new Tetris(rows, columns, color);
            ui = new TetrisTextUI(tetris);
            ui.init();
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese números válidos para filas y columnas.");
            System.exit(1);
        }
        
        
    }
}