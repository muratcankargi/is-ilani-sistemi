package NYP_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Login extends JFrame {
    private JPanel wrapper;
    private JPanel topWrapper;
    private JPanel bottomWrapper;
    private JTextField txt_username;
    private JPasswordField txt_password;
    private JButton btn_giris;
    private JButton btn_register;
    private JLabel lbl_goz;
    private JLabel lbl_note;


    public Login() {
        add(wrapper);
        setSize(400, 400);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Giriş");
        setResizable(false);
        setVisible(true);

        btn_giris.addActionListener(e -> {
            txt_username.setText(txt_username.getText().replace(" ", ""));
            if (txt_username.getText().equals("") || txt_password.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lütfen boş alan bırakmayınız!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (Kontrol.girisKontrol(txt_username.getText(), txt_password.getText())) {
                    if (Kontrol.uyeTipi(txt_username.getText()).equals("Üye")) {
                        UyePanel uyePanel = new UyePanel(txt_username.getText());
                        dispose();
                    } else {
                        SirketPanel sirketPanel = new SirketPanel(txt_username.getText());
                        dispose();
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Kullanıcı adınız ya da şifreniz hatalı!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        btn_register.addActionListener(e -> {
            Register register = new Register();
            dispose();
        });

        lbl_goz.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
             txt_password.setEchoChar((char)0);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                txt_password.setEchoChar('*');
            }
        }
        );
    }

    public static void main(String[] args) {
        Login sistem = new Login();
    }
}
