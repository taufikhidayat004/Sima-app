import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UbahAdminFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnSimpan;

    public UbahAdminFrame() {
        setTitle("Ubah Akun Admin - SIMA APP");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username Baru:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password Baru:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnSimpan = new JButton("Simpan Perubahan");
        panel.add(new JLabel());
        panel.add(btnSimpan);

        btnSimpan.addActionListener(e -> simpanPerubahan());

        add(panel);
        setVisible(true);
    }

    private void simpanPerubahan() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE admin SET username=?, password=? WHERE id=1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Akun admin berhasil diperbarui!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui akun admin.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
