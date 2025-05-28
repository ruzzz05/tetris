package es.unileon.prg1.tetris;

/**
 * La clase TetrisTextUI es la encargada de toda la interfaz que ve el usuario, recibe el input, leyendo
 * el teclado, además de hacer los print y conseguir los bloques y el board actualizado.
 * También crea el título del Tetris en colores. De forma adicional hemos decidido crear una pantalla de 
 * inicio en el juego, para que al ejecutarlo no salte directamente el bloque, board y demás.
 * @author Miguel Ruz García
*/

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TetrisTextUI{

    /**
     * Esta Variable "exit" es la encargada de que el juego continue funcionando, mientras esta
     * se mantanga en falsa, el juego continuará funcionando. Esta cambiará si el usuario desea
     * terminar o por el contrario este pierde.
     */
    private Boolean exit = false;
    private Tetris tetris;
    /**
     * Esta variable llamada drop, se encarga de saber si el drop se ha realizado correctamente, en 
     * el caso de que se haga drop, será verdadero y se cogerá un bloque nuevo, en el caso de que 
     * el drop no sea posible, se hará un print de lo mismo que se ha hecho anteriormente.
     */
    private Boolean drop;
    /**
     * De forma adicional hemos creado una pequeña pantalla donde el usuario tiene que introducir 
     * una F, para ejecutar el programa completo. En un inicio la variable start será falsa, cuando
     * el usuario introduzca esa F ya mencionada, esta será verdadera y el programa se ejecutará completo
     */
    private Boolean start = false;
    /**
     * Esta variable la utilizo para saber si se debe hacer un print, en init o no.
     */
    private Boolean print = false;
    private Block actualBlock;
    /**
     * Aquí creamos el Logger para despues usarlos
     */
    private static final Logger Logger = LogManager.getLogger(TetrisTextUI.class.getName());

    
    /**
     * En el constructor lo único que hacemos es declarar la variable tetris como this.tetris
     * @param tetris
     * @throws TetrisException
     */
    public TetrisTextUI(Tetris tetris){
        this.tetris = tetris;
        
    }
    /**
     * El método init es el que inicia el juego, y va mandando los prints.
     * En este se inicia la pantalla anteriormente mencionada. Se crea un bloque, que será el actualBlock, y se llamará
     * al método prints(), que hará los prints de toda la interfaz que verá el usuario. Luego se llamará al método getLimit(),
     * este calculará un nuevo limit que se usará a la hora de mover el bloque, dependiendo del bloque que sea, el limit podría
     * cambiar. Luego se entra en un bucle,donde mientras que el usuario no indique que quiere salir, no terminará,
     * donde se irá leyendo el teclado. Cuando hemos leído el teclado, si el print es verdadero, es decir se ha hecho un input
     * válido y si no se ha indicado que se quiere salir, se llamará al método getDrop(). Con ello se sabrá si se ha hecho un drop
     * en caso de que el drop sea verdadero, se creará un nuevo bloque, y se volverá a llamar al método getLimit(), para que se 
     * calcule el limit de este bloque. También tenemos la clase checkSpace(), que se encarga de comprobar si el bloque se puede
     * bajar y colocar en el board, si este descubre que no entra pondrá el exit como true, y se cerrará el juego. En el caso de que
     * el drop no sea verdadero, se usará el mismo bloque que antes, y se volverán a hacer los prints y demás.
     * @throws TetrisException
     */
    public void init() throws TetrisException{
        Logger.info(" ");
        Logger.info("*************************");
        Logger.info("Empieza el juego.........");
        Logger.info("*************************");
        Logger.info(" ");

        this.startgame();
        actualBlock = tetris.getBlock();
        this.prints();  
        tetris.getLimit();
        while(!this.exit){
            Logger.info("El bloque se encuentra en: X = " + tetris.getPositionX() + ", Y = " + tetris.getPositionY());
            this.readKeyboard();
            if(this.print && !this.exit){
                this.getDrop();
                if (drop){
                    Logger.info("Como hemos colocado un Bloque cogemos otro");
                    actualBlock = tetris.getBlock();// Nuevo bloque solo si se hace drop
                    tetris.getLimit();
                }else{
                    actualBlock = tetris.getActuaBlock();

                }
                this.prints();
            }
                
            }
    }                
    
     
    
   /**
    * Este método es el encargado de hacer los prints, primero se hace un print de una línea vacía, para que se vea mejor, posteriormente
    * se llama al método printTitle(), que es el encargado de hacer el título del juego, y por último se hace un print de todo el juego,
    * bloque, board, el mensaje de abajo, los puntos...
    */
    public void prints(){
        Logger.info("Enseñamos el tablero de juego");
        String voidLine = "\n";
        System.out.println(voidLine);
        printTitle("TETRIS");
        System.out.println(this.tetris.toString());
         
    }
    /**
     * Este método es algo que hemos realizado de forma adicional, para que el usuario al ejecutar el programa no se encuentre
     * con el bloque, el board y demás directamente, sino que se encuentre con una pantalla de inicio, donde se le pide que introduzca
     * una F para empezar, y así se ejecutará el programa completo.
     */
    public void startgame(){
        Logger.info("Se enseña la pantalla de inicio");
        System.out.println("Lets play tetris");
        System.out.println("Press 'F' and ENTER to start: ");
        Logger.info("Empezamos a leer en teclado para empezar a jugar...");
        this.readKeyboardStart(); 
        
    }


    
    /**
     * Este método requiere un texto, en este caso este es TETRIS, así crea un char, donde lo convierte a Array, 
     * y con ello podemos ponerle color pasándole el código de color y ponerle el fondo de color también.
     * @param title
     */

    public static void printTitle(String title) {
        char[] letters = title.toCharArray();
        String[] colors = {"\u001B[31m", "\u001B[34m", "\u001B[32m", "\u001B[33m", "\u001B[35m", "\u001B[36m"};

         for (int i = 0; i < letters.length; i++) {
            System.out.print(colors[i % colors.length] + Character.toUpperCase(letters[i]));
        }

    }

    /**
     * Este método lee el teclado para el método startgame(), funciona leyendo el input del usuario del teclado,
     * y si este introduce un caracter que no sea una simple f, este volvera a pedir un input válido.
     */
    public void readKeyboardStart(){
        while(!this.start){
            String inputUser = Keyboard.readString();
            switch (inputUser) {
                case "F":
                case "f":
                    this.start = true;
                    break;
            
                default:
                    Logger.warn("Invalid Initial Input: " + inputUser);

                    
            }
    }
    }


    
    /**
     * Este método, readKeyboard(), lee el input del usuario, y crea un bucle y hasta que el usuario no introduzca
     * un input válido este seguirá pidiendo uno. Cuando el usuario decide que acción hacer, este llamará a hacer
     * esa acción a Tetris que a su vez lo hará con el Block o el Board, dependiendo de la acción. He creado la variable
     * print, para que en el caso de que se introduzca Salir no se haga un print. También he creado la variable drop, para
     * saber si se ha hecho un drop, y así poder crear un nuevo bloque. Finalmente en el caso de que el input no sea válido
     * lanzamos una exception y lo reflejamos en el log.
     * @return
     * @throws TetrisException
     */
    public boolean readKeyboard() throws TetrisException{
        this.print = false;
        this.drop = false;
        while(!this.exit && !this.print){
                String inputUser = Keyboard.readString();
                switch (inputUser) {
                    case "A":
                    case "a":
                        Logger.info(" ");
                        Logger.info("El jugador ha dicho: Mover a la izquierda");
                        this.moveLeft();
                        print = true;
                        break;
                    case "D":
                    case "d":
                        Logger.info(" ");
                        Logger.info("El jugador ha dicho: Mover a la derecha");
                        this.moveRigth();
                        print = true;
                        break;
                    case "W":
                    case "w":
                        Logger.info(" ");
                        Logger.info("El jugador ha dicho: Girar a la izquierda");
                        this.spinLeft();
                        print = true;
                        break;
                    case "E":
                    case "e":
                        Logger.info(" ");
                        Logger.info("El jugador ha dicho: Girar a la derecha");
                        this.spinRight();  
                        print = true;
                        break;
                    case "S":
                    case "s":
                        Logger.info(" ");
                        Logger.info("El jugador ha dicho: Drop");
                        this.drop();
                        print = true;
                        drop = true;
                        break;
                    case "Salir":
                    case "salir":
                    case "SALIR":
                        Logger.info(" ");
                        Logger.info("El jugador ha dicho: Salir");
                        Logger.info(" ");
                        Logger.info("*****************");
                        Logger.info("Gracias por Jugar");
                        Logger.info("*****************");
                        this.exit();
                        break;
                    default:          
                        try{
                            this.invalidInput();
                        }catch(TetrisException e){
                            if(!exit){
                                Logger.info("EXCEPTION Input No Válido(EL JUGADOR HA INTRODUCIDO UN MENSAJE NO VÁLIDO): " + inputUser);
                            }
                        }
                        print = true;
                }  

        }
        return print;
   
        }
    
    /**
     * Este método llama al Tetris.moveRight(), este comprobará si se puede mover a la derecha, y si es así, lo moverá, 
     * en el caso contrario, lanzará una exception, que cogerá este método y lo reflejará en el log.
     * @throws TetrisException
     */
    public void moveRigth() throws TetrisException{
        try{
            this.tetris.moveRight();
        }catch(TetrisException e){
            Logger.info("EXCEPTION No se puede mover a la derecha");
        }
    }
    /**
     * Este método hace lo mismo que el anterior pero hacia la izquierda.
     * @throws TetrisException
     */
    public void moveLeft() throws TetrisException{
        try{
            this.tetris.moveLeft();
        }catch(TetrisException e){
            Logger.info("EXCEPTION No se puede mover a la izquierda");
        }
    }
    /**
     * El método spinRight, hace lo mismo que los anteriores, llama al Tetris.spinRight(), que llamará al método del mismo nombre del 
     * Block, que girará el bloque.
     */
    public void spinRight(){
        this.tetris.spinRight();

    }
    /**
     * El método spinLeft, hará lo mismo que el anterior pero a la izquierda
     */
    public void spinLeft(){
        this.tetris.spinLeft();
    }
    /**
     * El metodo drop, llamará al método drop del Tetris, en el caso de que el drop no se efectue correctamente, se lanzará una exception
     * que cogerá este método y lo reflejará en el log.
     */
    public void drop(){
        try{
            this.tetris.drop();

        }catch(TetrisException e){
            Logger.info("EXCEPTION No se puede hacer drop");
        }
    }
        
    /**
     * Este en un método al que llamar en caso de querer terminar el juego, cambia la variable exit, y así se dejarían
     * de hacer los prints y de leer el teclado.
     *  */ 
    public void exit(){
        this.exit = true;
    }

    /**
     * El método getDrop, utiliza el getDrop del Tetris para saber si el drop se ha realizado correctamente, en el caso
     * de que sí, se generará un nuevo bloque, de lo contrario, se hará un print de lo mismo que lo anterior.
     * @return
     */
    public Boolean getDrop(){
        this.drop = this.tetris.getDrop();
        return this.drop;
    }

    /**
     * Este método es el encargado de lanzar la exception en el caso de que el input no sea válido.
     * @throws TetrisException
     */

    public void invalidInput() throws TetrisException{
        throw new TetrisException("Input no válido");
    }
}

  
