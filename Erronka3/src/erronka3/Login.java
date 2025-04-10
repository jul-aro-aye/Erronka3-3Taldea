package erronka3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtPasa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 404);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErab = new JLabel("Erabiltzailea:");
		lblErab.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblErab.setBounds(79, 195, 120, 30);
		contentPane.add(lblErab);
		
		txtPasa = new JPasswordField();
		txtPasa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPasa.setBounds(499, 200, 115, 25);
		contentPane.add(txtPasa);
		
		JTextArea txtErab = new JTextArea();
		txtErab.setBounds(199, 200, 115, 25);
		contentPane.add(txtErab);
		
		JLabel lblPasa = new JLabel("Pasahitza:");
		lblPasa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPasa.setBounds(379, 195, 120, 30);
		contentPane.add(lblPasa);
		
		JButton btnSaioaHasi = new JButton("Saioa Hasi");
		btnSaioaHasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Erab = txtErab.getText();
				char[] Pasa = txtPasa.getPassword();
				String Pashitza = new String(Pasa);

				Kontrola kontrola = new Kontrola();

				try {

					boolean emaitza = kontrola.langileErabiltzaileaKonprobatu(Erab);

					if (emaitza == true) {

						kontrola.loginEgin(Erab, Pashitza, Login.this);

					} else {
						JOptionPane.showMessageDialog(null, "Erabiltzailea eta pasahitza okerrak da!", "Arazoak",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Errore bat gertatu da!", "Errorea", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSaioaHasi.setBackground(new Color(128, 255, 128));
		btnSaioaHasi.setBounds(281, 269, 129, 48);
		contentPane.add(btnSaioaHasi);
		
		JLabel lblIzenburua = new JLabel("Ongi etorri AeroPark App-era");
		lblIzenburua.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblIzenburua.setBounds(126, 39, 488, 101);
		contentPane.add(lblIzenburua);
	}
}
