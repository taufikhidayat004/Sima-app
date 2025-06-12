# 🎓 SIMA APP (Sistem Informasi Mahasiswa)

Aplikasi desktop berbasis Java untuk mengelola data mahasiswa, dilengkapi fitur login admin, CRUD data mahasiswa, pencarian, dan export ke file CSV/Excel.

---

## ✨ Fitur Utama

- 🔐 Login Admin
- ➕ Tambah Mahasiswa
- 📋 Lihat Semua Mahasiswa
- ✏️ Edit Data Mahasiswa
- 🗑️ Hapus Mahasiswa
- 🔍 Cari Mahasiswa
- 📤 Export Data ke CSV
- 📤 Export Data ke Excel (.xlsx)
- 👤 Ubah Username & Password Admin
- 👁️ Lihat Password Saat Login
- 🖼️ GUI (Graphical User Interface) via Java Swing

---

## 🖼️ Tampilan Aplikasi (GUI)

| Login Screen | Menu Utama | Input Mahasiswa |
|--------------|------------|-----------------|
| ![login](screenshots/login.png) | ![menu](screenshots/menu.png) | ![input](screenshots/input.png) |

*(Bisa ditambahkan sendiri jika lu punya gambar GUI-nya, tinggal simpan di folder `screenshots/`)*

---

## ⚙️ Cara Clone Repo

```bash
git clone https://github.com/taufikhidayat004/Sima-app.git
CREATE TABLE admin (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50),
  password VARCHAR(100)
);
INSERT INTO admin (username, password) VALUES ('admin', 'admin123');
CREATE TABLE mahasiswa (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nama VARCHAR(100),
  nim VARCHAR(20),
  jurusan VARCHAR(100)
);
