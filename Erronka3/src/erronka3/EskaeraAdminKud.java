package erronka3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class EskaeraAdminKud extends MenuAdminNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable eskaerak;
	private JTextField txtEskaeraIzena;
	private JTextField txtEskaeraData;
	private JComboBox<String> txtEskaeraErab;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EskaeraAdminKud frame = new EskaeraAdminKud();
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
	public EskaeraAdminKud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		eskaerak = new JTable();
		JScrollPane scrollPane = new JScrollPane(eskaerak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		
		Eskaera eskaera = new Eskaera();
		List<Eskaera> eskaerakList = eskaera.eskaerakBistaratu();
		TaulaKargatu(eskaerakList);
		
		JLabel orriIzenburua = new JLabel("Eskaerak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);
		
		JLabel lblEskaeraIzena = new JLabel("Izena:");
		lblEskaeraIzena.setBounds(40, 60, 80, 20);
		lblEskaeraIzena.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEskaeraIzena);
		
		JLabel lblEskaeraData = new JLabel("Data:");
		lblEskaeraData.setBounds(40, 135, 80, 20);
		lblEskaeraData.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEskaeraData);
		
		JLabel lblEskaeraErab = new JLabel("Egilea:");
		lblEskaeraErab.setBounds(320, 135, 80, 20);
		lblEskaeraErab.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblEskaeraErab);
		
		txtEskaeraIzena = new JTextField();
		txtEskaeraIzena.setBounds(155, 60, 120, 20);
		txtEskaeraIzena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEskaeraIzena);
		
		txtEskaeraData = new JTextField();
		txtEskaeraData.setBounds(155, 135, 120, 20);
		txtEskaeraData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEskaeraData);
		
		List<String> erabAukerak = eskaera.erabAukeraGuztiak();

		txtEskaeraErab = new JComboBox<>();
		txtEskaeraErab.setBounds(410, 135, 120, 20);
		txtEskaeraErab.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtEskaeraErab);

		txtEskaeraErab.addItem("Aukeratu");

		for (String erabil : erabAukerak) {
			txtEskaeraErab.addItem(erabil);
		}
		
		eskaerak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});
		
		JButton fakturaSortu = new JButton("Aurrekontua eskatu");
		fakturaSortu.setBounds(700, 60, 100, 20);
		contentPane.add(fakturaSortu);
		
		JButton ezabatuEskaera = new JButton("Ezabatu");
		ezabatuEskaera.setBounds(700, 30, 100, 20);
		ezabatuEskaera.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuEskaera);
		
		JButton eguneratuEskaera = new JButton("Eguneratu");
		eguneratuEskaera.setBounds(700, 90, 100, 20);
		eguneratuEskaera.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuEskaera);
		
		fakturaSortu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		        int row = eskaerak.getSelectedRow();
		        Kontrola kontrola = new Kontrola();

		        if (kontrola.erregistroaKomprobatu(eskaerak, row)) {
		            int idEskaera = (int) eskaerak.getValueAt(row, 0);

		            try {
		                FakturaSortu.sortuPDF(idEskaera);
		                JOptionPane.showMessageDialog(null, "PDF sortu da eskaera ID: " + idEskaera);
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Errorea PDF sortzerakoan: " + ex.getMessage());
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Hautatu eskaera bat mesedez.");
		        }
		    }
		});
		
		ezabatuEskaera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = eskaerak.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(eskaerak, row)) {
					int idEskaera = (int) eskaerak.getValueAt(row, 0);
					try {
						Eskaera eskaera = kontrola.eskaeraKonprobatu(idEskaera);
						if (eskaera != null) {
							if (kontrola.ezabatuEskaera(idEskaera)) {
								JOptionPane.showMessageDialog(null, "Eskaera ongi ezabatuta.");
								TaulaKargatu(eskaera.eskaerakBistaratu());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ezin izan da eskaera ezabatu.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu eskaera bat ezabatzeko.");
				}
			}
		});
		
		eguneratuEskaera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = eskaerak.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(eskaerak, row)) {
					int idEskaera = (int) eskaerak.getValueAt(row, 0);

					Eskaera eskaera = kontrola.eskaeraKonprobatu(idEskaera);

					if (eskaera != null) {
						eskaera.setEskaeraIzena(txtEskaeraIzena.getText());
						try {
						    String dataTestua = txtEskaeraData.getText();
						    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						    Date data = sdf.parse(dataTestua);
						    eskaera.setEskaeraData(data);
						} catch (Exception e1) {
						    e1.printStackTrace();
						    JOptionPane.showMessageDialog(null, "Data formatua ez da zuzena. Erabili: yyyy-MM-dd");
						}
						eskaera.setEskaeraErab(txtEskaeraErab.getSelectedItem().toString());
						
						if (kontrola.eguneratuEskaera(eskaera)) {
							JOptionPane.showMessageDialog(null, "Eskaera ongi eguneratuta.");
							TaulaKargatu(eskaera.eskaerakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da eskaera eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da eskaera aurkitu: " + idEskaera);
					}
				}
			}
		});
		
	}
	private void TaulaKargatu(List<Eskaera> eskaerakList) {
		String[] Zutabeak = { "ID", "Izena", "Data", "Egilea" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Eskaera zala : eskaerakList) {
			Object[] row = { zala.getIdEskaera(), zala.getEskaeraIzena(), zala.getEskaeraData(), zala.getEskaeraErab() };
			model.addRow(row);
		}
		eskaerak.setModel(model);
	}
	
	private void AukeratutakoErregistroaErakutsi() {
		int row = eskaerak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(eskaerak, row)) {
			txtEskaeraIzena.setText(eskaerak.getValueAt(row, 1).toString());
			txtEskaeraData.setText(eskaerak.getValueAt(row, 2).toString());

			String egoera = eskaerak.getValueAt(row, 3).toString().trim().toLowerCase();
			for (int i = 0; i < txtEskaeraErab.getItemCount(); i++) {
				if (txtEskaeraErab.getItemAt(i).toString().trim().toLowerCase().equals(egoera)) {
					txtEskaeraErab.setSelectedIndex(i);
					break;
				}
			}
		}
	}

}
