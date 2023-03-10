package NYP_Project;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JFrame {
    private JPanel wrapper;
    private JTextField txt_name;
    private JTextField txt_username;
    private JPasswordField txt_password;
    private JRadioButton rbtn_sirket;
    private JButton btn_register;
    private JRadioButton rbtn_uye;
    private JButton btn_login;
    private JTextField txt_uye_yas;
    private JComboBox cmb_egitim;
    private JTextPane txt_deneyim;
    private JPanel pnl_uye;
    private JPanel pnl_sirket;
    private JTextField txt_tarih;
    private JLabel lbl_goz;
    public Register() {
        add(wrapper);
        setSize(400, 600);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Kaydol");
        setResizable(false);
        setVisible(true);


        btn_register.addActionListener(e -> {
            if (txt_name.getText().equals("") || txt_username.getText().equals("") || String.valueOf(txt_password.getPassword()).equals("") || !(rbtn_uye.isSelected() || rbtn_sirket.isSelected())) {
                JOptionPane.showMessageDialog(null, "Boş alan bırakmayınız!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (Kontrol.kullaniciKontrol(txt_username.getText())) {
                    JOptionPane.showMessageDialog(null, "Lütfen farklı bir kullanıcı adıyla tekrar deneyiniz!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (rbtn_uye.isSelected()) {
                        if (txt_uye_yas.getText().equals("") || cmb_egitim.getSelectedItem().equals("") || txt_deneyim.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Boş alan bırakmayınız!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            try {
                                Uye u= new Uye(txt_name.getText(), txt_username.getText(), String.valueOf(txt_password.getPassword()), rbtn_uye.getText(),Integer.parseInt(txt_uye_yas.getText()), cmb_egitim.getSelectedItem().toString(), txt_deneyim.getText());
                                Kontrol.uyeList.add(u);
                                JOptionPane.showMessageDialog(null, "Kayıt Başarılı!", "Kayıt", JOptionPane.INFORMATION_MESSAGE);
                            }catch (Exception ex){
                                JOptionPane.showMessageDialog(null,"Lütfen doğru bilgi girdiğinizden emin olunuz!","Kayıt",JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    } else {

                        if (txt_tarih.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Boş alan bırakmayınız!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Sirket sirket= new Sirket(txt_name.getText(), txt_username.getText(), String.valueOf(txt_password.getPassword()), rbtn_sirket.getText(), txt_tarih.getText());
                            Kontrol.sirketList.add(sirket);
                            JOptionPane.showMessageDialog(null, "Kayıt Başarılı!", "Kayıt", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        rbtn_sirket.addActionListener(e -> {
            pnl_uye.setVisible(false);
            pnl_sirket.setVisible(true);
        });
        rbtn_uye.addActionListener(e -> {
            pnl_uye.setVisible(true);
            pnl_sirket.setVisible(false);
        });
        btn_login.addActionListener(e -> {
            Login login = new Login();
            dispose();
        });
        txt_tarih.setText("");

        lbl_goz.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txt_password.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txt_password.setEchoChar('*');
            }
        });

        textFocus();
    }

    public void textFocus() {
        rbtn_sirket.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (txt_tarih.getText().equals("")) {
                    txt_tarih.setText("GG/AA/YYYY");
                    txt_tarih.setForeground(new Color(153, 153, 153));
                }
            }
        });
        txt_tarih.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(txt_tarih.getText().equals("GG/AA/YYYY")){
                    txt_tarih.setText("");
                    txt_tarih.setForeground(new Color(0));
                }
            }
        });



    }


}
