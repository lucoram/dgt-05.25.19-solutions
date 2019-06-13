package veryeasy;

/**
 * ... Ato amin'ny Codesignal rehefa manao "tourneys" na "mini-marathon" dia
 * mahazo vola 2000 coins ianao raha mivoaka mpandresy, 1000 kosa raha faharoa,
 * 500 raha fahatelo dia mizara roa ohatr'izay hatrany hatrany ny vola azo
 * arakaraky ny laharana misy anao. Firy ary ny vola azonao raha fantatra ny
 * laharanao rehefa avy nilalao tourney iray ianao?
 *
 * Fanamarihana Avadio ho "integer" ambany ("floor") ny valiny raha toa ka lasa
 * "float" => ohatra raha 62.5 no tokony ho valiny dia boriborio 62
 *
 * @author luco
 */
public class TaskAVolaMarathon {

    /**
     *
     * @param laharana
     * @return 2000*(1/2)^(laharana−1)
     */
    public int volaMarathon(int laharana) {
        return (int) (2000 * (Math.pow(0.5, laharana - 1)));
    }
}
