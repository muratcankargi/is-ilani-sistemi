package NYP_Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class fileKontrol {
    static void run() {
        String[] uye = new String[8];
        String[] sirket = new String[6];
        String[] ilan= new String[4];
        Kontrol kontrol = new Kontrol();
        int j = 0;
        int i = 1;
        try {
            FileReader fileReader = new FileReader("src/NYP_Project/data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                j=0;
                if (i <= 10) {
                    uye = line.split(", ");
                    Kontrol.uyeEkle(uye[j++], uye[j++], uye[j++], uye[j++], Integer.parseInt(uye[j++]), uye[j++], uye[j]);
                    Arrays.fill(uye, null);
                } else if (i <= 25) {
                    sirket = line.split(", ");
                    Kontrol.sirketEkle(sirket[j++], sirket[j++], sirket[j++], sirket[j++], sirket[j]);
                    Arrays.fill(sirket, null);
                }
                else if(i<=50){
                    ilan = line.split(", ");
                    Kontrol.ilanEkle(ilan[j++],ilan[j++],ilan[j]);
                    Arrays.fill(ilan, null);
                }
                i++;
                line = bufferedReader.readLine();
            }
            kontrol.toString();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }


}
