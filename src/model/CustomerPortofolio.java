package model;

import java.util.HashMap;
import java.util.Map;

public class CustomerPortofolio {
    private Map<String, Integer> sahamDimiliki = new HashMap<>();
    private Map<String, Double> nominalPembelianSaham = new HashMap<>();
    private Map<String, Double> sbnDimiliki = new HashMap<>();
    private Map<String, Integer> jumlahSahamTerjual = new HashMap<>();
    private double saldo = 0.0;

    // Getter
    public Map<String, Integer> getSahamDimiliki() {
        return sahamDimiliki;
    }

    public Map<String, Double> getNominalPembelianSaham() {
        return nominalPembelianSaham;
    }

    public Map<String, Double> getSbnDimiliki() {
        return sbnDimiliki;
    }

    public Map<String, Integer> getJumlahSahamTerjual() {
        return jumlahSahamTerjual;
    }

    public double getSaldo() {
        return saldo;
    }

    // Setter
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
