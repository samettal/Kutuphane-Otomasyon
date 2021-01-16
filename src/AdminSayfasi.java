/*
@Authors: BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSayfasi extends JFrame{
    private JPanel adminPanel;
    private JButton kitapIslemleriButon;
    private JButton kullaniciIslemleriButon;
    private JButton cikisYapButon;
    private static AdminSayfasi f = null;


    AdminSayfasi(){
        setTitle("Admin Faaliyetleri");
        add(adminPanel);
        setSize(360,180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        kitapIslemleriButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminKitaplarSayfasi adminKitaplarSayfasi = new AdminKitaplarSayfasi();
                adminKitaplarSayfasi.setVisible(true);
            }
        });


        kullaniciIslemleriButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminKayitliKullanicilarSayfasi adminKayitliKullanicilarSayfasi = new AdminKayitliKullanicilarSayfasi();
                adminKayitliKullanicilarSayfasi.setVisible(true);
            }
        });


        cikisYapButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                girisEkraninaGit(actionEvent);
            }
        });

    }


    public static synchronized AdminSayfasi getInstance(){
        try {
            if (f == null) {
                f = (AdminSayfasi) Class.forName("AdminSayfasi").newInstance();
            }
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.toString());
        }
        return f;
    }


    private void girisEkraninaGit(ActionEvent evt) {
        KutuphaneAnaSayfa.getInstance().setVisible(true);
        this.dispose();
    }

}
