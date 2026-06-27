package model;

// =============================================
// Abstract Class Kursi
// Konsep OOP : Abstraction, Encapsulation
// Diwarisi KursiRegular & KursiVIP
// =============================================
public abstract class Kursi {

    private String  kodeKursi;
    private char    baris;
    private int     nomor;
    private boolean terisi;

    public Kursi(char baris, int nomor) {
        this.baris     = baris;
        this.nomor     = nomor;
        this.kodeKursi = "" + baris + nomor;
        this.terisi    = false;
    }

    // Abstract method → wajib diisi subclass (Polymorphism)
    public abstract String getKelas();
    public abstract double getHarga();

    // Getter & Setter (Encapsulation)
    public String  getKodeKursi() { return kodeKursi; }
    public char    getBaris()     { return baris; }
    public int     getNomor()     { return nomor; }
    public boolean isTerisi()     { return terisi; }
    public void    setTerisi(boolean terisi) { this.terisi = terisi; }

    // Tampilan di denah kursi
    public String tampil() {
        if (terisi) return "[###]";
        return String.format("[%2s ]", kodeKursi);
    }
}
