package medium;

/**
 * ... Rehefa avy nanendy mofo gasy tany amin'ny havanao ianao dia nila
 * nivarotra kafe. Loza anefa fa tsy hita ny nasiany ny kaopina kafe rehetra, fa
 * zinga anakiroa mirefy zingaA-kaopina kafe sy zingaB-kaopina kafe ihany no
 * hitanao tao. Ka rehefa hivarotra kafe izany ianao dia tsy maintsy manao izay
 * ahazoana isanaKaopy-kaopina kafe avy amin'ireo zinga ireo. Mety tsy ahazo
 * an'ilay isana kaopy anefa ianao ka lasa manao tomba-maso dia mety ho faty
 * antoka raha mihoatra ny kafe nomenao ilay mpividy. (Ao amin'ny ohatra mazava
 * tsara)
 *
 * INPUTS *
 * zingaA: "integer" misy ny isana kaopy vitan'ny zinga A 
 * zingaB: "integer" misy ny isana kaopy vitan'ny zinga B 
 * isanaKaopy: "integer" isan'ny kaopina kafe tian'ilay mpividy hovidiana 
 * 
 * 
 * OUTPUT 
 * "boolean":
 * false => raha tsy maty antoka ianao satria nahazo ilay isana kaopy Tiana
 * hovidiana 
 * true => raha maty antoka satria nanao tomba-maso 
 * 
 * OHATRA
 * raha zingaA = 3, zingaB = 5, isanaKaopy = 2 dia false ny valiny (tsy maty
 * antoka ianao) Fanazavana Nofenoina ny zingaB, avy eo dia nofenoina avy tao
 * amin'ny zingaB ny zingaA, ka lasa 2 kaopy sisa no tavela ao amin'ny zingaB,
 * ary iny no omena ilay mpividy.
 *
 * raha zingaA = 3, zingaB = 6, isanaKaopy = 2 dia true ny valiny (maty antoka
 * ianao) satria tsy mety ahazoana kaopina kafe 2 ny famenoana sy
 * famindramindrana kafe amin'ireo zinga A sy B
 *
 * raha zingaA = 3, zingaB = 5, isanaKaopy = 4 dia false ny valiny (tsy maty
 * antoka ianao) Fanazavana Naverina in-2 ilay natao tamin'ny ohatra voalohany
 * io (tsy nariana akory ny kafe tao amin'ilay zinga A tamin'ny faharoa fa
 * naverina tao am-bilany ihany)
 *
 * @author luco
 */
public class TaskFKafeMamonoAntoka {

    /**
     *
     * @param zingaA
     * @param zingaB
     * @param isanaKaopy
     * @return true => maty antoka, false => tsy maty antoka
     */
    public boolean kafeMamonoAntoka(int a, int b, int c) {
        int d = b;
        while ((a % d != 0 | b % d != 0) && --d > 0);
        return c % d != 0;
    }

}
