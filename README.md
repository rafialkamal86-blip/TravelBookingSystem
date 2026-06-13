# Travel Booking System

A Java console application for booking flights and hotels.

## Fitur
- Pencarian penerbangan berdasarkan kota asal, kota tujuan, tanggal, dan jumlah penumpang.
- Pencarian hotel berdasarkan lokasi dan tanggal check-in/check-out.
- Pemesanan penerbangan dan hotel dengan nomor konfirmasi 6 digit.
- Pembatalan reservasi berdasarkan nomor konfirmasi.
- Menampilkan semua reservasi saat ini.
- Validasi input dan penanganan eksepsi.

## Menjalankan Aplikasi
1. Buka terminal di folder `TravelBookingSystem`.
2. Compile semua file Java:
   ```bash
   javac src/*.java
   ```
3. Jalankan aplikasi:
   ```bash
   java -cp src Main
   ```

## Contoh Input
- Tanggal check-in: `2026-06-15`
- Tanggal perjalanan: `Besok atau H+1 mengikuti tanggal input`
- Kota asal: `Jakarta`
- Kota tujuan: `Bali`
- Lokasi hotel: `Bali`
