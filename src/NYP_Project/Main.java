package NYP_Project;

public class Main {
    public static void main(String[] args) {
        Kontrol.uyeEkle("murat", "murat", "123", "Üye", 20, "Lise", " ");
        Kontrol.sirketEkle("Apple","apple","123","Şirket","01/04/1976");
        Kontrol.sirketEkle("Microsoft","microsoft","123","Şirket","04/04/1975");
        Kontrol.ilanEkle(Kontrol.adKontrol("apple"), "Java Back-end Developer", "Alanında 3 yıl deneyimli Java diline hakim elaman arıyoruz.");
        Kontrol.ilanEkle(Kontrol.adKontrol("microsoft"), "JS Developer", "Alanında 1 yıl JS diline hakim elaman arıyoruz.");
        fileKontrol.run();
        Login login = new Login();
    }
}
