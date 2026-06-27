package model;

// =============================================
// Class Film
// Konsep OOP : Encapsulation
// Menyimpan data film yang sedang tayang
// =============================================
public class Film {

    private String   judul;
    private String   genre;
    private String   rating;   // SU, R13, R17, R21
    private int      durasi;   // menit
    private String[] jadwal;   // daftar jam tayang

    public Film(String judul, String genre, String rating,
                int durasi, String[] jadwal) {
        this.judul  = judul;
        this.genre  = genre;
        this.rating = rating;
        this.durasi = durasi;
        this.jadwal = jadwal;
    }

    // Getter (Encapsulation)
    public String   getJudul()  { return judul; }
    public String   getGenre()  { return genre; }
    public String   getRating() { return rating; }
    public int      getDurasi() { return durasi; }
    public String[] getJadwal() { return jadwal; }

    // Tampilkan info film di console
    public void tampilkanInfo(int nomor) {
        System.out.printf("  %d. %-32s [%s] | %s | %d menit%n",
                nomor, judul, rating, genre, durasi);
        System.out.print("     Jadwal  : ");
        for (int i = 0; i < jadwal.length; i++) {
            System.out.print((i + 1) + ". " + jadwal[i]);
            if (i < jadwal.length - 1) System.out.print("   ");
        }
        System.out.println();
    }
}
