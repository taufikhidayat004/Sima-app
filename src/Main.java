import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set tampilan UI ke sistem operasi biar lebih enak dilihat
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Gagal set Look and Feel: " + e.getMessage());
        }

        // Jalankan LoginFrame sebagai awal aplikasi
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
