import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SimaGUI extends JFrame {
    private Connection conn;

    public SimaGUI() {
        // Set title dan ukuran window
        setTitle("SIMA APP");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Koneksi database
        conn = DatabaseConnection.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Gagal koneksi ke database!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Contoh tombol untuk lihat data mahasiswa
        JButton btnLihat = new JButton("Lihat Mahasiswa");
        btnLihat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lihatMahasiswa();
            }
        });

        // Tambahkan tombol ke panel
        JPanel panel = new JPanel();
        panel.add(btnLihat);

        add(panel);
    }

    private void lihatMahasiswa() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mahasiswa");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getInt("id")).append(". ");
                sb.append(rs.getString("nama")).append(", ");
                sb.append(rs.getString("nim")).append(", ");
                sb.append(rs.getString("jurusan")).append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Tidak ada data mahasiswa.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimaGUI().setVisible(true);
        });
    }
}

