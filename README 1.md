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
Program ini merupakan pengembangan dari Mini Project 2. Pada Mini Project 3 diterapkan <b>polymorphism</b> (overriding dan overloading), <b>abstraction</b> (abstract class dan abstract method), serta struktur proyek <b>MVC</b>. Nilai tambah yang diterapkan adalah <b>interface</b> (<code>Crud&lt;T&gt;</code>).
</p>

<p align="justify">
Perbaikan dari revisi Mini Project 2 meliputi: setter diberi validasi, validasi harga dan tanggal diperketat, menekan Enter saat update mempertahankan data lama, data artist dipisah dari input booking dan dipilih dari daftar, <code>Main</code> hanya memanggil satu method, serta penggunaan keyword <code>final</code>.
</p>

---

## 2. Penjelasan Struktur Package

```
src/main/java
├── com/mycompany/sistempengelolaantatto2
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
| `model` | `Orang` | Abstract class induk (`nama`, `noHp`) dengan abstract method `getInfo()` |
| `model` | `Pelanggan`, `TattooArtist` | Subclass dari `Orang` (menambah `alamat` / `spesialisasi`) |
| `model` | `Booking` | Data booking, menyimpan objek `Pelanggan` dan `TattooArtist` |
| `view` | `BookingView` | Menampilkan menu, daftar artist, dan daftar booking |
| `controller` | `BookingController` | Menjalankan alur menu, proses CRUD booking, dan mengimplementasikan interface `Crud` |
| `controller` | `ArtistController` | Menyimpan dan mengelola daftar artist (termasuk data awal) |
| `interfaces` | `Crud<T>` | Interface kontrak `getAll`, `cariById`, dan `hapus` |
| `util` | `InputValidator` | Membaca input dan menyimpan aturan validasi |
| (root) | `Main` | Entry point, hanya memanggil `new BookingController().jalankan()` |

---

## 3. Penjelasan Alur Program

<p align="center">
  <img src="docs/alur-program.png" alt="Diagram alur program" width="700">
</p>

<p align="center"><i>Gambar 2. Diagram alur program</i></p>

<div align="justify">

1. **Mulai.** `Main` hanya membuat `BookingController` lalu memanggil `jalankan()`. Seluruh alur program berjalan dari method ini.
2. **Data awal.** Saat `BookingController` dibuat, `ArtistController` mengisi 3 artist (Realis, Minimalis, Tribal) dan `seedData()` mengisi 2 booking, sehingga data langsung tampil tanpa input manual.
3. **Menu utama.** `BookingView` menampilkan menu, lalu `jalankan()` mengulang menu dengan `do-while` sampai pengguna memilih `0`.
4. **Tambah Artist (1).** Pengguna mengisi nama, no HP, dan spesialisasi artist. Artist disimpan di `ArtistController`, terpisah dari input booking.
5. **Tambah Booking (2).** Pengguna mengisi data pelanggan, lalu **memilih artist dari daftar bernomor** (tidak mengetik data artist), kemudian mengisi desain, harga, dan tanggal.
6. **Lihat Semua Booking (3).** `BookingView` menampilkan seluruh booking beserta info pelanggan dan artist.
7. **Update Booking (4).** Program menampilkan daftar booking, pengguna memasukkan ID, lalu setiap field menampilkan nilai lama. **Menekan Enter mempertahankan nilai lama.**
8. **Ubah Status (5) dan Hapus (6).** Dilakukan berdasarkan ID booking yang dipilih dari daftar.
9. **Validasi.** Input dicek di `InputValidator` (diulang sampai benar), kemudian dicek lagi di setter model sebagai pengaman terakhir.

</div>

---

## 4. Penerapan Encapsulation dan Inheritance

### 4.1 Encapsulation

<p align="justify">
Semua atribut di <code>Orang</code>, <code>Pelanggan</code>, <code>TattooArtist</code>, dan <code>Booking</code> bersifat <code>private</code> dan hanya diakses melalui getter dan setter. Setiap setter memiliki validasi dan melempar <code>IllegalArgumentException</code> jika data tidak valid.
</p>

| Setter | Validasi |
|---|---|
| `Orang.setNama()` | tidak boleh kosong |
| `Orang.setNoHp()` | harus angka 10-13 digit |
| `Pelanggan.setAlamat()` | tidak boleh kosong |
| `TattooArtist.setSpesialisasi()` | tidak boleh kosong |
| `Booking.setNamaDesain()` | tidak boleh kosong |
| `Booking.setHarga()` | harus lebih dari 0 (tidak boleh minus) |
| `Booking.setTanggal()` | format `dd-MM-yyyy` dan tanggal harus nyata (contoh: `31-02-2026` ditolak) |
| `Booking.setStatus()` | hanya Menunggu, Dikerjakan, atau Selesai |

<p align="justify">
Contoh penerapan pada <code>model/Booking.java</code>:
</p>

```java
public void setHarga(double harga) {
    if (!InputValidator.isHargaValid(harga)) {
        throw new IllegalArgumentException("Harga harus lebih dari 0!");
    }
    this.harga = harga;
}
```
<p align="center"><i>Gambar 3. Encapsulation</i></p>

<p align="justify">
Constructor juga memanggil setter, sehingga objek yang dibuat tidak mungkin berisi data yang tidak valid.
</p>

**Penggunaan `final`:**

<div align="justify">

- `Booking`: `id`, `pelanggan`, dan `artist` bersifat `final` (tidak boleh berubah setelah booking dibuat).
- `Orang.setNama()` dan `Orang.setNoHp()` bersifat `final` karena dipanggil di constructor.
- `InputValidator` bersifat `final class`, dengan konstanta `final` (`FORMAT_TANGGAL`, `STATUS_VALID`).
- `BookingController`: `daftarBooking` dan `artistController` bersifat `final`. `ArtistController`: `daftarArtist` bersifat `final`.

</div>

### 4.2 Inheritance

<p align="justify">
<code>Orang</code> adalah superclass, dengan dua subclass yaitu <code>Pelanggan</code> dan <code>TattooArtist</code>. Kedua subclass mewarisi <code>nama</code>, <code>noHp</code>, beserta getter dan setter-nya dari <code>Orang</code>.
</p>

```java
public abstract class Orang { ... }
public class Pelanggan extends Orang { ... }     // + alamat
public class TattooArtist extends Orang { ... }  // + spesialisasi
```
<p align="center"><i>Gambar 4. Inheritance</i></p>

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
<p align="center"><i>Gambar 5. Abstraction</i></p>

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
<p align="center"><i>Gambar 6. Overriding</i></p>

<p align="justify">
<code>BookingView</code> memanggil method tersebut melalui reference bertipe <code>Orang</code>. Method yang dijalankan ditentukan oleh objek aslinya saat runtime.
</p>

```java
Orang pelanggan = b.getPelanggan();
Orang artist = b.getArtist();
System.out.println(" Pelanggan  : " + pelanggan.getInfo());
System.out.println(" Artist     : " + artist.getInfo());
```
<p align="center"><i>Gambar 7. Overriding</i></p>

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
<p align="center"><i>Gambar 7. Overloading</i></p>

## 6. Letak Penerapan Nilai Tambah

### Interface

<p align="justify">
Interface <code>Crud&lt;T&gt;</code> berada di <code>interfaces/Crud.java</code> dan berisi kontrak operasi data.
</p>

```java
public interface Crud<T> {
    ArrayList<T> getAll();
    T cariById(int id);
    boolean hapus(int id);
}
```

<p align="justify">
Interface ini diimplementasikan oleh <code>BookingController</code> (<code>controller/BookingController.java</code>) dengan menggunakan <code>@Override</code> pada ketiga method tersebut.
</p>

```java
public class BookingController implements Crud<Booking> {
    @Override public ArrayList<Booking> getAll() { ... }
    @Override public Booking cariById(int id) { ... }
    @Override public boolean hapus(int id) { ... }
}
```
