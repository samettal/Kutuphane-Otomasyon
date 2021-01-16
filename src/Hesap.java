/*
@Authors: BurakYuksek, SametAltuntas
*/

public abstract class Hesap {
    private String isim;
    private String soyisim;
    private String kullaniciNo;
    private String parola;
    private String unvan;


    public Hesap(String isim, String soyisim, String parola, String kullaniciNo, String unvan) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.kullaniciNo = kullaniciNo;
        this.parola = parola;
        this.unvan = unvan;
    }

    //setter ve getter:
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }
    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getKullaniciNo() {
        return kullaniciNo;
    }
    public void setKullaniciNo(String kullaniciNo) {
        this.kullaniciNo = kullaniciNo;
    }

    public String getParola() {
        return parola;
    }
    public void setParola(String password) {
        this.parola = parola;
    }

    public String getUnvan() {
        return unvan;
    }
    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }
    
}
