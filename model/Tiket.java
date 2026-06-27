package model;

import interfaces.Printable;
import java.text.NumberFormat;
import java.util.Locale;

// =============================================
// Class Tiket
// Konsep OOP : Encapsulation, Interface (Printable)
// Menyimpan data booking & mencetak tiket bioskop
// =============================================
public class Tiket implements Printable {

    private String   kodeBooking;
    private Penonton penonton;
    private Kursi    kursi;
    private Film     film;
    private String   jadwal;
    private boolean  aktif;

    public Tiket(String kodeBooking, Penonton penonton,
                 Kursi kursi, Film film, String jadwal) {
        this.kodeBooking = kodeBooking;
        this.penonton    = penonton;
        this.kursi       = kursi;
        this.film        = film;
        this.jadwal      = jadwal;
        this.aktif       = true;
    }

    // Getter & Setter
    public String  getKodeBooking()          { return kodeBooking; }
    public Kursi   getKursi()                { return kursi; }
    public Film    getFilm()                 { return film; }
    public boolean isAktif()                 { return aktif; }
    public void    setAktif(boolean aktif)   { this.aktif = aktif; }

    // Implementasi dari interface Printable
    @Override
    public void cetakTiket() {
        NumberFormat nf     = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String       status = aktif ? "AKTIF" : "DIBATALKAN";

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║               C I N E M A X                  ║");
        System.out.println("║          Jl. Sudirman No. 99, Jakarta        ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf( "║  Kode Booking : %-29s║%n", kodeBooking);
        System.out.printf( "║  Status       : %-29s║%n", status);
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf( "║  Nama         : %-29s║%n", penonton.getNama());
        System.out.printf( "║  No. HP       : %-29s║%n", penonton.getNoHp());
        System.out.printf( "║  Email        : %-29s║%n", penonton.getEmail());
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf( "║  Film         : %-29s║%n", film.getJudul());
        System.out.printf( "║  Genre        : %-29s║%n", film.getGenre());
        System.out.printf( "║  Rating       : %-29s║%n", film.getRating());
        System.out.printf( "║  Durasi       : %-29s║%n", film.getDurasi() + " menit");
        System.out.printf( "║  Studio       : %-29s║%n", "Studio 1");
        System.out.printf( "║  Jadwal       : %-29s║%n", jadwal);
        System.out.printf( "║  Kursi        : %-29s║%n",
                kursi.getKodeKursi() + "  (" + kursi.getKelas() + ")");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf( "║  Total Harga  : %-29s║%n", nf.format(kursi.getHarga()));
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println("  Harap tiba 15 menit sebelum film dimulai.");
    }
}
