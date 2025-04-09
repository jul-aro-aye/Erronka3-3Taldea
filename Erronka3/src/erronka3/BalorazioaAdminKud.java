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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class BalorazioaAdminKud extends MenuAdminNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable balorazioak;
	private JTextField txtIzarKop;
	private JTextField txtZergatia;
	private JComboBox<String> txtBalorazioErab;
	private JComboBox<String> txtBarrakaIzena;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BalorazioaAdminKud frame = new BalorazioaAdminKud();
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
	public BalorazioaAdminKud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		balorazioak = new JTable();
		JScrollPane scrollPane = new JScrollPane(balorazioak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		
		Balorazioa balorazioa = new Balorazioa();
		List<Balorazioa> balorazioakList = balorazioa.balorazioakBistaratu();
		TaulaKargatu(balorazioakList);
		
		JLabel orriIzenburua = new JLabel("Balorazioak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);
		
		JLabel lblIzarKop = new JLabel("Izar kopurua:");
		lblIzarKop.setBounds(40, 60, 80, 20);
		lblIzarKop.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblIzarKop);
		
		JLabel lblBalorazioZergatia = new JLabel("Zergatia:");
		lblBalorazioZergatia.setBounds(40, 135, 80, 20);
		lblBalorazioZergatia.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBalorazioZergatia);
		
		JLabel lblBalorazioErab = new JLabel("Bezeroa:");
		lblBalorazioErab.setBounds(350, 60, 80, 20);
		lblBalorazioErab.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBalorazioErab);
		
		JLabel lblBalorazioBarraka = new JLabel("Barraka:");
		lblBalorazioBarraka.setBounds(350, 135, 80, 20);
		lblBalorazioBarraka.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblBalorazioBarraka);
		
		txtIzarKop = new JTextField();
		txtIzarKop.setBounds(155, 60, 100, 20);
		txtIzarKop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtIzarKop);
		
		txtZergatia = new JTextField();
		txtZergatia.setBounds(155, 135, 100, 20);
		txtZergatia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtZergatia);
		
		List<String> erabAukerak = balorazioa.erabAukeraGuztiak();

		txtBalorazioErab = new JComboBox<>();
		txtBalorazioErab.setBounds(430, 60, 120, 20);
		txtBalorazioErab.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBalorazioErab);

		txtBalorazioErab.addItem("Aukeratu");

		for (String erabil : erabAukerak) {
			txtBalorazioErab.addItem(erabil);
		}
		
		List<String> barrakaAukerak = balorazioa.barrakaAukeraGuztiak();
		
		txtBarrakaIzena = new JComboBox<>();
		txtBarrakaIzena.setBounds(430, 135, 120, 20);
		txtBarrakaIzena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtBarrakaIzena);
		
		txtBarrakaIzena.addItem("Aukeratu");
		
		for (String barraka : barrakaAukerak) {
			txtBarrakaIzena.addItem(barraka);
		}
		
		balorazioak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});
		
		JButton ezabatuBalorazioa = new JButton("Ezabatu");
		ezabatuBalorazioa.setBounds(700, 30, 100, 20);
		ezabatuBalorazioa.setBackground(new Color(255, 128, 128));
		contentPane.add(ezabatuBalorazioa);
		
		ezabatuBalorazioa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = balorazioak.getSelectedRow();
				Kontrola kontrola = new Kontrola();
				if (kontrola.erregistroaKomprobatu(balorazioak, row)) {
					int idBalorazioa = (int) balorazioak.getValueAt(row, 0);
					try {
						Balorazioa balorazio = kontrola.balorazioaKonprobatu(idBalorazioa);
						if (balorazio != null) {
							if (kontrola.ezabatuBalorazioa(idBalorazioa)) {
								JOptionPane.showMessageDialog(null, "Balorazioa ongi ezabatuta.");
								TaulaKargatu(balorazio.balorazioakBistaratu());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ezin izan da balorazioa ezabatu.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu balorazio bat ezabatzeko.");
				}
			}
		});
		
	}
	private void TaulaKargatu(List<Balorazioa> balorazioakList) {
		String[] Zutabeak = { "ID", "IzarKop", "Zergatia", "Egilea", "Barraka" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Balorazioa balo : balorazioakList) {
			Object[] row = { balo.getIdBalorazioa(), balo.getIzarKop(), balo.getZergatia(), balo.getBezeroa(), balo.getBarraka() };
			model.addRow(row);
		}
		balorazioak.setModel(model);
	}
	
	private void AukeratutakoErregistroaErakutsi() {
		int row = balorazioak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(balorazioak, row)) {
			txtIzarKop.setText(balorazioak.getValueAt(row, 1).toString());
			txtZergatia.setText(balorazioak.getValueAt(row, 2).toString());
			
			String erab = balorazioak.getValueAt(row, 3).toString().trim().toLowerCase();
			for (int i = 0; i < txtBalorazioErab.getItemCount(); i++) {
				if (txtBalorazioErab.getItemAt(i).toString().trim().toLowerCase().equals(erab)) {
					txtBalorazioErab.setSelectedIndex(i);
					break;
				}
			}
			
			String barraka = balorazioak.getValueAt(row, 4).toString().trim().toLowerCase();
			for (int i = 0; i < txtBarrakaIzena.getItemCount(); i++) {
				if (txtBarrakaIzena.getItemAt(i).toString().trim().toLowerCase().equals(barraka)) {
					txtBarrakaIzena.setSelectedIndex(i);
					break;
				}
			}
		}
	}

}
