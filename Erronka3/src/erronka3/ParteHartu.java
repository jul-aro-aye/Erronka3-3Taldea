package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ParteHartu {
	private int parteHartuId;
	private String paeteHartuBarraka;
	private String parteHartuEkintza;
	
	public ParteHartu() {
	}

	public ParteHartu(int parteHartuId, String paeteHartuBarraka, String parteHartuEkintza) {
		this.parteHartuId = parteHartuId;
		this.paeteHartuBarraka = paeteHartuBarraka;
		this.parteHartuEkintza = parteHartuEkintza;
	}

	/**
	 * @return the parteHartuId
	 */
	public int getParteHartuId() {
		return parteHartuId;
	}

	/**
	 * @param parteHartuId the parteHartuId to set
	 */
	public void setParteHartuId(int parteHartuId) {
		this.parteHartuId = parteHartuId;
	}

	/**
	 * @return the paeteHartuBarraka
	 */
	public String getPaeteHartuBarraka() {
		return paeteHartuBarraka;
	}

	/**
	 * @param paeteHartuBarraka the paeteHartuBarraka to set
	 */
	public void setPaeteHartuBarraka(String paeteHartuBarraka) {
		this.paeteHartuBarraka = paeteHartuBarraka;
	}

	/**
	 * @return the parteHartuEkintza
	 */
	public String getParteHartuEkintza() {
		return parteHartuEkintza;
	}

	/**
	 * @param parteHartuEkintza the parteHartuEkintza to set
	 */
	public void setParteHartuEkintza(String parteHartuEkintza) {
		this.parteHartuEkintza = parteHartuEkintza;
	}
	
	public List<ParteHartu> parteHartzaileakBistaratu() {
		List<ParteHartu> parteHartzaikeak = new ArrayList<>();
	    String sql = "SELECT p.idParteHartu, ba.izena, e.izenburua FROM partehartu p INNER JOIN barraka ba ON p.barraka_idBarraka = ba.idBarraka INNER JOIN ekintza e ON e.idEkintza = p.ekintza_idEkintza";
	    
	    try (Connection conn = Konexioa.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            ParteHartu parte = new ParteHartu(
	            	rs.getInt("p.idParteHartu"),
	                rs.getString("ba.izena"), 
	                rs.getString("e.izenburua")
	            );
	            parteHartzaikeak.add(parte); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return parteHartzaikeak;
	}

	public static ParteHartu parteHartzaileaBilatu(int idParteHartzen) {
		ParteHartu parte = null;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT p.idParteHartu, ba.izena, e.izenburua FROM partehartu p INNER JOIN barraka ba ON p.barraka_idBarraka = ba.idBarraka INNER JOIN ekintza e ON e.idEkintza = p.ekintza_idEkintza WHERE idParteHartu = ?";
			   
			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idParteHartzen);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int idParteHartu = emaitza.getInt("p.idParteHartu");
						String barraka = emaitza.getString("ba.izena");
						String ekintza = emaitza.getString("e.izenburua");

						parte = new ParteHartu(idParteHartu, barraka, ekintza);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return parte;
	}
	
	public boolean parteHartzaileaEzabatu(int idParteHartzen) {
		boolean ongiEzabatuta = false;

		try (Connection konexioadb = Konexioa.getConnection()) {
			String ezabatuKontsulta = "DELETE FROM partehartu WHERE idParteHartu = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idParteHartzen);

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

	public boolean eguneratuParteHartu(ParteHartu parteHartzailea) {
		boolean eginDa = false;
		
	    String sql = "UPDATE partehartu p SET \r\n"
	    		+ "  p.barraka_idBarraka = (SELECT idBarraka FROM barraka WHERE izena = ?), \r\n"
	    		+ "  p.ekintza_idEkintza = (SELECT idEkintza FROM ekintza WHERE izenburua = ?) \r\n"
	    		+ "WHERE idParteHartu = ?";
	    try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	    	stmt.setString(1, this.getPaeteHartuBarraka());
	    	stmt.setString(2, this.getParteHartuEkintza());
	    	stmt.setInt(3, this.getParteHartuId());
	        if (stmt.executeUpdate() > 0) {
	        	eginDa = true;
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return eginDa;
	}

	public List<String> ekintzaGuztiak() {
		List<String> eskaerak = new ArrayList<>();
	    String query = "SELECT DISTINCT izenburua FROM ekintza";

	    try (
	        Connection conn = Konexioa.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)) {
	        while (rs.next()) {
	        	eskaerak.add(rs.getString("izenburua"));
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

	public boolean sortuParteHartu(String ekintza) {

		String sql = "INSERT INTO partehartu (barraka_idBarraka, ekintza_idEkintza) " +
	             "VALUES ((SELECT idBarraka FROM barraka WHERE izena = ?), " +
	             "(SELECT idEkintza FROM ekintza WHERE izenburua = ?))";

	try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	    stmt.setString(1, this.getPaeteHartuBarraka());
	    stmt.setString(2, this.getParteHartuEkintza());

	    return stmt.executeUpdate() > 0;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
	}
}
