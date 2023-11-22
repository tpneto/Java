import javax.swing.*;

public class Main {
    public static void main(String args []){

        Game game = new Game(new JFrame()); // Initialize the game with the frame
        JFrame frame = game.getFrame();

        frame.add(game); //Add the game to the frame
        frame.setResizable(false); //Make it not resizable
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit when close the frame.


        frame.pack();//Automatic adjustment to the window.
        frame.setLocationRelativeTo(null);//centralize the window
        frame.setVisible(true);//Make the window visible
        new Thread(game).start();//Start the Thread to call the method run()

    }

//    public static void main(String[] args) {
//        Game game = new Game(); // Inicialize o jogo diretamente
//        game.run(); // Inicie o jogo
//    }

}
