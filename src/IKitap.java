/*
@Authors: BurakYuksek - SametAltuntas
*/

public interface IKitap {
    void kitapEkle(Kitap kitap);
    void kitapSil(String ISBN);
    void kitapGuncelle(String yeniKitapAdi, String yeniYazaradi, String yeniYayinevi, String yeniIsbn);
}
