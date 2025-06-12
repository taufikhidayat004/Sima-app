import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class LihatMahasiswaFrame extends JFrame {
    private JTable table;
    private Connection conn;

    public LihatMahasiswaFrame() {
        setTitle("Lihat Data Mahasiswa");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        conn = DatabaseConnection.getConnection();

        String[] columnNames = {"ID", "Nama", "NIM", "Jurusan"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadData(model);
    }

    private void loadData(DefaultTableModel model) {
        try {
            String sql = "SELECT * FROM mahasiswa";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String nim = rs.getString("nim");
                String jurusan = rs.getString("jurusan");

                Object[] row = {id, nama, nim, jurusan};
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
