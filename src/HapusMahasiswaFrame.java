import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class
HapusMahasiswaFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private Connection conn;

    public HapusMahasiswaFrame() {
        setTitle("Hapus Mahasiswa");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        conn = DatabaseConnection.getConnection();

        model = new DefaultTableModel(new String[]{"ID", "Nama", "NIM", "Jurusan"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnHapus = new JButton("Hapus Mahasiswa");
        btnHapus.addActionListener(e -> hapusMahasiswa());

        add(scrollPane, BorderLayout.CENTER);
        add(btnHapus, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            String sql = "SELECT * FROM mahasiswa";
            PreparedStatement stmt = conn.prepareStatement(sql);
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void hapusMahasiswa() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih dulu datanya yang mau dihapus.");
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);

        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin mau hapus mahasiswa ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM mahasiswa WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }
}
