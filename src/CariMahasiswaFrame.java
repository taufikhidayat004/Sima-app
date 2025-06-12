import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CariMahasiswaFrame extends JFrame {
    private JTextField tfCari;
    private JTable table;
    private DefaultTableModel model;
    private Connection conn;

    public CariMahasiswaFrame() {
        setTitle("Cari Mahasiswa");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        conn = DatabaseConnection.getConnection();

        JPanel atas = new JPanel(new BorderLayout());
        tfCari = new JTextField();
        JButton btnCari = new JButton("Cari");

        atas.add(tfCari, BorderLayout.CENTER);
        atas.add(btnCari, BorderLayout.EAST);

        String[] kolom = {"ID", "Nama", "NIM", "Jurusan"};
        model = new DefaultTableModel(kolom, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(atas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnCari.addActionListener(e -> cariMahasiswa());
    }

    private void cariMahasiswa() {
        model.setRowCount(0); // hapus isi tabel lama
        String keyword = tfCari.getText();

        try {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ? OR nim LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("nim"),
                        rs.getString("jurusan")
                };
                model.addRow(row);
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
