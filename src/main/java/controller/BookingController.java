/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import interfaces.Crud;
import model.Booking;
import model.Pelanggan;
import model.TattooArtist;
import util.InputValidator;
import view.BookingView;
import java.util.ArrayList;
 
public class BookingController implements Crud {
    private final ArrayList<Booking> daftarBooking = new ArrayList<>();
    private final ArtistController artistController = new ArtistController();
    private int nextId = 1;
 
    public BookingController() {
        seedData();
    }
 
    private void seedData() {
        ArrayList<TattooArtist> artist = artistController.getAll();

        Pelanggan p1 = new Pelanggan("Andi Saputra", "08124657890", "Banjarmasin");
        tambahBooking(p1, artist.get(0), "Naga Jepang", 750000, "12-09-2026");
 
        Pelanggan p2 = new Pelanggan("Siti Nurhaliza", "081355657898", "Martapura");
        tambahBooking(p2, artist.get(1), "Bunga Mawar", 350000, "13-09-2026");
    }

    public void jalankan() {
        int pilihan;
        do {
            BookingView.tampilkanMenuUtama();
            pilihan = InputValidator.bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1: inputArtist(); break;
                case 2: inputBooking(); break;
                case 3: BookingView.tampilkanDaftarBooking(getAll()); break;
                case 4: inputUpdateBooking(); break;
                case 5: inputUbahStatus(); break;
                case 6: inputHapusBooking(); break;
                case 0: BookingView.tampilkanPesan("Terima kasih, program selesai."); break;
                default: BookingView.tampilkanPesan("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }

    private void inputArtist() {
        BookingView.tampilkanHeader("INPUT ARTIST BARU");

        String nama = InputValidator.bacaString("Nama Tattoo Artist: ");
        String hp = InputValidator.bacaString("No HP Artist: ");
        String spesialisasi = InputValidator.bacaString("Spesialisasi Artist: ", "Realis / Tribal / Minimalis");

        artistController.tambahArtist(nama, hp, spesialisasi);
        BookingView.tampilkanPesan("Artist berhasil ditambahkan.");
    }

    private void inputBooking() {
        BookingView.tampilkanHeader("INPUT BOOKING BARU");

        String namaPelanggan = InputValidator.bacaString("Nama Pelanggan: ");
        String hpPelanggan = InputValidator.bacaString("No HP Pelanggan: ");
        String alamatPelanggan = InputValidator.bacaString("Alamat Pelanggan: ");
        Pelanggan pelanggan = new Pelanggan(namaPelanggan, hpPelanggan, alamatPelanggan);

        ArrayList<TattooArtist> daftarArtist = artistController.getAll();
        BookingView.tampilkanDaftarArtist(daftarArtist);
        int pilihan = InputValidator.bacaInt("Pilih nomor artist: ", 1, daftarArtist.size());
        TattooArtist artist = daftarArtist.get(pilihan - 1);

        String namaDesain = InputValidator.bacaString("Nama Desain/Layanan: ");
        double harga = InputValidator.bacaHarga("Harga: ");
        String tanggal = InputValidator.bacaTanggal("Tanggal Booking (dd-mm-yyyy): ", "20-09-2026");

        tambahBooking(pelanggan, artist, namaDesain, harga, tanggal);
        BookingView.tampilkanPesan("Booking berhasil ditambahkan.");
    }

    private void inputUpdateBooking() {
        BookingView.tampilkanHeader("UPDATE BOOKING");
        if (!tampilkanDataAtauKosong()) return;
        int id = InputValidator.bacaInt("ID Booking yang diupdate: ");
        Booking b = cariById(id);
        if (b == null) {
            BookingView.tampilkanPesan("ID tidak ditemukan.");
            return;
        }

        BookingView.tampilkanPesan("\nData saat ini:");
        BookingView.tampilkanGarisTipis();
        BookingView.tampilkanDetailBooking(b);
        BookingView.tampilkanGarisTipis();
        BookingView.tampilkanPesan("Isi data baru (tekan Enter jika tidak ada perubahan):\n");
        String namaDesain = InputValidator.bacaStringOpsional("Nama Desain baru", b.getNamaDesain());
        double harga = InputValidator.bacaHargaOpsional("Harga baru", b.getHarga());
        String tanggal = InputValidator.bacaTanggalOpsional("Tanggal baru (dd-mm-yyyy)", b.getTanggal());

        boolean berhasil = updateBooking(id, namaDesain, harga, tanggal);
        BookingView.tampilkanPesan(berhasil ? "Data booking diperbarui." : "ID tidak ditemukan.");
    }

    private void inputUbahStatus() {
        BookingView.tampilkanHeader("UBAH STATUS BOOKING");
        if (!tampilkanDataAtauKosong()) return;
        int id = InputValidator.bacaInt("ID Booking: ");
        String status = InputValidator.bacaStatus("Status baru (Menunggu/Dikerjakan/Selesai): ");
        boolean berhasil = ubahStatus(id, status);
        BookingView.tampilkanPesan(berhasil ? "Status diperbarui." : "ID tidak ditemukan.");
    }

    private void inputHapusBooking() {
        BookingView.tampilkanHeader("HAPUS BOOKING");
        if (!tampilkanDataAtauKosong()) return;
        int id = InputValidator.bacaInt("ID Booking yang dihapus: ");
        boolean berhasil = hapus(id);
        BookingView.tampilkanPesan(berhasil ? "Data dihapus." : "ID tidak ditemukan.");
    }

    /** Menampilkan daftar booking agar pengguna tahu ID yang tersedia. */
    private boolean tampilkanDataAtauKosong() {
        if (daftarBooking.isEmpty()) {
            BookingView.tampilkanPesan(" (Belum ada data)");
            BookingView.tampilkanGarisTipis();
            return false;
        }
        BookingView.tampilkanIsiDaftarBooking(daftarBooking);
        return true;
    }

    // ===== Proses data =====
 
    public Booking tambahBooking(Pelanggan pelanggan, TattooArtist artist, String namaDesain, double harga, String tanggal) {
        Booking b = new Booking(nextId++, pelanggan, artist, namaDesain, harga, tanggal);
        daftarBooking.add(b);
        return b;
    }
 
    @Override
    public ArrayList<Booking> getAll() {
        return daftarBooking;
    }
 
    @Override
    public Booking cariById(int id) {
        for (Booking b : daftarBooking) {
            if (b.getId() == id) return b;
        }
        return null;
    }
 
    public boolean updateBooking(int id, String namaDesain, double harga, String tanggal) {
        Booking b = cariById(id);
        if (b == null) return false;
        b.setNamaDesain(namaDesain);
        b.setHarga(harga);
        b.setTanggal(tanggal);
        return true;
    }
 
    public boolean ubahStatus(int id, String status) {
        Booking b = cariById(id);
        if (b == null) return false;
        b.setStatus(status);
        return true;
    }
 
    @Override
    public boolean hapus(int id) {
        Booking b = cariById(id);
        if (b == null) return false;
        daftarBooking.remove(b);
        return true;
    }
}
