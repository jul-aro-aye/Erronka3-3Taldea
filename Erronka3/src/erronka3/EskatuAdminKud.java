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

public class EskatuAdminKud extends MenuAdminNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable eskatutakoak;
	private JComboBox<String> txtEskatuEskaera;
	private JComboBox<String> txtEskaeraBarraka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EskatuAdminKud frame = new EskatuAdminKud();
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
	public EskatuAdminKud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		eskatutakoak = new JTable();
		JScrollPane scrollPane = new JScrollPane(eskatutakoak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		
		JLabel orriIzenburua = new JLabel("Eskatutakoak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);
		
		JLabel lblEskatutakoIzena = new JLabel("Eskaera:");
		lblEskatutakoIzena.setBounds(40, 60, 80, 20);
		lblEskatutakoIzena.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEskatutakoIzena);
		
		JLabel lblEskatutakoBarraka = new JLabel("Barraka:");
		lblEskatutakoBarraka.setBounds(40, 135, 80, 20);
		lblEskatutakoBarraka.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEskatutakoBarraka);
		
		txtEskatuEskaera = new JComboBox<>();
		txtEskatuEskaera.setBounds(155, 60, 120, 20);
		txtEskatuEskaera.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEskatuEskaera);
		
		txtEskaeraBarraka = new JComboBox<>();
		txtEskaeraBarraka.setBounds(155, 135, 120, 20);
		txtEskaeraBarraka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEskaeraBarraka);
		
		Eskatu eskatu = new Eskatu();
		List<Eskatu> eskatuList = eskatu.eskatutakoakBistaratu();
		TaulaKargatu(eskatuList);
		
		List<String> eskaAukerak = eskatu.eskaeraGuztiak();
		
		txtEskatuEskaera.addItem("Aukeratu");

		for (String eska : eskaAukerak) {
			txtEskatuEskaera.addItem(eska);
		}
		
		List<String> barraAukerak = eskatu.barrakaGuztiak();
		
		txtEskaeraBarraka.addItem("Aukeratu");

		for (String barra : barraAukerak) {
			txtEskaeraBarraka.addItem(barra);
		}
		
		JButton ezabatuEskatutakoa = new JButton("Ezabatu");
		ezabatuEskatutakoa.setBounds(700, 30, 100, 20);
		ezabatuEskatutakoa.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuEskatutakoa);
		
		JButton eguneratuEskatutakoa = new JButton("Eguneratu");
		eguneratuEskatutakoa.setBounds(700, 90, 100, 20);
		eguneratuEskatutakoa.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuEskatutakoa);
		
		eskatutakoak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});
		
		ezabatuEskatutakoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = eskatutakoak.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(eskatutakoak, row)) {
					int idEskatuta = (int) eskatutakoak.getValueAt(row, 0);
					try {
						Eskatu eskatuta = kontrola.eskatutaKonprobatu(idEskatuta);
						if (eskatuta != null) {
							if (kontrola.ezabatuEskatuta(idEskatuta)) {
								JOptionPane.showMessageDialog(null, "Erregistroa ongi ezabatuta.");
								TaulaKargatu(eskatuta.eskatutakoakBistaratu());
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
				int row = eskatutakoak.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(eskatutakoak, row)) {
					int idEskatuta = (int) eskatutakoak.getValueAt(row, 0);

					Eskatu eskatuta = kontrola.eskatutaKonprobatu(idEskatuta);

					if (eskatuta != null) {
						eskatuta.setEskatutakoEskaera(txtEskatuEskaera.getSelectedItem().toString());
						eskatuta.setEskatutakoBarraka(txtEskaeraBarraka.getSelectedItem().toString());
						
						if (kontrola.eguneratuEskatuta(eskatuta)) {
							JOptionPane.showMessageDialog(null, "Eskatu erregistroa ongi eguneratuta.");
							TaulaKargatu(eskatu.eskatutakoakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da eskatutako erregistroa eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da eskatutako erregistroa aurkitu: " + idEskatuta);
					}
				}
			}
		});
		
	}
	
	private void TaulaKargatu(List<Eskatu> eskatuList) {
		String[] Zutabeak = { "ID", "Eskaera", "Barraka" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Eskatu eska : eskatuList) {
			Object[] row = { eska.getEskatutakoId(), eska.getEskatutakoEskaera(), eska.getEskatutakoBarraka() };
			model.addRow(row);
		}
		eskatutakoak.setModel(model);
	}
	
	private void AukeratutakoErregistroaErakutsi() {
		int row = eskatutakoak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(eskatutakoak, row)) {
			
			String eskaera = eskatutakoak.getValueAt(row, 1).toString().trim().toLowerCase();
			for (int i = 0; i < txtEskatuEskaera.getItemCount(); i++) {
				if (txtEskatuEskaera.getItemAt(i).toString().trim().toLowerCase().equals(eskaera)) {
					txtEskatuEskaera.setSelectedIndex(i);
					break;
				}
			}

			String barraka = eskatutakoak.getValueAt(row, 2).toString().trim().toLowerCase();
			for (int i = 0; i < txtEskaeraBarraka.getItemCount(); i++) {
				if (txtEskaeraBarraka.getItemAt(i).toString().trim().toLowerCase().equals(barraka)) {
					txtEskaeraBarraka.setSelectedIndex(i);
					break;
				}
			}
		}
	}

}
