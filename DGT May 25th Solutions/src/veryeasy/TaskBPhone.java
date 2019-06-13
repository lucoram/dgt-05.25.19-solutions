package veryeasy;

/**
 * ... Tamin'ianao very phone teny 67ha dia nanapak'hevitra ny hividy vaovao
 * ianao. Ny phone hitanao fa tsara ka tianao hovidiana dia izay be mpividy
 * indrindra. Ka nomena ary ny lisitry ny phone anaranNyPhone misy any an-tsena
 * sy ny isan'ny mpividy isanNyMpividy azy ireo tsirairay avy, manorata programa
 * misafidy ny phone mety aminao.
 *
 * Ohatra
 *
 * raha anaranNyPhone = ["Samsung", "Nokia", "Lenovo", "Asus", "iPhone", "Acer"]
 * ary isanNyNividy = [10, 15, 26, 25, 31, 12] dia "iPhone" no valiny satria io
 * no be mpividy indrindra, 31 no nividy azy.
 *
 * @author luco
 */
public class TaskBPhone {

    /**
     * 
     * @param anaranNyPhone
     * @param isanNyNividy
     * @return selectedPhoneName
     */
    public String phone(String[] anaranNyPhone, int[] isanNyNividy) {
        String selectedPhoneName = "";
        int maxPhoneBuyers = 0;
        for (int i = 0; i < anaranNyPhone.length; i++) {
            if (maxPhoneBuyers < isanNyNividy[i]) {
                maxPhoneBuyers = isanNyNividy[i];
                selectedPhoneName = anaranNyPhone[i];
            }
        }
        return selectedPhoneName;
    }
}
