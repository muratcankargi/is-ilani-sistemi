package NYP_Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class OrnekData {
    static void run() {
        Uye u;
        Sirket s;
        Ilan iln;
        String[] uye;
        String[] sirket;
        String[] ilan;
        Kontrol kontrol = new Kontrol();
        int j;
        int i = 1;
        try {
            FileReader fileReader = new FileReader("src/NYP_Project/data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                j = 0;
                if (i <= 10) {
                    uye = line.split(", ");
                    u = new Uye(uye[j++], uye[j++], uye[j++], uye[j++], Integer.parseInt(uye[j++]), uye[j++], uye[j]);
                    Kontrol.uyeList.add(u);
                    Arrays.fill(uye, null);
                } else if (i <= 25) {
                    sirket = line.split(", ");
                    s = new Sirket(sirket[j++], sirket[j++], sirket[j++], sirket[j++], sirket[j]);
                    Kontrol.sirketList.add(s);
                    Arrays.fill(sirket, null);
                } else if (i <= 50) {
                    ilan = line.split(", ");
                    iln = new Ilan(ilan[j++], ilan[j++], ilan[j]);
                    Kontrol.ilanList.add(iln);
                    Arrays.fill(ilan, null);
                }
                i++;
                line = bufferedReader.readLine();
            }

            kontrol.toString();
        } catch (IOException io) {
            //System.out.println(io.getMessage());
        }
    }


}
