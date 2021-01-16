/*
@Authors : BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminKitaplarSayfasi extends JFrame {
    private JPanel kitaplariGoruntulePanel;
    private JTable kitaplarTablosu;
    private JButton kitapSilButon;
    private JButton kitapGuncelleButon;
    private JButton kitapEkleButon;


    AdminKitaplarSayfasi(){
        setTitle("Kayıtlı Kitaplar Sayfası");
        setSize(800,450);
        setLocationRelativeTo(null);
        add(kitaplariGoruntulePanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        DefaultTableModel kitapVeriModeli = (DefaultTableModel) kitaplarTablosu.getModel();
        VeriTabani db = new VeriTabani();
        kitaplarTablosu.setModel(db.kitaplar(kitapVeriModeli));


        kitapEkleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                KitapEklemeSayfasi kitapEklemeSayfasi = new KitapEklemeSayfasi();
                kitapEklemeSayfasi.setVisible(true);
            }
        });


        kitapSilButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kitapISBN= JOptionPane.showInputDialog("Lütfen ISBN No giriniz: ");
                if (!(kitapISBN.equals(""))){
                    db.kitapSil(kitapISBN);
                    kitapVeriModeli.setRowCount(0);
                    kitapVeriModeli.setColumnCount(0);
                    kitaplarTablosu.setModel(db.kitaplar(kitapVeriModeli));
                    JOptionPane.showMessageDialog(kitaplariGoruntulePanel, "Kitap Silindi!");
                }
                else{
                    JOptionPane.showMessageDialog(kitaplariGoruntulePanel, "Lütfen kitap ISBN bilgisini giriniz");
                }
            }
        });


        kitapGuncelleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminKitapGuncelleSayfasi adminKitapGuncelleSayfasi = new AdminKitapGuncelleSayfasi();
                adminKitapGuncelleSayfasi.setVisible(true);
            }
        });
    }
}
