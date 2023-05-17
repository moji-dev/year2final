import java.util.Random;

public class question4 {

    public static String randomStartingPoint() {
        Random rand = new Random();
        String binaryString = "";

        for (int i = 0; i < 8; i++) {
            int randomColumn = rand.nextInt(8); // random column index (0-7)
            binaryString += String.format("%03d", Integer.parseInt(Integer.toBinaryString(randomColumn)));
        }
        return binaryString;
    }

    public static void main(String[] args) {

        System.out.println(randomStartingPoint());
    }

}
