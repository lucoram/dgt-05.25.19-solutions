package hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * ... Vakansy ny andro ka mijanona fotsiny ao an-trano ny zandrinao roa, i Lita
 * sy Bozy. Nanapaka hevitra izy ireo ny hilalao Batay ("Bataille") ka toy izao
 * ny fandehany:
 *
 * Pasoana ny karatra 52 ary zaraina roa (tsy voatery mitovy isa) ka ny manana
 * ampahany iray avy izy mianadahy. Alohan'ny hanomboan'ny lalao dia manao
 * ankitsapaka izy hoe iza no hanomboka azy. Manomboka ary ny lalao ka
 * ataontsika hoe "Lita" no nanomboka azy, mametraka ny karatra iray eo amin'ny
 * farany ambony eny @ tànany i "Lita" ary mametraka karatra eo ambonin'iny i
 * "Bozy": 
 * -> raha ny karatr'i Lita no ambony dia alain'i Lita ireo ary
 * ampidiriny amin'ny farany ambanin'ny karatra rehetra eny an-tànany ka mbola i
 * Lita ihany no mandefa manaraka. Torak'izay koa raha I Bozy no nanana karatra
 * ambonin'ny an'i Lita, dia izy no maka azy ary sady mbola mandefa avy eo. 
 * -> raha mitovy ny karatr'izy dahy (samy As ohatra) dia izay no antsoina hoe
 * "bataille" ka mametraka karatra miafina iray avy izy mianadahy ary manampy
 * karatra iray avy indray avy eo dia izay ambony no maka azy rehetra, dia
 * ohatr'izay hatrany hatrany raha mbola mitovy foana ny karatra mivoaka eo.
 * rehefa lany tanteraka ny karatra rehetra eny am-pelatanan'ny iray amin'izy
 * dahy dia resy izy ary ny iray nahazo ny karatra rehetra no maharesy, tapitra
 * ny lalao. 
 * 
 * Leo be anefa izy mianadahy satria mety aharitra adin'ny iray sy
 * sasany ny lalao iray (ary mety tsy hifarana mihitsy aza indraindray) vao misy
 * mpandresy, nanapak'hevitra ary ianao zokiny fa hanamboatra programa mba
 * ahafahan'izy ireo tonga dia mamantatra avy hatrany izay mandresy rehefa
 * voapasoka sy voazara ny karatra ary fantatra hoe iza no manomboka ny lalao.
 *
 * INPUTS
 * - tabilaona teny karatra[] -> misy ny karatr'i Lita ny karatra[0] ary ny an'i
 * Bozy ny karatra[1] miendrika toy izao ohatra ["AP/7C/RK/8K", "8C/AK/7P/RP"]
 * - anaran'izay manomboka manomboka -> "Lita" na "Bozy" 
 * 
 * OUTPUT
 * Teny misy ny anaran'izay nandresy sy ny hoe impiry nihodina ny karatr'izay nandresy,
 * impiry nisy "Bataille", ary impiry nandefa karatra tsirairay. Miendrika toy
 * izao: "Lita/15/40/600" => midika hoe "I Lita no nandresy, nihodina in-15 ny
 * karany, nisy "bataille" 40 ny lalao ary in-600 nandefa karatra izy".
 * 
 * FANAMARIHANA 
 * - Ny filaharana "bridge" no filahatry ny tanjaky ny karatra, izany hoe As > Roi
 * > Dame > Valet ... 3 > 2 A = As, R = Roi, D = Dame, V = Valet P = Pique, C =
 * Coeur, K = Carreau, T = Treffle 
 * - Tsy misy dikany ny lokon'ny karatra amin'ny
 * "bataille", fa rehefa mitovy fotsiny ny "valeur"-n'ilay karatra dia mitovy
 * tanjaka izy izay, ohatra hoe samy 7 fa ny iray coeur ary ny iray pique. 
 * - Ny fihodinana iray dia midika fa efa niverina tamin'ny karatra nalefany
 * voalohany ny karatra nalefany (raha azony ilay voalohany) na niverina
 * tamin'izay karatra azony voalohany. 
 * 
 * OHATRA
 * - raha karatra = ["AP/7C/RK/8K", "8C/AK/7P/RP"] ary manoboka = "Lita" dia
 * "Bozy/2/2/11" no valiny. Fanahiniana natao 4 avy fotsiny
 * ny karant'izy dahy eto mba ahafohy ny fanazavana.
 * - raha karatra = ["AP/7C/RK/8K", "8C/AK/7P/RP"] ary manoboka = "Bozy" dia "Lita/1/2/9" no
 * valiny. Ato ny fanazavana.
 *
 * @author luco
 */
public class TaskJBatay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaskJBatay b = new TaskJBatay();

        String[] karatra = {"AP/RK/RP/AK", "8C/7C/7P/8K"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"AP/RK/RP/8K", "8C/7C/7P/AK"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"AP/RK/7C/8K", "8C/RP/7P/AK"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"AP/RK/7C/8K", "8C/AK/7P/RP"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"AP/7C/RK/8K", "8C/AK/7P/RP"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"AP/8C/RK/8K", "7C/AK/7P/RP"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"RK/10C/7P/AC/7K/5K/3K/DT/4P/VT/2T/2C/7C/5C/VK/RT/9K/VP/9T/DK/RC/8C/6K/AK/2P/6P", "10P/5T/2K/10T/7T/AP/6T/8T/VC/RP/4T/5P/9C/4K/DC/4C/DP/3T/8P/6C/AT/9P"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"DP/RK/8C/8K/RT/3P/8T/6T/7P/AP/4P/VK/7T/AT/3T/7K/4T/DC/RC/5C", "4C/4K/2K/VT/5P/VP/7C/5K/2P/RP/5T/8P/9C/9T/10T/AK/VC/9K/10C"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"DP/AP/4C/10P/5T/10K/DC/2C/7T/2T/8T/AC/6P/9T/9C/RK/2P/AT/5P/8P/3P/7P/6C/6T/7K", "3C/10T/VK/VP/RT/10C/3T/9P/5K/3K/9K/RP/RC/DK/AK/5C/DT"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"RC/8C/4C/4K/5P/AP/7T/3K/2K/DK/2P/RT/2T/9T/10P/VT/5T/RP/AT/7C/DP/DC/5K/RK/6C/3T", "9P/6P/DT/8T/7K/10T/6K/AC/9K/10K/AK/9C/5C/4T/2C/VK/VC/3C/3P/7P/4P/6T/8P/10C/8K/VP"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"AP/AT/AK/AC", "DP/RK/8C/8K/RT/3P/8T/6T/7P/4P/VK/7T/3T/7K/4T/DC/RC/5C/4C/4K/2K/VT/5P/VP/7C/5K/2P/RP/5T/8P/9C/9T/10T/VC/9K/10C"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"10K/2P/3C/10P/RT/4C/9T/7T/6K/7C/8K/DP/VT/8T/VK/5T/AC/9C/8P/2C/2T/6P/6C/4T/2K/3P/VC/8C/AT/5K/AK/7K", "9P/RP/RC/5C/3K/3T/AP/10C/4K/7P/10T/4P/DC/DT/RK/6T/VP/5P/DK/9K"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

        karatra = new String[]{"4K/5C/AP/3P/6P/RP/RK/7T/2K/AT/9C/10P/DP/4T", "8T/AC/2P/4C/DT/VP/RC/7P/6C/2T/7K/9T/VK/3C/5P/10T/3K/DC/5T/10C/8K/2C/3T/7C/10K/9K/8P/DK/VC/9P/8C/RT/5K/AK/6T/6K/VT/4P"};
        System.out.println(b.batay(karatra, "Lita") + " | " + b.batay(karatra, "Bozy"));

    }

    private static final Map<Character, Integer> SUIT_IDX = new HashMap<>();
    private static final Map<Character, Integer> C_VALUES = new HashMap<>();
    private static final Queue<Card> TABLE = new LinkedList<>();

    private int nbTurns;
    private int nbBattles;
    private boolean currentlyOnBattle;
    private boolean litaSTurn;

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

    public String batay(String[] karatra, String manomboka) {

        nbTurns = 0;
        nbBattles = 0;
        TABLE.clear();
        currentlyOnBattle = false;

        Player lita = buildPlayer("Lita", karatra[0]);
        Player bozy = buildPlayer("Bozy", karatra[1]);

        litaSTurn = manomboka.equals(lita.name);

        while (!lita.deck.isEmpty() && !bozy.deck.isEmpty()) {

            runTurn(lita, bozy);
        }

        Player winner = lita.deck.isEmpty() ? bozy : lita;
        int roundTrips = winner.nbRoundTrip - 1;

        return winner.name + "/" + (roundTrips < 0 ? 0 : roundTrips) + "/" + nbBattles + "/" + nbTurns;
    }

    private void runTurn(Player lita, Player bozy) {
        nbTurns++;

        if (!currentlyOnBattle) {
            TABLE.clear();
        }

        Card litaSCurrent = lita.deck.poll();
        Card bozySCurrent = bozy.deck.poll();

        updateRoundTrip(lita, litaSCurrent);
        updateRoundTrip(bozy, bozySCurrent);
        updateTable(litaSCurrent, bozySCurrent);

        if (litaSCurrent.value == bozySCurrent.value) {

            nbBattles++;
            nbTurns++;
            currentlyOnBattle = true;
            runBattle(lita, bozy);

        } else if (litaSCurrent.value > bozySCurrent.value) {

            setTurnWinner(lita, litaSCurrent);
        } else {

            setTurnWinner(bozy, bozySCurrent);
        }
    }

    private void runBattle(Player lita, Player bozy) {

        updateRoundTrip(lita);
        updateRoundTrip(bozy);
        updateTable(lita.deck.poll(), bozy.deck.poll());
    }

    private void updateTable(Card litaSCurrent, Card bozySCurrent) {

        if (litaSTurn) {
            TABLE.add(litaSCurrent);
            TABLE.add(bozySCurrent);
        } else {
            TABLE.add(bozySCurrent);
            TABLE.add(litaSCurrent);
        }
    }

    private void updateRoundTrip(Player player) {

        if (!player.deck.isEmpty()) {
            updateRoundTrip(player, player.deck.peek());
        }
    }

    private void updateRoundTrip(Player player, Card playersCurrentCard) {

        if (playersCurrentCard.isPivot()) {
            player.nbRoundTrip++;
        }
    }

    private void setTurnWinner(Player turnWinner, Card winnerSCurrentCard) {

        turnWinner.deck.addAll(TABLE);
        turnWinner.clearPivots();
        
        if (!turnWinner.hasPivot()) {
            winnerSCurrentCard.pivotOwner = turnWinner.name;
        }
        
        litaSTurn = turnWinner.name.equals("Lita");
        currentlyOnBattle = false;
    }

    private static Player buildPlayer(String name, String allCards) {

        Queue<Card> cards = new LinkedList<>();

        String[] individualCards = allCards.split("/");

        for (int i = 0; i < individualCards.length; i++) {
            Card currentCard = buildCard(individualCards[i]);
            if (i == 0) {
                currentCard.pivotOwner = name;
            }
            cards.add(currentCard);
        }

        return new Player(name, cards);
    }

    private static Card buildCard(String cardString) {

        char color = cardString.charAt(cardString.length() - 1);
        int value = extractCardValue(cardString, color);
        return new Card(value, color);
    }

    private static int extractCardValue(String card, char suit) {

        String stringValue = card.replace(String.valueOf(suit), "");
        if (stringValue.matches("[a-zA-Z]+")) {
            return C_VALUES.get(stringValue.charAt(0));
        }
        return Integer.parseInt(stringValue);
    }
}

class Player {

    String name;
    int nbRoundTrip;
    Queue<Card> deck;

    Player(String name, Queue<Card> deck) {
        this.name = name;
        this.deck = deck;
    }

    boolean hasPivot() {
        return deck.stream().anyMatch((c) -> (c.pivotOwner != null && c.pivotOwner.equals(name)));
    }

    void clearPivots() {
        for (Card c : deck) {
            if (c.isPivot() && !c.pivotOwner.equals(name)) {
                c.pivotOwner = null;
            }
        }
    }
}

class Card {

    int value;
    char color;
    String pivotOwner;

    Card(int value, char color) {
        this.value = value;
        this.color = color;
    }

    boolean isPivot() {
        return pivotOwner != null;
    }
}
