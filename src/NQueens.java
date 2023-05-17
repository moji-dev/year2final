import java.util.*;

public class NQueens {

    public static boolean isValidSquare(Character square) {
        if (square == null) {
            return false;
        }
        return square.equals('Q') || square.equals('.');
    }

    public static boolean isValidBoard(Character[][] board) {
        // Check if the board is null or not of the correct size
        if (board == null || board.length != 8 || board[0].length != 8) {
            return false;
        }

        int queenCount = 0;

        // Iterate over each square in the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Character square = board[i][j];

                // Check if the square is a valid board square
                if (!isValidSquare(square)) {
                    return false;
                }

                // Count the number of queens
                if (square.equals('Q')) {
                    queenCount++;
                }
            }
        }

        // Check if there are exactly eight queens on the board
        if (queenCount != 8) {
            return false;
        }

        return true;
    }

    public static String boardToBinary(Character[][] board) {
        StringBuilder binaryRepresentation = new StringBuilder();

        for (int i = board.length - 1; i >= 0; i--) {
            int queenPosition = -1;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'Q') {
                    queenPosition = j;
                    break;
                }
            }
            String binaryString = String.format("%03d", Integer.parseInt(Integer.toBinaryString(queenPosition)));
            binaryRepresentation.append(binaryString);
        }

        return binaryRepresentation.toString();
    }

    // public static String createRandomArrangement() {
    // Random random = new Random();
    // StringBuilder arrangement = new StringBuilder();

    // for (int i = 0; i < 8; i++) {
    // int queenPosition = random.nextInt(8);
    // String binaryString = Integer.toBinaryString(queenPosition);
    // arrangement.append(String.format("%03d", Integer.parseInt(binaryString)));
    // }

    // return arrangement.toString();
    // }
    public static String createRandomArrangement() {
        Random random = new Random();
        String arrangement = "";

        for (int i = 0; i < 8; i++) {
            int queenPosition = random.nextInt(8);
            String binaryString = String.format("%03d", Integer.parseInt(Integer.toBinaryString(queenPosition)));
            arrangement += binaryString;
        }

        return arrangement;
    }

    public static String flipRandomBit(String arrangement) {
        Random random = new Random();
        int index = random.nextInt(arrangement.length());
        char flippedBit = (arrangement.charAt(index) == '0') ? '1' : '0';
        String modifiedArrangement = arrangement.substring(0, index) + flippedBit + arrangement.substring(index + 1);
        return modifiedArrangement;
    }

    // public static int calculateFitness(String arrangement) {
    // int clashes = 0;
    // int totalAttacks = 8 * 7;

    // for (int i = 0; i < 8; i++) {
    // int row = i;
    // int column = Integer.parseInt(arrangement.substring(i * 3, (i + 1) * 3), 2);

    // // Check for clashes in the same column
    // for (int j = i + 1; j < 8; j++) {
    // int nextColumn = Integer.parseInt(arrangement.substring(j * 3, (j + 1) * 3),
    // 2);
    // if (column == nextColumn) {
    // clashes++;
    // }
    // }

    // // Check for clashes in diagonals
    // for (int j = i + 1; j < 8; j++) {
    // int nextColumn = Integer.parseInt(arrangement.substring(j * 3, (j + 1) * 3),
    // 2);
    // int nextRow = j;

    // if (Math.abs(column - nextColumn) == Math.abs(row - nextRow)) {
    // clashes++;
    // }
    // }
    // }

    // int fitness = totalAttacks - clashes;
    // return fitness;
    // }

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

    // Method to calculate the fitness value
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

    //// final////////////////////////
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
                System.out.println("Solution found after " + i + " iterations");
                return currentBoard;
            }
        }

        // Return the best board found
        return currentBoard;
    }

    public static void main(String[] args) {

        // System.out.println(isValidSquare(null));
        Character[][] board = {
                { '.', '.', '.', '.', '.', 'Q', '.', '.' },
                { 'Q', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', 'Q' },
                { 'Q', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', 'Q', '.' },
                { '.', '.', '.', '.', 'Q', '.', '.', '.' },
                { 'Q', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', 'Q', '.', '.', '.', '.' }
        };

        // boolean isValid = isValidBoard(board);

        // System.out.println(isValid); // Output: true
        // System.out.println(boardToBinary(board));
        // System.out.println(createRandomArrangement());
        // for (int i = 0; i < 10; i++) {
        // System.out.println(calculateFitness(createRandomArrangement()));

        // }

        // String arrangement = "011000100110000111000101";
        // // System.out.println(flipRandomBit(arrangement));
        // System.out.println(calculateFitness(arrangement));

        int iterations = 1000;
        String solution = solve(iterations);
        System.out.println("Solution: " + solution);
        System.out.println("Fitness: " + calculateFitness(solution));

    }
}
