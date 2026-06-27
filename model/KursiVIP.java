package model;

// =============================================
// Class KursiVIP
// Konsep OOP : Inheritance, Polymorphism
// Baris D, E — Harga Rp 75.000
// =============================================
public class KursiVIP extends Kursi {

    private static final double HARGA = 75_000;

    public KursiVIP(char baris, int nomor) {
        super(baris, nomor);
    }

    @Override
    public String getKelas() { return "VIP"; }

    @Override
    public double getHarga() { return HARGA; }
}
