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
                { '.', '.', '.', '.', '.', 'Q', '.', '.' }, // 7
                { 'Q', '.', '.', '.', '.', '.', '.', '.' }, // 6
                { '.', '.', '.', '.', '.', '.', '.', 'Q' }, // 5
                { 'Q', '.', '.', '.', '.', '.', '.', '.' }, // 4
                { '.', '.', '.', '.', '.', '.', 'Q', '.' }, // 3
                { '.', '.', '.', '.', 'Q', '.', '.', '.' }, // 2
                { 'Q', '.', '.', '.', '.', '.', '.', '.' }, // 1
                { '.', '.', '.', 'Q', '.', '.', '.', '.' } // 0
                // 0 //1 //2 //3 //4 //5 //6 //7
        };
        System.out.println(boardToBinary(board));
    }

}
