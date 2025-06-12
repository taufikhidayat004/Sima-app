import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TambahMahasiswaFrame extends JFrame {

    public TambahMahasiswaFrame() {
        setTitle("Tambah Data Mahasiswa");
        setSize(400, 300); // Diperkecil
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Komponen
        JLabel namaLabel = new JLabel("Nama:");
        JTextField namaField = new JTextField();

        JLabel nimLabel = new JLabel("NIM:");
        JTextField nimField = new JTextField();

        JLabel jurusanLabel = new JLabel("Jurusan:");
        JTextField jurusanField = new JTextField();

        JButton simpanButton = new JButton("Simpan");

        // Layout Panel
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(namaLabel); panel.add(namaField);
        panel.add(nimLabel); panel.add(nimField);
        panel.add(jurusanLabel); panel.add(jurusanField);
        panel.add(new JLabel()); panel.add(simpanButton);

        add(panel);

        // Event Simpan
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = namaField.getText();
                String nim = nimField.getText();
                String jurusan = jurusanField.getText();

                // Simpan ke database via DAO
                // MahasiswaDAO.tambahMahasiswa(nama, nim, jurusan);
                JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil disimpan!");
                dispose();
            }
        });

        setVisible(true);
    }
}
