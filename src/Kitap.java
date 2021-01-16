/*
@Authors: BurakYuksek - SametAltuntas
*/

public class Kitap {
    private String kitapAdi;
    private String yazarAdi;
    private String yayinTarihi;
    private String yayinci;
    private String isbn;


    Kitap(String kitapAdi, String yazarAdi, String yayinTarihi, String yayinci, String isbn){
        this.kitapAdi= kitapAdi;
        this.yazarAdi = yazarAdi;
        this.yayinTarihi = yayinTarihi;
        this.yayinci = yayinci;
        this.isbn = isbn;

        System.out.println("Kitap eklendi.");
    }


    //setter ve getter:
    public String getKitapAdi() {
        return kitapAdi;
    }
    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getYazarAdi() {
        return yazarAdi;
    }
    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
    }

    public String getYayinTarihi() {
        return yayinTarihi;
    }
    public void setYayinTarihi(String yayinTarihi) {
        this.yayinTarihi = yayinTarihi;
    }

    public String getYayinci() {
        return yayinci;
    }
    public void setYayinci(String yayinci) {
        this.yayinci = yayinci;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
