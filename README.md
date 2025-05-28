# p-tetris-1h
p-tetris-1h created by GitHub Classroom

##(Referente a la primera entrega, primera convocatoria) En algunos commit aparece referenciado "allTestBlock.java". Esto es un error: el archivo es realmente "OtherBlockTest.java".

Debido a el uso de un random, una función de Tetris.java no puede ser testeada de forma coherente.

A pesar de marcarse como "no usadas" algunas variables en ciertos archivos test, son necesarias para el correcto funcionamiento de algunos de ellos.

15 codesmells provienen de la clase Keyboard.java (no debemos tocar ese archivo según entendemos)

Hay varios codesmells que provienen de usar "System.out" en vez de loggers. El programa usa intencionalmente los "System.out" para el propósito de mostrar algo en pantalla, por tanto ¿no se "arregla?

El código duplicado son dos métodos que esencialmente hacen lo mismo, pero uno recibe un parámetro y el otro no, cambiando en cierta manera el funcionamiento y resultado. 
