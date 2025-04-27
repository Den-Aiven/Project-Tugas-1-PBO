package service;

import model.*;
import util.DataStore;
import util.Validasi;

public class AdminService {

    public static void menuAdmin() {
        while (true) {
            tampilkanMenu();
            int pilih = Validasi.inputAngka("Pilih: ");

            switch (pilih) {
                case 1:
                    tambahSaham();
                    break;
                case 2:
                    ubahHargaSaham();
                    break;
                case 3:
                    tambahSBN();
                    break;
                case 4:
                    lihatDaftarProduk();
                    break;
                case 5:
                    hapusProduk();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("⚠ Peringatan: Pilihan tidak valid.");
            }
        }
    }

    private static void tampilkanMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Tambah Saham");
        System.out.println("2. Ubah Harga Saham");
        System.out.println("3. Tambah SBN");
        System.out.println("4. Lihat Daftar Produk Investasi");
        System.out.println("5. Hapus Produk Investasi");
        System.out.println("6. Logout");
    }

    private static void tambahSaham() {
        System.out.println("\n--- Tambah Saham --- (ketik 0 untuk kembali)");
        String kode = Validasi.inputString("Kode Saham: ");
        if (kode.equals("0")) return;
        if (DataStore.daftarSaham.containsKey(kode)) {
            System.out.println("⚠ Peringatan: Kode saham sudah terdaftar.");
            return;
        }

        String nama = Validasi.inputString("Nama Perusahaan: ");
        if (nama.equals("0")) return;

        double harga = Validasi.inputDoublePositif("Harga: ");
        if (harga < 0) return;

        DataStore.daftarSaham.put(kode, new Saham(kode, nama, harga));
        System.out.println("✅ Saham berhasil ditambahkan.");
    }

    private static void ubahHargaSaham() {
        if (DataStore.daftarSaham.isEmpty()) {
            System.out.println("❌ Tidak ada data saham.");
            return;
        }

        DataStore.daftarSaham.forEach((k, v) -> System.out.println(v));
        System.out.println("\n--- Ubah Harga Saham --- (ketik 0 untuk kembali)");
        String kode = Validasi.inputString("Kode Saham: ");
        if (kode.equals("0")) return;

        Saham saham = DataStore.daftarSaham.get(kode);
        if (saham != null) {
            double hargaBaru = Validasi.inputDoublePositif("Harga Baru: ");
            if (hargaBaru < 0) return;
            saham.setHarga(hargaBaru);
            System.out.println("✅ Harga saham berhasil diperbarui.");
        } else {
            System.out.println("⚠ Peringatan: Kode saham tidak ditemukan.");
        }
    }

    private static void tambahSBN() {
        System.out.println("\n--- Tambah SBN --- (ketik 0 untuk kembali)");
        String nama = Validasi.inputString("Nama SBN: ");
        if (nama.equals("0")) return;
        if (DataStore.daftarSBN.containsKey(nama)) {
            System.out.println("⚠ Peringatan: SBN dengan nama tersebut sudah ada.");
            return;
        }

        double bunga = Validasi.inputDoublePositif("Bunga (%): ");
        if (bunga < 0) return;

        int jangka = Validasi.inputAngka("Jangka Waktu (tahun): ");
        if (jangka <= 0) {
            System.out.println("⚠ Peringatan: Jangka waktu harus lebih dari 0.");
            return;
        }

        String tgl = Validasi.inputString("Tanggal Jatuh Tempo: ");
        if (tgl.equals("0")) return;

        double kuota = Validasi.inputDoublePositif("Kuota Nasional: ");
        if (kuota < 0) return;

        DataStore.daftarSBN.put(nama, new SuratBerhargaNegara(nama, bunga, jangka, tgl, kuota));
        System.out.println("✅ SBN berhasil ditambahkan.");
    }

    private static void lihatDaftarProduk() {
        System.out.println("\n--- Daftar Saham ---");
        if (DataStore.daftarSaham.isEmpty()) {
            System.out.println("❌ Tidak ada data saham.");
        } else {
            for (Saham saham : DataStore.daftarSaham.values()) {
                System.out.println(saham);
            }
        }

        System.out.println("\n--- Daftar SBN ---");
        if (DataStore.daftarSBN.isEmpty()) {
            System.out.println("❌ Tidak ada data SBN.");
        } else {
            for (SuratBerhargaNegara sbn : DataStore.daftarSBN.values()) {
                System.out.println(sbn);
            }
        }
    }

    private static void hapusProduk() {
        System.out.println("\n--- Hapus Produk Investasi ---");
        System.out.println("1. Hapus Saham");
        System.out.println("2. Hapus SBN");
        int jenis = Validasi.inputAngka("Pilih jenis yang ingin dihapus (0 untuk kembali): ");
        if (jenis == 0) return;

        switch (jenis) {
            case 1:
                String kode = Validasi.inputString("Masukkan kode saham yang ingin dihapus (0 untuk kembali): ");
                if (kode.equals("0")) return;
                if (DataStore.daftarSaham.remove(kode) != null) {
                    System.out.println("✅ Saham berhasil dihapus.");
                } else {
                    System.out.println("⚠ Kode saham tidak ditemukan.");
                }
                break;
            case 2:
                String nama = Validasi.inputString("Masukkan nama SBN yang ingin dihapus (0 untuk kembali): ");
                if (nama.equals("0")) return;
                if (DataStore.daftarSBN.remove(nama) != null) {
                    System.out.println("✅ SBN berhasil dihapus.");
                } else {
                    System.out.println("⚠ Nama SBN tidak ditemukan.");
                }
                break;
            default:
                System.out.println("⚠ Pilihan tidak valid.");
        }
    }
}
