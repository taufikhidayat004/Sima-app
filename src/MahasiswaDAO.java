import java.sql.*;
import java.util.ArrayList;

public class MahasiswaDAO {

    public void tambahMahasiswa(String nama, String nim, String jurusan) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nama);
        ps.setString(2, nim);
        ps.setString(3, jurusan);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public ArrayList<Mahasiswa> getAllMahasiswa() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM mahasiswa");
        ArrayList<Mahasiswa> list = new ArrayList<>();
        while (rs.next()) {
            Mahasiswa m = new Mahasiswa(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
            );
            list.add(m);
        }
        rs.close();
        conn.close();
        return list;
    }

    public void updateMahasiswa(int id, String nama, String nim, String jurusan) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE mahasiswa SET nama = ?, nim = ?, jurusan = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nama);
        ps.setString(2, nim);
        ps.setString(3, jurusan);
        ps.setInt(4, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void deleteMahasiswa(int id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM mahasiswa WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}

