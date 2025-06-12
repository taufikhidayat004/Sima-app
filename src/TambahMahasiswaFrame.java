import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TambahMahasiswaFrame extends JFrame {
    private JTextField tfNama, tfNIM, tfJurusan;
    private Connection conn;

    public TambahMahasiswaFrame() {
        setTitle("Tambah Mahasiswa");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        conn = DatabaseConnection.getConnection();

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        panel.add(new JLabel("Nama:"));
        tfNama = new JTextField();
        panel.add(tfNama);

        panel.add(new JLabel("NIM:"));
        tfNIM = new JTextField();
        panel.add(tfNIM);

        panel.add(new JLabel("Jurusan:"));
        tfJurusan = new JTextField();
        panel.add(tfJurusan);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> simpanMahasiswa());
        panel.add(new JLabel());
        panel.add(btnSimpan);

        add(panel);
    }

    private void simpanMahasiswa() {
        String nama = tfNama.getText();
        String nim = tfNIM.getText();
        String jurusan = tfJurusan.getText();

        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, nim);
            stmt.setString(3, jurusan);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            dispose(); // tutup jendela
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}

