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

public class HerriaAdminKud extends MenuAdminNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable herriak;
	private JTextField txtHerriIzena;
	private JTextField txtHerriDistantzia;
	private JTextField txtHerriTarifa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HerriaAdminKud frame = new HerriaAdminKud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HerriaAdminKud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		herriak = new JTable();
		JScrollPane scrollPane = new JScrollPane(herriak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		
		Herria herria = new Herria();
		List<Herria> herriLista = herria.herriakBistaratu();
		TaulaKargatu(herriLista);
		
		JLabel orriIzenburua = new JLabel("Herriak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);
		
		JLabel lblHerriIzena = new JLabel("Izena:");
		lblHerriIzena.setBounds(40, 40, 80, 20);
		lblHerriIzena.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblHerriIzena);
		
		JLabel lblHerriDistantzia = new JLabel("Distantzia:");
		lblHerriDistantzia.setBounds(40, 90, 80, 20);
		lblHerriDistantzia.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblHerriDistantzia);
		
		JLabel lblHerriTarifa = new JLabel("Tarifa:");
		lblHerriTarifa.setBounds(40, 143, 80, 20);
		lblHerriTarifa.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblHerriTarifa);
		
		txtHerriIzena = new JTextField();
		txtHerriIzena.setBounds(155, 40, 120, 20);
		txtHerriIzena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtHerriIzena);
		
		txtHerriDistantzia = new JTextField();
		txtHerriDistantzia.setBounds(155, 90, 120, 20);
		txtHerriDistantzia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtHerriDistantzia);
		
		txtHerriTarifa = new JTextField();
		txtHerriTarifa.setBounds(155, 143, 120, 20);
		txtHerriTarifa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHerriTarifa.setColumns(10);
		contentPane.add(txtHerriTarifa);
		
		JButton HerriaSortu = new JButton("Gehitu");
		HerriaSortu.setBounds(700, 60, 100, 20);
		contentPane.add(HerriaSortu);
		
		JButton eguneratuHerria = new JButton("Eguneratu");
		eguneratuHerria.setBounds(700, 90, 100, 20);
		eguneratuHerria.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuHerria);
		
		JButton ezabatuHerria = new JButton("Ezabatu");
		ezabatuHerria.setBounds(700, 30, 100, 20);
		ezabatuHerria.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuHerria);
		
		herriak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});
		
		HerriaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String izena = txtHerriIzena.getText().trim();
				Integer distantzia = Integer.parseInt(txtHerriDistantzia.getText().trim());
				Double tarifa = Double.parseDouble(txtHerriTarifa.getText().trim());

				if (izena.isEmpty() || distantzia == null || tarifa == null) {
					JOptionPane.showMessageDialog(null, "Ezin dituzu erregistroak hutsik bidali.");
					return;
				}

				Kontrola kontrola = new Kontrola();

				try {
					if (kontrola.herriIzenaKonprobatu(izena)) {
						JOptionPane.showMessageDialog(null, "Herriaren izena ezarrita dago, berriro saiatu.");
					} else {
						Herria herria = new Herria(0, izena, distantzia, tarifa);
						boolean success = herria.sortuHerria(herria);
						if (success) {
							JOptionPane.showMessageDialog(null, "Herria ongi sortuta.");
							TaulaKargatu(herria.herriakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da herria sortzean.");
						}
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		eguneratuHerria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = herriak.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(herriak, row)) {
					int idHerria = (int) herriak.getValueAt(row, 0);

					Herria herria = kontrola.herriaKonprobatu(idHerria);

					if (herria != null) {
						herria.setIzena(txtHerriIzena.getText());
						herria.setDistantzia(Integer.parseInt(txtHerriDistantzia.getText()));
						herria.setTarifa(Double.parseDouble(txtHerriTarifa.getText()));

						if (kontrola.eguneratuHerria(herria)) {
							JOptionPane.showMessageDialog(null, "Herriaren erregistroa ongi eguneratuta.");
							TaulaKargatu(herria.herriakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da herria eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da herriaren erregistroa aurkitu: " + idHerria);
					}
				}
			}
		});
		
		ezabatuHerria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = herriak.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(herriak, row)) {
					int idHerria = (int) herriak.getValueAt(row, 0);
					try {
						Herria herria = kontrola.herriaKonprobatu(idHerria);
						if (herria != null) {
							if (kontrola.ezabatuHerria(idHerria)) {
								JOptionPane.showMessageDialog(null, "Herriaren erregistroa ongi ezabatuta.");
								TaulaKargatu(herria.herriakBistaratu());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ezin izan da herria ezabatu.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu herri bat ezabatzeko.");
				}
			}
		});
		
	}
	
	private void TaulaKargatu(List<Herria> herriakList) {
		String[] Zutabeak = { "ID", "Izena", "Distantzia", "Tarifa" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Herria herri : herriakList) {
			Object[] row = { herri.getIdHerria(), herri.getIzena(), herri.getDistantzia(), herri.getTarifa() };
			model.addRow(row);
		}
		herriak.setModel(model);
	}

	private void AukeratutakoErregistroaErakutsi() {
		int row = herriak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(herriak, row)) {
			txtHerriIzena.setText(herriak.getValueAt(row, 1).toString());
			txtHerriDistantzia.setText(herriak.getValueAt(row, 2).toString());
			txtHerriTarifa.setText(herriak.getValueAt(row, 3).toString());
			
		}
	}

}
