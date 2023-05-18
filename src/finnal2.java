import java.util.*;

public class finnal2 {
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

    // Solve the Eight Queens problem using Random Restart Hill Climbing
    public static String solve(int iterations) {
        String currentBoard = generateRandomBoard();
        int currentFitness = calculateFitness(currentBoard);

        for (int i = 0; i < iterations; i++) {
            String newBoard = mutateBoard(currentBoard);
            int newFitness = calculateFitness(newBoard);

            // If the new board is better, update the current board
            if (newFitness > currentFitness) {
                currentBoard = newBoard;
                currentFitness = newFitness;
            }

            // If a solution is found, return it
            if (currentFitness == 56) {
                return currentBoard;
            }

            // If the current board is not a solution, generate a new random board
            if (i % 100 == 0 && currentFitness < 56) {
                currentBoard = generateRandomBoard();
                currentFitness = calculateFitness(currentBoard);
            }
        }

        // Return the best board found
        return currentBoard;
    }

    public static int calculateFitness(String solution) {
        Character[][] board = new Character[8][8];

        for (int i = 0; i < 8; i++) {
            int queenPosition = Integer.parseInt(solution.substring(i * 3, (i + 1) * 3), 2);
            Arrays.fill(board[i], '.');
            board[i][7 - queenPosition] = 'Q';
        }

        int totalAttacks = calculateAttacks(board);

        // Calculate the fitness value
        int fitness = 56 - totalAttacks;

        return fitness;
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
        int iterations = 100000;
        String solution = solve(iterations);
        System.out.println("Solution: " + solution);
        System.out.println("Fitness: " + calculateFitness(solution));
    }
}