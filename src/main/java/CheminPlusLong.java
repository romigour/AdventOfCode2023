public class CheminPlusLong {
    public static int trouverCheminPlusLong(int[][] carte) {
        int lignes = carte.length;
        int colonnes = carte[0].length;

        int[][] dp = new int[lignes][colonnes];

        // Initialisation des bords de la carte
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                dp[i][j] = 1;
            }
        }

        // Parcours de la carte pour mettre à jour les valeurs
        for (int i = 1; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                // Mise à jour de la valeur maximale parmi les cellules adjacentes
                if (j > 0 && carte[i][j] > carte[i - 1][j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
                }
                if (j < colonnes - 1 && carte[i][j] > carte[i - 1][j + 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j + 1] + 1);
                }
            }
        }

        // Trouver la valeur maximale dans le tableau
        int max = 0;
        for (int i = 0; i < colonnes; i++) {
            max = Math.max(max, dp[lignes - 1][i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] carte = {
                {1, 2, 3},
                {6, 5, 4},
                {7, 8, 9}
        };

        int resultat = trouverCheminPlusLong(carte);
        System.out.println("La longueur du chemin le plus long est : " + resultat);
    }
}
