/*
@Authors : BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KullaniciKitaplarSayfasi extends JFrame{

    private JPanel kullaniciKitaplarPanel;
    private JTable kitaplarTablosu;
    private JButton kitapBagislaButon;
    private JButton kitapAlButon;


    KullaniciKitaplarSayfasi(){
        setTitle("Kitaplar Sayfası");
        add(kullaniciKitaplarPanel);
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel kitapVeriModeli = (DefaultTableModel) kitaplarTablosu.getModel();
        VeriTabani db = new VeriTabani();
        kitaplarTablosu.setModel(db.kitaplar(kitapVeriModeli));


        kitapAlButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kitapISBN = JOptionPane.showInputDialog("Lütfen ISBN No giriniz: ");
                if (!(kitapISBN.equals(""))){
                    db.kitapSil(kitapISBN);
                    kitapVeriModeli.setRowCount(0);
                    kitapVeriModeli.setColumnCount(0);
                    kitaplarTablosu.setModel(db.kitaplar(kitapVeriModeli));
                    JOptionPane.showMessageDialog(kullaniciKitaplarPanel, "Kitap Alındı!");
                }
            }
        });


        kitapBagislaButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                KitapEklemeSayfasi kitapEklemeSayfasi = new KitapEklemeSayfasi();
                kitapEklemeSayfasi.setVisible(true);
            }
        });
    }
}
