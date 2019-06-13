package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * ... Anisan'ny tena fahalementsika malagasy ity fotoan-gasy ity. Tsy misy tsy
 * manao azy, eny na ianao aza. Misy namanao iray izay mifanao fotoana matetika
 * aminao, ary tara foana izy indraindray ary ndraindray koa anefa ianao no tara
 * foana. Tsy mety miara tonga arak'ilay fotoana nomenao azy foana ianareo dia
 * lasa miteraka disadisa aminareo. Mba hitsaharan'izany disadisa momban'ny
 * fotoana izany dia te hanamboatra programa ianao izay manome ny ora
 * tokony ahatongavan'ilay namanao amin'ny fotoananareo manaraka, raha toa ka
 * omena ny ora tena izy sy ny "historiquen"-ny fotoana rehetra nifanaovanareo
 * taloha. Izany hoe miankina amin'ny fandinihina ireo fotoana taloha no
 * hanemboranao na handrosoanao ilay oran'ny fotoana manaraka amin'izay tonga
 * amin'ny ora tena izy ilay namanao.
 *
 * INPUTS 
 * - `fotoanaNifanaovanaRehetra` : tabilaona tabilaona "string"
 * `string[n][2]` mitahiry ny fotoana rehetra nifanaovanareo taloha, ary ny iray
 * amin'izany dia miendrika toy izao `["HH:mm", "HH:mm"]`. Ny ao amin'ny indice
 * `[0]` ny fotoana nifanaovanareo ary ao amin'ny indice `[1]` ny ora
 * nahatongavany. 
 * - `fotoanaManaraka` : "string" miendrika `"HH:mm"` mitahiry ny
 * ora tianao ahatongavan'ilay namanao amin'ny fotoananareo manaraka.
 *
 * OUTPUT 
 * - "string" miendrika `"HH:mm"` milaza ny ora "lainga" teneninao
 * amin'ilay namanao mba ahatongavany amin'ilay ora tena izy. Raha tara lava izy
 * dia aroso ny ora lazaina aminy, raha tonga aloha lava izy dia ahembotrao ilay
 * ora lazaina aminy. Boriborio ho isaky ny 5 minitra ny valin'ny programanao,
 * ohatra hoe raha eo anelanelan'ny `"08:58"` sy `"09:02"` no valiny dia
 * boriborio ho `"09:00"` izany, raha `"08:57"` dia boriboriana ho `"08:55"` ary
 * raha `"09:03"` dia boriboriana ho `"09:05"`.
 *
 * OHATRA 
 * - raha `fotoanaNifanaovana = [["09:00","10:00"], ["08:00","09:00"],
 * ["06:30","07:30"], ["07:00","08:00"]]` ary `fotoanaManaraka = "08:15"` dia
 * `"07:15"` no valiny. Eto zao hita mazava fa tara adin'ny iray foana ilay
 * namanao tamin'ny fotoananareo taloha, ka mba ahatongavany amin'ny ora tsy
 * diso ("08:15") amin'ny fotoanareo manaraka dia arosonao adin'ny iray ny
 * fotoana lazaina azy ("07:15"). Raha ny mifamadika amin'izay no nitranga dia
 * ahembotrao adin'ny iray ny fotoana lazaina azy (Test 2) 
 * - raha `fotoanaNifanaovana = [["11:00","11:05"]]` ary `fotoanaManaraka = "08:15"`
 * dia aroso amin'ny `"08:10"` ny fotoana lazaina azy satria tara 5 minitra izy
 * teo aloha.
 *
 * @author luco
 */
public class TaskGFotoanGasy {

    /**
     * fotoanGasy => rendezVous
     * 
     * @param fotoanaNifanaovana // pastRendezVousHistory
     * @param fotoanaManaraka // nextRendezVousTime
     * @return 
     */
    public String fotoanGasy(String[][] fotoanaNifanaovana, String fotoanaManaraka) {

        String[][] inputVariables = regularizeInputs(fotoanaNifanaovana);

        Integer[] independentVariables = extractInputVariables(inputVariables, 0);
        Integer[] dependentVariables = extractInputVariables(inputVariables, 1);

        double[] regLinCoeeffs = singleLinearRegressor(independentVariables, dependentVariables);
        int fotoanaManarakaToMillis = HHmmToMinutes(fotoanaManaraka);

        int newFotoanaManaraka = (int) (regLinCoeeffs[0] + regLinCoeeffs[1] * fotoanaManarakaToMillis);
        boolean ahembotra = fotoanaManarakaToMillis > newFotoanaManaraka;

        int diff = Math.abs(fotoanaManarakaToMillis - newFotoanaManaraka);

        newFotoanaManaraka = fotoanaManarakaToMillis + (ahembotra ? diff : -diff);

        return minutesToHHmm(newFotoanaManaraka);
    }

    private double[] singleLinearRegressor(Integer[] independentVariablesX, Integer[] dependentVariablesY) {
        double meanX = mean(independentVariablesX);
        double meanY = mean(dependentVariablesY);
        double N = 0;
        double D = 0;

        for (int i = 0; i < independentVariablesX.length; i++) {
            double c = independentVariablesX[i] - meanX;
            N += (c * (dependentVariablesY[i] - meanY));
            D += (c * c);
        }

        double B1 = N / D;
        double B0 = meanY - B1 * meanX;

        return new double[]{B0, B1};
    }

    private double mean(Integer[] a) {
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        return (double) sum / a.length;
    }

    private Integer[] extractInputVariables(String[][] fotoanaNifanaovana, int variableIndex) {
        int instancesNumber = fotoanaNifanaovana.length;
        Integer[] inputVariables = new Integer[instancesNumber];

        for (int i = 0; i < instancesNumber; i++) {
            inputVariables[i] = HHmmToMinutes(fotoanaNifanaovana[i][variableIndex]);
        }

        return inputVariables;
    }

    private String[][] regularizeInputs(String[][] fotoanaNifanaovana) {

        fotoanaNifanaovana = extendInputs(fotoanaNifanaovana);
        fotoanaNifanaovana = normalizeInputs(fotoanaNifanaovana);

        return fotoanaNifanaovana;
    }

    private String[][] extendInputs(String[][] fotoanaNifanaovana) {
        int inputLength = fotoanaNifanaovana.length;
        if (inputLength < 100) {
            Map<Scheddule, Integer> occurences = new HashMap<>();

            for (String[] input : fotoanaNifanaovana) {
                int independent = HHmmToMinutes(input[0]);
                int dependent = HHmmToMinutes(input[1]);

                Scheddule scheddule = new Scheddule(independent, dependent);

                occurences.put(scheddule, occurences.containsKey(scheddule) ? occurences.get(scheddule) + 1 : 1);
            }

            int totalInputs = 0;

            for (Scheddule s : occurences.keySet()) {
                int currentTotal = occurences.get(s) * 100 / inputLength;
                occurences.put(s, currentTotal);
                totalInputs += currentTotal;
            }

            String[][] newInput = new String[totalInputs][2];

            for (int i = 0; i < totalInputs;) {
                for (Scheddule s : occurences.keySet()) {
                    int currentOccs = occurences.get(s);
                    while (currentOccs-- > 0) {
                        newInput[i][0] = minutesToHHmm(s.expected);
                        newInput[i++][1] = minutesToHHmm(s.real);
                    }
                }
            }

            return newInput;
        }
        return fotoanaNifanaovana;
    }

    private String[][] normalizeInputs(String[][] fotoanaNifanaovana) {
        Map<Integer, Boolean> expectedMap = new HashMap<>();
        Map<Integer, Boolean> realMap = new HashMap<>();

        for (int i = 0; i < fotoanaNifanaovana.length; i++) {
            int independent = HHmmToMinutes(fotoanaNifanaovana[i][0]);
            int dependent = HHmmToMinutes(fotoanaNifanaovana[i][1]);

            while (expectedMap.containsKey(independent) || realMap.containsKey(dependent)) {
                independent++;
                dependent++;
            }

            expectedMap.put(independent, true);
            realMap.put(dependent, true);

            fotoanaNifanaovana[i][0] = minutesToHHmmNoRound(independent);
            fotoanaNifanaovana[i][1] = minutesToHHmmNoRound(dependent);
        }

        return fotoanaNifanaovana;
    }

    private String minutesToHHmmNoRound(int minutes) {
        int hours = (int) (minutes / 60);
        minutes %= 60;
        return addLeadingZeros(hours).concat(":").concat(addLeadingZeros(minutes));
    }

    private int HHmmToMinutes(String timeString) {
        String[] splittedTime = timeString.split(":");
        int hours = parseInt(splittedTime[0]);
        int minutes = parseInt(splittedTime[1]);

        return hours * 60 + minutes;
    }

    private int parseInt(String value) {
        return Integer.parseInt(value);
    }

    private String minutesToHHmm(int minutes) {
        int hours = (int) (minutes / 60);
        minutes %= 60;

        minutes = roundToMultipleOf5(minutes);

        return addLeadingZeros(hours).concat(":").concat(addLeadingZeros(minutes));
    }

    private String addLeadingZeros(int number) {
        return String.format("%02d", number);
    }

    private int roundToMultipleOf5(int minutes) {
        int remainder = minutes % 5;
        return minutes + (remainder > 2 ? 5 - remainder : -remainder);
    }
}

class Scheddule {

    int expected;
    int real;

    public Scheddule(int expected, int real) {
        this.expected = expected;
        this.real = real;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.expected;
        hash = 29 * hash + this.real;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Scheddule other = (Scheddule) obj;
        if (this.expected != other.expected) {
            return false;
        }
        return this.real == other.real;
    }
}
