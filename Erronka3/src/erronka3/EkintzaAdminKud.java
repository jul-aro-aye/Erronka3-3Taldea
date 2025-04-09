package erronka3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class EkintzaAdminKud extends MenuAdminNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable ekintzak;
	private JTextField txtEkintzaIzenburua;
	private JTextField txtEkintzaInformazioa;
	private JTextField txtEkintzaData;
	private JComboBox<String> txtEkintzaHerria;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EkintzaAdminKud frame = new EkintzaAdminKud();
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
	public EkintzaAdminKud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ekintzak = new JTable();
		JScrollPane scrollPane = new JScrollPane(ekintzak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		Ekintza ekintza = new Ekintza();
		Herria herria = new Herria();
		List<Ekintza> ekintzakList = ekintza.ekintzakBistaratu();
		TaulaKargatu(ekintzakList);
		
		JLabel orriIzenburua = new JLabel("Ekintzak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);
		
		JLabel lblEkintzaIzenburua = new JLabel("Izenburua:");
		lblEkintzaIzenburua.setBounds(40, 40, 80, 20);
		lblEkintzaIzenburua.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEkintzaIzenburua);
		
		JLabel lblEkintzaInfo = new JLabel("Informazioa:");
		lblEkintzaInfo.setBounds(40, 90, 80, 20);
		lblEkintzaInfo.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEkintzaInfo);
		
		JLabel lblEkintzaData = new JLabel("Data:");
		lblEkintzaData.setBounds(348, 40, 80, 20);
		lblEkintzaData.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEkintzaData);
		
		JLabel lblEkintzaHerria = new JLabel("Herria:");
		lblEkintzaHerria.setBounds(348, 90, 80, 20);
		lblEkintzaHerria.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEkintzaHerria);
		
		txtEkintzaIzenburua = new JTextField();
		txtEkintzaIzenburua.setBounds(155, 40, 120, 20);
		txtEkintzaIzenburua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEkintzaIzenburua);
		
		txtEkintzaInformazioa = new JTextField();
		txtEkintzaInformazioa.setBounds(155, 90, 120, 20);
		txtEkintzaInformazioa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEkintzaInformazioa);
		
		txtEkintzaData = new JTextField();
		txtEkintzaData.setBounds(438, 40, 120, 20);
		txtEkintzaData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEkintzaData);
		
		List<String> herriAukerak = herria.herriAukeraGuztiak();
		
		txtEkintzaHerria = new JComboBox<>();
		txtEkintzaHerria.setBounds(438, 90, 120, 20);
		txtEkintzaHerria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEkintzaHerria);
		
		txtEkintzaHerria.addItem("Aukeratu");

		for (String herriIzena : herriAukerak) {
			txtEkintzaHerria.addItem(herriIzena);
		}
		
		JButton ekintzaSortu = new JButton("Gehitu");
		ekintzaSortu.setBounds(700, 60, 100, 20);
		contentPane.add(ekintzaSortu);

		JButton eguneratuEkintza = new JButton("Eguneratu");
		eguneratuEkintza.setBounds(700, 90, 100, 20);
		eguneratuEkintza.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuEkintza);

		JButton ezabatuEkintza = new JButton("Ezabatu");
		ezabatuEkintza.setBounds(700, 30, 100, 20);
		ezabatuEkintza.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuEkintza);
		
		ekintzak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});
		
		eguneratuEkintza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = ekintzak.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(ekintzak, row)) {
					int idEkintza = (int) ekintzak.getValueAt(row, 0);

					Ekintza ekintza = kontrola.ekintzaKonprobatu(idEkintza);

					if (ekintza != null) {
						ekintza.setIzenburua(txtEkintzaIzenburua.getText());
						ekintza.setInformazioa(txtEkintzaInformazioa.getText());
						DateTimeFormatter formatua = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate data = LocalDate.parse(txtEkintzaData.getText(), formatua);
						ekintza.setData(data);
						ekintza.setEkintzaHerria(txtEkintzaHerria.getSelectedItem().toString());

						if (kontrola.eguneratuEkintza(ekintza)) {
							JOptionPane.showMessageDialog(null, "Ekintza ongi eguneratuta.");
							TaulaKargatu(ekintza.ekintzakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da ekintza eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da ekintza aurkitu: " + idEkintza);
					}
				}
			}
		});
		
		ezabatuEkintza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = ekintzak.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(ekintzak, row)) {
					int idEkintza = (int) ekintzak.getValueAt(row, 0);
					try {
						Ekintza ekintza = kontrola.ekintzaKonprobatu(idEkintza);
						if (ekintza != null) {
							if (kontrola.ezabatuEkintza(idEkintza)) {
								JOptionPane.showMessageDialog(null, "Ekintza ongi ezabatuta.");
								TaulaKargatu(ekintza.ekintzakBistaratu());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ezin izan da ekintza ezabatu.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu ekintza bat ezabatzeko.");
				}
			}
		});
		
		ekintzaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String izenburua = txtEkintzaIzenburua.getText().trim();
				String informazioa = txtEkintzaInformazioa.getText().trim();
				DateTimeFormatter formatua = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate data = LocalDate.parse(txtEkintzaData.getText(), formatua);
				String aukeratutakoHerria = txtEkintzaHerria.getSelectedItem().toString();

				if (izenburua.isEmpty() || informazioa.isEmpty() || aukeratutakoHerria.equals("Aukeratu")
						|| data == null) {
					JOptionPane.showMessageDialog(null, "Ezin dituzu erregistroak hutsik bidali.");
					return;
				}
				
				try {
					Ekintza ekintza = new Ekintza(0, izenburua, informazioa, data, aukeratutakoHerria);
					boolean success = ekintza.sortuEkintza(ekintza);
					if (success) {
						JOptionPane.showMessageDialog(null, "Ekintza ongi sortuta.");
						TaulaKargatu(ekintza.ekintzakBistaratu());
					} else {
						JOptionPane.showMessageDialog(null, "Errore bat gertatu da ekintza sortzean.");
					}
					
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
	}
	
	private void TaulaKargatu(List<Ekintza> ekintzakList) {
		String[] Zutabeak = { "ID", "Izenburua", "Informazioa", "Data", "Herria" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Ekintza ekin : ekintzakList) {
			Object[] row = { ekin.getIdEkintza(), ekin.getIzenburua(), ekin.getInformazioa(), ekin.getData(), ekin.getEkintzaHerria() };
			model.addRow(row);
		}
		ekintzak.setModel(model);
	}

	private void AukeratutakoErregistroaErakutsi() {
		int row = ekintzak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(ekintzak, row)) {
			txtEkintzaIzenburua.setText(ekintzak.getValueAt(row, 1).toString());
			txtEkintzaInformazioa.setText(ekintzak.getValueAt(row, 2).toString());
			txtEkintzaData.setText(ekintzak.getValueAt(row, 3).toString());
			
			String egoera = ekintzak.getValueAt(row, 4).toString().trim().toLowerCase();
			for (int i = 0; i < txtEkintzaHerria.getItemCount(); i++) {
				if (txtEkintzaHerria.getItemAt(i).toString().trim().toLowerCase().equals(egoera)) {
					txtEkintzaHerria.setSelectedIndex(i);
					break;
				}
			}
			
		}
	}

}
