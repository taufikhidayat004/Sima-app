import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("SIMA APP - Menu Utama");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnTambah = new JButton("Tambah Mahasiswa");
        JButton btnLihat = new JButton("Lihat Mahasiswa");
        JButton btnCari = new JButton("Cari Mahasiswa");
        JButton btnLogout = new JButton("Logout");

        // AKSI TOMBOL
        btnTambah.addActionListener(e -> new TambahMahasiswaFrame().setVisible(true));
        btnLihat.addActionListener(e -> new LihatMahasiswaFrame().setVisible(true));
        btnCari.addActionListener(e -> new CariMahasiswaFrame().setVisible(true));
        JButton btnHapus = new JButton("Hapus Mahasiswa");
        btnHapus.addActionListener(e -> new HapusMahasiswaFrame().setVisible(true));
        panel.add(btnHapus);
        JButton btnExport = new JButton("Export ke CSV");
        btnExport.addActionListener(e -> new ExportMahasiswaFrame().setVisible(true));
        panel.add(btnExport);
        JButton btnUbahAdmin = new JButton("Ubah Akun Admin");
        btnUbahAdmin.addActionListener(e -> {
            new UbahAdminFrame();
        });
        panel.add(btnUbahAdmin);



        btnLogout.addActionListener(e -> {
            dispose(); // Tutup menu
            new LoginFrame(); // Balik ke login
        });

        // TAMBAHKAN TOMBOL KE PANEL
        panel.add(btnTambah);
        panel.add(btnLihat);
        panel.add(btnCari);
        panel.add(btnHapus);
        panel.add(btnExport);
        panel.add(btnUbahAdmin);
        panel.add(btnLogout);


        add(panel);
        setVisible(true);
    }
}
