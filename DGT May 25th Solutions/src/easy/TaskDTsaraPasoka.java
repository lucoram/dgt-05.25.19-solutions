package easy;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * ... Alohan'ny hilalaovan'i Lita sy Bozy batay dia mila hamarinina tsara hoe
 * tsara pasoka ve ny karatra hilalaovan-dry zareo amin'izay mba tsy tonga dia
 * hita mazava be am-boalohany hoe iza no haresy.
 * Mamoròna programa ary manamarina ny fahatsaran'ny pasoky ny karatra ka toy
 * izao ny momba izany. Nomena ny lisitry ny karatra avy nopasohana ka avohay
 * totalin'ny isan'ny fifanelanelanana sy ny fifamadihama amin'ny karatra mitovy
 * loko.
 *
 * INPUT 
 * - `karatraVoapasoka` : "string" misy ny karatra rehetra avy nopasohana
 * miendrika toy izao `"AP/RC/7K/2T"` => izany hoe "As de Pique" no karatra eo
 * ambony indrindra, avy eo "Roi de Coeur", manaraka azy "7 de Carreau" ary
 * farany "2 de Treffle".
 *
 * FANAMARIHINA 
 * - Midika toy izao ny loko: `P` = Pique, `C` = Coeur, `K` = Carreau,
 * `T` = Treffle 
 * - Ary midika toy izao ny isa ambonin'ny 10: `A` = As, `R` = Roi, `D` = Dame,
 * `V` = Valet. 
 * - `/` kosa dia mpanelanelana ireo karatra fotsiny ihany 
 * - Ny filaharana "Bridge" no filaharan'ny karatra ka ny "As" no
 * ambony indrindra ary 2 no ambany indrindra. 
 * - Tsy misy dikany ny loko amin'ny "Bataille" ankoatr'ny 
 * fanavahana ireo karatra fotsiny ihany.
 *
 * OUTPUT 
 * - tabilaona `int[2]` miendrika `[x, y]` => `x` = totalin'ny isan'ny
 * elanelan'ny karatra mitovy loko rehetra, `y` => totalin'ny fifamadihan'ny
 * karatra mitovy loko (Ao amin'ny ohatra mazava kokoa)
 *
 * OHATRA 
 * - raha `karatraVoapasoka = "AP/AK/7C/7P/RK/8C/RP/8K"` dia `[10, 5]` no
 * valiny. Fanazavana ("Ouvrir l'image dans un nouvel raha tsy hita mazava
 * tsara") ![Test 1](https://i.postimg.cc/1XzHySwb/Test-1.png "Test 1")
 *
 * - raha `karatraVoapasoka = "AP/7C/7P/RK/AK/8C/RP/8K"` dia `[9, 4]` no valiny
 * Fanazavana ![Test 2](https://i.postimg.cc/4yqs5J5g/Test-2.png "Test 2")
 *
 * @author luco
 */
public class TaskDTsaraPasoka {

    private static final Map<Character, Integer> SUIT_IDX = new HashMap<>();
    private static final Map<Character, Integer> C_VALUES = new HashMap<>();

    static {
        SUIT_IDX.put('P', 0);
        SUIT_IDX.put('C', 1);
        SUIT_IDX.put('K', 2);
        SUIT_IDX.put('T', 3);

        C_VALUES.put('V', 11);
        C_VALUES.put('D', 12);
        C_VALUES.put('R', 13);
        C_VALUES.put('A', 14);
    }

    public int[] tsaraPasoka(String karatraVoapasoka) {

        String[] cards = karatraVoapasoka.split("/");

        int totalDistance = calcTotalDistForAllSuits(cards);
        int totalPermutation = calcTotalPermForAllSuits(cards);

        return new int[]{totalDistance, totalPermutation};
    }

    private int calcTotalDistForAllSuits(String[] cards) {
        int[] lastIndexes = {-1, -1, -1, -1};
        int[] totalDistances = {0, 0, 0, 0};

        for (int i = 0; i < cards.length; i++) {
            int suitIdx = SUIT_IDX.get(extractSuit(cards, i));
            if (lastIndexes[suitIdx] != -1) {
                totalDistances[suitIdx] += i - lastIndexes[suitIdx] - 1;
            }
            lastIndexes[suitIdx] = i;
        }

        return IntStream.of(totalDistances).sum();
    }

    private int calcTotalPermForAllSuits(String[] cards) {
        int totalPermutation = 0;

        for (char key : SUIT_IDX.keySet()) {
            totalPermutation += calcTotalPermForSuit(cards, key);
        }

        return totalPermutation;
    }

    private int calcTotalPermForSuit(String[] cards, char suit) {
        int totalPermForSuit = 0;
        for (int i = 0; i < cards.length - 1; i++) {
            char currentSuit = extractSuit(cards, i);

            if (currentSuit != suit) {
                continue;
            }

            int currentValue = extractCardValue(cards[i], suit);

            for (int j = i + 1; j < cards.length; j++) {
                char nextSuit = extractSuit(cards, j);

                if (nextSuit != suit) {
                    continue;
                }

                int nextValue = extractCardValue(cards[j], suit);
                totalPermForSuit += nextValue < currentValue ? 1 : 0;
            }
        }
        return totalPermForSuit;
    }

    private int extractCardValue(String card, char suit) {
        String stringValue = card.replace(String.valueOf(suit), "");
        if (stringValue.matches("[a-zA-Z]+")) {
            return C_VALUES.get(stringValue.charAt(0));
        }
        return Integer.parseInt(stringValue);
    }

    private char extractSuit(String[] cards, int index) {
        return cards[index].charAt(cards[index].length() - 1);
    }
}
