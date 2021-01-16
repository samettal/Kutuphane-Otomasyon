/*
Authors: BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YeniKayitSayfasi extends JFrame {

    private JPanel kayitAnaPanel;
    private JTextField kayitKullaniciNoAlani;
    private JTextField kayitIsimAlani;
    private JTextField kayitSoyisimAlani;
    private JTextField kayitParolaAlani;
    private JButton kaydolButon;


    YeniKayitSayfasi(){
        setTitle("Yeni Kayıt Sayfası");
        add(kayitAnaPanel);
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        VeriTabani db = new VeriTabani();


        kaydolButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciNo = kayitKullaniciNoAlani.getText();
                String isim = kayitIsimAlani.getText();
                String soyisim = kayitSoyisimAlani.getText();
                String parola = kayitParolaAlani.getText();
                String unvan = "kullanıcı";

                if(kullaniciNo.equals("") || isim.equals("") || soyisim.equals("") || parola.equals("")){
                    JOptionPane.showMessageDialog(kayitAnaPanel,"Lütfen boşlukları doldurun.");
                }
                else{
                    Kullanici yeniKullanici = new Kullanici(isim, soyisim, parola, kullaniciNo, unvan);
                    db.kullaniciEkle(yeniKullanici);
                    JOptionPane.showMessageDialog(kayitAnaPanel,"Kayıt başarılı. Lütfen giriş yapın");
                }
            }
        });
    }
}
