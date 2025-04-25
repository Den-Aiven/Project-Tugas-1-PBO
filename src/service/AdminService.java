package service;

import model.*;
import util.DataStore;

public class AdminService {
    public static void menuAdmin() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Tambah Saham");
            System.out.println("2. Ubah Harga Saham");
            System.out.println("3. Tambah SBN");
            System.out.println("4. Lihat Daftar Produk Investasi");
            System.out.println("5. Hapus Produk Investasi");
            System.out.println("6. Logout");

            System.out.print("Pilih: ");
            int pilih = 0;

            try {
                pilih = Integer.parseInt(DataStore.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Peringatan: Pilihan harus berupa angka.");
                continue;
            }

            try {
                if (pilih == 1) {
                    DataStore.daftarSaham.forEach((k, v) -> System.out.println(v));
                    System.out.println("\n--- Tambah Saham --- (ketik 0 untuk kembali)");
                    System.out.print("Kode Saham: ");
                    String kode = DataStore.scanner.nextLine();
                    if (kode.equals("0")) continue;

                    System.out.print("Nama Perusahaan: ");
                    String nama = DataStore.scanner.nextLine();
                    if (nama.equals("0")) continue;

                    System.out.print("Harga: ");
                    String hargaInput = DataStore.scanner.nextLine();
                    if (hargaInput.equals("0")) continue;
                    double harga = Double.parseDouble(hargaInput);

                    DataStore.daftarSaham.put(kode, new Saham(kode, nama, harga));
                }
                else if (pilih == 2) {
                    DataStore.daftarSaham.forEach((k, v) -> System.out.println(v));
                    System.out.println("\n--- Ubah Harga Saham --- (ketik 0 untuk kembali)");
                    System.out.print("Kode Saham: ");
                    String kode = DataStore.scanner.nextLine();
                    if (kode.equals("0")) continue;

                    if (DataStore.daftarSaham.containsKey(kode)) {
                        System.out.print("Harga Baru: ");
                        String hargaBaruInput = DataStore.scanner.nextLine();
                        if (hargaBaruInput.equals("0")) continue;
                        double harga = Double.parseDouble(hargaBaruInput);
                        DataStore.daftarSaham.get(kode).harga = harga;
                    } else {
                        System.out.println("Peringatan: Kode saham tidak ditemukan.");
                    }
                }
                else if (pilih == 3) {
                    DataStore.daftarSBN.forEach((k, v) -> System.out.println(v));
                    System.out.println("\n--- Tambah SBN --- (ketik 0 untuk kembali)");
                    System.out.print("Nama SBN: ");
                    String nama = DataStore.scanner.nextLine();
                    if (nama.equals("0")) continue;

                    System.out.print("Bunga (%): ");
                    String bungaInput = DataStore.scanner.nextLine();
                    if (bungaInput.equals("0")) continue;
                    double bunga = Double.parseDouble(bungaInput);

                    System.out.print("Jangka Waktu (tahun): ");
                    String jangkaInput = DataStore.scanner.nextLine();
                    if (jangkaInput.equals("0")) continue;
                    int jangka = Integer.parseInt(jangkaInput);

                    System.out.print("Tanggal Jatuh Tempo: ");
                    String tgl = DataStore.scanner.nextLine();
                    if (tgl.equals("0")) continue;

                    System.out.print("Kuota Nasional: ");
                    String kuotaInput = DataStore.scanner.nextLine();
                    if (kuotaInput.equals("0")) continue;
                    double kuota = Double.parseDouble(kuotaInput);

                    DataStore.daftarSBN.put(nama, new SuratBerhargaNegara(nama, bunga, jangka, tgl, kuota));
                }else if (pilih == 4) {
                    System.out.println("\n--- Daftar Saham ---");
                    if (DataStore.daftarSaham.isEmpty()) {
                        System.out.println("Tidak ada data saham.");
                    } else {
                        for (Saham saham : DataStore.daftarSaham.values()) {
                            System.out.println("Kode: " + saham.kode + ", Nama: " + saham.namaPerusahaan + ", Harga: " + saham.harga);
                        }
                    }

                    System.out.println("\n--- Daftar SBN ---");
                    if (DataStore.daftarSBN.isEmpty()) {
                        System.out.println("Tidak ada data SBN.");
                    } else {
                        for (SuratBerhargaNegara sbn : DataStore.daftarSBN.values()) {
                            System.out.println("Nama: " + sbn.nama + ", Bunga: " + sbn.bunga + "%, Jangka: " + sbn.jangkaWaktu +
                                    " tahun, Jatuh Tempo: " + sbn.tanggalJatuhTempo + ", Kuota: " + sbn.kuotaNasional);
                        }
                    }
                }
                else if (pilih == 5) {
                    System.out.println("\n--- Hapus Produk Investasi ---");
                    System.out.println("1. Hapus Saham");
                    System.out.println("2. Hapus SBN");
                    System.out.print("Pilih jenis yang ingin dihapus (0 untuk kembali): ");
                    String jenisInput = DataStore.scanner.nextLine();
                    if (jenisInput.equals("0")) continue;
                    int jenis = Integer.parseInt(jenisInput);

                    if (jenis == 1) {
                        System.out.println("\n--- Daftar Saham ---");
                        for (Saham s : DataStore.daftarSaham.values()) {
                            System.out.println("Kode: " + s.kode + ", Nama: " + s.namaPerusahaan + ", Harga: " + s.harga);
                        }
                        System.out.print("Masukkan kode saham yang ingin dihapus (0 untuk kembali): ");
                        String kode = DataStore.scanner.nextLine();
                        if (kode.equals("0")) continue;
                        if (DataStore.daftarSaham.remove(kode) != null) {
                            System.out.println("Saham berhasil dihapus.");
                        } else {
                            System.out.println("Kode saham tidak ditemukan.");
                        }

                    } else if (jenis == 2) {
                        System.out.println("\n--- Daftar SBN ---");
                        for (SuratBerhargaNegara sbn : DataStore.daftarSBN.values()) {
                            System.out.println("Nama: " + sbn.nama + ", Bunga: " + sbn.bunga + "%, Jangka: " + sbn.jangkaWaktu + " tahun, Jatuh Tempo: " + sbn.tanggalJatuhTempo);
                        }
                        System.out.print("Masukkan nama SBN yang ingin dihapus (0 untuk kembali): ");
                        String nama = DataStore.scanner.nextLine();
                        if (nama.equals("0")) continue;
                        if (DataStore.daftarSBN.remove(nama) != null) {
                            System.out.println("SBN berhasil dihapus.");
                        } else {
                            System.out.println("Nama SBN tidak ditemukan.");
                        }

                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                }

                else if (pilih == 6) {
                    break;
                } else {
                    System.out.println("Peringatan: Pilihan tidak valid. Silakan pilih antara 1 sampai 6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Peringatan: Format input tidak valid. Pastikan angka dimasukkan dengan benar.");
            }
        }
    }
}
