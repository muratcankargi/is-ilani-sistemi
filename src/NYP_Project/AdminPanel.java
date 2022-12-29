package NYP_Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JTable tbl_uyeler;
    private JTable tbl_sirketler;
    private JTable tbl_ilanlar;
    private JLabel lbl_uye;
    private JButton btn_uye_sil;
    private JLabel lbl_uye_yas_ortalamasi;

    AdminPanel() {
        add(wrapper);
        setSize(1000, 400);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Giriş");
        setResizable(false);
        setVisible(true);

        uyelerPageRefresh();
        sirketlerPageRefresh();
        ilanlarPageRefresh();

        tbl_uyeler.getSelectionModel().addListSelectionListener(e ->{
            try {
                String select_ilan_id = tbl_uyeler.getValueAt(tbl_uyeler.getSelectedRow(), 1).toString();
                lbl_uye.setText(select_ilan_id);
            } catch (Exception exception) {
                //System.out.println(exception.getMessage());
            }
        });

        btn_uye_sil.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Hesabı silmek üzeresiniz. Emin misiniz", "Hesap", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION&&!(lbl_uye.getText().equals("murat"))) {
                if (Kontrol.kullaniciSil(lbl_uye.getText())) {
                    JOptionPane.showMessageDialog(null, "Hesap silindi.", "Hesap", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "Hesap silinemedi.", "Hesap", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            lbl_uye.setText("");
            uyelerPageRefresh();

        });
    }

    public void uyelerPageRefresh() {
        DefaultTableModel mdl_sirket_basvurulan_ilan = new DefaultTableModel();
        Object[] col_ilan_list = {"Ad", "Kullanıcı adı", "Şifre", "Yaş","Eğitim","Deneyim"};
        mdl_sirket_basvurulan_ilan.setColumnIdentifiers(col_ilan_list);
        DefaultTableModel clearModel = (DefaultTableModel) tbl_uyeler.getModel();
        clearModel.setRowCount(0);

        for (Uye u : Kontrol.uyeList) {

            int j = 0;
            Object[] row = new Object[col_ilan_list.length];
            row[j++] = u.getName();
            row[j++] = u.getUsername();
            row[j++] = u.getPassword();
            row[j++] = u.getYas();
            row[j++]=u.getEgitim();
            row[j++]=u.getDeneyim();

            mdl_sirket_basvurulan_ilan.addRow(row);


        }
        tbl_uyeler.setModel(mdl_sirket_basvurulan_ilan);
        tbl_uyeler.getTableHeader().setReorderingAllowed(false);
        tbl_uyeler.getColumnModel().getColumn(0).setPreferredWidth(20);

        tbl_uyeler.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //aynı anda birden fazla satır seçmeyi engeller
        tbl_uyeler.setDefaultEditor(Object.class, null); //seçilen sütunun bilgerini değiştirememeyi sağlar
        double sum=0;
        for(Uye u:Kontrol.uyeList){
            sum+=u.getYas();
        }
        lbl_uye_yas_ortalamasi.setText(String.valueOf(sum/Kontrol.uyeList.size()));
    }
    public void sirketlerPageRefresh() {
        DefaultTableModel mdl_sirket_basvurulan_ilan = new DefaultTableModel();
        Object[] col_ilan_list = {"Ad", "Kullanıcı adı", "Şifre", "Kuruluş Tarihi"};
        mdl_sirket_basvurulan_ilan.setColumnIdentifiers(col_ilan_list);
        DefaultTableModel clearModel = (DefaultTableModel) tbl_sirketler.getModel();
        clearModel.setRowCount(0);

        for (Sirket s : Kontrol.sirketList) {

            int j = 0;
            Object[] row = new Object[col_ilan_list.length];
            row[j++] = s.getName();
            row[j++] = s.getUsername();
            row[j++] = s.getPassword();
            row[j] = s.getKurulusTarihi();

            mdl_sirket_basvurulan_ilan.addRow(row);


        }
        tbl_sirketler.setModel(mdl_sirket_basvurulan_ilan);
        tbl_sirketler.getTableHeader().setReorderingAllowed(false);
        tbl_sirketler.getColumnModel().getColumn(0).setPreferredWidth(20);

        tbl_sirketler.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //aynı anda birden fazla satır seçmeyi engeller
        tbl_sirketler.setDefaultEditor(Object.class, null); //seçilen sütunun bilgerini değiştirememeyi sağlar

    }
    public void ilanlarPageRefresh() {
        DefaultTableModel mdl_sirket_basvurulan_ilan = new DefaultTableModel();
        Object[] col_ilan_list = {"ID", "Şirket", "Başlık", "Açıklama"};
        mdl_sirket_basvurulan_ilan.setColumnIdentifiers(col_ilan_list);
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ilanlar.getModel();
        clearModel.setRowCount(0);

        for (Ilan i : Kontrol.ilanList) {

            int j = 0;
            Object[] row = new Object[col_ilan_list.length];
            row[j++] = i.getIlanId();
            row[j++] = i.getSirketAdi();
            row[j++] = i.getBaslik();
            row[j] = i.getAciklama();
            mdl_sirket_basvurulan_ilan.addRow(row);


        }
        tbl_ilanlar.setModel(mdl_sirket_basvurulan_ilan);
        tbl_ilanlar.getTableHeader().setReorderingAllowed(false);
        tbl_ilanlar.getColumnModel().getColumn(0).setPreferredWidth(20);

        tbl_ilanlar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //aynı anda birden fazla satır seçmeyi engeller
        tbl_ilanlar.setDefaultEditor(Object.class, null); //seçilen sütunun bilgerini değiştirememeyi sağlar

    }

}
