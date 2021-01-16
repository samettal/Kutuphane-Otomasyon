/*
@Authors: BurakYuksek - SametAltuntas
*/

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class VeriTabani implements IKitap, IKullanici {
    private Connection con = null;   //Encapsulation kullanımına bir örnek
    public Statement stat = null;
    public ResultSet rs = null;
    public String[] veriler = new String[5];


    public VeriTabani(){
        this.connect();
    }


    public void connect(){
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);   //driver değişkenini Java'da gömülü olarak gelen forName metoduna gönderdik
            String veriTabaniIsmi = "kutuphane_otomasyon";
            String url = "jdbc:mysql://localhost:3306/" + veriTabaniIsmi + "?serverTimezone=UTC";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);
            stat = con.createStatement();
            System.out.println("Veritabanı bağlantısı başarılı");

        }
        catch (Exception e){
            System.out.println("Veritabanı bağlantısı başarısız");
            System.err.println("Hata" + e);
        }
    }


    public DefaultTableModel kitaplar(DefaultTableModel dataModel){
        connect();
        try{
            //Tablolardan kitaplar tablosu seçiliyor
            rs = stat.executeQuery("SELECT * FROM kitaplar");
            //Veritabanı teknik bilgileri alınıyor (satır/sütun sayısı vs.)
            ResultSetMetaData md = rs.getMetaData();

            //Tablonun sütun sayısı sutunAdedi değişkenine atıldı ve sutünlardaki veriler tek tek okunarak eklendi
            int sutunAdedi = md.getColumnCount();
            for(int i = 1; i <= sutunAdedi; i++){
                dataModel.addColumn(md.getColumnName(i));
            }

            Vector<String> row;   //String tipinde bir vektör oluşturup adına row dedik

            while(rs.next()){   //İçerideki veri bitene kadar okuyoruz
                row = new Vector<>(sutunAdedi);   //Oluşturduğumuz row değişkenine sütun sayısı kadar vektör oluşturup row'a attık
                for(int i = 1; i<= sutunAdedi; i++){
                    row.add(rs.getString(i));   //Satırlara verileri yerleştirdik
                }
                //Bütün veriyi row değişkeni ile dataModel e ekledik
                dataModel.addRow(row);
            }
        }
        catch (SQLException e) {
            System.out.println("Hata: " + e);
        }

        closeDb();   //veritabanı bağlantısını kapatıyoruz
        return dataModel;
    }


    @Override
    public void kitapEkle(Kitap kitap){
        connect();
        try {
            //Sisteme yeni bir kitap nesnesi ekleniyor
            String query = "INSERT INTO kitaplar(kitap_adi, yazari, yayin_tarihi, yayinevi, ISBN) VALUES (?,?,?,?,?);";
            PreparedStatement p_stat = con.prepareStatement(query);
            p_stat.setString(1, kitap.getKitapAdi());
            p_stat.setString(2, kitap.getYazarAdi());
            p_stat.setString(3, kitap.getYayinTarihi());
            p_stat.setString(4, kitap.getYayinci());
            p_stat.setString(5, kitap.getIsbn());
            p_stat.executeUpdate();
            p_stat.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void kitapSil(String ISBN) {
        connect();
        try{
            //Sistemdeki mevcut bir kitap ISBN numarası ile bulunup siliniyor
            String query = "DELETE FROM kitaplar WHERE ISBN=?;";
            PreparedStatement p_stat = con.prepareStatement(query);
            p_stat.setString(1, ISBN);
            p_stat.executeUpdate();
            p_stat.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void kitapGuncelle(String yeniKitapAdi, String yeniYazaradi, String yeniYayinevi, String yeniISBN) {
        connect();
        try{
            //Eski bir kitap ISBN numarası ile bulunup yeni veriler ile güncelleniyor
            String query = "UPDATE kitaplar SET kitap_adi=?, yazari=?, yayinevi=? WHERE ISBN=?";
            PreparedStatement p_stat = con.prepareStatement(query);
            p_stat.setString(1, yeniKitapAdi);
            p_stat.setString(2, yeniYazaradi);
            p_stat.setString(3, yeniYayinevi);
            p_stat.setString(4, yeniISBN);

            p_stat.executeUpdate();
            con.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public DefaultTableModel kullanicilariGoruntule(DefaultTableModel dataModel){
        connect();
        try{
            //Veritabanındaki tablolardan kullanicilar tablosu seçiliyor
            rs = stat.executeQuery("SELECT * FROM kullanicilar");
            //Veritabanı teknik bilgileri alınıyor (satır/sütun sayısı vs.)
            ResultSetMetaData md = rs.getMetaData();

            //Tablonun sütun sayısı sutunAdedi değişkenine atıldı ve sutünlardaki veriler tek tek okunarak eklendi
            int sutunAdedi = md.getColumnCount();
            for(int i = 1; i <= sutunAdedi; i++){
                dataModel.addColumn(md.getColumnName(i));
            }

            Vector<String> row;   //String tipinde bir vektör oluşturup adına row dedik
            while(rs.next()){
                row = new Vector<>(sutunAdedi);
                for(int i = 1; i<= sutunAdedi; i++){
                    row.add(rs.getString(i));
                }
                dataModel.addRow(row);
            }
        }
        catch (SQLException e) {
            System.out.println("Hata: " + e);
        }

        closeDb();
        return dataModel;
    }


    public void kullaniciEkle(Kullanici kullanici){
        connect();
        try {
            //Veritabanındaki kullanicilar tablosuna bilgileri girilerek yeni kullanıcı eklenir
            String query = "INSERT INTO kullanicilar(isim, soy_isim, parola, kullanici_no, unvan) VALUES (?,?,?,?,?);";
            PreparedStatement p_stat = con.prepareStatement(query);
            p_stat.setString(1, kullanici.getIsim());
            p_stat.setString(2, kullanici.getSoyisim());
            p_stat.setString(3, kullanici.getParola());
            p_stat.setString(4, kullanici.getKullaniciNo());
            p_stat.setString(5, kullanici.getUnvan());
            int status = p_stat.executeUpdate();
            p_stat.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

//Admin kullanıcı arama yetkisine sahip. Bu metot sayesinde kullaniciNo bilgisinden kullanıcı bilgilerine erişebilmekte
    public String[] kullaniciNoIleAra(String kullaniciNo) {
        connect();
        try{
            String query = "SELECT * FROM kullanicilar WHERE kullanici_no="+ kullaniciNo; //kullanıcı numarasını arıyor kullanıcılarda
            rs = stat.executeQuery(query);   //Sorgu yapıyor
            while(rs.next()){   //Veri olduğu sürece devam et
                //Verileri alıp diziye atıyor
                String isim = rs.getString("isim");
                String soy_isim = rs.getString("soy_isim");
                String parola = rs.getString("parola");
                String kullanici_no = rs.getString("kullanici_no");
                String unvan = rs.getString("unvan");
                veriler[0] = isim;
                veriler[1] = soy_isim;
                veriler[2] = parola;
                veriler[3] = kullanici_no;
                veriler[4] = unvan;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return veriler;
    }

//Admin kullanıcı silme yetkisine sahip bu metotla kullanıcı no ile sistemden bir kullanıcıyı silebilir
    public void kullaniciSil(String kullaniciNo) {
        connect();
        try{
            String query = "DELETE FROM kullanicilar WHERE kullanici_no=?;";
            PreparedStatement p_stat = con.prepareStatement(query);
            p_stat.setString(1, kullaniciNo);
            p_stat.executeUpdate();
            p_stat.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void closeDb(){
        try{
            con.close();
            stat.close();
        }
        catch(Exception e){
            System.err.println("Hata" + e);
        }
    }
}
