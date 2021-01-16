/*
@Authors : BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminKayitliKullanicilarSayfasi extends JFrame{
    private JPanel kullanicilariGoruntulePanel;
    private JTable kullanicilariGoruntuleTablo;
    private JButton kullaniciyiSil;
    private JButton kullaniciAramaButon;
    private JButton kullaniciVeyaAdminEkleButon;


    AdminKayitliKullanicilarSayfasi(){
        setTitle("Kayıtlı Kullanıcılar Sayfası");
        add(kullanicilariGoruntulePanel);
        setSize(800,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);;


        DefaultTableModel kullaniciVeriModeli = (DefaultTableModel) kullanicilariGoruntuleTablo.getModel();
        VeriTabani db = new VeriTabani();
        kullanicilariGoruntuleTablo.setModel(db.kullanicilariGoruntule(kullaniciVeriModeli)); //kullanıcıları veri tabanından kullanicilariGoruntule metodu ile veriModeli gönderip verimi return ediyor.


        kullaniciVeyaAdminEkleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminKullaniciEklemeSayfasi kullaniciEkleFormSayfasi = new AdminKullaniciEklemeSayfasi();
                kullaniciEkleFormSayfasi.setVisible(true);
            }
        });


        kullaniciyiSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciNo = JOptionPane.showInputDialog("Lütfen kullanıcı noyu giriniz: ");
                db.kullaniciSil(kullaniciNo); //kullanıcı silindi
                kullaniciVeriModeli.setRowCount(0); //row sıfırlandı
                kullaniciVeriModeli.setColumnCount(0); //column sıfırlandı
                kullanicilariGoruntuleTablo.setModel(db.kullanicilariGoruntule(kullaniciVeriModeli)); //yenilenen veri tekrar çekildi
            }
        });


        kullaniciAramaButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciNo = JOptionPane.showInputDialog("Lütfen Kullanıcı No'yu girin: ");
                String[] data = db.kullaniciNoIleAra(kullaniciNo); //veri String[] tipi geldiği için String[] tipinde değişkene atandı
                if(data.length > 0){ //eğer veri varsa
                    JOptionPane.showMessageDialog(kullanicilariGoruntulePanel,"İsim: " + data[0] + "\n" + "Soyisim: " + data[1] + "\n" + "Parola: " + data[2] + "\n" + "Kullanıcı No:" + data[3] + "\n" + "Unvan: " + data[4] );
                }else{
                    JOptionPane.showMessageDialog(kullanicilariGoruntulePanel,"Kullanıcı Bulunamadı");
                }
            }
        });
    }
}
