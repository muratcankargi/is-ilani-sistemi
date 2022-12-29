package NYP_Project;

import java.util.ArrayList;

public class Kontrol {
    static ArrayList<Uye> uyeList = new ArrayList<>();
    static ArrayList<Sirket> sirketList = new ArrayList<>();
    static ArrayList<Ilan> ilanList = new ArrayList<>();

    static boolean girisKontrol(String username, String password) {
        for (Uye u : uyeList) {
            for (Sirket s : sirketList) {

                if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                    return true;
                } else if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                    return true;
                }
            }

        }
        return false;
    }

    static boolean kullaniciKontrol(String username) {
        for (Uye u : uyeList) {
            if (username.equals(u.getUsername())) {
                return true;
            }
        }
        for (Sirket s : sirketList) {
            if (username.equals(s.getUsername())) {
                return true;
            }
        }
        return false;
    }

    static String userTipi(String username) {
        for (Uye u : uyeList) {
            if (u.getUsername().equals(username)) {
                return "Üye";
            }
        }
        return "Şirket";
    }

    static String adKontrol(String username) {
        for (Uye u : uyeList) {
            if (u.getUsername().equals(username)) {
                return u.getName();
            }
        }
        for (Sirket s : sirketList) {
            if (s.getUsername().equals(username)) {
                return s.getName();
            }
        }
        return "";
    }

    static boolean ilanGuncelle(int ilanId, String baslik, String aciklama) {
        for (Ilan i : ilanList) {
            if (i.getIlanId() == ilanId) {
                i.setBaslik(baslik);
                i.setAciklama(aciklama);
                return true;
            }
        }
        return false;
    }

    static Uye uyeninKendisi(String username) {
        for (Uye u : uyeList) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
    //overloading
    static boolean silindiMi(String username) {
        for (Uye u : uyeList) {
            if (u.getUsername().equals(username)) {
                uyeList.remove(u);
                return true;
            }
        }
        for (Sirket s : sirketList) {
            if (s.getUsername().equals(username)) {
                sirketList.remove(s);
                return true;
            }
        }
        return false;
    }
    static boolean silindiMi(int ilanId) {
        for (Ilan i : ilanList) {
            if (i.getIlanId() == ilanId) {
                ilanList.remove(ilanId - 1);
                return true;
            }
        }
        return false;
    }

    //overriding
    @Override
    public String toString() {
        String uye = "";
        for (Uye u : uyeList) {
            uye = (u.getName() + ", " + u.getUsername() + ", " + u.getPassword() + ", " + u.getYas() + ", " + u.getEgitim() + ", " + u.getDeneyim());
            System.out.println(uye);
        }
        for (Sirket u : sirketList) {
            uye = (u.getName() + ", " + u.getUsername() + ", " + u.getPassword() + ", " + u.getUserType() + ", " + u.getKurulusTarihi());
            System.out.println(uye);
        }
        return uye;
    }
}


