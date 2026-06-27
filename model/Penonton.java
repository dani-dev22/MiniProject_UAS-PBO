package model;

// =============================================
// Class Penonton
// Konsep OOP : Encapsulation
// Menyimpan data penonton / pemesan tiket
// =============================================
public class Penonton {

    private String nama;
    private String noHp;
    private String email;

    public Penonton(String nama, String noHp, String email) {
        this.nama  = nama;
        this.noHp  = noHp;
        this.email = email;
    }

    // Getter (Encapsulation)
    public String getNama()  { return nama; }
    public String getNoHp()  { return noHp; }
    public String getEmail() { return email; }
}
