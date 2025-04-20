package model;

public class SuratBerhargaNegara {
    public String nama;
    public double bunga;
    public int jangkaWaktu;
    public String tanggalJatuhTempo;
    public double kuotaNasional;

    public SuratBerhargaNegara(String nama, double bunga, int jangkaWaktu, String tanggalJatuhTempo, double kuotaNasional) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.kuotaNasional = kuotaNasional;
    }

    public String toString() {
        return nama + " - Bunga: " + bunga + "% - Kuota: Rp" + kuotaNasional;
    }
}