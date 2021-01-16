/*
@Authors: BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminKitapGuncelleSayfasi extends JFrame{
    private JPanel kitapGuncelleAnaPanel;
    private JTextField isbnAlani;
    private JTextField yeniKitapIsimAlani;
    private JTextField yeniYazarIsimAlani;
    private JTextField yeniYayineviIsimAlani;
    private JButton kitapGuncelleButon;


    AdminKitapGuncelleSayfasi(){
        super("Kitap Güncelleme Sayfası");
        add(kitapGuncelleAnaPanel);
        setSize(400,270);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        VeriTabani db = new VeriTabani();


        kitapGuncelleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnAlani.getText();
                String yeniKitapIsmi = yeniKitapIsimAlani.getText();
                String yeniYazarIsmi = yeniYazarIsimAlani.getText();
                String yeniYayineviIsmi = yeniYayineviIsimAlani.getText();

                if (!(isbn.equals("") || yeniKitapIsmi.equals("") || yeniYazarIsmi.equals("") || yeniYayineviIsmi.equals(""))){
                    db.kitapGuncelle(yeniKitapIsmi, yeniYazarIsmi, yeniYayineviIsmi, isbn);
                    JOptionPane.showMessageDialog(kitapGuncelleAnaPanel, "Kitap bilgileri güncellendi!");
                }
                else{
                    JOptionPane.showMessageDialog(kitapGuncelleAnaPanel, "Boş alanları doldur!");
                }
            }
        });
    }
}
