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

<p align="center">
<img width="357" height="411" alt="image" src="https://github.com/user-attachments/assets/e3e216eb-e78f-4a34-a5df-58c4defdff2c" />
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
  <img width="735" height="272" alt="image" src="https://github.com/user-attachments/assets/18cb82b3-22b4-45d5-8ea4-fecfa23887a7" />
</p>
<p align="center"><i>Gambar 2. Menu utama</i></p>

<p align="justify">
<code>BookingView</code> menampilkan menu 1-6 dan 0 (keluar). Pengguna memilih menu dengan memasukkan angka, yang dibaca oleh <code>InputValidator.bacaInt()</code>. Jika yang dimasukkan bukan angka atau bukan pilihan menu, program menampilkan pesan kesalahan lalu kembali ke menu.
</p>

### 3.2 Lihat Semua Booking (Menu 3)

<p align="center">
  <img width="741" height="423" alt="image" src="https://github.com/user-attachments/assets/1e99c455-a6c8-4035-960d-64b4a029ab74" />
</p>
<p align="center"><i>Gambar 3. Lihat semua booking (data awal)</i></p>

<p align="justify">
Menampilkan seluruh booking beserta ID, info pelanggan, info artist, desain, harga, tanggal, dan status. Info pelanggan dan artist diambil dari method <code>getInfo()</code> masing-masing class (polymorphism). Saat pertama kali dijalankan, sudah ada 2 booking dari data awal. Jika tidak ada data, tampil pesan "(Belum ada data)".
</p>

### 3.3 Tambah Artist (Menu 1)

<p align="center">
  <img width="732" height="182" alt="image" src="https://github.com/user-attachments/assets/09f2348e-d0a1-41c6-8600-0bb128f2c027" />
</p>
<p align="center"><i>Gambar 4. Tambah artist</i></p>

<p align="justify">
Pengguna mengisi nama, no HP, dan spesialisasi artist. Artist disimpan di <code>ArtistController</code>, terpisah dari input booking, sehingga data artist tidak perlu diketik ulang setiap kali membuat booking.
</p>

### 3.4 Tambah Booking (Menu 2)

<p align="center">
  <img width="732" height="455" alt="image" src="https://github.com/user-attachments/assets/7f13cfff-7c7a-4e1c-bdf8-af0fc6d07eb0" />
</p>
<p align="center"><i>Gambar 5. Tambah booking dengan memilih artist dari daftar</i></p>

<p align="justify">
Pengguna mengisi data pelanggan (nama, no HP, alamat), lalu program menampilkan <b>daftar artist bernomor</b> dan pengguna cukup memilih nomornya. Setelah itu pengguna mengisi nama desain, harga, dan tanggal. Status awal booking otomatis <code>Menunggu</code>.
</p>

<p align="center">
  <img width="732" height="602" alt="image" src="https://github.com/user-attachments/assets/0de7bbde-6d04-4851-b47a-c3ccca3a41b2" />
</p>
<p align="center"><i>Gambar 6. Booking baru muncul di daftar booking</i></p>

### 3.5 Validasi Input

<p align="center">
  <img width="747" height="387" alt="image" src="https://github.com/user-attachments/assets/61f2bb11-cae5-4f4a-a087-fb07b7833ad7" />
</p>
<p align="center"><i>Gambar 7. Validasi input</i></p>

<p align="justify">
Input dicek di <code>InputValidator</code> dan diulang sampai benar: nomor artist harus sesuai daftar, harga harus angka dan lebih dari 0 (tidak boleh minus), tanggal harus berformat <code>dd-mm-yyyy</code> dan nyata (misalnya <code>31-02-2026</code> ditolak), serta teks tidak boleh kosong. Data kemudian dicek lagi di setter model sebagai pengaman terakhir.
</p>

### 3.6 Update Booking (Menu 4)

<p align="center">
  <img width="727" height="347" alt="image" src="https://github.com/user-attachments/assets/1090fad7-f339-4780-81e0-b2c6d48ce453" />
</p>
<p align="center"><i>Gambar 8. Update booking, Enter mempertahankan data lama</i></p>

<p align="justify">
Program menampilkan daftar booking, pengguna memasukkan ID, lalu data saat ini ditampilkan. Field yang dapat diubah adalah nama desain, harga, dan tanggal. Setiap field menampilkan nilai lama di dalam tanda kurung siku, dan <b>menekan Enter mempertahankan nilai lama</b>. Jika ID tidak ditemukan, tampil pesan "ID tidak ditemukan.".
</p>

### 3.7 Ubah Status Booking (Menu 5)

<p align="center">
  <img width="721" height="675" alt="image" src="https://github.com/user-attachments/assets/43cf0a00-a12d-4b0a-8961-51753e6f7420" />
</p>
<p align="center"><i>Gambar 9. Ubah status booking</i></p>

<p align="justify">
Pengguna memilih ID booking dari daftar, lalu mengisi status baru. Status hanya boleh <code>Menunggu</code>, <code>Dikerjakan</code>, atau <code>Selesai</code>, dan input diulang sampai sesuai.
</p>

### 3.8 Hapus Booking (Menu 6)

<p align="center">
  <img width="737" height="655" alt="image" src="https://github.com/user-attachments/assets/93c25dc8-b75b-44ab-960f-b3849865ca9a" />
</p>
<p align="center"><i>Gambar 10. Hapus booking</i></p>

<p align="justify">
Pengguna memilih ID booking dari daftar yang ditampilkan, lalu booking tersebut dihapus oleh method <code>hapus()</code> dari interface <code>Crud</code>.
</p>

### 3.9 Keluar (Menu 0)

<p align="center">
  <img width="733" height="415" alt="image" src="https://github.com/user-attachments/assets/78f98bd7-c3ee-4202-a52c-b57151997886" />
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

<p align="center">
  <img width="511" height="468" alt="image" src="https://github.com/user-attachments/assets/2836f56a-b561-4fc6-a63d-6011e3a508dc" />
</p>
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

<p align="center">
<img width="302" height="21" alt="image" src="https://github.com/user-attachments/assets/ff882bd7-1dcd-4b8c-926b-d31248ad8b82" />
<img width="392" height="21" alt="image" src="https://github.com/user-attachments/assets/104fcfc8-a90d-4c13-aacf-c03b7305a288" />
<img width="416" height="22" alt="image" src="https://github.com/user-attachments/assets/c969d74a-ec73-49a4-8195-69a39043d75a" />
</p>

<p align="center"><i>Gambar 13. Inheritance</i></p>

## 5. Penerapan Polymorphism dan Abstraction

### 5.1 Abstraction

<p align="justify">
<code>Orang</code> adalah <b>abstract class</b> dengan <b>abstract method</b> <code>getInfo()</code> (<code>model/Orang.java</code>). Class ini tidak dapat dibuat objeknya secara langsung, dan setiap subclass wajib mengimplementasikan <code>getInfo()</code>.
</p>

<p align="center">
<img width="513" height="586" alt="image" src="https://github.com/user-attachments/assets/7abda4b4-8eff-4524-b269-bad9a9160bc1" />
</p>
<p align="center"><i>Gambar 14. Abstraction</i></p>

### 5.2 Polymorphism - Overriding

<p align="justify">
<code>Pelanggan</code> dan <code>TattooArtist</code> meng-override <code>getInfo()</code> dengan isi yang berbeda.
</p>

<p align="center">
<img width="817" height="67" alt="image" src="https://github.com/user-attachments/assets/0f30eeea-0ee0-45f9-a7e1-525e8ad63fc4" />
<img width="705" height="65" alt="image" src="https://github.com/user-attachments/assets/aecec4ab-dcad-48f5-9b5b-ec01c8e40e5f" />
</p>
<p align="center"><i>Gambar 15. Overriding</i></p>

<p align="justify">
<code>BookingView</code> memanggil method tersebut melalui reference bertipe <code>Orang</code>. Method yang dijalankan ditentukan oleh objek aslinya saat runtime.
</p>

<p align="center">
<img width="677" height="248" alt="image" src="https://github.com/user-attachments/assets/8dfaefb8-3df8-4eaa-8ee5-dda763409a96" />
</p>

<p align="center"><i>Gambar 16. Pemanggilan method hasil overriding</i></p>

### 5.3 Polymorphism - Overloading

<p align="justify">
<code>util/InputValidator.java</code> memiliki beberapa method dengan nama sama tetapi parameter berbeda.
</p>

<p align="center">
<img width="692" height="248" alt="image" src="https://github.com/user-attachments/assets/d7679702-4d4b-4e2e-9db0-2768bf418367" />
<img width="696" height="278" alt="image" src="https://github.com/user-attachments/assets/e44ba0dc-9ad0-4fa1-878b-aa2fde2a7a90" />
<img width="692" height="272" alt="image" src="https://github.com/user-attachments/assets/1caf4fd3-66e4-4c07-a2c6-d110005c309c" />
</p>

<p align="center"><i>Gambar 17. Overloading</i></p>

## 6. Letak Penerapan Nilai Tambah

### Interface

<p align="justify">
Interface <code>Crud</code> berada di <code>interfaces/Crud.java</code> dan berisi kontrak operasi data booking.
</p>

<p align="center">
<img width="342" height="87" alt="image" src="https://github.com/user-attachments/assets/e7a2d558-eb55-4785-bd2b-dffd5a8b300b" />
</p>
<p align="center"><i>Gambar 18. Interface</i></p>

<p align="justify">
Interface ini diimplementasikan oleh <code>BookingController</code> (<code>controller/BookingController.java</code>) dengan menggunakan <code>@Override</code> pada ketiga method tersebut.
</p>

<p align="center">
<img width="501" height="28" alt="image" src="https://github.com/user-attachments/assets/7fd640a9-51c0-4dea-a767-4be0b6103845" />
<img width="380" height="90" alt="image" src="https://github.com/user-attachments/assets/239715ca-486d-4619-ac8d-c0529d40a33c" />
<img width="392" height="160" alt="image" src="https://github.com/user-attachments/assets/4b34a611-35f8-4a8c-a515-640a3f134d2d" />
<img width="327" height="160" alt="image" src="https://github.com/user-attachments/assets/b9029b48-8f6e-468e-962c-68a9e1453cf9" />
</p>
<p align="center"><i>Gambar 19. Implementasi interface</i></p>
