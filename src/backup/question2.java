public class question2 {

    public static boolean isValidBoard(String[][] board) {
        if (board.length != 8) {
            return false;
        }

        int dotCount = 0;
        int queenCount = 0;

        for (int i = 0; i < 8; i++) {
            if (board[i].length != 8) {
                return false;
            }

            for (int j = 0; j < 8; j++) {
                if (board[i][j].equals("Q")) {
                    queenCount++;
                } else if (board[i][j].equals(".")) {
                    dotCount++;
                } else {
                    return false;
                }
            }
        }

        if (queenCount != 8) {
            return false;
        }

        return dotCount == 56 && queenCount == 8;
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

        if (isValidBoard(board)) {
            System.out.println("The board is valid.");
        } else {
            System.out.println("The board is not valid.");
        }
    }

}
