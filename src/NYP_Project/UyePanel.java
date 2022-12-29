package NYP_Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UyePanel extends JFrame {

    private JPanel wrapper;
    private JScrollPane scrl_pnl_bottom;
    private JTable tbl_ilan_list;
    private JLabel lbl_hosgeldin;
    private JTextField txt_ilan_id;
    private JButton btn_basvur;
    private JButton btn_basvuru_iptal;
    private JPanel pnl_basvurulan_ilanlar;
    private JTable tbl_basvurulan_ilanlar;
    private JButton btn_cikis;
    private JPanel pnl_bilgiler;
    private JTextField txt_uye_ad;
    private JTextField txt_uye_username;
    private JPasswordField txt_uye_sifre;
    private JTextField txt_uye_yas;
    private JComboBox cmb_mezuniyet;
    private JTextArea txt_uye_deneyim;
    private JButton btn_guncelle;
    private JButton btn_uye_sil;
    private JTabbedPane tbl_bilgiler;
    private JLabel lbl_goz;
    private JTextField txt_basvurulan_ilan_id;
    private JButton btn_basvuru_iptal_2;
    private DefaultTableModel mdl_ilanlarim;
    static String userName;

    public UyePanel(String username) {
        add(wrapper);
        setSize(1000, 400);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Üye Paneli");
        setResizable(false);
        setVisible(true);
        userName = username;
        pageRefresh();
        basvuruPageRefresh();
        bilgilerPageRefresh();
        tbl_ilan_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_ilan_id = tbl_ilan_list.getValueAt(tbl_ilan_list.getSelectedRow(), 0).toString();
                txt_ilan_id.setText(select_ilan_id);
            } catch (Exception ex) {
                //System.out.println(ex.getMessage());
            }

            try {

                int ilanId = Integer.parseInt(txt_ilan_id.getText());
                if (Kontrol.uyeninKendisi(userName).ilanaBasvurulmusMu(ilanId)) {
                    btn_basvur.setEnabled(false);
                    btn_basvuru_iptal.setEnabled(true);
                } else {
                    btn_basvur.setEnabled(true);
                    btn_basvuru_iptal.setEnabled(false);
                }
            } catch (Exception exception) {
                //System.out.println(exception.getMessage());
            }

        });
        tbl_basvurulan_ilanlar.getSelectionModel().addListSelectionListener(e ->{
            try {
                String select_ilan_id = tbl_basvurulan_ilanlar.getValueAt(tbl_basvurulan_ilanlar.getSelectedRow(), 0).toString();
                txt_basvurulan_ilan_id.setText(select_ilan_id);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        });

        btn_basvur.addActionListener(e -> {
            int ilanId = Integer.parseInt(txt_ilan_id.getText());
            if (Kontrol.uyeninKendisi(userName).ilanaBasvurulmusMu(ilanId)) {
                JOptionPane.showMessageDialog(null, "Bu ilana zaten başvurdunuz.", "Başvuru", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Kontrol.uyeninKendisi(userName).ilanaBasvur(ilanId);
                JOptionPane.showMessageDialog(null, "İlana başvuruldu!", "Başvuru", JOptionPane.INFORMATION_MESSAGE);

            }
            pageRefresh();
            basvuruPageRefresh();
        });
        btn_basvuru_iptal.addActionListener(e -> {
            try {
                int ilanId = Integer.parseInt(txt_ilan_id.getText());
                if (Kontrol.uyeninKendisi(userName).ilanaBasvurulmusMu(ilanId)) {
                    Kontrol.uyeninKendisi(userName).ilanIptal(ilanId);
                    JOptionPane.showMessageDialog(null, "Başvuru iptal edildi.", "Başvuru", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Kontrol.uyeninKendisi(userName).ilanaBasvur(ilanId);
                    JOptionPane.showMessageDialog(null, "İlana başvuruldu!", "Başvuru", JOptionPane.INFORMATION_MESSAGE);

                }
                pageRefresh();
                basvuruPageRefresh();
            } catch (Exception exception) {
                //System.out.println(exception.getMessage());
            }
        });
        btn_cikis.addActionListener(e -> {
            Login login = new Login();
            dispose();
        });
        btn_guncelle.addActionListener(e -> {
            if (txt_uye_ad.getText().equals("") || txt_uye_username.getText().equals("") || String.valueOf(txt_uye_sifre.getPassword()).equals("") || txt_uye_yas.getText().equals("") || cmb_mezuniyet.getSelectedItem().equals("") || txt_uye_deneyim.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Boş alan bırakmayınız!", "Fail", JOptionPane.ERROR_MESSAGE);
            } else {
                for (Uye u : Kontrol.uyeList) {
                    if (Kontrol.uyeninKendisi(userName) == u) {
                        try {
                            u.setName(txt_uye_ad.getText());
                            u.setUsername(txt_uye_username.getText());
                            u.setPassword(String.valueOf(txt_uye_sifre.getPassword()));
                            u.setYas(Integer.parseInt(txt_uye_yas.getText()));
                            u.setEgitim(cmb_mezuniyet.getSelectedItem().toString());
                            u.setDeneyim(txt_uye_deneyim.getText());
                            JOptionPane.showMessageDialog(null, "Güncelleme başarılı.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null, "Lütfen doğru bilgi girdiğinizden emin olunuz!", "Kayıt", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
                bilgilerPageRefresh();
                pageRefresh();
            }
        });
        btn_uye_sil.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Hesabınızı silmek üzeresiniz. Emin misiniz", "Hesap", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                if (Kontrol.silindiMi(userName)) {
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
                txt_uye_sifre.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txt_uye_sifre.setEchoChar('*');
            }
        });

        btn_basvuru_iptal_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    int ilanId=Integer.parseInt(txt_basvurulan_ilan_id.getText());
                    if (Kontrol.uyeninKendisi(userName).ilanaBasvurulmusMu(ilanId)) {
                        Kontrol.uyeninKendisi(userName).ilanIptal(ilanId);
                        JOptionPane.showMessageDialog(null, "Başvuru iptal edildi.", "Başvuru", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "İptal edilemedi!", "Başvuru", JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch (Exception exception){
                    System.out.println(exception);
                }
                txt_basvurulan_ilan_id.setText("");
                pageRefresh();
                basvuruPageRefresh();
            }
        });
    }

    public void pageRefresh() {
        mdl_ilanlarim = new DefaultTableModel();
        Object[] col_ilan_list = {"İlan No", "Şirket", "Başlık", "Açıklama"};
        mdl_ilanlarim.setColumnIdentifiers(col_ilan_list);
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ilan_list.getModel();
        clearModel.setRowCount(0);
        Button btn = new Button();
        for (Ilan i : Kontrol.ilanList) {
            int j = 0;
            Object[] row = new Object[col_ilan_list.length];
            row[j++] = i.getIlanId();
            row[j++] = i.getSirketAdi();
            row[j++] = i.getBaslik();
            row[j] = i.getAciklama();
            mdl_ilanlarim.addRow(row);
        }

        tbl_ilan_list.setModel(mdl_ilanlarim);
        tbl_ilan_list.getTableHeader().setReorderingAllowed(false);
        tbl_ilan_list.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbl_ilan_list.getColumnModel().getColumn(1).setPreferredWidth(userName.length());
        tbl_ilan_list.getColumnModel().getColumn(2).setPreferredWidth(75);
        tbl_ilan_list.getColumnModel().getColumn(3).setPreferredWidth(300);

        tbl_ilan_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //aynı anda birden fazla satır seçmeyi engeller
        tbl_ilan_list.setDefaultEditor(Object.class, null); //seçilen sütunun bilgerini değiştirememeyi sağlar
    }

    public void basvuruPageRefresh() {
        mdl_ilanlarim = new DefaultTableModel();
        Object[] col_ilan_list = {"İlan No", "Şirket", "Başlık", "Açıklama"};
        mdl_ilanlarim.setColumnIdentifiers(col_ilan_list);
        DefaultTableModel clearModel = (DefaultTableModel) tbl_basvurulan_ilanlar.getModel();
        clearModel.setRowCount(0);
        Button btn = new Button();
        for (Ilan i : Kontrol.uyeninKendisi(userName).basvurdugumIlanlarim) {
            int j = 0;
            Object[] row = new Object[col_ilan_list.length];
            row[j++] = i.getIlanId();
            row[j++] = i.getSirketAdi();
            row[j++] = i.getBaslik();
            row[j] = i.getAciklama();
            mdl_ilanlarim.addRow(row);

        }

        tbl_basvurulan_ilanlar.setModel(mdl_ilanlarim);
        tbl_basvurulan_ilanlar.getTableHeader().setReorderingAllowed(false);
        tbl_basvurulan_ilanlar.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbl_basvurulan_ilanlar.getColumnModel().getColumn(1).setPreferredWidth(userName.length());
        tbl_basvurulan_ilanlar.getColumnModel().getColumn(2).setPreferredWidth(75);
        tbl_basvurulan_ilanlar.getColumnModel().getColumn(3).setPreferredWidth(300);

        tbl_basvurulan_ilanlar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //aynı anda birden fazla satır seçmeyi engeller
        tbl_basvurulan_ilanlar.setDefaultEditor(Object.class, null); //seçilen sütunun bilgerini değiştirememeyi sağlar
    }

    public void bilgilerPageRefresh() {
        lbl_hosgeldin.setText(Kontrol.adKontrol(userName) + ", hoşgeldiniz!");

        for (Uye u : Kontrol.uyeList) {
            if (Kontrol.uyeninKendisi(userName) == u) {
                txt_uye_ad.setText(u.getName());
                txt_uye_username.setText(u.getUsername());
                txt_uye_sifre.setText(u.getPassword());
                txt_uye_yas.setText(String.valueOf(u.getYas()));
                cmb_mezuniyet.setSelectedItem(u.getEgitim());
                txt_uye_deneyim.setText(u.getDeneyim());
            }
        }
    }


}
