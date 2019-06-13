package easy;

import java.text.DecimalFormat;

/**
 * ... Saika diso ianao tamin'ilay hividy
 * phone satria tadidinao tampoka fa tsy ny isan'ny mpividy no tena milaza ny
 * hatsaran'ny phone iray fa ny ratings omen'ireo mpividy azy. Ny "rating" dia 
 * karazana "note" omen'ny tompon'ilay phone taorian'ny nividianany ka
 * nampiasany azy. Toy izao ny dikan'ny rating tsirairay: 
 * - 5 -> tena tsara, tena afapo tanteraka ilay nividy ilay phone
 * - 4 -> tsara, mahafapo ihany ilay phone 
 * - 3 -> antonony, mahavita izay ilana azy ilay phone
 * - 2 -> tsy tsara, tsy afapo ilay nividy azy 
 * - 1 -> tena ratsy, "hialao lavitra raha tsy te hanenina"
 *
 * Nangoninao ary ny "données" misy ny ratings nomen'ny olona nividy ireo phone
 * misy eny an-tsena tsirairay (`ratings`), ka araky ny valin'ny
 * "analyse"-n'ireo "données" ireo, safidio izay phone tena mety aminao, izany
 * hoe: 
 * - izay tsara "moyenne"-na rating indrindra 
 * - raha misy mitovy rating dia izay be mpividy indrindra 
 * - raha sady mitovy rating no mitovy isan'ny mpividy dia izay voalohany
 * amin'ny laharana eo fotsiny
 *
 * INPUTS
 * - `anatanNyPhone` : tabilao misy ny anaran'ireo phones rehetra
 * any amin'ny magazay 
 * - `ratings` : tabilaona tabilaona "integer" miendrika `int[n][5]` ka:
 *        -- ny ratings-ny phone `anaranNyPhone[i]` dia ao amin'ny
 *          `ratings[i]` no misy azy ary: 
 *           --- `ratings[i][0]` => isan'ny mpividy nanome note 5/5 
 *           --- `ratings[i][1]` => ------//------ 4/5
 *              .
 *              .
 *              .
 *           --- `ratings[i][4]` => ------//------ 1/5
 *
 *
 * OUTPUT
 * Ny anaran'ny phone mety aminao
 *
 * OHATRA 
 * - Ao amin'ny Test 1 izao dia `3.9` no "moyenne"-ny rating
 * an'ny `Asus` ary izany no ambony indrindra ka izy no voasafidy. 
 * - Ao amin'ny Test 2 dia mitovy rating i `Lenovo` sy `iPhone` saingy
 * `iPhone` no be mpividy (`16` nividy iPhone ary `15` nividy Lenovo) 
 * dia izy no voasafidy.
 * - Ao amin'ny Test 3 dia mitovy rating sy isana mpividy `Lenovo` sy
 * `iPhone` ka `Lenovo` izany no voasafidy satria izy no ao alohan'ny `iPhone`
 * ao anaty lisitra.
 *
 * FANAMARIHINA 
 * Boriborio miakatra (Round Half Up) amina "one decimal
 * place" ny "moyenne"-n'ny rating an'ny phone tsirairay alohan'ny hampitahana
 * azy. Ohatra, raha nahazo rating `3.8888` sy `3.9112` ohatra ny phone roa dia
 * samy boriboriana ho `3.9` daholo izany ka lasa mitovy.
 *
 * @author luco
 */
public class TaskCPhoneRatings {

    /**
     * 
     * @param anaranNyPhone
     * @param ratings
     * @return phone with the highest rating average or the most buyers or the least index
     */
    public String phoneRating(String[] anaranNyPhone, int[][] ratings) {
        int indexOfMostValued = 0;
        double maxAverage = 0;
        double maxBuyers = 0;
        int length = anaranNyPhone.length;

        for (int i = 0; i < length; i++) {
            double[] currentAvgAndTotalBuyers = calculateAvgAndTotalBuyers(ratings[i]);

            if (maxAverage < currentAvgAndTotalBuyers[0] || maxAverage == currentAvgAndTotalBuyers[0] && maxBuyers < currentAvgAndTotalBuyers[1]) {
                maxAverage = currentAvgAndTotalBuyers[0];
                maxBuyers = currentAvgAndTotalBuyers[1];
                indexOfMostValued = i;
            }
        }

        return anaranNyPhone[indexOfMostValued];
    }

    private double[] calculateAvgAndTotalBuyers(int[] rating) {
        double denominator = 0;
        double numerator = 0;
        double[] averageAndTotalBuyers = new double[2];
        DecimalFormat df = new DecimalFormat("#.#");

        for (int i = 0; i < 5; i++) {
            denominator += (double) rating[i];
            numerator += (double) rating[i] * (5 - i);
        }

        if (denominator > 0) {
            averageAndTotalBuyers[0] = Double.parseDouble(df.format(numerator / denominator));
            averageAndTotalBuyers[1] = denominator;
        }
        return averageAndTotalBuyers;
    }
    
}
