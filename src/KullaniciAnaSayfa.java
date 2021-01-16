/*
@Authors: BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KullaniciAnaSayfa extends JFrame{
    private JButton kitaplariGoruntuleButonu;
    private JButton cikisYapButonu;
    private JPanel kullaniciPanel;
    private static KullaniciAnaSayfa f = null;


    KullaniciAnaSayfa(){
        setTitle("Kullanıcı Ana Sayfası");
        add(kullaniciPanel);
        setSize(360,180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        kitaplariGoruntuleButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KullaniciKitaplarSayfasi kullaniciKitaplarSayfasi = new KullaniciKitaplarSayfasi();
                kullaniciKitaplarSayfasi.setVisible(true);
            }
        });


        cikisYapButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                girisYapSayfasinaGit(e);
            }
        });
    }

    //Formlar arası geçiş sağlayan kod
    public static synchronized KullaniciAnaSayfa getInstance(){
        try {
            if (f == null) {
                f = (KullaniciAnaSayfa) Class.forName("KullaniciAnaSayfa").newInstance();
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.toString());
        }
        return f;
    }


    private void girisYapSayfasinaGit(ActionEvent evt) {
        KutuphaneAnaSayfa.getInstance().setVisible(true);
        this.dispose();
    }
}
