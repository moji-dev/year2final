
import java.util.*;

public class question7 {
    private static final int BOARD_SIZE = 8;
    private static final Random random = new Random();
    private static final int MAX_ATTACKS = BOARD_SIZE * (BOARD_SIZE - 1);

    public static void main(String[] args) {
        int y = 0;
        for (int i = 0; i < 100; i++) {
            String solution = randomRestartHillClimbing(1000);
            y = y + fitness(solution);

        }
        System.out.println(y / 100);
        // String solution = randomRestartHillClimbing(1000);
        // System.out.println("Solution: " + solution);
        // System.out.println("Fitness: " + fitness(solution));
    }

    public static String randomRestartHillClimbing(int iterations) {
        Random random = new Random();
        char[] current = randomArrangement();
        int currentFitness = fitness(new String(current));

        for (int i = 0; i < iterations; i++) {
            char[] neighbor = flipRandomBit(Arrays.copyOf(current, current.length));
            int neighborFitness = fitness(new String(neighbor));

            if (neighborFitness >= currentFitness) {
                current = neighbor;
                currentFitness = neighborFitness;
            } else {
                current = randomArrangement();
                currentFitness = fitness(new String(current));
            }

            if (currentFitness == 56) {
                break;
            }
        }

        return new String(current);
    }

    public static char[] randomArrangement() {
        Random random = new Random();
        char[] arrangement = new char[24];
        for (int i = 0; i < 8; i++) {
            int queenPos = random.nextInt(8);
            String binaryString = Integer.toBinaryString(queenPos);
            while (binaryString.length() < 3) {
                binaryString = "0" + binaryString;
            }
            for (int j = 0; j < 3; j++) {
                arrangement[i * 3 + j] = binaryString.charAt(j);
            }
        }
        return arrangement;
    }

    public static int fitness(String solution) {
        int totalAttacks = 56; // Maximum number of attacks possible
        int[] queens = new int[8];

        for (int i = 0; i < 8; i++) {
            queens[i] = Integer.parseInt(solution.substring(i * 3, i * 3 + 3), 2);
        }

        // Calculating number of individual attacks by each queen
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i != j) {
                    if (queens[i] == queens[j] || Math.abs(queens[i] - queens[j]) == Math.abs(i - j)) {
                        totalAttacks--;
                    }
                }
            }
        }

        return totalAttacks;
    }

    public static char[] flipRandomBit(char[] arrangement) {
        Random random = new Random();
        int bitToFlip = random.nextInt(24);
        arrangement[bitToFlip] = arrangement[bitToFlip] == '0' ? '1' : '0';

        return arrangement;
    }
}
