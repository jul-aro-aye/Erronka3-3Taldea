package erronka3;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;

public class MenuAdminNagusia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdminNagusia frame = new MenuAdminNagusia();
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
	public MenuAdminNagusia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 362);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnKudeatu = new JMenu("Kudeatu");
		menuBar.add(mnKudeatu);
		
		JMenuItem mnLangilea = new JMenuItem("Langileak");
		mnKudeatu.add(mnLangilea);
		
		JMenuItem mnBezeroa = new JMenuItem("Bezeroak");
		mnKudeatu.add(mnBezeroa);
		
		JMenuItem mnBalorazioa = new JMenuItem("Balorazioak");
		mnKudeatu.add(mnBalorazioa);
		
		JMenuItem mnEkintza = new JMenuItem("Ekintzak");
		mnKudeatu.add(mnEkintza);
		
		JMenuItem mnEskaera = new JMenuItem("Eskaerak");
		mnKudeatu.add(mnEskaera);
		
		JMenuItem mnHerria = new JMenuItem("Herriak");
		mnKudeatu.add(mnHerria);
		
		JMenuItem mnZalantza = new JMenuItem("Zalantzak");
		mnKudeatu.add(mnZalantza);
		
		JMenuItem mnBarraka = new JMenuItem("Barrakak");
		mnKudeatu.add(mnBarraka);
		
		JMenuItem mnEskatu = new JMenuItem("Eskaerak esleitu");
        mnKudeatu.add(mnEskatu);
        
        JMenuItem mnParteHartu = new JMenuItem("Barrakak esleitu");
        mnKudeatu.add(mnParteHartu);
		
		JMenu mnOrria = new JMenu("Orria");
        menuBar.add(mnOrria);
        
        JMenuItem mnSaioaItxi = new JMenuItem("Saioa itxi");
		mnOrria.add(mnSaioaItxi);
        
        JMenuItem mnAtera = new JMenuItem("Atera");
        mnOrria.add(mnAtera);
		
		mnLangilea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	LangileaAdminKud langileaKud = new LangileaAdminKud();
                langileaKud.setVisible(true);
                dispose();
            }
        });
		
		mnBezeroa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	BezeroaAdminKud bezeroaaKud = new BezeroaAdminKud();
            	bezeroaaKud.setVisible(true);
                dispose();
            }
        });
		
		mnEkintza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	EkintzaAdminKud ekintzaKud = new EkintzaAdminKud();
            	ekintzaKud.setVisible(true);
                dispose();
            }
        });
		
		mnEskaera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	EskaeraAdminKud eskaeraKud = new EskaeraAdminKud();
            	eskaeraKud.setVisible(true);
                dispose();
            }
        });
		
		mnHerria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	HerriaAdminKud herriaKud = new HerriaAdminKud();
            	herriaKud.setVisible(true);
                dispose();
            }
        });
		
		mnZalantza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ZalantzaAdminKud zalantzaKud = new ZalantzaAdminKud();
            	zalantzaKud.setVisible(true);
                dispose();
            }
        });
		
		mnBalorazioa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	BalorazioaAdminKud balorazioaKud = new BalorazioaAdminKud();
            	balorazioaKud.setVisible(true);
                dispose();
            }
        });
		
		mnParteHartu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ParteHartuAdminKud parteHartu = new ParteHartuAdminKud();
            	parteHartu.setVisible(true);
                dispose();
            }
        });
		
		mnEskatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	EskatuAdminKud eskatuKud = new EskatuAdminKud();
            	eskatuKud.setVisible(true);
                dispose();
            }
        });
		
		mnBarraka.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	BarrakaAdminKud barrakaKud = new BarrakaAdminKud();
            	barrakaKud.setVisible(true);
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
		lblOngiEtorri.setBounds(231, 20, 147, 123);
		contentPane.add(lblOngiEtorri);
	}
}
