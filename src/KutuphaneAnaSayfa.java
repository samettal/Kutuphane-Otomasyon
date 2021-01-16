/*
@Author : BurakYuksek - SametAltuntas
*/

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KutuphaneAnaSayfa extends JFrame{

    private JPanel girisPanel;
    private JTextField kullaniciNoAlani;
    private JButton girisButon;
    private JButton kayitButon;
    private JTextField parolaAlani;
    private JLabel label1;
    private static KutuphaneAnaSayfa f = null;


    public KutuphaneAnaSayfa(){
        setTitle("Kütüphane Anasayfa");
        add(girisPanel);
        setSize(800,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        girisButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Kullanıcının girdiği kullanıcı no'yu ve parolayı alıyoruz
                String kullaniciNo = kullaniciNoAlani.getText();
                String parola = parolaAlani.getText();

                // Kullanıcının girdiği değerler boşsa kullanıcıya uyarı mesajı veriyoruz
                if(kullaniciNo.equals("")){
                    JOptionPane.showMessageDialog(null,"Lütfen kullanıcı no giriniz");
                }
                else if(parola.equals("")){
                    JOptionPane.showMessageDialog(null,"Lütfen parola giriniz");
                }
                else{
                    VeriTabani db = new VeriTabani(); //Veri tabanını çağırdık
                    try{
                        db.connect(); // Veri tabanına bağlandık
                        db.stat.executeUpdate("USE kutuphane_otomasyon"); //"kutuphane_otomasyon" adlı veritabanını kullan

                        //kullanıcının girdiği kullaniciNo ile passwordu veri tabanında sorgulatıyoruz
                        String query = ("SELECT * FROM kullanicilar WHERE kullanici_no='"+ kullaniciNo +"' AND parola='"+ parola +"'");
                        db.rs = db.stat.executeQuery(query);
                        if(!db.rs.next()){
                            System.out.print("Kullanıcı yok!");
                            JOptionPane.showMessageDialog(girisPanel,"Böyle bir kullanıcı yok, bilgilerini kontrol et!");
                        }
                        else{
                            db.rs.beforeFirst();  //Kullanıcı bilgileri veri tabanında eşleşmişse tüm veri tabanını taramasını istemiyoruz ve sorgunun başına dönüyoruz
                            while(db.rs.next())
                            {
                                String admin = db.rs.getString("Unvan"); //Kullanıcı admindir
                                if(admin.equals("admin")) { //Eğer eşleşirse true döndür
                                    adminFormunaGit(e); //Admin menüsünü açar
                                }
                                else{
                                    kullaniciFormunaGit(e); //Kullanıcı menüsünü açar
                                }
                            }
                        }
                    }
                    catch(Exception error){
                        System.err.println(error);
                    }
                }
            }
        });


        kayitButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                YeniKayitSayfasi yeniKayitSayfasi = new YeniKayitSayfasi();
                yeniKayitSayfasi.setVisible(true);
            }
        });
    }


    public static synchronized KutuphaneAnaSayfa getInstance(){
        try {
            if (f == null) {
                f = (KutuphaneAnaSayfa) Class.forName("KutuphaneAnaSayfa").newInstance();
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.toString());
        }
        return f;
    }


    private void adminFormunaGit(ActionEvent evt) {
        AdminSayfasi.getInstance().setVisible(true);
        this.dispose();
    }


    private void kullaniciFormunaGit(ActionEvent evt) {
        KullaniciAnaSayfa.getInstance().setVisible(true);
        this.dispose();
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> KutuphaneAnaSayfa.getInstance().setVisible(false));
    }


    private void createUIComponents() {
        label1 = new JLabel(new ImageIcon("kutuphane.png"));
    }
}

