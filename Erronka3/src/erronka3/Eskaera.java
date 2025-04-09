package erronka3;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Eskaera {
	private int idEskaera;
	private String eskaeraIzena;
	private Date eskaeraData;
	private String eskaeraErab;
	
	public Eskaera() {
	}

	public Eskaera(int idEskaera, String eskaeraIzena, Date eskaeraData, String eskaeraErab) {
		this.idEskaera = idEskaera;
		this.eskaeraIzena = eskaeraIzena;
		this.eskaeraData = eskaeraData;
		this.eskaeraErab = eskaeraErab;
	}

	/**
	 * @return the idEskaera
	 */
	public int getIdEskaera() {
		return idEskaera;
	}

	/**
	 * @param idEskaera the idEskaera to set
	 */
	public void setIdEskaera(int idEskaera) {
		this.idEskaera = idEskaera;
	}

	/**
	 * @return the eskaeraIzena
	 */
	public String getEskaeraIzena() {
		return eskaeraIzena;
	}

	/**
	 * @param eskaeraIzena the eskaeraIzena to set
	 */
	public void setEskaeraIzena(String eskaeraIzena) {
		this.eskaeraIzena = eskaeraIzena;
	}

	/**
	 * @return the eskaeraData
	 */
	public Date getEskaeraData() {
		return eskaeraData;
	}

	/**
	 * @param eskaeraData the eskaeraData to set
	 */
	public void setEskaeraData(Date eskaeraData) {
		this.eskaeraData = eskaeraData;
	}

	/**
	 * @return the eskaeraErab
	 */
	public String getEskaeraErab() {
		return eskaeraErab;
	}

	/**
	 * @param eskaeraErab the eskaeraErab to set
	 */
	public void setEskaeraErab(String eskaeraErab) {
		this.eskaeraErab = eskaeraErab;
	}

	public List<Eskaera> eskaerakBistaratu() {
		List<Eskaera> eskaerak = new ArrayList<>();
	    String sql = "SELECT idEskaera, e.izena, data, b.erabiltzailea FROM eskaera e INNER JOIN bezeroa b ON e.Bezeroa_idBezeroa = b.idBezeroa";
	    
	    try (Connection conn = Konexioa.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Eskaera lang = new Eskaera(
	                rs.getInt("idEskaera"), 
	                rs.getString("e.izena"), 
	                rs.getDate("data"),
	                rs.getString("b.erabiltzailea")
	            );
	            eskaerak.add(lang); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return eskaerak;
	}
	
	public List<String> erabAukeraGuztiak() {
	    List<String> erabiltzaileak = new ArrayList<>();
	    String query = "SELECT DISTINCT erabiltzailea FROM bezeroa";

	    try (
	        Connection conn = Konexioa.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)) {
	        while (rs.next()) {
	            erabiltzaileak.add(rs.getString("erabiltzailea"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return erabiltzaileak;
	}

	public static Eskaera eskaeraBilatu(int idEskaera) {
		Eskaera eska = null;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT idEskaera, e.izena, data, b.erabiltzailea FROM eskaera e "
					+ "INNER JOIN bezeroa b ON b.idBezeroa = e.Bezeroa_idBezeroa WHERE idEskaera = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idEskaera);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int id = emaitza.getInt("idEskaera");
						String izarrak = emaitza.getString("e.izena");
						Date data = emaitza.getDate("data");
						String bezeroa = emaitza.getString("b.erabiltzailea");

						eska = new Eskaera(id, izarrak, data, bezeroa);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return eska;
	}

	public boolean eskaeraEzabatu(int idEskaera) {
		boolean ongiEzabatuta = false;

		try (Connection konexioadb = Konexioa.getConnection()) {
			String ezabatuKontsulta = "DELETE FROM eskaera WHERE idEskaera = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idEskaera);

				int ilaraKop = ps.executeUpdate();

				if (ilaraKop > 0) {
					ongiEzabatuta = true;
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektatzean", "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

		return ongiEzabatuta;
	}

	public boolean eguneratuEskaera(Eskaera eskaera) {
		boolean eginDa = false;
		java.util.Date utilDate = eskaera.getEskaeraData();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    String sql = "UPDATE eskaera e SET "
                + "e.izena = ?, data = ?, "
                + "Bezeroa_idBezeroa = (SELECT idBezeroa FROM bezeroa WHERE erabiltzailea = ?) "
                + "WHERE idEskaera = ?";
	    try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, this.getEskaeraIzena());
	        stmt.setDate(2, sqlDate);
	        stmt.setString(3, this.getEskaeraErab());
	        stmt.setInt(4, this.getIdEskaera());
	        if (stmt.executeUpdate() > 0) {
	        	eginDa = true;
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return eginDa;
	}
		
}
