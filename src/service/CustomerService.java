package service;

import model.*;
import util.DataStore;

public class CustomerService {

    public static void menuCustomer() {
        while (true) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. Beli Saham");
            System.out.println("2. Jual Saham");
            System.out.println("3. Beli SBN");
            System.out.println("4. Simulasi SBN");
            System.out.println("5. Lihat Portofolio");
            System.out.println("6. Setor Saldo");
            System.out.println("7. Tarik Saldo");
            System.out.println("8. Logout");
            System.out.print("Pilih: ");
            int pilih = 0;

            try {
                pilih = Integer.parseInt(DataStore.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Peringatan: Pilihan harus berupa angka.");
                continue;
            }

            CustomerPortofolio p = DataStore.portofolios.get(DataStore.currentUser);

            try {
                if (pilih == 1) {
                    DataStore.daftarSaham.forEach((k, v) -> System.out.println(v));
                    System.out.print("Kode Saham (0 untuk kembali): ");
                    String kode = DataStore.scanner.nextLine().toUpperCase();
                    if (kode.equals("0")) continue;

                    if (DataStore.daftarSaham.containsKey(kode)) {
                        System.out.print("Jumlah Lembar (0 untuk batal): ");
                        int jumlah = Integer.parseInt(DataStore.scanner.nextLine());
                        if (jumlah == 0) continue;

                        if (jumlah <= 0) {
                            System.out.println("Peringatan: Jumlah lembar harus lebih dari 0.");
                            continue;
                        }

                        Saham s = DataStore.daftarSaham.get(kode);
                        double total = s.harga * jumlah;

                        if (total > p.saldo) {
                            System.out.println("❌ Saldo tidak mencukupi. Dibutuhkan: Rp" + total + ", Saldo Anda: Rp" + p.saldo);
                            continue;
                        }

                        p.sahamDimiliki.put(kode, p.sahamDimiliki.getOrDefault(kode, 0) + jumlah);
                        p.nominalPembelianSaham.put(kode, p.nominalPembelianSaham.getOrDefault(kode, 0.0) + total);
                        p.saldo -= total;
                        System.out.println("✅ Berhasil membeli saham " + kode);
                    } else {
                        System.out.println("Peringatan: Kode saham tidak ditemukan.");
                    }
                } else if (pilih == 2) {
                    p.sahamDimiliki.forEach((k, v) -> System.out.println(k + " - " + v + " lembar"));
                    System.out.print("Kode Saham (0 untuk kembali): ");
                    String kode = DataStore.scanner.nextLine().toUpperCase();
                    if (kode.equals("0")) continue;

                    if (p.sahamDimiliki.containsKey(kode)) {
                        System.out.print("Jumlah Lembar (0 untuk batal): ");
                        int jumlah = Integer.parseInt(DataStore.scanner.nextLine());
                        if (jumlah == 0) continue;

                        int dimiliki = p.sahamDimiliki.get(kode);
                        if (jumlah <= dimiliki) {
                            p.sahamDimiliki.put(kode, dimiliki - jumlah);
                            p.jumlahSahamTerjual.put(kode, p.jumlahSahamTerjual.getOrDefault(kode, 0) + jumlah);
                            if (p.sahamDimiliki.get(kode) == 0) {
                                p.sahamDimiliki.remove(kode);
                                p.nominalPembelianSaham.remove(kode);
                            }
                            double nilaiJual = DataStore.daftarSaham.get(kode).harga * jumlah;
                            p.saldo += nilaiJual;
                            System.out.println("✅ Berhasil menjual " + jumlah + " lembar saham " + kode);
                        } else {
                            System.out.println("Peringatan: Jumlah melebihi yang dimiliki!");
                        }
                    } else {
                        System.out.println("Peringatan: Kode saham tidak ditemukan dalam portofolio Anda.");
                    }
                } else if (pilih == 3) {
                    DataStore.daftarSBN.forEach((k, v) -> System.out.println(v));
                    System.out.print("Nama SBN (0 untuk kembali): ");
                    String nama = DataStore.scanner.nextLine().toUpperCase();
                    if (nama.equals("0")) continue;

                    if (DataStore.daftarSBN.containsKey(nama)) {
                        System.out.print("Nominal Pembelian (0 untuk batal): ");
                        double nominal = Double.parseDouble(DataStore.scanner.nextLine());
                        if (nominal == 0) continue;

                        SuratBerhargaNegara sbn = DataStore.daftarSBN.get(nama);

                        if (nominal > p.saldo) {
                            System.out.println("❌ Saldo tidak mencukupi. Dibutuhkan: Rp" + nominal + ", Saldo Anda: Rp" + p.saldo);
                            continue;
                        }

                        if (nominal <= sbn.kuotaNasional) {
                            sbn.kuotaNasional -= nominal;
                            p.sbnDimiliki.put(nama, p.sbnDimiliki.getOrDefault(nama, 0.0) + nominal);
                            p.saldo -= nominal;
                            System.out.println("✅ Berhasil membeli SBN " + nama);
                        } else {
                            System.out.println("Peringatan: Kuota tidak mencukupi!");
                        }
                    } else {
                        System.out.println("Peringatan: Nama SBN tidak ditemukan.");
                    }
                } else if (pilih == 4) {
                    DataStore.daftarSBN.forEach((k, v) -> System.out.println(v));
                    System.out.print("Nama SBN (0 untuk kembali): ");
                    String nama = DataStore.scanner.nextLine().toUpperCase();
                    if (nama.equals("0")) continue;

                    if (DataStore.daftarSBN.containsKey(nama)) {
                        System.out.print("Nominal Investasi (0 untuk batal): ");
                        double nominal = Double.parseDouble(DataStore.scanner.nextLine());
                        if (nominal == 0) continue;

                        SuratBerhargaNegara sbn = DataStore.daftarSBN.get(nama);
                        double bungaBulanan = sbn.bunga / 12 / 100 * 0.9 * nominal;
                        System.out.printf("Estimasi bunga/bulan: Rp%.2f\n", bungaBulanan);
                    } else {
                        System.out.println("Peringatan: Nama SBN tidak ditemukan.");
                    }
                } else if (pilih == 5) {
                    System.out.println("\n--- Portofolio Saham ---");
                    for (String kode : p.sahamDimiliki.keySet()) {
                        int jumlahSekarang = p.sahamDimiliki.get(kode);
                        double totalBeli = p.nominalPembelianSaham.get(kode);
                        double hargaSekarang = DataStore.daftarSaham.get(kode).harga;
                        double nilaiPasar = hargaSekarang * jumlahSekarang;

                        int jumlahTotalBeli = jumlahSekarang + p.jumlahSahamTerjual.getOrDefault(kode, 0);
                        double hargaBeliPerLembar = jumlahTotalBeli > 0 ? totalBeli / jumlahTotalBeli : 0;

                        System.out.println(String.format(
                                "%s: %d lembar, Nilai Pasar: Rp%.2f",
                                kode, jumlahSekarang, nilaiPasar
                        ));
                    }
                    System.out.println("\n--- Portofolio SBN ---");
                    for (String nama : p.sbnDimiliki.keySet()) {
                        double nominal = p.sbnDimiliki.get(nama);
                        double bunga = DataStore.daftarSBN.get(nama).bunga / 12 / 100 * 0.9 * nominal;
                        System.out.println(String.format(nama + ": Nominal Rp%.2f, Bunga/Bulan: Rp%.2f", nominal, bunga));
                    }
                    System.out.println("\nSaldo: Rp" + p.saldo);
                } else if (pilih == 6) {
                    System.out.print("Masukkan jumlah yang ingin disetor (0 untuk batal): ");
                    double jumlahSetor = Double.parseDouble(DataStore.scanner.nextLine());
                    if (jumlahSetor == 0) continue;

                    if (jumlahSetor > 0) {
                        p.saldo += jumlahSetor;
                        System.out.println("✅ Berhasil setor saldo sebesar Rp" + jumlahSetor);
                    } else {
                        System.out.println("⚠ Jumlah harus lebih dari 0.");
                    }
                } else if (pilih == 7) {
                    System.out.print("Masukkan jumlah yang ingin ditarik (0 untuk batal): ");
                    double jumlahTarik = Double.parseDouble(DataStore.scanner.nextLine());
                    if (jumlahTarik == 0) continue;

                    if (jumlahTarik > 0) {
                        if (jumlahTarik <= p.saldo) {
                            p.saldo -= jumlahTarik;
                            System.out.println("✅ Berhasil tarik saldo sebesar Rp" + jumlahTarik);
                        } else {
                            System.out.println("❌ Saldo tidak mencukupi untuk penarikan.");
                        }
                    } else {
                        System.out.println("⚠ Jumlah harus lebih dari 0.");
                    }
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Peringatan: Format input tidak valid. Pastikan angka dimasukkan dengan benar.");
            }
        }
    }
}
