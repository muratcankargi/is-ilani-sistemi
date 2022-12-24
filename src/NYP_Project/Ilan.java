package NYP_Project;

public class Ilan {
    private int ilanId;
    private String baslik;
    private String aciklama;
    private String sirketAdi;


    public Ilan(String sirketAdi, String baslik,String aciklama) {
        this.ilanId=Kontrol.id++;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.sirketAdi=sirketAdi;
    }

    public int getIlanId() {
        return ilanId;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }
    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getSirketAdi() {
        return sirketAdi;
    }

    public void setSirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
    }
}
