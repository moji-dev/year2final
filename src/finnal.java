import java.util.*;

public class finnal {
    private static final Random rand = new Random();

    // Generate a random board
    public static String generateRandomBoard() {
        String board = "";
        for (int i = 0; i < 8; i++) {
            board += String.format("%03d", Integer.parseInt(Integer.toBinaryString(rand.nextInt(8))));
        }
        return board;
    }

    // Mutate a given board
    public static String mutateBoard(String board) {
        int index = rand.nextInt(8);
        return board.substring(0, index * 3)
                + String.format("%03d", Integer.parseInt(Integer.toBinaryString(rand.nextInt(8))))
                + board.substring((index + 1) * 3);
    }

    // Solve the Eight Queens problem
    public static String solve(int iterations) {
        String currentBoard = generateRandomBoard();
        int currentFitness = calculateFitness(currentBoard);

        for (int i = 0; i < iterations; i++) {
            String newBoard = mutateBoard(currentBoard);
            int newFitness = calculateFitness(newBoard);

            // If the new board is better, or we randomly decide to explore, update the
            // current board
            if (newFitness > currentFitness || rand.nextDouble() < 0.05) {
                currentBoard = newBoard;
                currentFitness = newFitness;
            }

            // If a solution is found, return it
            if (currentFitness == 56) {
                return currentBoard;
            }
        }

        // Return the best board found
        return currentBoard;
    }

    public static int calculateFitness(String solution) {
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

    public static int calculateAttacks(Character[][] board) {
        int attacks = 0;

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board[row][column] == 'Q') {
                    // Check for attacks in each direction
                    int[] rowDirections = { -1, -1, -1, 0, 0, 1, 1, 1 };
                    int[] columnDirections = { -1, 0, 1, -1, 1, -1, 0, 1 };

                    for (int i = 0; i < 8; i++) {
                        int newRow = row + rowDirections[i], newColumn = column + columnDirections[i];
                        while (newRow >= 0 && newRow < 8 && newColumn >= 0 && newColumn < 8) {
                            if (board[newRow][newColumn] == 'Q') {
                                attacks++;
                                break;
                            }
                            newRow += rowDirections[i];
                            newColumn += columnDirections[i];
                        }
                    }
                }
            }
        }
        return attacks;
    }

    public static void main(String[] args) {
        int iterations = 5000;
        String solution = solve(iterations);
        System.out.println("Solution: " + solution);
        System.out.println("Fitness: " + calculateFitness(solution));
    }
}