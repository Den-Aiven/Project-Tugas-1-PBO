package model;

import java.util.*;

public class CustomerPortofolio {
    public Map<String, Integer> sahamDimiliki = new HashMap<>();
    public Map<String, Double> nominalPembelianSaham = new HashMap<>();
    public Map<String, Double> sbnDimiliki = new HashMap<>();
    public Map<String, Integer> jumlahSahamTerjual = new HashMap<>();

    public double saldo = 0;

    public void setorSaldo(double jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
            System.out.println("✅ Berhasil setor saldo sebesar Rp" + jumlah);
        } else {
            System.out.println("⚠ Jumlah setor harus lebih dari 0.");
        }
    }

    public void tarikSaldo(double jumlah) {
        if (jumlah > 0) {
            if (jumlah <= saldo) {
                saldo -= jumlah;
                System.out.println("✅ Berhasil tarik saldo sebesar Rp" + jumlah);
            } else {
                System.out.println("❌ Saldo tidak mencukupi untuk penarikan.");
            }
        } else {
            System.out.println("⚠ Jumlah tarik harus lebih dari 0.");
        }
    }

    public void tampilkanSaldo() {
        System.out.println("💰 Saldo Anda saat ini: Rp" + saldo);
    }
}