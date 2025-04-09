package erronka3; 

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Kontrola {
	
	public boolean langileErabiltzaileaKonprobatu(String Erab) throws SQLException {
		boolean egoera = false;
		if (Langilea.erabiltzaileaKonprobatu(Erab)) {
			egoera = true;
		}
		return egoera;
	}
	
	public boolean langileEmailaKonprobatu(String Emaila) throws SQLException {
		boolean egoera = false;
		if (Langilea.emailaKonprobatu(Emaila)) {
			egoera = true;
		}
		return egoera;
	}
	
	public boolean bezeroTelefonoaKonprobatu(String Telefonoa) throws SQLException {
		boolean egoera = false;
		if (Bezeroa.telefonoaKonprobatu(Telefonoa)) {
			egoera = true;
		}
		return egoera;
	}
	
	public boolean bezeroErabiltzaileaKonprobatu(String Erab) throws SQLException {
		boolean egoera = false;
		if (Bezeroa.erabiltzaileaKonprobatu(Erab)) {
			egoera = true;
		}
		return egoera;
	}
	
	public boolean bezeroEmailaKonprobatu(String Emaila) throws SQLException {
		boolean egoera = false;
		if (Bezeroa.emailaKonprobatu(Emaila)) {
			egoera = true;
		}
		return egoera;
	}
	
	public boolean langileTelefonoaKonprobatu(String Telefonoa) throws SQLException {
		boolean egoera = false;
		if (Langilea.telefonoaKonprobatu(Telefonoa)) {
			egoera = true;
		}
		return egoera;
	}
	
	public boolean herriIzenaKonprobatu(String izena) throws SQLException {
		boolean egoera = false;
		if (Herria.herriaKonprobatu(izena)) {
			egoera = true;
		}
		return egoera;
	}
	
	public void loginEgin(String Erab, String Pasa, JFrame loginWindow) {
		
		Langilea langilea = new Langilea();
        langilea.setErabiltzailea(Erab);
        langilea.setPasahitza(Pasa);
        langilea.saioaHasi(langilea.getErabiltzailea(), langilea.getPasahitza(), loginWindow);
        
	}
	
	public Langilea langileaKonprobatu(int idLangilea) {
	    Langilea langilea = Langilea.langileaBilatu(idLangilea);
	    
	    if (langilea == null) {
	        JOptionPane.showMessageDialog(null, "Ez da langilea aurkitu: " + idLangilea, "Errorea", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    return langilea;
	}
	
	public boolean ezabatuLangilea(int idLangilea) {
		boolean ongiEzabatu = false;
		Langilea langilea = new Langilea();
    	if (langilea.langileaEzabatu(idLangilea)) {
    		ongiEzabatu = true;
    	}
    	return ongiEzabatu;
    }
	
	public boolean erregistroaKomprobatu(JTable tabla, int row) {
		if (tabla == null || row < 0 || row >= tabla.getRowCount()) {
	        return false;
	    }

	    for (int col = 0; col < tabla.getColumnCount(); col++) {
	        if (tabla.getValueAt(row, col) != null) {
	        	return true;
	        }
	    }
		return false;
	}
	
	public Bezeroa bezeroaKonprobatu(int idBezeroa) {
		Bezeroa bezeroa = Bezeroa.bezeroaBilatu(idBezeroa);
		
		if (bezeroa == null) {
			JOptionPane.showMessageDialog(null, "Ez da bezeroa aurkitu: " + idBezeroa, "Errorea", JOptionPane.WARNING_MESSAGE);
		}
		return bezeroa;
	}
	
	public boolean ezabatuBezeroa(int idBezeroa) {
		boolean ongiEzabatu = false;
		Bezeroa bezeroa = new Bezeroa();
		if (bezeroa.bezeroaEzabatu(idBezeroa)) {
			ongiEzabatu = true;
		}
		return ongiEzabatu;
	}

	public boolean eguneratuLangilea(Langilea langilea) {
		boolean ongiEguneratu = false;
		if (langilea.eguneratuLangilea(langilea)) {
			ongiEguneratu = true;
		}
		return ongiEguneratu;
	}
	
	public boolean eguneratuBezeroa(Bezeroa bezeroa) {
		boolean ongiEguneratu = false;
		try {
			if (bezeroa.eguneratuBezeroa(bezeroa)) {
				ongiEguneratu = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ongiEguneratu;
	}
	
	public boolean ezabatuHerria(int idHerria) {
		boolean ongiEzabatu = false;
		Herria herria = new Herria();
		if (herria.herriaEzabatu(idHerria)) {
			ongiEzabatu = true;
		}
		return ongiEzabatu;
	}

	public Herria herriaKonprobatu(int idHerria) {
		
		Herria bezeroa = Herria.herriaBilatu(idHerria);
		
		if (bezeroa == null) {
			JOptionPane.showMessageDialog(null, "Ez da herria aurkitu: " + idHerria, "Errorea", JOptionPane.WARNING_MESSAGE);
		}
		return bezeroa;
	}

	public boolean eguneratuHerria(Herria herria) {
		boolean ongiEguneratu = false;
		try {
			if (herria.eguneratuHerria(herria)) {
				ongiEguneratu = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ongiEguneratu;
	}

	public Ekintza ekintzaKonprobatu(int idEkintza) {
		Ekintza ekintza = Ekintza.ekintzaBilatu(idEkintza);
	    
	    if (ekintza == null) {
	        JOptionPane.showMessageDialog(null, "Ez da ekintza aurkitu: " + idEkintza, "Errorea", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    return ekintza;
	}
	
	public boolean ezabatuEkintza(int idEkintza) {
		boolean ongiEzabatu = false;
		Ekintza ekintza = new Ekintza();
    	if (ekintza.ekintzaEzabatu(idEkintza)) {
    		ongiEzabatu = true;
    	}
    	return ongiEzabatu;
    }

	public boolean eguneratuEkintza(Ekintza ekintza) {
		boolean ongiEguneratu = false;
		if (ekintza.eguneratuEkintza(ekintza)) {
			ongiEguneratu = true;
		}
		return ongiEguneratu;
	}

	public Zalantza zalantzaKonprobatu(int idZalantza) {
		Zalantza zalantza = Zalantza.zalantzaBilatu(idZalantza);
	    
	    if (zalantza == null) {
	        JOptionPane.showMessageDialog(null, "Ez da zalantza aurkitu: " + idZalantza, "Errorea", JOptionPane.WARNING_MESSAGE);
	    }
		return zalantza;
	    
	}

	public boolean ezabatuZalantza(int idZalantza) {
		boolean ongiEzabatu = false;
		Zalantza zalantza = new Zalantza();
    	if (zalantza.zalantzaEzabatu(idZalantza)) {
    		ongiEzabatu = true;
    	}
    	return ongiEzabatu;
	}

	public boolean eguneratuZalantza(Zalantza zalantza) {
		boolean ongiEguneratu = false;
		if (zalantza.eguneratuZalantza(zalantza)) {
			ongiEguneratu = true;
		}
		return ongiEguneratu;
	}
	
	public Balorazioa balorazioaKonprobatu(int idBalorazioa) {
		Balorazioa balorazio = Balorazioa.balorazioaBilatu(idBalorazioa);
	    
	    if (balorazio == null) {
	        JOptionPane.showMessageDialog(null, "Ez da balorazioa aurkitu: " + idBalorazioa, "Errorea", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    return balorazio;
	}
	
	public boolean ezabatuBalorazioa(int idBalorazioa) {
		boolean ongiEzabatu = false;
		Balorazioa balorazioa = new Balorazioa();
    	if (balorazioa.balorazioaEzabatu(idBalorazioa)) {
    		ongiEzabatu = true;
    	}
    	return ongiEzabatu;
	}

	public Eskaera eskaeraKonprobatu(int idEskaera) {
		Eskaera eskaera = Eskaera.eskaeraBilatu(idEskaera);
	    
	    if (eskaera == null) {
	        JOptionPane.showMessageDialog(null, "Ez da eskaera aurkitu: " + idEskaera, "Errorea", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    return eskaera;
	}
	public boolean ezabatuEskaera(int idEskaera) {
		boolean ongiEzabatu = false;
		Eskaera eskaera = new Eskaera();
    	if (eskaera.eskaeraEzabatu(idEskaera)) {
    		ongiEzabatu = true;
    	}
    	return ongiEzabatu;
	}

	public boolean eguneratuEskaera(Eskaera eskaera) {
		boolean ongiEguneratu = false;
		if (eskaera.eguneratuEskaera(eskaera)) {
			ongiEguneratu = true;
		}
		return ongiEguneratu;
	}
	
	public Eskatu eskatutaKonprobatu(int idEskatuta) {
		Eskatu eskaera = Eskatu.eskaeraBilatu(idEskatuta);
	    
	    if (eskaera == null) {
	        JOptionPane.showMessageDialog(null, "Ez da erregistroa aurkitu: " + idEskatuta, "Errorea", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    return eskaera;
	}
	
	public boolean ezabatuEskatuta(int idEskatutakoa) {
		boolean ongiEzabatu = false;
		Eskatu eskatuta = new Eskatu();
    	if (eskatuta.eskatutaEzabatu(idEskatutakoa)) {
    		ongiEzabatu = true;
    	}
    	return ongiEzabatu;
	}

	public boolean eguneratuEskatuta(Eskatu eskatuta) {
		boolean ongiEguneratu = false;
		if (eskatuta.eguneratuEskatuta(eskatuta)) {
			ongiEguneratu = true;
		}
		return ongiEguneratu;
	}

	public ParteHartu parteHartuKonprobatu(int idParteHartu) {
		ParteHartu eskaera = ParteHartu.parteHartzaileaBilatu(idParteHartu);
	    
	    if (eskaera == null) {
	        JOptionPane.showMessageDialog(null, "Ez da erregistroa aurkitu: " + idParteHartu, "Errorea", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    return eskaera;
	}

	public boolean ezabatuParteHartu(int idParteHartu) {
		boolean ongiEzabatu = false;
		ParteHartu parte = new ParteHartu();
    	if (parte.parteHartzaileaEzabatu(idParteHartu)) {
    		ongiEzabatu = true;
    	}
    	return ongiEzabatu;
	}
	
	public boolean eguneratuParteHartu(ParteHartu parte) {
		boolean ongiEguneratu = false;
		if (parte.eguneratuParteHartu(parte)) {
			ongiEguneratu = true;
		}
		return ongiEguneratu;
	}
	
	public Barraka barrakaKonprobatu(int idBarraka) {
		Barraka barraka = Barraka.barrakaBilatu(idBarraka);
		
		if (barraka == null) {
			JOptionPane.showMessageDialog(null, "Ez da bezeroa aurkitu: " + idBarraka, "Errorea", JOptionPane.WARNING_MESSAGE);
		}
		return barraka;
	}

	public boolean eguneratuBarraka(Barraka barraka) {
		boolean ongiEguneratu = false;
		if (barraka.eguneratuBarraka(barraka)) {
			ongiEguneratu = true;
		}
		return ongiEguneratu;
	}
	
	public boolean ezabatuBarraka(int idBarraka) {
		boolean ongiEzabatu = false;
		Barraka barra = new Barraka();
    	if (barra.barrakaEzabatu(idBarraka)) {
    		ongiEzabatu = true;
    	}
    	return ongiEzabatu;
	}
	
	public boolean barrakaIzenaKonprobatu(String Izena) throws SQLException {
		boolean egoera = false;
		if (Barraka.izenaKonprobatu(Izena)) {
			egoera = true;
		}
		return egoera;
	}
	
}
