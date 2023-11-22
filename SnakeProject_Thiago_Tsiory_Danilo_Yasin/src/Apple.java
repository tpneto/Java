import java.util.Random;
    public class Apple {
        private int x;
        private int y;
        private int maxWidth;
        private int maxHeight;

        public Apple(int maxWidth, int maxHeight) {
            this.maxWidth = maxWidth;
            this.maxHeight = maxHeight;
            generateRandomPosition();
        }//Parametrized constructor.
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void generateRandomPosition() {
            x = new Random().nextInt(maxWidth - 10);
            y = new Random().nextInt(maxHeight - 10);
        }//Randomize the apple's position inside the board.
    }


