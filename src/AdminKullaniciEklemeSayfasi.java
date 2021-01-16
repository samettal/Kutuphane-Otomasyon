/*
@Authors: BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminKullaniciEklemeSayfasi extends JFrame{
    private JPanel kullaniciEklePanel;
    private JTextField kullaniciNoAlani;
    private JRadioButton kullaniciRadyoButon;
    private JRadioButton adminRadyoButon;
    private JButton kullaniciOlustur;
    private JTextField isimAlani;
    private JTextField soyisimAlani;
    private JTextField parolaAlani;


    AdminKullaniciEklemeSayfasi(){
        setTitle("Kullanıcı Ekleme Sayfası");
        add(kullaniciEklePanel);
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        VeriTabani db = new VeriTabani();
        kullaniciOlustur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciIsmi = isimAlani.getText();
                String kullaniciSoyIsmi = soyisimAlani.getText();
                String kullaniciNo = kullaniciNoAlani.getText();
                String parola = parolaAlani.getText();
                String unvan = "";
                if(kullaniciIsmi.equals("") || kullaniciSoyIsmi.equals("") || kullaniciNo.equals("") || parola.equals("")){
                    JOptionPane.showMessageDialog(kullaniciEklePanel,"Lütfen boş alanları doldurun!");
                }
                else {
                    if (kullaniciRadyoButon.isSelected()) {
                        unvan = "kullanici";
                        Kullanici yeniKullanici = new Kullanici(kullaniciIsmi, kullaniciSoyIsmi, parola, kullaniciNo, unvan);
                        db.kullaniciEkle(yeniKullanici);
                        JOptionPane.showMessageDialog(kullaniciEklePanel, "Kullanıcı oluşturuldu");
                    } else if (adminRadyoButon.isSelected()) {
                        unvan = "admin";
                        Kullanici yeniKullanici = new Kullanici(kullaniciIsmi, kullaniciSoyIsmi, parola, kullaniciNo, unvan);
                        db.kullaniciEkle(yeniKullanici);
                        JOptionPane.showMessageDialog(kullaniciEklePanel, "Kullanıcı oluşturuldu");
                    } else {
                        JOptionPane.showMessageDialog(kullaniciEklePanel, "Lütfen unvan seçin");
                    }
                }
            }
        });
    }
}
