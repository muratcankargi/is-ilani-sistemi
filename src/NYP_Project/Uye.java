package NYP_Project;

import java.util.ArrayList;

public class Uye extends User {
    private int yas;
    private String egitim;
    private String deneyim;
    ArrayList<Ilan> basvurdugumIlanlarim = new ArrayList<>();

    public Uye(String name, String username, String password, String userType, int yas, String egitim, String deneyim) {
        super(name, username, password, userType);
        this.yas = yas;
        this.egitim = egitim;
        this.deneyim = deneyim;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public String getEgitim() {
        return egitim;
    }

    public void setEgitim(String egitim) {
        this.egitim = egitim;
    }

    public String getDeneyim() {
        return deneyim;
    }

    public void setDeneyim(String deneyim) {
        this.deneyim = deneyim;
    }

    @Override
    public void ilanaBasvur(int ilanId) {

        for (Ilan i : Kontrol.ilanList) {
            if (i.getIlanId() == ilanId) {
                basvurdugumIlanlarim.add(i);
            }
        }
    }

    @Override
    public boolean ilanaBasvurulmusMu(int ilanId) {
        try {
            for (Ilan i : basvurdugumIlanlarim) {
                if (i.getIlanId() == ilanId) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
    public void ilanIptal(int ilanId){
        try {
            for(Ilan i: basvurdugumIlanlarim){
                if(i.getIlanId()==ilanId){
                    basvurdugumIlanlarim.remove(i);
                }
            }
        }catch (Exception e){
            //System.out.println(e.getMessage());
        }
    }
}
