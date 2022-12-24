package NYP_Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SirketPanel extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_sirketPanel;
    private JPanel pnl_ilanlar;
    private JScrollPane scrl_ilan_list;
    private JTable tbl_ilan_list;
    private JLabel lbl_hosgeldiniz;
    private JTextPane txt_ilan_baslik;
    private JTextPane txt_ilan_aciklama;
    private JButton btn_guncelle;
    private JButton btn_sil;
    private JButton btn_ekle;
    private JButton btn_exit;
    private JTable tbl_basvuranlar;
    private JTextArea txt_aciklama2;
    private JTextField txt_sirket_ad;
    private JTextField txt_sirket_username;
    private JTextField txt_sirket_kurulus;
    private JPasswordField txt_sirket_sifre;
    private JButton güncelleButton;
    private JButton btn_sirket_sil;
    private JLabel lbl_goz;

    private DefaultTableModel mdl_sirket_basvurulan_ilan;
    private Object[] row_ilan_list;
    static String userName;

    public SirketPanel(String username) {
        userName = username;
        add(wrapper);
        setSize(1000, 400);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Üye Paneli");
        setResizable(false);
        setVisible(true);

        pageRefresh();
        basvuranlarPageRefresh();
        bilgilerPageRefresh();

        tbl_ilan_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_ilan_baslik = tbl_ilan_list.getValueAt(tbl_ilan_list.getSelectedRow(), 2).toString();
                txt_ilan_baslik.setText(select_ilan_baslik);
                String select_ilan_aciklama = tbl_ilan_list.getValueAt(tbl_ilan_list.getSelectedRow(), 3).toString();
                txt_ilan_aciklama.setText(select_ilan_aciklama);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        btn_guncelle.addActionListener(e -> {
            try {
                String ilanId = tbl_ilan_list.getValueAt(tbl_ilan_list.getSelectedRow(), 0).toString();
                if (Kontrol.ilanGuncelle(Integer.parseInt(ilanId), txt_ilan_baslik.getText(), txt_ilan_aciklama.getText())) {
                    JOptionPane.showMessageDialog(null, "Güncelleme Başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Güncelleme yapılamadı.", "Fail", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            txt_ilan_baslik.setText("");
            txt_ilan_aciklama.setText("");
            pageRefresh();
        });
        btn_sil.addActionListener(e -> {
            try {
                String ilanId = tbl_ilan_list.getValueAt(tbl_ilan_list.getSelectedRow(), 0).toString();
                if (Kontrol.ilanSil(Integer.parseInt(ilanId))) {
                    JOptionPane.showMessageDialog(null, "İlan silindi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "İlan silinemedi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            txt_ilan_baslik.setText("");
            txt_ilan_aciklama.setText("");
            pageRefresh();
        });


        btn_ekle.addActionListener(e -> {
            try {
                Kontrol.ilanEkle(Kontrol.adKontrol(username), txt_ilan_baslik.getText(), txt_ilan_aciklama.getText());
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            txt_ilan_baslik.setText("");
            txt_ilan_aciklama.setText("");
            pageRefresh();
        });

        btn_exit.addActionListener(e -> {
            Login login = new Login();
            dispose();
        });

        btn_sirket_sil.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Hesabınızı silmek üzeresiniz. Emin misiniz", "Hesap", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                if (Kontrol.kullaniciSil(userName)) {
                    dispose();
                    Login login = new Login();
                } else {
                    JOptionPane.showMessageDialog(null, "Hesabınız silinmedi.", "Hesap", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });
        lbl_goz.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txt_sirket_sifre.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txt_sirket_sifre.setEchoChar('*');
            }
        });
        güncelleButton.addActionListener(e -> {
            if (txt_sirket_ad.getText().equals("") || txt_sirket_username.getText().equals("") || txt_sirket_sifre.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Boş alan bırakmayınız!", "Fail", JOptionPane.ERROR_MESSAGE);
            } else {
                for (Sirket s : Kontrol.sirketList) {
                    if (userName.equals(s.getUsername())) {
                        for(Ilan i:Kontrol.ilanList){
                            if(i.getSirketAdi().equals(s.getName())){
                                i.setSirketAdi(txt_sirket_ad.getText());
                            }
                        }
                        s.setName(txt_sirket_ad.getText());
                        s.setUsername(txt_sirket_username.getText());
                        s.setPassword(txt_sirket_sifre.getText());
                        JOptionPane.showMessageDialog(null, "Güncelleme başarılı.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                bilgilerPageRefresh();
                pageRefresh();

            }
        });

    }

    public void pageRefresh() {
        lbl_hosgeldiniz.setText(Kontrol.adKontrol(userName) + ", hoşgeldiniz!");

        mdl_sirket_basvurulan_ilan = new DefaultTableModel();
        Object[] col_ilan_list = {"Id", "Şirket", "Başlık", "Açıklama"};
        mdl_sirket_basvurulan_ilan.setColumnIdentifiers(col_ilan_list);
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ilan_list.getModel();
        clearModel.setRowCount(0);

        for (Sirket s : Kontrol.sirketList) {
            for (Ilan i : Kontrol.ilanList) {
                int j = 0;
                if (s.getUsername().equals(userName) && i.getSirketAdi().equals(s.getName())) {
                    Object[] row = new Object[col_ilan_list.length];
                    row[j++] = i.getIlanId();
                    row[j++] = i.getSirketAdi();
                    row[j++] = i.getBaslik();
                    row[j++] = i.getAciklama();
                    mdl_sirket_basvurulan_ilan.addRow(row);
                }
            }
        }
        tbl_ilan_list.setModel(mdl_sirket_basvurulan_ilan);
        tbl_ilan_list.getTableHeader().setReorderingAllowed(false);
        tbl_ilan_list.getColumnModel().getColumn(0).setPreferredWidth(20);
        tbl_ilan_list.getColumnModel().getColumn(1).setPreferredWidth(userName.length() + 100);
        tbl_ilan_list.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbl_ilan_list.getColumnModel().getColumn(3).setPreferredWidth(400);

        tbl_ilan_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //aynı anda birden fazla satır seçmeyi engeller
        tbl_ilan_list.setDefaultEditor(Object.class, null); //seçilen sütunun bilgerini değiştirememeyi sağlar
    }

    public void basvuranlarPageRefresh() {
        mdl_sirket_basvurulan_ilan = new DefaultTableModel();
        Object[] col_ilan_list = {"Ilan No", "Adı", "Yaşı", "Mezuniyet", "Deneyim"};
        mdl_sirket_basvurulan_ilan.setColumnIdentifiers(col_ilan_list);
        DefaultTableModel clearModel = (DefaultTableModel) tbl_basvuranlar.getModel();
        clearModel.setRowCount(0);
        Object[] row = new Object[col_ilan_list.length];
        for (Uye u : Kontrol.uyeList) {
            for (int k = 0; k < u.basvurdugumIlanlarim.size(); k++) {
                int j = 0;
                if (u.basvurdugumIlanlarim.get(k).getSirketAdi().equals(Kontrol.adKontrol(userName))) {
                    row[j++] = u.basvurdugumIlanlarim.get(k).getIlanId();
                    row[j++] = u.getName();
                    row[j++] = u.getYas();
                    row[j++] = u.getEgitim();
                    row[j++] = u.getDeneyim();
                    mdl_sirket_basvurulan_ilan.addRow(row);
                }

            }


        }
        tbl_basvuranlar.setModel(mdl_sirket_basvurulan_ilan);
        tbl_basvuranlar.getTableHeader().setReorderingAllowed(false);
        tbl_basvuranlar.getColumnModel().getColumn(0).setPreferredWidth(20);
        tbl_basvuranlar.getColumnModel().getColumn(1).setPreferredWidth(userName.length() + 100);

        tbl_basvuranlar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //aynı anda birden fazla satır seçmeyi engeller
        tbl_basvuranlar.setDefaultEditor(Object.class, null); //seçilen sütunun bilgerini değiştirememeyi sağlar
    }

    public void bilgilerPageRefresh() {
        for (Sirket s : Kontrol.sirketList) {
            if (s.getUsername().equals(userName)) {
                txt_sirket_ad.setText(s.getName());
                txt_sirket_username.setText(s.getUsername());
                txt_sirket_kurulus.setText(s.getKurulusTarihi());
                txt_sirket_sifre.setText(s.getPassword());
            }
        }
    }

    public static void main(String[] args) {
        SirketPanel sirketPanel = new SirketPanel("");
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
