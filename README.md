# NaikKuy, Aplikasi Transportasi Umum Android

NaikKuy adalah aplikasi Android berbasis Java dan XML untuk informasi rute, jadwal, pemesanan tiket, riwayat pesanan, dan profil pengguna.

## Fitur
1. Splash screen 2 detik.
2. Login dan register dengan validasi.
3. Dashboard menu menggunakan CardView.
4. Daftar rute dengan RecyclerView dan fitur pencarian.
5. Jadwal transportasi dengan filter Spinner.
6. Booking tiket dengan validasi, DatePicker, Spinner, dan hitung total otomatis.
7. Riwayat pesanan menggunakan SQLite, bisa hapus satu pesanan atau hapus semua.
8. Profil pengguna dan logout.

## Teknologi
- Android Studio
- Java
- XML Layout
- SQLite
- SharedPreferences
- RecyclerView
- CardView
- Min SDK 23

## Dependency app/build.gradle
```gradle
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.recyclerview:recyclerview:1.3.2'
implementation 'androidx.cardview:cardview:1.0.0'
```

## Cara menjalankan
1. Buka Android Studio.
2. Pilih **Open**.
3. Arahkan ke folder `NaikKuy`.
4. Tunggu Gradle Sync selesai.
5. Jalankan ke emulator atau HP Android.
6. Klik Register, buat akun, lalu Login.
7. Coba fitur rute, jadwal, booking, riwayat, dan logout.

## Catatan penting
Project ini dibuat original untuk tugas kuliah dan menggunakan data dummy lokal, tanpa backend online atau API berbayar.


## Update Back Navigation

Versi ini sudah ditambahkan tombol **← Kembali** pada RegisterActivity, RouteActivity, ScheduleActivity, BookingActivity, HistoryActivity, dan ProfileActivity. Tombol tersebut menggunakan `finish()` agar pengguna kembali ke halaman sebelumnya tanpa membuat stack Activity baru.
