public class question5 {

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

    public static void main(String[] args) {
        String sol = "011000100110000111000101";
        System.out.println(calculateFitness(sol));
    }

}
