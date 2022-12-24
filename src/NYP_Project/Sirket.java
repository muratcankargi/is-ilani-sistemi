package NYP_Project;

public class Sirket extends User{
    private String kurulusTarihi;
    public Sirket(String name, String username, String password, String userType, String tarih)  {
        super(name, username, password, userType);
        this.kurulusTarihi =tarih;

    }

    public String getKurulusTarihi() {
        return kurulusTarihi.toString();
    }

}
