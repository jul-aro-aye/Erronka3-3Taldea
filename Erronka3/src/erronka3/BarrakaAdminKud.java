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

public class BarrakaAdminKud extends MenuAdminNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable barrakak;
	private JTextField txtBarrakaIzena;
	private JTextField txtBarrakaModalitatea;
	private JTextField txtBarrakaKapazitatea;
	private JTextField txtBarrakaPrezioa;
	private JComboBox<String> txtBarrakaLangilea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarrakaAdminKud frame = new BarrakaAdminKud();
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
	public BarrakaAdminKud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		barrakak = new JTable();
		JScrollPane scrollPane = new JScrollPane(barrakak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		
		Barraka barraka = new Barraka();
		List<Barraka> zalantzakList = barraka.barrakakBistaratu();
		TaulaKargatu(zalantzakList);
		
		JLabel orriIzenburua = new JLabel("Barrakak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);
		
		JLabel lblBarrakaIzena = new JLabel("Izena:");
		lblBarrakaIzena.setBounds(40, 60, 80, 20);
		lblBarrakaIzena.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBarrakaIzena);
		
		JLabel lblBarrakaModalitatea = new JLabel("Modalitatea:");
		lblBarrakaModalitatea.setBounds(40, 100, 80, 20);
		lblBarrakaModalitatea.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBarrakaModalitatea);
		
		JLabel lblBarrakaKapazitatea = new JLabel("Kapazitatea:");
		lblBarrakaKapazitatea.setBounds(40, 135, 80, 20);
		lblBarrakaKapazitatea.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBarrakaKapazitatea);
		
		JLabel lblBarrakaPrezioa = new JLabel("Prezioa:");
		lblBarrakaPrezioa.setBounds(370, 60, 80, 20);
		lblBarrakaPrezioa.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBarrakaPrezioa);
		
		JLabel lblBarrakaLangilea = new JLabel("Langilea:");
		lblBarrakaLangilea.setBounds(370, 100, 80, 20);
		lblBarrakaLangilea.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBarrakaLangilea);
		
		txtBarrakaIzena = new JTextField();
		txtBarrakaIzena.setBounds(155, 59, 120, 20);
		txtBarrakaIzena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBarrakaIzena);
		
		txtBarrakaModalitatea = new JTextField();
		txtBarrakaModalitatea.setBounds(155, 100, 120, 20);
		txtBarrakaModalitatea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBarrakaModalitatea);
		
		txtBarrakaKapazitatea = new JTextField();
		txtBarrakaKapazitatea.setBounds(155, 135, 120, 20);
		txtBarrakaKapazitatea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBarrakaKapazitatea);
		
		txtBarrakaPrezioa = new JTextField();
		txtBarrakaPrezioa.setBounds(450, 60, 120, 20);
		txtBarrakaPrezioa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBarrakaPrezioa);
		
		List<String> langiAukerak = barraka.langiAukeraGuztiak();

		txtBarrakaLangilea = new JComboBox<>();
		txtBarrakaLangilea.setBounds(450, 100, 120, 20);
		txtBarrakaLangilea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBarrakaLangilea);

		txtBarrakaLangilea.addItem("Aukeratu");

		for (String erabil : langiAukerak) {
			txtBarrakaLangilea.addItem(erabil);
		}
		
		barrakak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});
		
		JButton barrakaSortu = new JButton("Gehitu");
		barrakaSortu.setBounds(700, 60, 100, 20);
		contentPane.add(barrakaSortu);

		JButton eguneratuBarraka = new JButton("Eguneratu");
		eguneratuBarraka.setBounds(700, 90, 100, 20);
		eguneratuBarraka.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuBarraka);

		JButton ezabatuBarraka = new JButton("Ezabatu");
		ezabatuBarraka.setBounds(700, 30, 100, 20);
		ezabatuBarraka.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuBarraka);
		
		ezabatuBarraka.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = barrakak.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(barrakak, row)) {
					int idBarraka = (int) barrakak.getValueAt(row, 0);
					try {
						Barraka barraka = kontrola.barrakaKonprobatu(idBarraka);
						if (barraka != null) {
							if (kontrola.ezabatuBarraka(idBarraka)) {
								JOptionPane.showMessageDialog(null, "Bezeroa ongi ezabatuta.");
								TaulaKargatu(barraka.barrakakBistaratu());
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
		
		eguneratuBarraka.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = barrakak.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(barrakak, row)) {
					int idBarraka = (int) barrakak.getValueAt(row, 0);

					Barraka barraka = kontrola.barrakaKonprobatu(idBarraka);

					if (barraka != null) {
						try {
						    barraka.setBarrakaIzena(txtBarrakaIzena.getText().trim());
						    barraka.setBarrakaModalitatea(txtBarrakaModalitatea.getText().trim());
						    int kapazitatea = Integer.parseInt(txtBarrakaKapazitatea.getText().trim());
						    barraka.setBarrakaKapazitatea(kapazitatea);
						    double prezioa = Double.parseDouble(txtBarrakaPrezioa.getText().trim());
						    barraka.setBarrakaPrezioa(prezioa);
						    barraka.setBarrakaLangilea(txtBarrakaLangilea.getSelectedItem().toString());
						} catch (NumberFormatException e1) {
						    JOptionPane.showMessageDialog(null, "Errorea: Mesedez baliozko datu motak ezarri.",
						                                  "Error de entrada", JOptionPane.ERROR_MESSAGE);
						}

						if (kontrola.eguneratuBarraka(barraka)) {
							JOptionPane.showMessageDialog(null, "Barraka ongi eguneratuta.");
							TaulaKargatu(barraka.barrakakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da barraka eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da langilea aurkitu: " + idBarraka);
					}
				}
			}
		});
		
		barrakaSortu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String izena = txtBarrakaIzena.getText().trim();
		        String modalitatea = txtBarrakaModalitatea.getText().trim();
		        
		        int kapazitatea = 0;
		        double prezioa = 0.0;
		        
		        try {
		            kapazitatea = Integer.parseInt(txtBarrakaKapazitatea.getText().trim());
		            prezioa = Double.parseDouble(txtBarrakaPrezioa.getText().trim());
		        } catch (NumberFormatException e1) {
		            JOptionPane.showMessageDialog(null, "Errorea: Mesedez, sartu baliozko datu motak.", 
		                                          "Error de entrada", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        String langilea = txtBarrakaLangilea.getSelectedItem().toString();

		        if (izena.isEmpty() || modalitatea.isEmpty() || langilea.equals("Aukeratu")) {
		            JOptionPane.showMessageDialog(null, "Ezin dituzu erregistroak hutsik bidali.");
		            return;
		        }
		        
		        if (kapazitatea <= 0 || prezioa <= 0) {
		            JOptionPane.showMessageDialog(null, "Kapazitatea eta prezioa balio positiboak izan behar dute.");
		            return;
		        }

		        Kontrola kontrola = new Kontrola();

		        try {
		            if (kontrola.barrakaIzenaKonprobatu(izena)) {
		                JOptionPane.showMessageDialog(null, "Barraka errepikatuta dago, berriro saiatu.");
		            } else {
		                Barraka barraka = new Barraka(0, izena, modalitatea, kapazitatea, prezioa, langilea);
		                boolean success = barraka.sortuBarraka(barraka);
		                if (success) {
		                    JOptionPane.showMessageDialog(null, "Barraka ongi sortuta.");
		                    TaulaKargatu(barraka.barrakakBistaratu());
		                } else {
		                    JOptionPane.showMessageDialog(null, "Errore bat gertatu da barraka sortzean.");
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
	private void TaulaKargatu(List<Barraka> barrakakList) {
		String[] Zutabeak = { "ID", "Izena", "Modalitatea", "Kapazitatea", "Prezioa", "Langilea" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Barraka barra : barrakakList) {
			Object[] row = { barra.getIdBarraka(), barra.getBarrakaIzena(), barra.getBarrakaModalitatea(), barra.getBarrakaKapazitatea(), barra.getBarrakaPrezioa(), barra.getBarrakaLangilea() };
			model.addRow(row);
		}
		barrakak.setModel(model);
	}
	
	private void AukeratutakoErregistroaErakutsi() {
		int row = barrakak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(barrakak, row)) {
			txtBarrakaIzena.setText(barrakak.getValueAt(row, 1).toString());
			txtBarrakaModalitatea.setText(barrakak.getValueAt(row, 2).toString());
			txtBarrakaKapazitatea.setText(barrakak.getValueAt(row, 3).toString());
			txtBarrakaPrezioa.setText(barrakak.getValueAt(row, 4).toString());

			String langi = barrakak.getValueAt(row, 5).toString().trim().toLowerCase();
			for (int i = 0; i < txtBarrakaLangilea.getItemCount(); i++) {
				if (txtBarrakaLangilea.getItemAt(i).toString().trim().toLowerCase().equals(langi)) {
					txtBarrakaLangilea.setSelectedIndex(i);
					break;
				}
			}
		}
	}

}
