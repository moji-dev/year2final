public class question2 {

    public static boolean isValidBoard(Character[][] board) {
        // check for null or incorrect size
        if (board == null || board.length != 8) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            if (board[i] == null || board[i].length != 8) {
                return false;
            }
        }

        int queenCount = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                char square = board[row][col];
                if (square == 'Q') {
                    queenCount++;
                } else if (square != '.') {
                    return false; // invalid character
                }
            }
        }

        return queenCount == 8; // valid if there are exactly eight queens
    }

    public static void main(String[] args) {

        Character[][] board = {
                { '.', '.', '.', '.', '.', 'Q', '.', '.' },
                { 'Q', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', 'Q' },
                { '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', 'Q', '.' },
                { '.', '.', '.', '.', 'Q', '.', '.', '.' },
                { 'Q', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', 'Q', '.', '.', '.', '.' }
        };

        System.out.println(isValidBoard(board));
    }
}
