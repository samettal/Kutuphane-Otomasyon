/*
@Authors: BurakYuksek - SametAltuntas
*/

public class Kullanici extends Hesap {

    Kullanici(String ad, String soyad, String sifre, String kullaniciNo, String unvan){
        super(ad, soyad, sifre, kullaniciNo, unvan);
        System.out.println("Kullanıcı oluşturuldu.");
    }
}
