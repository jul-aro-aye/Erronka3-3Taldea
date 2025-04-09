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

public class BezeroaAdminKud extends MenuAdminNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable bezeroak;
	private JTextField txtBezeroIzena;
	private JTextField txtBezeroErabiltzailea;
	private JTextField txtBezeroPasahitza;
	private JTextField txtBezeroTelefonoa;
	private JTextField txtBezeroEmaila;
	private JTextField txtBezeroAbizenak;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BezeroaAdminKud frame = new BezeroaAdminKud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BezeroaAdminKud() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		bezeroak = new JTable();
		JScrollPane scrollPane = new JScrollPane(bezeroak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		Bezeroa bezeroa = new Bezeroa();
		List<Bezeroa> bezeroakList = bezeroa.bezeroakBistaratu();
		TaulaKargatu(bezeroakList);

		JLabel orriIzenburua = new JLabel("bezeroak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);

		JLabel lblBezeroIzena = new JLabel("Izena:");
		lblBezeroIzena.setBounds(40, 40, 80, 20);
		lblBezeroIzena.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBezeroIzena);

		JLabel lblBezeroAbizenak = new JLabel("Abizenak:");
		lblBezeroAbizenak.setFont(new Font("Agency FB", Font.BOLD, 15));
		lblBezeroAbizenak.setBounds(40, 80, 80, 20);
		contentPane.add(lblBezeroAbizenak);
		
		JLabel lblBezeroErabiltzailea = new JLabel("Erabiltzailea:");
		lblBezeroErabiltzailea.setBounds(40, 110, 80, 20);
		lblBezeroErabiltzailea.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBezeroErabiltzailea);

		JLabel lblBezeroPasahitza = new JLabel("Pasahitza:");
		lblBezeroPasahitza.setBounds(40, 145, 80, 20);
		lblBezeroPasahitza.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBezeroPasahitza);

		JLabel lblBezeroTelefonoa = new JLabel("Telefonoa:");
		lblBezeroTelefonoa.setBounds(384, 40, 80, 20);
		lblBezeroTelefonoa.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBezeroTelefonoa);

		JLabel lblBezeroEmaila = new JLabel("Emaila:");
		lblBezeroEmaila.setBounds(384, 75, 80, 20);
		lblBezeroEmaila.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBezeroEmaila);

		txtBezeroIzena = new JTextField();
		txtBezeroIzena.setBounds(155, 40, 120, 20);
		txtBezeroIzena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBezeroIzena);
		
		txtBezeroAbizenak = new JTextField();
		txtBezeroAbizenak.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBezeroAbizenak.setBounds(155, 80, 120, 20);
		contentPane.add(txtBezeroAbizenak);

		txtBezeroErabiltzailea = new JTextField();
		txtBezeroErabiltzailea.setBounds(155, 110, 120, 20);
		txtBezeroErabiltzailea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBezeroErabiltzailea);

		txtBezeroPasahitza = new JTextField();
		txtBezeroPasahitza.setBounds(155, 145, 120, 20);
		txtBezeroPasahitza.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBezeroPasahitza.setColumns(10);
		contentPane.add(txtBezeroPasahitza);

		txtBezeroTelefonoa = new JTextField();
		txtBezeroTelefonoa.setBounds(474, 40, 120, 20);
		txtBezeroTelefonoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBezeroTelefonoa.setColumns(10);
		contentPane.add(txtBezeroTelefonoa);

		txtBezeroEmaila = new JTextField();
		txtBezeroEmaila.setBounds(474, 75, 120, 20);
		txtBezeroEmaila.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBezeroEmaila.setColumns(10);
		contentPane.add(txtBezeroEmaila);

		JButton bezeroaSortu = new JButton("Gehitu");
		bezeroaSortu.setBounds(700, 60, 100, 20);
		contentPane.add(bezeroaSortu);

		JButton eguneratuBezeroa = new JButton("Eguneratu");
		eguneratuBezeroa.setBounds(700, 90, 100, 20);
		eguneratuBezeroa.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuBezeroa);

		JButton ezabatuBezeroa = new JButton("Ezabatu");
		ezabatuBezeroa.setBounds(700, 30, 100, 20);
		ezabatuBezeroa.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuBezeroa);

		bezeroak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});

		ezabatuBezeroa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = bezeroak.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(bezeroak, row)) {
					int idBezeroa = (int) bezeroak.getValueAt(row, 0);
					try {
						Bezeroa bezeroa = kontrola.bezeroaKonprobatu(idBezeroa);
						if (bezeroa != null) {
							if (kontrola.ezabatuBezeroa(idBezeroa)) {
								JOptionPane.showMessageDialog(null, "Bezeroa ongi ezabatuta.");
								TaulaKargatu(bezeroa.bezeroakBistaratu());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ezin izan da bezeroa ezabatu.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu bezero bat ezabatzeko.");
				}
			}
		});

		eguneratuBezeroa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = bezeroak.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(bezeroak, row)) {
					int idBezeroa = (int) bezeroak.getValueAt(row, 0);

					Bezeroa bezeroa = kontrola.bezeroaKonprobatu(idBezeroa);

					if (bezeroa != null) {
						bezeroa.setIzena(txtBezeroIzena.getText());
						bezeroa.setAbizenak(txtBezeroAbizenak.getText());
						bezeroa.setErabiltzailea(txtBezeroErabiltzailea.getText());
						bezeroa.setPasahitza(txtBezeroPasahitza.getText());
						bezeroa.setTelefonoa(txtBezeroTelefonoa.getText());
						bezeroa.setEmaila(txtBezeroEmaila.getText());

						if (kontrola.eguneratuBezeroa(bezeroa)) {
							JOptionPane.showMessageDialog(null, "Bezeroa ongi eguneratuta.");
							TaulaKargatu(bezeroa.bezeroakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da bezeroa eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da bezeroa aurkitu: " + idBezeroa);
					}
				}
			}
		});

		bezeroaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String izena = txtBezeroIzena.getText().trim();
				String abizenak = txtBezeroAbizenak.getText().trim();
				String erabiltzailea = txtBezeroErabiltzailea.getText().trim();
				String pasahitza = txtBezeroPasahitza.getText().trim();
				String telefonoa = txtBezeroTelefonoa.getText().trim();
				String emaila = txtBezeroEmaila.getText().trim();

				if (izena.isEmpty() || abizenak.isEmpty() || pasahitza.isEmpty() || telefonoa.isEmpty()
						|| erabiltzailea.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ezin dituzu erregistroak hutsik bidali.");
					return;
				}

				Kontrola kontrola = new Kontrola();

				try {
					if (kontrola.bezeroEmailaKonprobatu(emaila)) {
						JOptionPane.showMessageDialog(null, "Emaila errepikatuta dago, berriro saiatu.");
					} else if (kontrola.bezeroTelefonoaKonprobatu(telefonoa)) {
						JOptionPane.showMessageDialog(null, "Telefonoa errepikatuta dago, berriro saiatu.");
					} else if (kontrola.bezeroErabiltzaileaKonprobatu(erabiltzailea)) {
						JOptionPane.showMessageDialog(null, "Erabiltzailea errepikatuta dago, berriro saiatu.");
					} else {
						Bezeroa bezeroa = new Bezeroa(0, izena, abizenak, erabiltzailea, pasahitza, telefonoa,
								emaila);
						boolean success = bezeroa.sortuBezeroa(bezeroa);
						if (success) {
							JOptionPane.showMessageDialog(null, "Bezeroa ongi sortuta.");
							TaulaKargatu(bezeroa.bezeroakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da bezeroa sortzean.");
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
	
	private void TaulaKargatu(List<Bezeroa> bezeroakList) {
		String[] Zutabeak = { "ID", "Izena", "Abizenak", "Erabiltzailea", "Pasahitza", "Telefonoa", "Emaila" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Bezeroa beze : bezeroakList) {
			Object[] row = { beze.getIdBezeroa(), beze.getIzena(), beze.getAbizenak(), beze.getErabiltzailea(),
					beze.getPasahitza(), beze.getTelefonoa(), beze.getEmaila() };
			model.addRow(row);
		}
		bezeroak.setModel(model);
	}

	private void AukeratutakoErregistroaErakutsi() {
		int row = bezeroak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(bezeroak, row)) {
			txtBezeroIzena.setText(bezeroak.getValueAt(row, 1).toString());
			txtBezeroAbizenak.setText(bezeroak.getValueAt(row, 2).toString());
			txtBezeroErabiltzailea.setText(bezeroak.getValueAt(row, 3).toString());
			txtBezeroPasahitza.setText(bezeroak.getValueAt(row, 4).toString());
			txtBezeroTelefonoa.setText(bezeroak.getValueAt(row, 5).toString());
			txtBezeroEmaila.setText(bezeroak.getValueAt(row, 6).toString());

		}
	}

}
