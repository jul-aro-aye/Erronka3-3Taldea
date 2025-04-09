package erronka3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ParteHartuAdminKud extends MenuAdminNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable parteHartu;
	private JComboBox<String> txtParteBarraka;
	private JComboBox<String> txtParteEkintza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParteHartuAdminKud frame = new ParteHartuAdminKud();
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
	public ParteHartuAdminKud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		parteHartu = new JTable();
		JScrollPane scrollPane = new JScrollPane(parteHartu);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		
		JLabel orriIzenburua = new JLabel("Parte Hartu");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);
		
		JLabel lblEskatutakoIzena = new JLabel("Barraka:");
		lblEskatutakoIzena.setBounds(40, 60, 80, 20);
		lblEskatutakoIzena.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEskatutakoIzena);
		
		JLabel lblEskatutakoBarraka = new JLabel("Ekintza:");
		lblEskatutakoBarraka.setBounds(40, 135, 80, 20);
		lblEskatutakoBarraka.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEskatutakoBarraka);
		
		txtParteBarraka = new JComboBox<>();
		txtParteBarraka.setBounds(155, 60, 120, 20);
		txtParteBarraka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtParteBarraka);
		
		txtParteEkintza = new JComboBox<>();
		txtParteEkintza.setBounds(155, 135, 120, 20);
		txtParteEkintza.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtParteEkintza);
		
		ParteHartu parteHartzailea = new ParteHartu();
		List<ParteHartu> eskatuList = parteHartzailea.parteHartzaileakBistaratu();
		TaulaKargatu(eskatuList);
		
		List<String> barraAukerak = parteHartzailea.barrakaGuztiak();
		
		txtParteBarraka.addItem("Aukeratu");

		for (String eska : barraAukerak) {
			txtParteBarraka.addItem(eska);
		}
		
		List<String> ekinAukerak = parteHartzailea.ekintzaGuztiak();
		
		txtParteEkintza.addItem("Aukeratu");

		for (String ekin : ekinAukerak) {
			txtParteEkintza.addItem(ekin);
		}
		
		JButton parteHartuSortu = new JButton("Gehitu");
		parteHartuSortu.setBounds(700, 60, 100, 20);
		contentPane.add(parteHartuSortu);
		
		JButton ezabatuEskatutakoa = new JButton("Ezabatu");
		ezabatuEskatutakoa.setBounds(700, 30, 100, 20);
		ezabatuEskatutakoa.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuEskatutakoa);
		
		JButton eguneratuEskatutakoa = new JButton("Eguneratu");
		eguneratuEskatutakoa.setBounds(700, 90, 100, 20);
		eguneratuEskatutakoa.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuEskatutakoa);
		
		parteHartu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});
		
		parteHartuSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String barraka = txtParteBarraka.getSelectedItem().toString();
				String ekintza = txtParteEkintza.getSelectedItem().toString();

				if (barraka.equals("Aukeratu") || ekintza.equals("Aukeratu")) {
					JOptionPane.showMessageDialog(null, "Ezin dituzu erregistroak hutsik bidali.");
					return;
				}
				
				try {
					ParteHartu parte = new ParteHartu(0, barraka, ekintza);
					boolean success = parte.sortuParteHartu(ekintza);
					if (success) {
						JOptionPane.showMessageDialog(null, "Erregistroa ongi sortuta.");
						TaulaKargatu(parte.parteHartzaileakBistaratu());
					} else {
						JOptionPane.showMessageDialog(null, "Errore bat gertatu da erregistroa sortzean.");
					}
					
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		ezabatuEskatutakoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = parteHartu.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(parteHartu, row)) {
					int idParteHartu = (int) parteHartu.getValueAt(row, 0);
					try {
						ParteHartu parte = kontrola.parteHartuKonprobatu(idParteHartu);
						if (parte != null) {
							if (kontrola.ezabatuParteHartu(idParteHartu)) {
								JOptionPane.showMessageDialog(null, "Erregistroa ongi ezabatuta.");
								TaulaKargatu(parte.parteHartzaileakBistaratu());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ezin izan da erregistroa ezabatu.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu eskatutako erregistro bat ezabatzeko.");
				}
			}
		});
		
		eguneratuEskatutakoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = parteHartu.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(parteHartu, row)) {
					int idParteHartu = (int) parteHartu.getValueAt(row, 0);

					ParteHartu eskatuta = kontrola.parteHartuKonprobatu(idParteHartu);

					if (eskatuta != null) {
						eskatuta.setPaeteHartuBarraka(txtParteBarraka.getSelectedItem().toString());
						eskatuta.setParteHartuEkintza(txtParteEkintza.getSelectedItem().toString());
						
						if (kontrola.eguneratuParteHartu(eskatuta)) {
							JOptionPane.showMessageDialog(null, "Parte hartzen duen erregistroa ongi eguneratuta.");
							TaulaKargatu(parteHartzailea.parteHartzaileakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da parte hartzen duen erregistroa eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da parte hartze erregistroa aurkitu: " + idParteHartu);
					}
				}
			}
		});
		
	}
	
	private void TaulaKargatu(List<ParteHartu> parteList) {
		String[] Zutabeak = { "ID", "Barraka", "Ekintza" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (ParteHartu parte : parteList) {
			Object[] row = { parte.getParteHartuId(), parte.getPaeteHartuBarraka(), parte.getParteHartuEkintza() };
			model.addRow(row);
		}
		parteHartu.setModel(model);
	}
	
	private void AukeratutakoErregistroaErakutsi() {
		int row = parteHartu.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(parteHartu, row)) {
			
			String eskaera = parteHartu.getValueAt(row, 1).toString().trim().toLowerCase();
			for (int i = 0; i < txtParteBarraka.getItemCount(); i++) {
				if (txtParteBarraka.getItemAt(i).toString().trim().toLowerCase().equals(eskaera)) {
					txtParteBarraka.setSelectedIndex(i);
					break;
				}
			}

			String barraka = parteHartu.getValueAt(row, 2).toString().trim().toLowerCase();
			for (int i = 0; i < txtParteEkintza.getItemCount(); i++) {
				if (txtParteEkintza.getItemAt(i).toString().trim().toLowerCase().equals(barraka)) {
					txtParteEkintza.setSelectedIndex(i);
					break;
				}
			}
		}
	}

}
