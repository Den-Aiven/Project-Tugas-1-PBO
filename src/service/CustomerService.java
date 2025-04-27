package service;

import model.*;
import util.DataStore;
import util.Validasi;

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
            int pilih = Validasi.inputInteger("Pilih menu: ");

            if (pilih == -1) continue;

            CustomerPortofolio p = DataStore.portofolios.get(DataStore.currentUser);
            if (p == null) {
                System.out.println("Error: Portofolio tidak ditemukan.");
                continue;
            }

            switch (pilih) {
                case 1: beliSaham(p); break;
                case 2: jualSaham(p); break;
                case 3: beliSBN(p); break;
                case 4: simulasiSBN(); break;
                case 5: lihatPortofolio(p); break;
                case 6: setorSaldo(p); break;
                case 7: tarikSaldo(p); break;
                case 8: return;
                default:
                    System.out.println("Peringatan: Pilihan tidak valid.");
                    break;
            }
        }
    }

    private static void beliSaham(CustomerPortofolio p) {
        DataStore.daftarSaham.forEach((k, v) -> System.out.println(v));
        System.out.print("Kode Saham (0 untuk kembali): ");
        String kode = DataStore.scanner.nextLine().toUpperCase();
        if (kode.equals("0")) return;

        if (Validasi.isValidSahamCode(kode)) {
            int jumlah = Validasi.inputInteger("Jumlah Lembar (0 untuk batal): ");
            if (jumlah <= 0) return;

            Saham s = DataStore.daftarSaham.get(kode);
            double total = s.getHarga() * jumlah;

            if (total > p.saldo) {
                System.out.println("❌ Saldo tidak mencukupi. Dibutuhkan: Rp" + total + ", Saldo Anda: Rp" + p.saldo);
                return;
            }

            p.sahamDimiliki.put(kode, p.sahamDimiliki.getOrDefault(kode, 0) + jumlah);
            p.nominalPembelianSaham.put(kode, p.nominalPembelianSaham.getOrDefault(kode, 0.0) + total);
            p.saldo -= total;
            System.out.println("✅ Berhasil membeli saham " + kode);
        } else {
            System.out.println("Peringatan: Kode saham tidak ditemukan.");
        }
    }

    private static void jualSaham(CustomerPortofolio p) {
        p.sahamDimiliki.forEach((k, v) -> System.out.println(k + " - " + v + " lembar"));
        System.out.print("Kode Saham (0 untuk kembali): ");
        String kode = DataStore.scanner.nextLine().toUpperCase();
        if (kode.equals("0")) return;

        if (p.sahamDimiliki.containsKey(kode)) {
            int jumlah = Validasi.inputInteger("Jumlah Lembar (0 untuk batal): ");
            if (jumlah <= 0) return;

            int dimiliki = p.sahamDimiliki.get(kode);
            if (jumlah <= dimiliki) {
                p.sahamDimiliki.put(kode, dimiliki - jumlah);
                p.jumlahSahamTerjual.put(kode, p.jumlahSahamTerjual.getOrDefault(kode, 0) + jumlah);
                if (p.sahamDimiliki.get(kode) == 0) {
                    p.sahamDimiliki.remove(kode);
                    p.nominalPembelianSaham.remove(kode);
                }
                double nilaiJual = DataStore.daftarSaham.get(kode).getHarga() * jumlah;
                p.saldo += nilaiJual;
                System.out.println("✅ Berhasil menjual " + jumlah + " lembar saham " + kode);
            } else {
                System.out.println("Peringatan: Jumlah melebihi yang dimiliki!");
            }
        } else {
            System.out.println("Peringatan: Kode saham tidak ditemukan dalam portofolio Anda.");
        }
    }

    private static void beliSBN(CustomerPortofolio p) {
        DataStore.daftarSBN.forEach((k, v) -> System.out.println(v));
        System.out.print("Nama SBN (0 untuk kembali): ");
        String nama = DataStore.scanner.nextLine().toUpperCase();
        if (nama.equals("0")) return;

        if (DataStore.daftarSBN.containsKey(nama)) {
            double nominal = Validasi.inputDouble("Nominal Pembelian (0 untuk batal): ");
            if (nominal == 0) return;

            SuratBerhargaNegara sbn = DataStore.daftarSBN.get(nama);

            if (nominal > p.saldo) {
                System.out.println("❌ Saldo tidak mencukupi. Dibutuhkan: Rp" + nominal + ", Saldo Anda: Rp" + p.saldo);
                return;
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
    }

    private static void simulasiSBN() {
        DataStore.daftarSBN.forEach((k, v) -> System.out.println(v));
        System.out.print("Nama SBN (0 untuk kembali): ");
        String nama = DataStore.scanner.nextLine().toUpperCase();
        if (nama.equals("0")) return;

        if (DataStore.daftarSBN.containsKey(nama)) {
            double nominal = Validasi.inputDouble("Nominal Investasi (0 untuk batal): ");
            if (nominal == 0) return;

            SuratBerhargaNegara sbn = DataStore.daftarSBN.get(nama);
            double bungaBulanan = sbn.bunga / 12 / 100 * 0.9 * nominal;
            System.out.printf("Estimasi bunga/bulan: Rp%.2f\n", bungaBulanan);
        } else {
            System.out.println("Peringatan: Nama SBN tidak ditemukan.");
        }
    }

    private static void lihatPortofolio(CustomerPortofolio p) {
        System.out.println("\n--- Portofolio Saham ---");
        for (String kode : p.sahamDimiliki.keySet()) {
            int jumlahSekarang = p.sahamDimiliki.get(kode);
            double hargaSekarang = DataStore.daftarSaham.get(kode).getHarga();
            double nilaiPasar = hargaSekarang * jumlahSekarang;
            System.out.printf("%s: %d lembar, Nilai Pasar: Rp%.2f\n", kode, jumlahSekarang, nilaiPasar);
        }

        System.out.println("\n--- Portofolio SBN ---");
        for (String nama : p.sbnDimiliki.keySet()) {
            double nominal = p.sbnDimiliki.get(nama);
            double bunga = DataStore.daftarSBN.get(nama).bunga / 12 / 100 * 0.9 * nominal;
            System.out.printf("%s: Nominal Rp%.2f, Bunga/Bulan: Rp%.2f\n", nama, nominal, bunga);
        }
        System.out.println("\nSaldo: Rp" + p.saldo);
    }

    private static void setorSaldo(CustomerPortofolio p) {
        double jumlahSetor = Validasi.inputDouble("Jumlah Saldo yang ingin disetor: ");
        if (jumlahSetor > 0) {
            p.saldo += jumlahSetor;
            System.out.println("✅ Berhasil menyetor saldo: Rp" + jumlahSetor);
        } else {
            System.out.println("Peringatan: Jumlah setor saldo tidak valid.");
        }
    }

    private static void tarikSaldo(CustomerPortofolio p) {
        double jumlahTarik = Validasi.inputDouble("Jumlah Saldo yang ingin ditarik: ");
        if (jumlahTarik > 0 && jumlahTarik <= p.saldo) {
            p.saldo -= jumlahTarik;
            System.out.println("✅ Berhasil menarik saldo: Rp" + jumlahTarik);
        } else {
            System.out.println("Peringatan: Jumlah tarik saldo tidak valid atau saldo tidak mencukupi.");
        }
    }
}
