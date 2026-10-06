<h1 align="center">Minpro-3-PBO-SistemPengelolaanTatto</h1>

<p align="center">
  <b>Nama:</b> Awang Rifky Muhadzib &nbsp;|&nbsp; <b>NIM:</b> 2509116059
</p>

---

## Daftar Isi

1. [Deskripsi Singkat Program](#1-deskripsi-singkat-program)
2. [Penjelasan Struktur Package](#2-penjelasan-struktur-package)
3. [Penjelasan Alur Program](#3-penjelasan-alur-program)
4. [Penerapan Encapsulation dan Inheritance](#4-penerapan-encapsulation-dan-inheritance)
5. [Penerapan Polymorphism dan Abstraction](#5-penerapan-polymorphism-dan-abstraction)
6. [Letak Penerapan Nilai Tambah](#6-letak-penerapan-nilai-tambah)

---

## 1. Deskripsi Singkat Program

<p align="justify">
Program ini adalah aplikasi <b>CRUD berbasis console (Java)</b> untuk mengelola <b>booking pada studio tattoo</b>. Setiap booking mencatat data pelanggan, tattoo artist yang mengerjakan, nama desain, harga, tanggal, dan status pengerjaan (Menunggu, Dikerjakan, atau Selesai).
</p>

<p align="justify">
Program ini merupakan pengembangan dari Mini Project 2. Pada Mini Project 3 diterapkan <b>polymorphism</b> (overriding dan overloading), <b>abstraction</b> (abstract class dan abstract method), serta struktur proyek <b>MVC</b>. Nilai tambah yang diterapkan adalah <b>interface</b> (<code>Crud</code>).
</p>

<p align="justify">
Perbaikan dari revisi Mini Project 2 meliputi: setter diberi validasi, validasi harga dan tanggal diperketat (termasuk pengecekan tahun kabisat), menekan Enter saat update mempertahankan data lama, data artist dipisah dari input booking dan dipilih dari daftar, <code>Main</code> hanya memanggil satu method, serta penggunaan keyword <code>final</code>.
</p>

---

## 2. Penjelasan Struktur Package

```
src/main/java
├── main
│   └── Main.java
├── model
│   ├── Orang.java            (abstract class)
│   ├── Pelanggan.java
│   ├── TattooArtist.java
│   └── Booking.java
├── view
│   └── BookingView.java
├── controller
│   ├── BookingController.java
│   └── ArtistController.java
├── interfaces
│   └── Crud.java             (interface)
└── util
    └── InputValidator.java
```

<p align="center"><i>Gambar 1. Struktur Package</i></p>

| Package | Class | Peran |
|---|---|---|
| `main` | `Main` | Entry point, hanya memanggil `new BookingController().jalankan()` |
| `model` | `Orang` | Abstract class induk (`nama`, `noHp`) dengan abstract method `getInfo()` |
| `model` | `Pelanggan`, `TattooArtist` | Subclass dari `Orang` (menambah `alamat` / `spesialisasi`) |
| `model` | `Booking` | Data booking, menyimpan objek `Pelanggan` dan `TattooArtist` |
| `view` | `BookingView` | Menampilkan header, menu, daftar artist, detail booking, dan pesan |
| `controller` | `BookingController` | Menjalankan alur menu, proses CRUD booking, dan mengimplementasikan interface `Crud` |
| `controller` | `ArtistController` | Menyimpan dan mengelola daftar artist (termasuk data awal) |
| `interfaces` | `Crud` | Interface kontrak `getAll`, `cariById`, dan `hapus` |
| `util` | `InputValidator` | Membaca input dari pengguna dan menyimpan aturan validasi |

---

## 3. Penjelasan Alur Program

<p align="justify">
<code>Main</code> hanya memanggil <code>new BookingController().jalankan()</code>. Saat <code>BookingController</code> dibuat, <code>ArtistController</code> mengisi 3 artist (Rian Ink - Realis, Dewi Tattoo - Minimalis, Bima Tribal - Tribal) dan <code>seedData()</code> mengisi 2 booking, sehingga data langsung tampil tanpa input manual. Setelah itu menu ditampilkan berulang dengan <code>do-while</code> dan <code>switch</code> sampai pengguna memilih <code>0</code>. Berikut alur program beserta hasil outputnya.
</p>

### 3.1 Menu Utama

<p align="center">
  <img src="docs/output-01-menu-utama.png" alt="Menu utama" width="650">
</p>
<p align="center"><i>Gambar 2. Menu utama</i></p>

<p align="justify">
<code>BookingView</code> menampilkan menu 1-6 dan 0 (keluar). Pengguna memilih menu dengan memasukkan angka, yang dibaca oleh <code>InputValidator.bacaInt()</code>. Jika yang dimasukkan bukan angka atau bukan pilihan menu, program menampilkan pesan kesalahan lalu kembali ke menu.
</p>

### 3.2 Lihat Semua Booking (Menu 3)

<p align="center">
  <img src="docs/output-02-lihat-booking.png" alt="Lihat semua booking" width="650">
</p>
<p align="center"><i>Gambar 3. Lihat semua booking (data awal)</i></p>

<p align="justify">
Menampilkan seluruh booking beserta ID, info pelanggan, info artist, desain, harga, tanggal, dan status. Info pelanggan dan artist diambil dari method <code>getInfo()</code> masing-masing class (polymorphism). Saat pertama kali dijalankan, sudah ada 2 booking dari data awal. Jika tidak ada data, tampil pesan "(Belum ada data)".
</p>

### 3.3 Tambah Artist (Menu 1)

<p align="center">
  <img src="docs/output-03-tambah-artist.png" alt="Tambah artist" width="650">
</p>
<p align="center"><i>Gambar 4. Tambah artist</i></p>

<p align="justify">
Pengguna mengisi nama, no HP, dan spesialisasi artist. Artist disimpan di <code>ArtistController</code>, terpisah dari input booking, sehingga data artist tidak perlu diketik ulang setiap kali membuat booking.
</p>

### 3.4 Tambah Booking (Menu 2)

<p align="center">
  <img src="docs/output-04-tambah-booking.png" alt="Tambah booking" width="650">
</p>
<p align="center"><i>Gambar 5. Tambah booking dengan memilih artist dari daftar</i></p>

<p align="justify">
Pengguna mengisi data pelanggan (nama, no HP, alamat), lalu program menampilkan <b>daftar artist bernomor</b> dan pengguna cukup memilih nomornya. Setelah itu pengguna mengisi nama desain, harga, dan tanggal. Status awal booking otomatis <code>Menunggu</code>.
</p>

<p align="center">
  <img src="docs/output-05-hasil-tambah.png" alt="Hasil tambah booking" width="650">
</p>
<p align="center"><i>Gambar 6. Booking baru muncul di daftar booking</i></p>

### 3.5 Validasi Input

<p align="center">
  <img src="docs/output-06-validasi.png" alt="Validasi input" width="650">
</p>
<p align="center"><i>Gambar 7. Validasi input</i></p>

<p align="justify">
Input dicek di <code>InputValidator</code> dan diulang sampai benar: nomor artist harus sesuai daftar, harga harus angka dan lebih dari 0 (tidak boleh minus), tanggal harus berformat <code>dd-mm-yyyy</code> dan nyata (misalnya <code>31-02-2026</code> ditolak), serta teks tidak boleh kosong. Data kemudian dicek lagi di setter model sebagai pengaman terakhir.
</p>

### 3.6 Update Booking (Menu 4)

<p align="center">
  <img src="docs/output-07-update.png" alt="Update booking" width="650">
</p>
<p align="center"><i>Gambar 8. Update booking, Enter mempertahankan data lama</i></p>

<p align="justify">
Program menampilkan daftar booking, pengguna memasukkan ID, lalu data saat ini ditampilkan. Field yang dapat diubah adalah nama desain, harga, dan tanggal. Setiap field menampilkan nilai lama di dalam tanda kurung siku, dan <b>menekan Enter mempertahankan nilai lama</b>. Jika ID tidak ditemukan, tampil pesan "ID tidak ditemukan.".
</p>

### 3.7 Ubah Status Booking (Menu 5)

<p align="center">
  <img src="docs/output-08-ubah-status.png" alt="Ubah status booking" width="650">
</p>
<p align="center"><i>Gambar 9. Ubah status booking</i></p>

<p align="justify">
Pengguna memilih ID booking dari daftar, lalu mengisi status baru. Status hanya boleh <code>Menunggu</code>, <code>Dikerjakan</code>, atau <code>Selesai</code>, dan input diulang sampai sesuai.
</p>

### 3.8 Hapus Booking (Menu 6)

<p align="center">
  <img src="docs/output-09-hapus.png" alt="Hapus booking" width="650">
</p>
<p align="center"><i>Gambar 10. Hapus booking</i></p>

<p align="justify">
Pengguna memilih ID booking dari daftar yang ditampilkan, lalu booking tersebut dihapus oleh method <code>hapus()</code> dari interface <code>Crud</code>.
</p>

### 3.9 Keluar (Menu 0)

<p align="center">
  <img src="docs/output-10-keluar.png" alt="Keluar" width="650">
</p>
<p align="center"><i>Gambar 11. Keluar dari program</i></p>

<p align="justify">
Program menampilkan "Terima kasih, program selesai." dan perulangan menu berhenti.
</p>

---

## 4. Penerapan Encapsulation dan Inheritance

### 4.1 Encapsulation

<p align="justify">
Semua atribut di <code>Orang</code>, <code>Pelanggan</code>, <code>TattooArtist</code>, dan <code>Booking</code> bersifat <code>private</code> dan hanya diakses melalui getter dan setter. Setiap setter memanggil method validasi di <code>InputValidator</code>, dan nilai baru hanya disimpan jika lolos validasi.
</p>

| Setter | Validasi |
|---|---|
| `Orang.setNama()` | tidak boleh kosong |
| `Orang.setNoHp()` | tidak boleh kosong |
| `Pelanggan.setAlamat()` | tidak boleh kosong |
| `TattooArtist.setSpesialisasi()` | tidak boleh kosong |
| `Booking.setNamaDesain()` | tidak boleh kosong |
| `Booking.setHarga()` | harus lebih dari 0 (tidak boleh minus atau nol) |
| `Booking.setTanggal()` | format `dd-mm-yyyy` dan tanggal harus nyata, termasuk tahun kabisat (contoh: `31-02-2026` ditolak) |
| `Booking.setStatus()` | hanya `Menunggu`, `Dikerjakan`, atau `Selesai` |

<p align="justify">
Contoh penerapan pada <code>model/Booking.java</code>:
</p>

```java
public void setHarga(double harga) {
    if (InputValidator.isHargaValid(harga)) {
        this.harga = harga;
    }
}
```
<p align="center"><i>Gambar 12. Encapsulation</i></p>

<p align="justify">
Constructor juga memanggil setter, sehingga data yang masuk ke objek selalu melewati validasi yang sama.
</p>

**Penggunaan `final`:**

<div align="justify">

- `Booking`: `id`, `pelanggan`, dan `artist` bersifat `final` (tidak boleh berubah setelah booking dibuat).
- `Orang.setNama()` dan `Orang.setNoHp()` bersifat `final` karena dipanggil di constructor.
- `InputValidator` bersifat `final class`, dengan `Scanner sc` bersifat `private static final`.
- `BookingController`: `daftarBooking` dan `artistController` bersifat `final`. `ArtistController`: `daftarArtist` bersifat `final`.

</div>

### 4.2 Inheritance

<p align="justify">
<code>Orang</code> adalah superclass, dengan dua subclass yaitu <code>Pelanggan</code> dan <code>TattooArtist</code>. Kedua subclass mewarisi <code>nama</code>, <code>noHp</code>, beserta getter dan setter-nya dari <code>Orang</code>, lalu memanggil <code>super(nama, noHp)</code> pada constructor.
</p>

```java
public abstract class Orang { ... }
public class Pelanggan extends Orang { ... }     // + alamat
public class TattooArtist extends Orang { ... }  // + spesialisasi
```
<p align="center"><i>Gambar 13. Inheritance</i></p>

## 5. Penerapan Polymorphism dan Abstraction

### 5.1 Abstraction

<p align="justify">
<code>Orang</code> adalah <b>abstract class</b> dengan <b>abstract method</b> <code>getInfo()</code> (<code>model/Orang.java</code>). Class ini tidak dapat dibuat objeknya secara langsung, dan setiap subclass wajib mengimplementasikan <code>getInfo()</code>.
</p>

```java
public abstract class Orang {
    public abstract String getInfo();
}
```
<p align="center"><i>Gambar 14. Abstraction</i></p>

### 5.2 Polymorphism - Overriding

<p align="justify">
<code>Pelanggan</code> dan <code>TattooArtist</code> meng-override <code>getInfo()</code> dengan isi yang berbeda.
</p>

```java
// Pelanggan.java
@Override
public String getInfo() {
    return "Nama: " + getNama() + " | HP: " + getNoHp() + " | Alamat: " + alamat;
}

// TattooArtist.java
@Override
public String getInfo() {
    return "Nama: " + getNama() + " | Spesialisasi: " + spesialisasi;
}
```
<p align="center"><i>Gambar 15. Overriding</i></p>

<p align="justify">
<code>BookingView</code> memanggil method tersebut melalui reference bertipe <code>Orang</code>. Method yang dijalankan ditentukan oleh objek aslinya saat runtime.
</p>

```java
Orang pelanggan = b.getPelanggan();
Orang artist = b.getArtist();
System.out.println(" Pelanggan  : " + pelanggan.getInfo());
System.out.println(" Artist     : " + artist.getInfo());
```
<p align="center"><i>Gambar 16. Pemanggilan method hasil overriding</i></p>

### 5.3 Polymorphism - Overloading

<p align="justify">
<code>util/InputValidator.java</code> memiliki beberapa method dengan nama sama tetapi parameter berbeda.
</p>

```java
bacaInt(String pesan)
bacaInt(String pesan, String contoh)
bacaInt(String pesan, int min, int max)   // dipakai saat memilih nomor artist

bacaString(String pesan)
bacaString(String pesan, String contoh)

bacaDouble(String pesan)
bacaDouble(String pesan, String contoh)
```
<p align="center"><i>Gambar 17. Overloading</i></p>

## 6. Letak Penerapan Nilai Tambah

### Interface

<p align="justify">
Interface <code>Crud</code> berada di <code>interfaces/Crud.java</code> dan berisi kontrak operasi data booking.
</p>

```java
public interface Crud {
    ArrayList<Booking> getAll();
    Booking cariById(int id);
    boolean hapus(int id);
}
```
<p align="center"><i>Gambar 18. Interface</i></p>

<p align="justify">
Interface ini diimplementasikan oleh <code>BookingController</code> (<code>controller/BookingController.java</code>) dengan menggunakan <code>@Override</code> pada ketiga method tersebut.
</p>

```java
public class BookingController implements Crud {
    @Override public ArrayList<Booking> getAll() { ... }
    @Override public Booking cariById(int id) { ... }
    @Override public boolean hapus(int id) { ... }
}
```
<p align="center"><i>Gambar 19. Implementasi interface</i></p>
