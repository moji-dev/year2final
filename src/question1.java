public class question1 {

    public static boolean isValidBoardSquare(Character square) {
        if (square == null) {
            return false;
        }
        return square == '.' || square == 'Q';
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
