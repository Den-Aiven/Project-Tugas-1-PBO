package model;

public class Saham {
    public String kode, namaPerusahaan;
    public double harga;

    public Saham(String kode, String namaPerusahaan, double harga) {
        this.kode = kode;
        this.namaPerusahaan = namaPerusahaan;
        this.harga = harga;
    }

    public String toString() {
        return kode + " - " + namaPerusahaan + " - Rp" + harga;
    }
}