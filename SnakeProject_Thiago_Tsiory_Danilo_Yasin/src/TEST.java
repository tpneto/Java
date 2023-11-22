public class TEST {

    public static void main(String args []){
        int r = 10;
        int c = 30;

        String[][] board = new String[r][c];

        Thread blinkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isVisible = true;
                while (true) {
                    if (isVisible) {
                        System.out.print("X"); // Caractere a ser piscado
                    } else {
                        System.out.print(" "); // Espaço em branco para apagar o caractere
                    }
                    isVisible = !isVisible; // Alterna a visibilidade do caractere
                    System.out.print("\r"); // Move o cursor de volta ao início da linha
                    try {
                        Thread.sleep(500); // Intervalo de piscagem (500 ms)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        blinkThread.start();
    }

}
