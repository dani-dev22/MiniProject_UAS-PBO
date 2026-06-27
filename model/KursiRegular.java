package model;

// =============================================
// Class KursiRegular
// Konsep OOP : Inheritance, Polymorphism
// Baris A, B, C — Harga Rp 40.000
// =============================================
public class KursiRegular extends Kursi {

    private static final double HARGA = 40_000;

    public KursiRegular(char baris, int nomor) {
        super(baris, nomor);
    }

    @Override
    public String getKelas() { return "Regular"; }

    @Override
    public double getHarga() { return HARGA; }
}
