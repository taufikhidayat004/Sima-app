import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ExportMahasiswaFrame extends JFrame {

    public ExportMahasiswaFrame() {
        setTitle("Export Mahasiswa ke CSV");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnExport = new JButton("Export ke CSV");
        btnExport.addActionListener(e -> exportData());

        JPanel panel = new JPanel();
        panel.add(btnExport);
        add(panel);
        setVisible(true);
    }

    private void exportData() {
        Connection conn = DatabaseConnection.getConnection();

        try (FileWriter csvWriter = new FileWriter("mahasiswa_export.csv")) {
            String sql = "SELECT * FROM mahasiswa";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Header
            csvWriter.append("ID;Nama;NIM;Jurusan\n");

            while (rs.next()) {
                csvWriter.append(rs.getInt("id") + ";");
                csvWriter.append(rs.getString("nama") + ";");
                csvWriter.append(rs.getString("nim") + ";");
                csvWriter.append(rs.getString("jurusan") + "\n");
            }

            csvWriter.flush();
            JOptionPane.showMessageDialog(this, "Berhasil export ke file mahasiswa_export.csv");
            try {
                Desktop.getDesktop().open(new java.io.File("mahasiswa_export.csv"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membuka file: " + ex.getMessage());
            }

        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal export: " + e.getMessage());
        }
    }
}

