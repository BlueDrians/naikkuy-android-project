# LAPORAN PROYEK APLIKASI MOBILE  
## NaikKuy - Aplikasi Informasi dan Pemesanan Transportasi Umum

---

## Cover

**Judul:** NaikKuy - Aplikasi Informasi dan Pemesanan Transportasi Umum  
**Mata Kuliah:** Pemrograman Mobile  
**Platform:** Android  
**Bahasa Pemrograman:** Java  
**Layout:** XML  
**Database Lokal:** SQLite dan SharedPreferences  

**Disusun oleh:**  
1. Nama Anggota 1 / NIM  
2. Nama Anggota 2 / NIM  

---

## 1. Latar Belakang

Transportasi umum merupakan salah satu kebutuhan penting masyarakat dalam melakukan aktivitas sehari-hari. Namun, informasi mengenai rute, jadwal keberangkatan, serta pemesanan tiket sering kali belum tersaji dalam satu aplikasi sederhana yang mudah digunakan.

Berdasarkan permasalahan tersebut, dibuat aplikasi **NaikKuy** sebagai aplikasi mobile Android sederhana yang membantu pengguna melihat informasi rute, jadwal transportasi, melakukan pemesanan tiket, melihat riwayat pesanan, dan mengelola profil pengguna.

---

## 2. Tujuan

Tujuan pembuatan aplikasi NaikKuy adalah:

1. Membuat aplikasi Android bertema transportasi umum menggunakan Java dan XML.
2. Menyediakan fitur login dan register pengguna.
3. Menampilkan daftar rute dan jadwal transportasi umum.
4. Menyediakan fitur pemesanan tiket sederhana.
5. Menyimpan riwayat pemesanan menggunakan database lokal SQLite.
6. Melatih penggunaan Activity, Intent, RecyclerView, Spinner, AlertDialog, Toast, SQLite, dan SharedPreferences.

---

## 3. Manfaat

Manfaat aplikasi NaikKuy adalah:

1. Mempermudah pengguna dalam melihat informasi rute transportasi umum.
2. Mempermudah pengguna mengetahui jadwal keberangkatan.
3. Mempermudah pengguna melakukan simulasi pemesanan tiket.
4. Menjadi media pembelajaran pengembangan aplikasi Android berbasis Java.
5. Menjadi contoh aplikasi mobile sederhana yang memiliki interaksi dinamis.

---

## 4. Rancangan Aplikasi

### 4.1 Daftar Activity

1. **SplashActivity**  
   Menampilkan logo dan nama aplikasi selama 2 detik.

2. **LoginActivity**  
   Menampilkan form login username dan password.

3. **RegisterActivity**  
   Menampilkan form pendaftaran akun baru.

4. **MainActivity**  
   Menampilkan dashboard menu utama aplikasi.

5. **RouteActivity**  
   Menampilkan daftar rute transportasi dan fitur pencarian.

6. **ScheduleActivity**  
   Menampilkan jadwal transportasi dan filter tujuan.

7. **BookingActivity**  
   Menampilkan form pemesanan tiket dan menghitung total harga.

8. **HistoryActivity**  
   Menampilkan riwayat pesanan dan fitur hapus riwayat.

9. **ProfileActivity**  
   Menampilkan profil pengguna dan tombol logout.

### 4.2 Database Lokal

Aplikasi menggunakan SQLite untuk menyimpan data user dan booking.

#### Tabel users
| Field | Tipe | Keterangan |
|---|---|---|
| id | INTEGER | Primary key |
| name | TEXT | Nama pengguna |
| username | TEXT | Username unik |
| password | TEXT | Password |
| phone | TEXT | Nomor HP |

#### Tabel bookings
| Field | Tipe | Keterangan |
|---|---|---|
| id | INTEGER | Primary key |
| username | TEXT | Pemilik pesanan |
| passenger_name | TEXT | Nama penumpang |
| transport | TEXT | Jenis transportasi |
| destination | TEXT | Tujuan |
| date | TEXT | Tanggal berangkat |
| ticket_count | INTEGER | Jumlah tiket |
| total_price | INTEGER | Total harga |

SharedPreferences digunakan untuk menyimpan session login pengguna.

---

## 5. Implementasi

Aplikasi dibuat menggunakan Android Studio dengan bahasa Java dan XML. Data rute dan jadwal menggunakan data dummy lokal agar mudah dijalankan saat presentasi tanpa memerlukan koneksi internet atau backend online.

Fitur utama yang diimplementasikan:

1. Validasi form login, register, dan booking.
2. Perpindahan halaman menggunakan Intent.
3. Feedback pengguna menggunakan Toast dan AlertDialog.
4. Daftar data menggunakan RecyclerView.
5. Filter data menggunakan Spinner.
6. Perhitungan otomatis total harga tiket.
7. Penyimpanan data lokal menggunakan SQLite.
8. Session login menggunakan SharedPreferences.
9. CRUD sederhana berupa tambah pesanan dan hapus riwayat pesanan.

---

## 6. Skenario Demo Presentasi 2 Anggota

### Anggota 1
1. Menjelaskan latar belakang aplikasi.
2. Menjelaskan tujuan dan manfaat aplikasi.
3. Menjelaskan alur Splash, Register, Login, dan Dashboard.
4. Menunjukkan fitur rute dan pencarian rute.

### Anggota 2
1. Menjelaskan fitur jadwal dan filter tujuan.
2. Menunjukkan fitur booking tiket.
3. Menjelaskan perhitungan total harga otomatis.
4. Menampilkan riwayat pesanan dan fitur hapus.
5. Menunjukkan profil pengguna dan logout.
6. Menyampaikan kesimpulan.

---

## 7. Kesimpulan

Aplikasi NaikKuy berhasil dibuat sebagai aplikasi Android bertema transportasi umum menggunakan Java dan XML. Aplikasi ini memiliki beberapa Activity dengan fungsi yang jelas, interaksi pengguna yang dinamis, penyimpanan data lokal, dan fitur CRUD sederhana. Dengan fitur rute, jadwal, booking, riwayat, dan profil, aplikasi ini sudah sesuai untuk dipresentasikan sebagai tugas kelompok 2 orang.

---

## 8. Profil Tim

### Anggota 1
Nama:  
NIM:  
Peran: UI, Login, Register, Dashboard, Route

### Anggota 2
Nama:  
NIM:  
Peran: Database, Booking, History, Profile, Dokumentasi
