package NYP_Project;

public class Main {
    public static void main(String[] args) {
        Sirket sirket;
        Ilan ilan;

        User u= new Uye("Murat Can Kargı", "murat", "123", "Üye", 20, "Lise", " ");
        Kontrol.uyeList.add((Uye)u);

        sirket= new Sirket("Apple","apple","123","Şirket","01/04/1976");
        Kontrol.sirketList.add(sirket);
        sirket= new Sirket("Microsoft","microsoft","123","Şirket","04/04/1975");
        Kontrol.sirketList.add(sirket);

        ilan= new Ilan(Kontrol.adKontrol("apple"), "Java Back-end Developer", "Alanında 3 yıl deneyimli Java diline hakim elaman arıyoruz.");
        Kontrol.ilanList.add(ilan);
        ilan= new Ilan(Kontrol.adKontrol("microsoft"), "JS Developer", "Alanında 1 yıl JS diline hakim elaman arıyoruz.");
        Kontrol.ilanList.add(ilan);

        OrnekData.run();

        Login login = new Login();
    }
}
