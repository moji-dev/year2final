import java.util.*;

public class finfinfin {
    private static final int BOARD_SIZE = 8;
    private static final Random random = new Random();
    private static final int MAX_ATTACKS = BOARD_SIZE * (BOARD_SIZE - 1);

    public static void main(String[] args) {
        int totalFitness = 0;

        for (int i = 0; i < 100; i++) {
            String solution = solve(1000);
            int fitness = fitness(solution);
            totalFitness += fitness;
            // Remove this line if you don't want to print the fitness of each solution
            // System.out.println(fitness);
        }
        System.out.println(totalFitness / 100.0); // prints the average fitness after 100 runs
    }

    public static String solve(int iterations) {
        String current = getRandomStartingArrangement();
        int currentFitness = fitness(current);
        double coolingRate = 0.99;
        double temperature = 600;

        for (int i = 0; i < iterations; i++) {
            String neighbor = flipRandomBit(current);
            int neighborFitness = fitness(neighbor);

            if (neighborFitness == MAX_ATTACKS) {
                return neighbor;
            }

            if (shouldAccept(neighborFitness, currentFitness, temperature)) {
                current = neighbor;
                currentFitness = neighborFitness;
            }

            temperature *= coolingRate;
        }

        return current;
    }

    private static String getRandomStartingArrangement() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            sb.append(String.format("%3s", Integer.toBinaryString(random.nextInt(BOARD_SIZE))).replace(' ', '0'));
        }
        return sb.toString();
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

    private static String flipRandomBit(String solution) {
        int randomIndex = random.nextInt(solution.length());
        char[] solutionChars = solution.toCharArray();
        solutionChars[randomIndex] = solutionChars[randomIndex] == '0' ? '1' : '0';
        return new String(solutionChars);
    }

    private static boolean shouldAccept(int candidateFitness, int currentFitness, double temperature) {
        if (candidateFitness > currentFitness) {
            return true;
        }
        double probability = Math.exp((candidateFitness - currentFitness) / temperature);
        return probability > random.nextDouble();
    }
}
