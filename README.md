<h1 align="center">Minpro-3-PBO-SistemPengelolaanTatto</h1>

<p align="center">
  <b>Nama:</b> Awang Rifky Muhadzib &nbsp;|&nbsp; <b>NIM:</b> 2509116059
</p>

<hr>

<h2 align="center">Daftar Isi</h2>


1. [Deskripsi Singkat Program](#1-deskripsi-singkat-program)
2. [Penjelasan Struktur Package](#2-penjelasan-struktur-package)
3. [Penjelasan Alur Program](#3-penjelasan-alur-program)
4. [Penerapan Encapsulation dan Inheritance](#4-penerapan-encapsulation-dan-inheritance)
5. [Penerapan Polymorphism dan Abstraction](#5-penerapan-polymorphism-dan-abstraction)
6. [Letak Penerapan Nilai Tambah](#6-letak-penerapan-nilai-tambah)


<hr>

<h2 align="center">1. Deskripsi Singkat Program</h2>

<p align="justify">
Program ini adalah aplikasi <b>CRUD berbasis console (Java)</b> untuk mengelola <b>booking pada studio tattoo</b>. Setiap booking mencatat data pelanggan, tattoo artist yang mengerjakan, nama desain, harga, tanggal, dan status pengerjaan (Menunggu, Dikerjakan, atau Selesai).
</p>

<p align="justify">
Program ini merupakan pengembangan dari Mini Project 2. Pada Mini Project 3 diterapkan <b>polymorphism</b> (overriding dan overloading), <b>abstraction</b> (abstract class dan abstract method), serta struktur proyek <b>MVC</b>. Nilai tambah yang diterapkan adalah <b>interface</b> (<code>Crud</code>).
</p>

<p align="justify">
Perbaikan dari revisi Mini Project 2 meliputi: setter diberi validasi, validasi harga dan tanggal diperketat (termasuk pengecekan tahun kabisat), menekan Enter saat update mempertahankan data lama, data artist dipisah dari input booking dan dipilih dari daftar, <code>Main</code> hanya memanggil satu method, serta penggunaan keyword <code>final</code>.
</p>

<hr>

<h2 align="center">2. Penjelasan Struktur Package</h2>

<p align="center">
  <img src="https://github.com/user-attachments/assets/e3e216eb-e78f-4a34-a5df-58c4defdff2c" alt="Struktur Package" width="600">
</p>
<p align="center"><i>Gambar 1. Struktur Package</i></p>

<div align="center">

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

</div>

<hr>

<h2 align="center">3. Penjelasan Alur Program</h2>

<p align="justify">
<code>Main</code> hanya memanggil <code>new BookingController().jalankan()</code>. Saat <code>BookingController</code> dibuat, <code>ArtistController</code> mengisi 3 artist (Rian Ink - Realis, Dewi Tattoo - Minimalis, Bima Tribal - Tribal) dan <code>seedData()</code> mengisi 2 booking, sehingga data langsung tampil tanpa input manual. Setelah itu menu ditampilkan berulang dengan <code>do-while</code> dan <code>switch</code> sampai pengguna memilih <code>0</code>. Berikut alur program beserta hasil outputnya.
</p>

<h3>3.1 Menu Utama</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/18cb82b3-22b4-45d5-8ea4-fecfa23887a7" alt="Menu utama" width="600">
</p>
<p align="center"><i>Gambar 2. Menu utama</i></p>

<p align="justify">
<code>BookingView</code> menampilkan menu 1-6 dan 0 (keluar). Pengguna memilih menu dengan memasukkan angka, yang dibaca oleh <code>InputValidator.bacaInt()</code>. Jika yang dimasukkan bukan angka atau bukan pilihan menu, program menampilkan pesan kesalahan lalu kembali ke menu.
</p>

<h3>3.2 Lihat Semua Booking (Menu 3)</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/1e99c455-a6c8-4035-960d-64b4a029ab74" alt="Lihat semua booking" width="600">
</p>
<p align="center"><i>Gambar 3. Lihat semua booking (data awal)</i></p>

<p align="justify">
Menampilkan seluruh booking beserta ID, info pelanggan, info artist, desain, harga, tanggal, dan status. Info pelanggan dan artist diambil dari method <code>getInfo()</code> masing-masing class (polymorphism). Saat pertama kali dijalankan, sudah ada 2 booking dari data awal. Jika tidak ada data, tampil pesan "(Belum ada data)".
</p>

<h3>3.3 Tambah Artist (Menu 1)</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/09f2348e-d0a1-41c6-8600-0bb128f2c027" alt="Tambah artist" width="600">
</p>
<p align="center"><i>Gambar 4. Tambah artist</i></p>

<p align="justify">
Pengguna mengisi nama, no HP, dan spesialisasi artist. Artist disimpan di <code>ArtistController</code>, terpisah dari input booking, sehingga data artist tidak perlu diketik ulang setiap kali membuat booking.
</p>

<h3>3.4 Tambah Booking (Menu 2)</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/7f13cfff-7c7a-4e1c-bdf8-af0fc6d07eb0" alt="Tambah booking" width="600">
</p>
<p align="center"><i>Gambar 5. Tambah booking dengan memilih artist dari daftar</i></p>

<p align="justify">
Pengguna mengisi data pelanggan (nama, no HP, alamat), lalu program menampilkan <b>daftar artist bernomor</b> dan pengguna cukup memilih nomornya. Setelah itu pengguna mengisi nama desain, harga, dan tanggal. Status awal booking otomatis <code>Menunggu</code>.
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/0de7bbde-6d04-4851-b47a-c3ccca3a41b2" alt="Booking baru muncul di daftar" width="600">
</p>
<p align="center"><i>Gambar 6. Booking baru muncul di daftar booking</i></p>

<h3>3.5 Validasi Input</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/61f2bb11-cae5-4f4a-a087-fb07b7833ad7" alt="Validasi input" width="600">
</p>
<p align="center"><i>Gambar 7. Validasi input</i></p>

<p align="justify">
Input dicek di <code>InputValidator</code> dan diulang sampai benar: nomor artist harus sesuai daftar, harga harus angka dan lebih dari 0 (tidak boleh minus), tanggal harus berformat <code>dd-mm-yyyy</code> dan nyata (misalnya <code>31-02-2026</code> ditolak), serta teks tidak boleh kosong. Data kemudian dicek lagi di setter model sebagai pengaman terakhir.
</p>

<h3>3.6 Update Booking (Menu 4)</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/1090fad7-f339-4780-81e0-b2c6d48ce453" alt="Update booking" width="600">
</p>
<p align="center"><i>Gambar 8. Update booking, Enter mempertahankan data lama</i></p>

<p align="justify">
Program menampilkan daftar booking, pengguna memasukkan ID, lalu data saat ini ditampilkan. Field yang dapat diubah adalah nama desain, harga, dan tanggal. Setiap field menampilkan nilai lama di dalam tanda kurung siku, dan <b>menekan Enter mempertahankan nilai lama</b>. Jika ID tidak ditemukan, tampil pesan "ID tidak ditemukan.".
</p>

<h3>3.7 Ubah Status Booking (Menu 5)</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/43cf0a00-a12d-4b0a-8961-51753e6f7420" alt="Ubah status booking" width="600">
</p>
<p align="center"><i>Gambar 9. Ubah status booking</i></p>

<p align="justify">
Pengguna memilih ID booking dari daftar, lalu mengisi status baru. Status hanya boleh <code>Menunggu</code>, <code>Dikerjakan</code>, atau <code>Selesai</code>, dan input diulang sampai sesuai.
</p>

<h3>3.8 Hapus Booking (Menu 6)</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/93c25dc8-b75b-44ab-960f-b3849865ca9a" alt="Hapus booking" width="600">
</p>
<p><i>Gambar 10. Hapus booking</i></p>

<p align="justify">
Pengguna memilih ID booking dari daftar yang ditampilkan, lalu booking tersebut dihapus oleh method <code>hapus()</code> dari interface <code>Crud</code>.
</p>

<h3>3.9 Keluar (Menu 0)</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/78f98bd7-c3ee-4202-a52c-b57151997886" alt="Keluar dari program" width="600">
</p>
<p align="center"><i>Gambar 11. Keluar dari program</i></p>

<p align="justify">
Program menampilkan "Terima kasih, program selesai." dan perulangan menu berhenti.
</p>

<hr>

<h2 align="center">4. Penerapan Encapsulation dan Inheritance</h2>

<h3>4.1 Encapsulation</h3>

<p align="justify">
Semua atribut di <code>Orang</code>, <code>Pelanggan</code>, <code>TattooArtist</code>, dan <code>Booking</code> bersifat <code>private</code> dan hanya diakses melalui getter dan setter. Setiap setter memanggil method validasi di <code>InputValidator</code>, dan nilai baru hanya disimpan jika lolos validasi.
</p>

<div align="center">

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

</div>

<p align="justify">
Contoh penerapan pada <code>model/Booking.java</code>:
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/2836f56a-b561-4fc6-a63d-6011e3a508dc" alt="Encapsulation" width="600">
</p>
<p align="center"><i>Gambar 12. Encapsulation</i></p>

<p align="justify">
Constructor juga memanggil setter, sehingga data yang masuk ke objek selalu melewati validasi yang sama.
</p>

<p align="center"><b>Penggunaan <code>final</code></b></p>

<div align="center">

| Lokasi | Penggunaan `final` |
|---|---|
| `Booking` | `id`, `pelanggan`, dan `artist` bersifat `final` (tidak boleh berubah setelah booking dibuat) |
| `Orang` | `setNama()` dan `setNoHp()` bersifat `final` karena dipanggil di constructor |
| `InputValidator` | `final class`, dengan `Scanner sc` bersifat `private static final` |
| `BookingController` | `daftarBooking` dan `artistController` bersifat `final` |
| `ArtistController` | `daftarArtist` bersifat `final` |

</div>

<h3>4.2 Inheritance</h3>

<p align="justify">
<code>Orang</code> adalah superclass, dengan dua subclass yaitu <code>Pelanggan</code> dan <code>TattooArtist</code>. Kedua subclass mewarisi <code>nama</code>, <code>noHp</code>, beserta getter dan setter-nya dari <code>Orang</code>, lalu memanggil <code>super(nama, noHp)</code> pada constructor.
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/ff882bd7-1dcd-4b8c-926b-d31248ad8b82" alt="Inheritance 1" width="600">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/104fcfc8-a90d-4c13-aacf-c03b7305a288" alt="Inheritance 2" width="600">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/c969d74a-ec73-49a4-8195-69a39043d75a" alt="Inheritance 3" width="600">
</p>
<p align="center"><i>Gambar 13. Inheritance</i></p>

<hr>

<h2 align="center">5. Penerapan Polymorphism dan Abstraction</h2>

<h3>5.1 Abstraction</h3>

<p align="justify">
<code>Orang</code> adalah <b>abstract class</b> dengan <b>abstract method</b> <code>getInfo()</code> (<code>model/Orang.java</code>). Class ini tidak dapat dibuat objeknya secara langsung, dan setiap subclass wajib mengimplementasikan <code>getInfo()</code>.
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/7abda4b4-8eff-4524-b269-bad9a9160bc1" alt="Abstraction" width="600">
</p>
<p align="center"><i>Gambar 14. Abstraction</i></p>

<h3>5.2 Polymorphism - Overriding</h3>

<p align="justify">
<code>Pelanggan</code> dan <code>TattooArtist</code> meng-override <code>getInfo()</code> dengan isi yang berbeda.
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/0f30eeea-0ee0-45f9-a7e1-525e8ad63fc4" alt="Overriding Pelanggan" width="600">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/aecec4ab-dcad-48f5-9b5b-ec01c8e40e5f" alt="Overriding TattooArtist" width="600">
</p>
<p align="center"><i>Gambar 15. Overriding</i></p>

<p align="justify">
<code>BookingView</code> memanggil method tersebut melalui reference bertipe <code>Orang</code>. Method yang dijalankan ditentukan oleh objek aslinya saat runtime.
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/8dfaefb8-3df8-4eaa-8ee5-dda763409a96" alt="Pemanggilan method hasil overriding" width="600">
</p>
<p align="center"><i>Gambar 16. Pemanggilan method hasil overriding</i></p>

<h3>5.3 Polymorphism - Overloading</h3>

<p align="justify">
<code>util/InputValidator.java</code> memiliki beberapa method dengan nama sama tetapi parameter berbeda.
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/d7679702-4d4b-4e2e-9db0-2768bf418367" alt="Overloading 1" width="600">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/e44ba0dc-9ad0-4fa1-878b-aa2fde2a7a90" alt="Overloading 2" width="600">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/1caf4fd3-66e4-4c07-a2c6-d110005c309c" alt="Overloading 3" width="600">
</p>
<p align="center"><i>Gambar 17. Overloading</i></p>

<hr>

<h2 align="center">6. Letak Penerapan Nilai Tambah</h2>

<h3>Interface</h3>

<p align="justify">
Interface <code>Crud</code> berada di <code>interfaces/Crud.java</code> dan berisi kontrak operasi data booking.
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/e7a2d558-eb55-4785-bd2b-dffd5a8b300b" alt="Interface Crud" width="600">
</p>
<p align="center"><i>Gambar 18. Interface</i></p>

<p align="justify">
Interface ini diimplementasikan oleh <code>BookingController</code> (<code>controller/BookingController.java</code>) dengan menggunakan <code>@Override</code> pada ketiga method tersebut.
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/7fd640a9-51c0-4dea-a767-4be0b6103845" alt="Implementasi interface 1" width="600">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/239715ca-486d-4619-ac8d-c0529d40a33c" alt="Implementasi interface 2" width="600">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/4b34a611-35f8-4a8c-a515-640a3f134d2d" alt="Implementasi interface 3" width="600">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/b9029b48-8f6e-468e-962c-68a9e1453cf9" alt="Implementasi interface 4" width="600">
</p>
<p align="center"><i>Gambar 19. Implementasi interface</i></p>
