package erronka3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class LangileaAdminKud extends MenuAdminNagusia {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable langileak;
	private JTextField txtLangileIzena;
	private JTextField txtLangileErabiltzailea;
	private JTextField txtLangilePasahitza;
	private JTextField txtLangileTelefonoa;
	private JTextField txtLangileEmaila;
	private JComboBox<String> txtLangileMota;
	private Langilea langilea;
	private JTextField txtLangileAbizenak;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LangileaAdminKud frame = new LangileaAdminKud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LangileaAdminKud() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		langileak = new JTable();
		JScrollPane scrollPane = new JScrollPane(langileak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		langilea = new Langilea();
		List<Langilea> langileakList = langilea.langileakBistaratu();
		TaulaKargatu(langileakList);

		JLabel orriIzenburua = new JLabel("Langileak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);

		JLabel lblLangileIzenAbizenak = new JLabel("Izena:");
		lblLangileIzenAbizenak.setBounds(40, 40, 80, 20);
		lblLangileIzenAbizenak.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblLangileIzenAbizenak);

		JLabel lblLangileErabiltzailea = new JLabel("Erabiltzailea:");
		lblLangileErabiltzailea.setBounds(40, 110, 80, 20);
		lblLangileErabiltzailea.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblLangileErabiltzailea);

		JLabel lblLangilePasahitza = new JLabel("Pasahitza:");
		lblLangilePasahitza.setBounds(40, 145, 80, 20);
		lblLangilePasahitza.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblLangilePasahitza);

		JLabel lblLangileTelefonoa = new JLabel("Telefonoa:");
		lblLangileTelefonoa.setBounds(384, 40, 80, 20);
		lblLangileTelefonoa.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblLangileTelefonoa);

		JLabel lblLangileEmaila = new JLabel("Emaila:");
		lblLangileEmaila.setBounds(384, 75, 80, 20);
		lblLangileEmaila.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblLangileEmaila);

		JLabel lblLangileMota = new JLabel("Kargua:");
		lblLangileMota.setBounds(384, 110, 80, 20);
		lblLangileMota.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblLangileMota);

		txtLangileIzena = new JTextField();
		txtLangileIzena.setBounds(155, 40, 120, 20);
		txtLangileIzena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtLangileIzena);

		txtLangileErabiltzailea = new JTextField();
		txtLangileErabiltzailea.setBounds(155, 110, 120, 20);
		txtLangileErabiltzailea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtLangileErabiltzailea);

		txtLangilePasahitza = new JTextField();
		txtLangilePasahitza.setBounds(155, 145, 120, 20);
		txtLangilePasahitza.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLangilePasahitza.setColumns(10);
		contentPane.add(txtLangilePasahitza);

		txtLangileTelefonoa = new JTextField();
		txtLangileTelefonoa.setBounds(474, 40, 120, 20);
		txtLangileTelefonoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLangileTelefonoa.setColumns(10);
		contentPane.add(txtLangileTelefonoa);

		txtLangileEmaila = new JTextField();
		txtLangileEmaila.setBounds(474, 75, 120, 20);
		txtLangileEmaila.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLangileEmaila.setColumns(10);
		contentPane.add(txtLangileEmaila);

		List<String> karguAukerak = langilea.KarguAukeraGuztiak();

		txtLangileMota = new JComboBox<>();
		txtLangileMota.setBounds(474, 110, 120, 20);
		txtLangileMota.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtLangileMota);

		txtLangileMota.addItem("Aukeratu");

		for (String kargua : karguAukerak) {
			txtLangileMota.addItem(kargua);
		}

		JButton langileaSortu = new JButton("Gehitu");
		langileaSortu.setBounds(700, 60, 100, 20);
		contentPane.add(langileaSortu);

		JButton eguneratuLangilea = new JButton("Eguneratu");
		eguneratuLangilea.setBounds(700, 90, 100, 20);
		eguneratuLangilea.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuLangilea);

		JButton ezabatuLangilea = new JButton("Ezabatu");
		ezabatuLangilea.setBounds(700, 30, 100, 20);
		ezabatuLangilea.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuLangilea);

		JLabel lblLangileAbizenak = new JLabel("Abizenak:");
		lblLangileAbizenak.setFont(new Font("Agency FB", Font.BOLD, 15));
		lblLangileAbizenak.setBounds(40, 79, 80, 20);
		contentPane.add(lblLangileAbizenak);

		txtLangileAbizenak = new JTextField();
		txtLangileAbizenak.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLangileAbizenak.setBounds(155, 80, 120, 20);
		contentPane.add(txtLangileAbizenak);

		langileak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});

		ezabatuLangilea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = langileak.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(langileak, row)) {
					int idLangilea = (int) langileak.getValueAt(row, 0);
					try {
						Langilea langilea = kontrola.langileaKonprobatu(idLangilea);
						if (langilea != null) {
							if (kontrola.ezabatuLangilea(idLangilea)) {
								JOptionPane.showMessageDialog(null, "Langilea ongi ezabatuta.");
								TaulaKargatu(langilea.langileakBistaratu());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ezin izan da langilea ezabatu.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu langile bat ezabatzeko.");
				}
			}
		});

		eguneratuLangilea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = langileak.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(langileak, row)) {
					int idLangilea = (int) langileak.getValueAt(row, 0);

					Langilea langilea = kontrola.langileaKonprobatu(idLangilea);

					if (langilea != null) {
						langilea.setIzena(txtLangileIzena.getText());
						langilea.setAbizenak(txtLangileAbizenak.getText());
						langilea.setErabiltzailea(txtLangileErabiltzailea.getText());
						langilea.setPasahitza(txtLangilePasahitza.getText());
						langilea.setTelefonoa(txtLangileTelefonoa.getText());
						langilea.setEmaila(txtLangileEmaila.getText());
						langilea.setKargua(txtLangileMota.getSelectedItem().toString());

						if (kontrola.eguneratuLangilea(langilea)) {
							JOptionPane.showMessageDialog(null, "Langilea ongi eguneratuta.");
							TaulaKargatu(langilea.langileakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da langilea eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da langilea aurkitu: " + idLangilea);
					}
				}
			}
		});

		langileaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String izena = txtLangileIzena.getText().trim();
				String abizenak = txtLangileAbizenak.getText().trim();
				String erabiltzailea = txtLangileErabiltzailea.getText().trim();
				String pasahitza = txtLangilePasahitza.getText().trim();
				String telefonoa = txtLangileTelefonoa.getText().trim();
				String emaila = txtLangileEmaila.getText().trim();
				String kargua = txtLangileMota.getSelectedItem().toString();

				if (izena.isEmpty() || abizenak.isEmpty() || kargua.equals("Aukeratu") || pasahitza.isEmpty()
						|| telefonoa.isEmpty() || erabiltzailea.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ezin dituzu erregistroak hutsik bidali.");
					return;
				}

				Kontrola kontrola = new Kontrola();

				try {
					if (kontrola.langileEmailaKonprobatu(emaila)) {
						JOptionPane.showMessageDialog(null, "Emaila errepikatuta dago, berriro saiatu.");
					} else if (kontrola.langileTelefonoaKonprobatu(telefonoa)) {
						JOptionPane.showMessageDialog(null, "Telefonoa errepikatuta dago, berriro saiatu.");
					} else if (kontrola.langileErabiltzaileaKonprobatu(erabiltzailea)) {
						JOptionPane.showMessageDialog(null, "Erabiltzailea errepikatuta dago, berriro saiatu.");
					} else {
						Langilea langilea = new Langilea(0, izena, abizenak, erabiltzailea, pasahitza, telefonoa,
								emaila, kargua);
						boolean success = langilea.sortuLangilea(langilea);
						if (success) {
							JOptionPane.showMessageDialog(null, "Langilea ongi sortuta.");
							TaulaKargatu(langilea.langileakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da langilea sortzean.");
						}
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	private void TaulaKargatu(List<Langilea> langileakList) {
		String[] Zutabeak = { "ID", "Izena", "Abizenak", "Erabiltzailea", "Pasahitza", "Telefonoa", "Emaila",
				"Kargua" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Langilea lang : langileakList) {
			Object[] row = { lang.getIdLangilea(), lang.getIzena(), lang.getAbizenak(), lang.getErabiltzailea(),
					lang.getPasahitza(), lang.getTelefonoa(), lang.getEmaila(), lang.getKargua() };
			model.addRow(row);
		}
		langileak.setModel(model);
	}

	private void AukeratutakoErregistroaErakutsi() {
		int row = langileak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(langileak, row)) {
			txtLangileIzena.setText(langileak.getValueAt(row, 1).toString());
			txtLangileAbizenak.setText(langileak.getValueAt(row, 2).toString());
			txtLangileErabiltzailea.setText(langileak.getValueAt(row, 3).toString());
			txtLangilePasahitza.setText(langileak.getValueAt(row, 4).toString());
			txtLangileTelefonoa.setText(langileak.getValueAt(row, 5).toString());
			txtLangileEmaila.setText(langileak.getValueAt(row, 6).toString());

			String egoera = langileak.getValueAt(row, 7).toString().trim().toLowerCase();
			for (int i = 0; i < txtLangileMota.getItemCount(); i++) {
				if (txtLangileMota.getItemAt(i).toString().trim().toLowerCase().equals(egoera)) {
					txtLangileMota.setSelectedIndex(i);
					break;
				}
			}
		}
	}
}
