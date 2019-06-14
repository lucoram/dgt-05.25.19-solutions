package hard;

import java.util.Arrays;

/**
 * ... Avy nandeha nitsangatsangana tany Ankaratra ianao dia andeha hody eny
 * Ambohitrimanjaka ka mila miandry bus eo 67ha. Loza fa voaendaka teo nefa
 * ianao ka very tamin'izany ny phone nangalanao sary tany ampitsangatsanganana.
 * Soa ihany fa tsy very ny smartwatch nanaovanao fa takon'ny tanan'ankajo. Ny
 * Smartwatch-nao izay misy "altimètre" ni-"enregistrer" ny danga (altitude)
 * an'ny tendro (sommets) sy lohasaha (vallées) rehetra nolalovanao tany
 * Ankaratra. Ny hany mba fahatsiarovanao tany Ankaratra izany sisa dia ireo
 * danga ireo, ary tianao ho avoaka avy amin'ireo ny sarin'ny tendrombohitra
 * nolalovanao ka te hanoratra programa mamoaka izany sary zany ianao.
 *
 * Toa izao ny mombamomba ilay programa:
 *
 * INPUT
 * - dangaRehetra: tabilaona "integer" misy ny danga rehetra izay
 * no-enregistern'ny smartwatch-nao.
 * 
 * OUTPUT
 * - "string" izay raha soratana mifampitaingina dia manoma sarina tendrombohitra
 * (jereo ny ohatra). 
 * --> ➚ (hex code 0x279a) no ampiasaina ho an'ny fiakarana,
 * --> ary ➘ (hex code 0x2798) ho an'ny fidinana raha miainga avany an-kavia
 * makany ankavanana 
 * --> [space] kosa no ampiasaina ho an'ny "espace vide" 
 * --> Tsy tokony hisy hifandipatra ny sarin'ilay tendrombohitra fa mirindra tsara,
 * izany hoe tsy tokony hisy ➚ sy ➘ mifampitaingina. 
 * --> Raiso ho "altitude" 0 ny
 * "base"-ny tendrombohitra.
 * 
 * @author luco
 */
public class TaskKAnkaratra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaskKAnkaratra a = new TaskKAnkaratra();

        a.print(a.ankaratra(new int[]{2, 1, 5, 3, 4}));
        a.print(a.ankaratra(new int[]{3, 1, 6, 4, 6, 5, 9}));
        a.print(a.ankaratra(new int[]{1}));
        a.print(a.ankaratra(new int[]{5}));
        a.print(a.ankaratra(new int[]{4, 2, 4}));
        a.print(a.ankaratra(new int[]{12, 10, 15, 5, 20, 17, 35, 2, 10}));
        a.print(a.ankaratra(new int[]{100, 55, 80, 44, 90, 77, 100}));
        a.print(a.ankaratra(new int[]{202, 101, 555, 327, 405}));
    }

    private final static char UP_HILL_CHAR = 0x279a;
    private final static char DOWN_HILL_CHAR = 0x2798;

    private int height;
    private int width;
    private boolean goingUpHill;

    public String[] ankaratra(int[] dangaRehetra) {

        calculateHeightAndWidth(dangaRehetra);

        char[][] resultCharMatrix = new char[++height][++width];

        for (char[] row : resultCharMatrix) {
            Arrays.fill(row, ' ');
        }

        buildCenters(dangaRehetra, resultCharMatrix);
        buildBase(resultCharMatrix);
        buildFlanks(resultCharMatrix);

        String[] result = new String[height];

        for (int i = 0; i < height; i++) {
            result[i] = new String(resultCharMatrix[i]);
        }
        return result;
    }

    private void calculateHeightAndWidth(int[] dangaRehetra) {
        int length = dangaRehetra.length;
        height = 0;
        width = length;

        for (int i = 0; i < length; i++) {

            int k = dangaRehetra[i];
            height = Math.max(height, k);

            if (i == length - 1) {
                width += Math.abs(k - 0);
            }

            if (i == 0) {
                width += Math.abs(k - 0);
                continue;
            }

            width += Math.abs(k - dangaRehetra[i - 1]);
        }
    }

    private void buildCenters(int[] dangaRehetra, char[][] resultCharMatrix) {

        goingUpHill = true;

        int currentHeight = 0;
        int colIndex = -1;

        for (int altitude : dangaRehetra) {
            while (currentHeight != altitude + 1) {
                currentHeight += goingUpHill ? 1 : -1;
                colIndex += 1;
            }
            int rowIndex = height - currentHeight;
            while (rowIndex < height) {
                resultCharMatrix[rowIndex][colIndex] = goingUpHill ? UP_HILL_CHAR : DOWN_HILL_CHAR;
                resultCharMatrix[rowIndex++][colIndex + 1] = goingUpHill ? DOWN_HILL_CHAR : UP_HILL_CHAR;
            }
            colIndex += 1;
            goingUpHill = !goingUpHill;
        }
    }

    private void buildBase(char[][] resultCharMatrix) {

        goingUpHill = true;

        for (int i = 0; i < width; i++) {
            switch (resultCharMatrix[height - 1][i]) {
                case UP_HILL_CHAR:
                    goingUpHill = true;
                    break;
                case DOWN_HILL_CHAR:
                    goingUpHill = false;
                    break;
                default:
                    resultCharMatrix[height - 1][i] = goingUpHill ? UP_HILL_CHAR : DOWN_HILL_CHAR;
                    break;
            }
        }
    }

    private void buildFlanks(char[][] resultCharMatrix) {
        for (int r = height - 2; r >= 0; r--) {
            for (int c = 1; c < width - 1; c++) {
                if (resultCharMatrix[r + 1][c - 1] == UP_HILL_CHAR
                        && resultCharMatrix[r + 1][c + 1] != DOWN_HILL_CHAR
                        && resultCharMatrix[r + 1][c] == UP_HILL_CHAR) {
                    resultCharMatrix[r][c] = UP_HILL_CHAR;
                } else if (resultCharMatrix[r + 1][c + 1] == DOWN_HILL_CHAR
                        && resultCharMatrix[r + 1][c - 1] != UP_HILL_CHAR
                        && resultCharMatrix[r][c - 1] != ' ') {
                    resultCharMatrix[r][c] = DOWN_HILL_CHAR;
                }
            }
        }
    }

    private void print(String[] lines) {
        System.out.println("---------------------------------");
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
