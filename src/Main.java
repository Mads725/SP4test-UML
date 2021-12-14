public class Main {
    public static void main(String[] args) {
        GameController gc = new GameController();

        while(true) { // Restarts the game if the player loses.
            gc.startGame();
        }
    }
}