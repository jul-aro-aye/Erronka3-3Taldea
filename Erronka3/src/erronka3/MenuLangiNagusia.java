package erronka3;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class MenuLangiNagusia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuLangiNagusia frame = new MenuLangiNagusia();
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
	public MenuLangiNagusia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 362);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnKudeatu = new JMenu("Kudeatu");
		menuBar.add(mnKudeatu);
		
		JMenuItem mnZalantza = new JMenuItem("Zalantzak");
		mnKudeatu.add(mnZalantza);
		
		JMenuItem mnBarraka = new JMenuItem("Barrakak");
		mnKudeatu.add(mnBarraka);
		
		JMenu mnOrria = new JMenu("Orria");
        menuBar.add(mnOrria);
        
        JMenuItem mnSaioaItxi = new JMenuItem("Saioa itxi");
		mnOrria.add(mnSaioaItxi);
        
        JMenuItem mnAtera = new JMenuItem("Atera");
        mnOrria.add(mnAtera);
		
		mnBarraka.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	BarrakaLangiKud barrakaKud = new BarrakaLangiKud();
            	barrakaKud.setVisible(true);
                dispose();
            }
        });
		
		mnZalantza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ZalantzaLangiKud bezeroaaKud = new ZalantzaLangiKud();
            	bezeroaaKud.setVisible(true);
                dispose();
            }
        });
		
		mnSaioaItxi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                Login login = new Login();
                login.setVisible(true);
            }
        });
		
		mnAtera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOngiEtorri = new JLabel("Ongi etorri");
		lblOngiEtorri.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblOngiEtorri.setBounds(233, 10, 147, 123);
		contentPane.add(lblOngiEtorri);
		
	}
}
