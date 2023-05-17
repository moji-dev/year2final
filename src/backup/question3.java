import java.util.Random;

public class question3 {

    // public static String boardToBinary(String[][] board) {
    // StringBuilder binary = new StringBuilder();

    // for (String[] row : board) {
    // for (int i = 0; i < 8; i++) {
    // if (row[i].equals("Q")) {
    // String bin = Integer.toBinaryString(i);
    // while (bin.length() < 3) {
    // bin = "0" + bin;
    // }
    // binary.append(bin);
    // break;
    // }
    // }
    // }

    // return binary.toString();
    // }

    public static String boardToBinary(String[][] board) {
        StringBuilder binary = new StringBuilder();
        int x = 0;

        for (int r = 7; r >= 0; r--) {
            String[] row = board[r];
            for (int i = 0; i < 8; i++) {
                if (row[i].equals("Q")) {
                    System.out.print(Integer.toBinaryString(i));
                }
            }
        }
        // line gap
        System.out.println();
        System.out.println(x);
        return "hi";
    }

    public static String eightQueensRandomBinary() {
        Random rand = new Random();
        StringBuilder binary = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            // Generate a random position for the queen in this row (0-7)
            int queenPosition = rand.nextInt(8);

            // Create a string of 3 zeros
            String queenRow = "000";

            // Convert the queenPosition to binary and pad it with leading zeros
            queenRow = String.format("%03d", Integer.parseInt(Integer.toBinaryString(queenPosition)));

            // Add this row to the binary string
            binary.append(queenRow);
        }

        return binary.toString();
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String[][] board = {
                { "Q", ".", ".", ".", ".", ".", ".", "." },
                { ".", "Q", ".", ".", ".", ".", ".", "." },
                { ".", ".", "Q", ".", ".", ".", ".", "." },
                { ".", ".", ".", "Q", ".", ".", ".", "." },
                { ".", ".", ".", ".", "Q", ".", ".", "." },
                { ".", ".", ".", ".", ".", "Q", ".", "." },
                { ".", ".", ".", ".", ".", ".", "Q", "." },
                { ".", ".", ".", ".", ".", ".", ".", "Q" }
        };

        // boardToBinary(board);
        // System.out.println("Binary: " + binary);

        // run random 6 times
        for (int i = 0; i < 6; i++) {
            // randomBoard();
            eightQueensRandomBinary();
        }

    }

}
