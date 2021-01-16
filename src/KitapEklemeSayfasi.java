/*
@Authors: BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KitapEklemeSayfasi extends JFrame {
    private JPanel kitapEklePanel;
    private JButton kitapEkleButon;
    private JTextField kitapAdiAlani;
    private JTextField yazarAdiAlani;
    private JTextField yayinTarihAlani;
    private JTextField isbnAlani;
    private JTextField yayineviAlani;


    KitapEklemeSayfasi(){
        setTitle("Kitap Ekleme Sayfası");
        add(kitapEklePanel);
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        VeriTabani db = new VeriTabani();


        kitapEkleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String kitapAdi = kitapAdiAlani.getText();
                String yazarAdi = yazarAdiAlani.getText();
                String yayinTarihi = yayinTarihAlani.getText();
                String ISBN = isbnAlani.getText();
                String yayinevi = yayineviAlani.getText();
                if (kitapAdi.equals("") || yazarAdi.equals("") || yayinTarihi.equals("") || ISBN.equals("") || yayinevi.equals("")){
                    JOptionPane.showMessageDialog(kitapEklePanel, "Boş bilgileri doldur!");
                }
                else{
                    Kitap kitap = new Kitap(kitapAdi, yazarAdi, yayinTarihi, yayinevi, ISBN);
                    db.kitapEkle(kitap);
                    JOptionPane.showMessageDialog(kitapEklePanel, "Kitap Oluşturuldu!");
                }
            }
        });

    }
}
