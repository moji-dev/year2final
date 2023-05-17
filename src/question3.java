public class question3 {

    public static String boardToBinary(Character[][] board) {
        String binaryString = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 7; j >= 0; j--) {
                if (board[i][j] == 'Q') {
                    binaryString = String.format("%03d", Integer.parseInt(Integer.toBinaryString(j))) + binaryString;
                    break;
                }
            }
        }
        return binaryString;
    }

    // main
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
        System.out.println(boardToBinary(board));
    }

}
