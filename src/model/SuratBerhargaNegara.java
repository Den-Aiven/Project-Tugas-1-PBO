package model;

public class SuratBerhargaNegara {
    private String nama;
    private double bunga;
    private int jangkaWaktu;
    private String tanggalJatuhTempo;
    private double kuotaNasional;

    public SuratBerhargaNegara(String nama, double bunga, int jangkaWaktu, String tanggalJatuhTempo, double kuotaNasional) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.kuotaNasional = kuotaNasional;
    }

    // Getter
    public double getBunga() {
        return bunga;
    }

    public int getJangkaWaktu() {
        return jangkaWaktu;
    }

    public double getKuotaNasional() {
        return kuotaNasional;
    }

    // Setter
    public void setKuotaNasional(double kuotaNasional) {
        this.kuotaNasional = kuotaNasional;
    }

    @Override
    public String toString() {
        return String.format("%s - Bunga: %.2f%% - Kuota: Rp%s", nama, bunga, kuotaNasional);
    }
}
