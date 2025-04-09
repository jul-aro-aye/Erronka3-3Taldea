package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Eskatu {
	private int eskatutakoId;
	private String eskatutakoBarraka;
	private String eskatutakoEskaera;

	public Eskatu(int eskatutakoId, String eskatutakoEskaera, String eskatutakoBarraka) {
		this.eskatutakoId = eskatutakoId;
		this.eskatutakoBarraka = eskatutakoBarraka;
		this.eskatutakoEskaera = eskatutakoEskaera;
	}

	public Eskatu() {
	}

	/**
	 * @return the eskatutakoId
	 */
	public int getEskatutakoId() {
		return eskatutakoId;
	}

	/**
	 * @param eskatutakoId the eskatutakoId to set
	 */
	public void setEskatutakoId(int eskatutakoId) {
		this.eskatutakoId = eskatutakoId;
	}

	/**
	 * @return the eskatutakoBarraka
	 */
	public String getEskatutakoBarraka() {
		return eskatutakoBarraka;
	}

	/**
	 * @param eskatutakoBarraka the eskatutakoBarraka to set
	 */
	public void setEskatutakoBarraka(String eskatutakoBarraka) {
		this.eskatutakoBarraka = eskatutakoBarraka;
	}

	/**
	 * @return the eskatutakoEskaera
	 */
	public String getEskatutakoEskaera() {
		return eskatutakoEskaera;
	}

	/**
	 * @param eskatutakoEskaera the eskatutakoEskaera to set
	 */
	public void setEskatutakoEskaera(String eskatutakoEskaera) {
		this.eskatutakoEskaera = eskatutakoEskaera;
	}

	public List<Eskatu> eskatutakoakBistaratu() {
		List<Eskatu> eskatutakoak = new ArrayList<>();
	    String sql = "SELECT e.idEskatu, es.izena, ba.izena FROM eskatu e INNER JOIN eskaera es ON es.idEskaera = e.eskaera_idEskaera INNER JOIN barraka ba ON e.barraka_idBarraka = ba.idBarraka";
	    
	    try (Connection conn = Konexioa.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Eskatu eska = new Eskatu(
	            	rs.getInt("e.idEskatu"),
	                rs.getString("es.izena"), 
	                rs.getString("ba.izena")
	            );
	            eskatutakoak.add(eska); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return eskatutakoak;
	}

	public static Eskatu eskaeraBilatu(int idEskatuta) {
		Eskatu eska = null;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT e.idEskatu, es.izena, ba.izena FROM eskatu e INNER JOIN eskaera es ON es.idEskaera = e.eskaera_idEskaera INNER JOIN barraka ba ON e.barraka_idBarraka = ba.idBarraka WHERE idEskatu = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idEskatuta);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int idEskatu = emaitza.getInt("e.idEskatu");
						String eskaera = emaitza.getString("es.izena");
						String barraka = emaitza.getString("ba.izena");

						eska = new Eskatu(idEskatu, eskaera, barraka);
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
	
	public boolean eskatutaEzabatu(int idEskatuta) {
		boolean ongiEzabatuta = false;

		try (Connection konexioadb = Konexioa.getConnection()) {
			String ezabatuKontsulta = "DELETE FROM eskatu WHERE idEskatu = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idEskatuta);

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

	public boolean eguneratuEskatuta(Eskatu eskatuta) {
		boolean eginDa = false;
		
	    String sql = "UPDATE eskatu e SET \r\n"
	    		+ "  e.eskaera_idEskaera = (SELECT idEskaera FROM eskaera WHERE izena = ?), \r\n"
	    		+ "  e.barraka_idBarraka = (SELECT idBarraka FROM barraka WHERE izena = ?) \r\n"
	    		+ "WHERE idEskatu = ?";
	    try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	    	stmt.setString(1, this.getEskatutakoEskaera());
	    	stmt.setString(2, this.getEskatutakoBarraka());
	    	stmt.setInt(3, this.getEskatutakoId());
	        if (stmt.executeUpdate() > 0) {
	        	eginDa = true;
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return eginDa;
	}

	public List<String> eskaeraGuztiak() {
		List<String> eskaerak = new ArrayList<>();
	    String query = "SELECT DISTINCT izena FROM eskaera";

	    try (
	        Connection conn = Konexioa.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)) {
	        while (rs.next()) {
	        	eskaerak.add(rs.getString("izena"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return eskaerak;
	}
	
	public List<String> barrakaGuztiak() {
		List<String> eskaerak = new ArrayList<>();
	    String query = "SELECT DISTINCT izena FROM barraka";

	    try (
	        Connection conn = Konexioa.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)) {
	        while (rs.next()) {
	        	eskaerak.add(rs.getString("izena"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return eskaerak;
	}
	
}
