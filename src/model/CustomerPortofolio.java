package model;

import java.util.*;

public class CustomerPortofolio {
    private Map<String, Integer> sahamDimiliki = new HashMap<>();
    private Map<String, Double> nominalPembelianSaham = new HashMap<>();
    private Map<String, Double> sbnDimiliki = new HashMap<>();
    private Map<String, Integer> jumlahSahamTerjual = new HashMap<>();
    private double saldo = 0;

    // Getter & Setter
    public Map<String, Integer> getSahamDimiliki() {
        return sahamDimiliki;
    }

    public void setSahamDimiliki(Map<String, Integer> sahamDimiliki) {
        this.sahamDimiliki = sahamDimiliki;
    }

    public Map<String, Double> getNominalPembelianSaham() {
        return nominalPembelianSaham;
    }

    public void setNominalPembelianSaham(Map<String, Double> nominalPembelianSaham) {
        this.nominalPembelianSaham = nominalPembelianSaham;
    }

    public Map<String, Double> getSbnDimiliki() {
        return sbnDimiliki;
    }

    public void setSbnDimiliki(Map<String, Double> sbnDimiliki) {
        this.sbnDimiliki = sbnDimiliki;
    }

    public Map<String, Integer> getJumlahSahamTerjual() {
        return jumlahSahamTerjual;
    }

    public void setJumlahSahamTerjual(Map<String, Integer> jumlahSahamTerjual) {
        this.jumlahSahamTerjual = jumlahSahamTerjual;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //     Behavior Methods
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