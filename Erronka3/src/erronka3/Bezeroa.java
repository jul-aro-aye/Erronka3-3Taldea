package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Bezeroa {
	private int idBezeroa;
	private String izena;
	private String abizenak;
	private String erabiltzailea;
	private String pasahitza;
	private String telefonoa;
	private String emaila;
	
	public Bezeroa(int idBezeroa, String izena, String abizenak, String erabiltzailea, String pasahitza, String telefonoa,
			String emaila) {
		this.idBezeroa = idBezeroa;
		this.izena = izena;
		this.abizenak = abizenak;
		this.erabiltzailea = erabiltzailea;
		this.pasahitza = pasahitza;
		this.telefonoa = telefonoa;
		this.emaila = emaila;
	}

	public Bezeroa(int idBezeroa, String izena, String abizenak, String erabiltzailea, String pasahitza, String telefonoa) {
		this.idBezeroa = idBezeroa;
		this.izena = izena;
		this.abizenak = abizenak;
		this.erabiltzailea = erabiltzailea;
		this.pasahitza = pasahitza;
		this.telefonoa = telefonoa;
	}

	public Bezeroa() {
	}

	/**
	 * @return the idBezeroa
	 */
	public int getIdBezeroa() {
		return idBezeroa;
	}

	/**
	 * @param idBezeroa the idBezeroa to set
	 */
	public void setIdBezeroa(int idBezeroa) {
		this.idBezeroa = idBezeroa;
	}

	/**
	 * @return the izena
	 */
	public String getIzena() {
		return izena;
	}

	/**
	 * @param izena the izena to set
	 */
	public void setIzena(String izena) {
		this.izena = izena;
	}

	/**
	 * @return the abizenak
	 */
	public String getAbizenak() {
		return abizenak;
	}

	/**
	 * @param abizenak the abizenak to set
	 */
	public void setAbizenak(String abizenak) {
		this.abizenak = abizenak;
	}

	/**
	 * @return the erabiltzailea
	 */
	public String getErabiltzailea() {
		return erabiltzailea;
	}

	/**
	 * @param erabiltzailea the erabiltzailea to set
	 */
	public void setErabiltzailea(String erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	/**
	 * @return the pasahitza
	 */
	public String getPasahitza() {
		return pasahitza;
	}

	/**
	 * @param pasahitza the pasahitza to set
	 */
	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	/**
	 * @return the telefonoa
	 */
	public String getTelefonoa() {
		return telefonoa;
	}

	/**
	 * @param telefonoa the telefonoa to set
	 */
	public void setTelefonoa(String telefonoa) {
		this.telefonoa = telefonoa;
	}

	/**
	 * @return the emaila
	 */
	public String getEmaila() {
		return emaila;
	}

	/**
	 * @param emaila the emaila to set
	 */
	public void setEmaila(String emaila) {
		this.emaila = emaila;
	}
	
	public boolean bezeroaEzabatu(int idBezeroa) {
		boolean ongiEzabatuta = false;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String ezabatuKontsulta = "UPDATE bezeroa SET aktibo = False WHERE idBezeroa = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idBezeroa);

				int ilaraKopurua = ps.executeUpdate();

				if (ilaraKopurua > 0) {
					ongiEzabatuta = true;
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return ongiEzabatuta;
	}
	
	public static boolean erabiltzaileaKonprobatu(String Erab) throws SQLException {
		boolean existitu = false;
		Connection konexioadb = Konexioa.getConnection();

		String kontsultaErab = "SELECT erabiltzailea FROM bezeroa WHERE erabiltzailea = ?";

		try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
			ps.setString(1, Erab);

			try (ResultSet emaitza = ps.executeQuery()) {
				if (emaitza.next()) {
					existitu = true;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return existitu;
	}
	
	public static boolean emailaKonprobatu(String Emaila) throws SQLException {
		boolean existitu = false;
		Connection konexioadb = Konexioa.getConnection();

		String kontsultaErab = "SELECT emaila FROM bezeroa WHERE emaila = ?";

		try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
			ps.setString(1, Emaila);

			try (ResultSet emaitza = ps.executeQuery()) {
				if (emaitza.next()) {
					existitu = true;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return existitu;
	}
	
	public static boolean telefonoaKonprobatu(String Telefonoa) throws SQLException {
		boolean existitu = false;
		Connection konexioadb = Konexioa.getConnection();

		String kontsultaErab = "SELECT telefonoa FROM bezeroa WHERE telefonoa = ?";

		try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
			ps.setString(1, Telefonoa);

			try (ResultSet emaitza = ps.executeQuery()) {
				if (emaitza.next()) {
					existitu = true;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return existitu;
	}

	public List<Bezeroa> bezeroakBistaratu() {
	    List<Bezeroa> bezeroak = new ArrayList<>();
	    String sql = "SELECT idBezeroa, izena, abizenak, erabiltzailea, pasahitza, telefonoa, emaila FROM bezeroa where aktibo = True";
	    
	    try (Connection conn = Konexioa.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Bezeroa beze = new Bezeroa(
	                rs.getInt("idBezeroa"), 
	                rs.getString("izena"), 
	                rs.getString("abizenak"),
	                rs.getString("erabiltzailea"), 
	                rs.getString("pasahitza"), 
	                rs.getString("telefonoa"),
	                rs.getString("emaila")
	            );
	            bezeroak.add(beze); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return bezeroak;
	}

	public boolean sortuBezeroa(Bezeroa beze) {

		String sql = "INSERT INTO bezeroa (izena, abizenak, erabiltzailea, pasahitza, telefonoa, emaila) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, beze.getIzena());
			stmt.setString(2, beze.getAbizenak());
			stmt.setString(3, beze.getErabiltzailea());
			stmt.setString(4, beze.getPasahitza());
			stmt.setString(5, beze.getTelefonoa());
			stmt.setString(6, beze.getEmaila());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean eguneratuBezeroa(Bezeroa beze) throws SQLException {

	    String sql = "UPDATE bezeroa SET izena = ?, abizenak = ?, erabiltzailea = ?, pasahitza = ?, telefonoa = ?, emaila = ? WHERE idBezeroa = ?";
	    try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, this.getIzena());
	        stmt.setString(2, this.getAbizenak());
	        stmt.setString(3, this.getErabiltzailea());
	        stmt.setString(4, this.getPasahitza());
	        stmt.setString(5, this.getTelefonoa());
	        stmt.setString(6, this.getEmaila());
	        stmt.setInt(7, this.getIdBezeroa());

	        return stmt.executeUpdate() > 0;
	    }
	}
	
	public static Bezeroa bezeroaBilatu(int idBezeroa) {
		Bezeroa bezeroa = null;
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT * FROM bezeroa WHERE idBezeroa = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idBezeroa);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int id = emaitza.getInt("idBezeroa");
	                    String izena = emaitza.getString("izena");
	                    String abizenak = emaitza.getString("abizenak");
	                    String erabiltzailea = emaitza.getString("erabiltzailea");
	                    String pasahitza = emaitza.getString("pasahitza");
	                    String telefonoa = emaitza.getString("telefonoa");
	                    String emaila = emaitza.getString("emaila");
	                    
	                    bezeroa = new Bezeroa(id, izena, abizenak, erabiltzailea, pasahitza, telefonoa, emaila);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return bezeroa;
	}
}
