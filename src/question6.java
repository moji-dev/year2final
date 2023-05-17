import java.util.*;

public class question6 {

    public static String flipRandomBit(String solution) {
        Random random = new Random();
        int randomIndex = random.nextInt(solution.length());

        char[] solutionChars = solution.toCharArray();
        solutionChars[randomIndex] = solutionChars[randomIndex] == '0' ? '1' : '0';

        return new String(solutionChars);
    }

    public static void main(String[] args) {
        String solution = "000000000000000000000000";
        System.out.println("Original solution:" + solution);

        String flippedSolution = flipRandomBit(solution);
        System.out.println("Flipped solution: " + flippedSolution);
    }

}
