package service;

import model.CustomerPortofolio;
import model.Saham;
import model.SuratBerhargaNegara;
import util.DataStore;
import util.Validasi;

public class CustomerService {

    public static void beliSaham(CustomerPortofolio p) {
        DataStore.daftarSaham.forEach((k, v) -> System.out.println(v));
        System.out.print("Kode Saham (0 untuk kembali): ");
        String kode = DataStore.scanner.nextLine().toUpperCase();
        if (kode.equals("0")) return;

        if (DataStore.daftarSaham.containsKey(kode)) {
            System.out.print("Jumlah Lembar (0 untuk batal): ");
            int jumlah = Validasi.inputInteger();
            if (jumlah <= 0) return;

            Saham s = DataStore.daftarSaham.get(kode);
            double total = s.getHarga() * jumlah;

            if (total > p.getSaldo()) {
                System.out.println("❌ Saldo tidak mencukupi. Dibutuhkan: Rp" + total + ", Saldo Anda: Rp" + p.getSaldo());
                return;
            }
            p.getSahamDimiliki().put(kode, p.getSahamDimiliki().getOrDefault(kode, 0) + jumlah);
            p.getNominalPembelianSaham().put(kode, p.getNominalPembelianSaham().getOrDefault(kode, 0.0) + total);
            p.setSaldo(p.getSaldo() - total);

            System.out.println("✅ Berhasil membeli saham " + kode);
        } else {
            System.out.println("Peringatan: Kode saham tidak ditemukan.");
        }
    }

    public static void jualSaham(CustomerPortofolio p) {
        p.getSahamDimiliki().forEach((k, v) -> System.out.println(k + " - " + v + " lembar"));
        System.out.print("Kode Saham (0 untuk kembali): ");
        String kode = DataStore.scanner.nextLine().toUpperCase();
        if (kode.equals("0")) return;

        if (p.getSahamDimiliki().containsKey(kode)) {
            System.out.print("Jumlah Lembar (0 untuk batal): ");
            int jumlah = Validasi.inputInteger();
            if (jumlah <= 0) return;

            int dimiliki = p.getSahamDimiliki().get(kode);
            if (jumlah <= dimiliki) {
                p.getSahamDimiliki().put(kode, dimiliki - jumlah);
                p.getJumlahSahamTerjual().put(kode, p.getJumlahSahamTerjual().getOrDefault(kode, 0) + jumlah);

                if (p.getSahamDimiliki().get(kode) == 0) {
                    p.getSahamDimiliki().remove(kode);
                    p.getNominalPembelianSaham().remove(kode);
                }

                double nilaiJual = DataStore.daftarSaham.get(kode).getHarga() * jumlah;
                p.setSaldo(p.getSaldo() + nilaiJual);

                System.out.println("✅ Berhasil menjual " + jumlah + " lembar saham " + kode);
            } else {
                System.out.println("Peringatan: Jumlah melebihi yang dimiliki!");
            }
        } else {
            System.out.println("Peringatan: Kode saham tidak ditemukan dalam portofolio Anda.");
        }
    }

    public static void beliSBN(CustomerPortofolio p) {
        DataStore.daftarSBN.forEach((k, v) -> System.out.println(v));
        System.out.print("Nama SBN (0 untuk kembali): ");
        String nama = DataStore.scanner.nextLine().toUpperCase();
        if (nama.equals("0")) return;

        if (DataStore.daftarSBN.containsKey(nama)) {
            System.out.print("Nominal Pembelian (0 untuk batal): ");
            double nominal = Validasi.inputDouble();
            if (nominal <= 0) return;

            SuratBerhargaNegara sbn = DataStore.daftarSBN.get(nama);

            if (nominal > p.getSaldo()) {
                System.out.println("❌ Saldo tidak mencukupi. Dibutuhkan: Rp" + nominal + ", Saldo Anda: Rp" + p.getSaldo());
                return;
            }

            if (nominal <= sbn.getKuotaNasional()) {
                sbn.setKuotaNasional(sbn.getKuotaNasional() - nominal);
                p.getSbnDimiliki().put(nama, p.getSbnDimiliki().getOrDefault(nama, 0.0) + nominal);
                p.setSaldo(p.getSaldo() - nominal);
                System.out.println("✅ Berhasil membeli SBN " + nama);
            } else {
                System.out.println("Peringatan: Kuota tidak mencukupi!");
            }
        } else {
            System.out.println("Peringatan: Nama SBN tidak ditemukan.");
        }
    }

    public static void simulasiSBN() {
        DataStore.daftarSBN.forEach((k, v) -> System.out.println(v));
        System.out.print("Nama SBN (0 untuk kembali): ");
        String nama = DataStore.scanner.nextLine().toUpperCase();
        if (nama.equals("0")) return;

        if (DataStore.daftarSBN.containsKey(nama)) {
            SuratBerhargaNegara sbn = DataStore.daftarSBN.get(nama);

            System.out.print("Nominal Investasi (0 untuk batal): ");
            double nominal = Validasi.inputDouble();
            if (nominal <= 0) return;

            double bunga = sbn.getBunga();
            int tahun = sbn.getJangkaWaktu();

            double hasilAkhir = nominal * Math.pow(1 + (bunga / 100), tahun);
            double keuntungan = hasilAkhir - nominal;

            System.out.println("=== Simulasi Hasil Investasi ===");
            System.out.printf("Nominal Awal: Rp%.2f\n", nominal);
            System.out.printf("Bunga per Tahun: %.2f%%\n", bunga);
            System.out.printf("Jangka Waktu: %d tahun\n", tahun);
            System.out.printf("Total setelah %d tahun: Rp%.2f\n", tahun, hasilAkhir);
            System.out.printf("Keuntungan: Rp%.2f\n", keuntungan);
        } else {
            System.out.println("Peringatan: Nama SBN tidak ditemukan.");
        }
    }

    public static void lihatPortofolio(CustomerPortofolio p) {
        System.out.println("\n--- Portofolio Saham ---");
        for (String kode : p.getSahamDimiliki().keySet()) {
            int jumlahSekarang = p.getSahamDimiliki().get(kode);
            double hargaSekarang = DataStore.daftarSaham.get(kode).getHarga();
            double nilaiPasar = hargaSekarang * jumlahSekarang;
            System.out.printf("%s: %d lembar, Nilai Pasar: Rp%.2f", kode, jumlahSekarang, nilaiPasar);
        }
        System.out.println("\n--- Portofolio SBN ---");
        for (String nama : p.getSbnDimiliki().keySet()) {
            double nominal = p.getSbnDimiliki().get(nama);
            double bunga = DataStore.daftarSBN.get(nama).getBunga() / 12 / 100 * 0.9 * nominal;
            System.out.printf(nama + ": Nominal Rp%.2f, Bunga/Bulan: Rp%.2f", nominal, bunga);
        }
        System.out.println("\nSaldo: Rp" + p.getSaldo());
    }

    public static void setorSaldo(CustomerPortofolio p) {
        System.out.print("Masukkan jumlah yang ingin disetor (0 untuk batal): ");
        double jumlahSetor = Validasi.inputDouble();
        if (jumlahSetor > 0) {
            p.setSaldo(p.getSaldo() + jumlahSetor);
            System.out.println("✅ Berhasil setor saldo sebesar Rp" + jumlahSetor);
        } else {
            System.out.println("⚠ Jumlah harus lebih dari 0.");
        }
    }

    public static void tarikSaldo(CustomerPortofolio p) {
        System.out.print("Masukkan jumlah yang ingin ditarik (0 untuk batal): ");
        double jumlahTarik = Validasi.inputDouble();
        if (jumlahTarik > 0) {
            if (jumlahTarik <= p.getSaldo()) {
                p.setSaldo(p.getSaldo() - jumlahTarik);
                System.out.println("✅ Berhasil tarik saldo sebesar Rp" + jumlahTarik);
            } else {
                System.out.println("❌ Saldo tidak mencukupi untuk penarikan.");
            }
        } else {
            System.out.println("⚠ Jumlah harus lebih dari 0.");
        }
    }
}
