package erronka3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

public class ZalantzaLangiKud extends MenuLangiNagusia {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable zalantzak;
	private JTextField txtZalantzaErantzuna;
	private JComboBox<String> txtZalantzaErab;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZalantzaLangiKud frame = new ZalantzaLangiKud();
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
	public ZalantzaLangiKud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		zalantzak = new JTable();
		JScrollPane scrollPane = new JScrollPane(zalantzak);
		scrollPane.setBounds(115, 219, 650, 281);
		contentPane.add(scrollPane);
		
		Zalantza zalantza = new Zalantza();
		List<Zalantza> zalantzakList = zalantza.zalantzakBistaratu();
		TaulaKargatu(zalantzakList);
		
		JLabel orriIzenburua = new JLabel("Zalantzak");
		orriIzenburua.setBounds(40, 5, 120, 30);
		orriIzenburua.setFont(new Font("Agency FB", Font.BOLD, 25));
		contentPane.add(orriIzenburua);
		
		JLabel lblZalantzaErantzuna = new JLabel("Erantzuna:");
		lblZalantzaErantzuna.setBounds(40, 59, 80, 20);
		lblZalantzaErantzuna.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblZalantzaErantzuna);
		
		JLabel lblZalantzaErab = new JLabel("Erantzulea:");
		lblZalantzaErab.setBounds(40, 133, 80, 20);
		lblZalantzaErab.setFont(new Font("Agency FB", Font.BOLD, 15));
		contentPane.add(lblZalantzaErab);
		
		txtZalantzaErantzuna = new JTextField();
		txtZalantzaErantzuna.setBounds(155, 59, 388, 20);
		txtZalantzaErantzuna.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtZalantzaErantzuna);
		
		List<String> erabAukerak = zalantza.erabAukeraGuztiak();

		txtZalantzaErab = new JComboBox<>();
		txtZalantzaErab.setBounds(155, 133, 120, 20);
		txtZalantzaErab.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtZalantzaErab);

		txtZalantzaErab.addItem("Aukeratu");

		for (String erabil : erabAukerak) {
			txtZalantzaErab.addItem(erabil);
		}
		
		zalantzak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					AukeratutakoErregistroaErakutsi();
				}
			}
		});
		
		JButton eguneratuZalantza = new JButton("Eguneratu");
		eguneratuZalantza.setBounds(700, 90, 100, 20);
		eguneratuZalantza.setBackground(new Color(255, 255, 128));
		contentPane.add(eguneratuZalantza);
		
		eguneratuZalantza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = zalantzak.getSelectedRow();
				Kontrola kontrola = new Kontrola();

				if (kontrola.erregistroaKomprobatu(zalantzak, row)) {
					int idZalantza = (int) zalantzak.getValueAt(row, 0);

					Zalantza zalantza = kontrola.zalantzaKonprobatu(idZalantza);

					if (zalantza != null) {
						zalantza.setErantzuna(txtZalantzaErantzuna.getText());
						zalantza.setErantzulea(txtZalantzaErab.getSelectedItem().toString());
						
						if (kontrola.eguneratuZalantza(zalantza)) {
							JOptionPane.showMessageDialog(null, "Zalantza ongi eguneratuta.");
							TaulaKargatu(zalantza.zalantzakBistaratu());
						} else {
							JOptionPane.showMessageDialog(null, "Errore bat gertatu da zalantza eguneratzean.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ez da zalantza aurkitu: " + idZalantza);
					}
				}
			}
		});
		
	}
	
	private void TaulaKargatu(List<Zalantza> zalantzakList) {
		String[] Zutabeak = { "ID", "Zalantza", "Egilea", "Erantzuna", "Erantzulea" };
		DefaultTableModel model = new DefaultTableModel(Zutabeak, 0);
		for (Zalantza zala : zalantzakList) {
			Object[] row = { zala.getIdZalantza(), zala.getGaldera(), zala.getEgilea(), zala.getErantzuna(), zala.getErantzulea() };
			model.addRow(row);
		}
		zalantzak.setModel(model);
	}
	
	private void AukeratutakoErregistroaErakutsi() {
		int row = zalantzak.getSelectedRow();
		Kontrola kontrola = new Kontrola();
		if (kontrola.erregistroaKomprobatu(zalantzak, row)) {
			Object erantzuna = zalantzak.getValueAt(row, 3);
			txtZalantzaErantzuna.setText(erantzuna != null ? erantzuna.toString() : "");

			String egoera = zalantzak.getValueAt(row, 4).toString().trim().toLowerCase();
			for (int i = 0; i < txtZalantzaErab.getItemCount(); i++) {
				if (txtZalantzaErab.getItemAt(i).toString().trim().toLowerCase().equals(egoera)) {
					txtZalantzaErab.setSelectedIndex(i);
					break;
				}
			}
		}
	}

}
