package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javax.swing.JFrame;

public class Langilea {
	private int idLangilea;
	private String izena;
	private String abizenak;
	private String erabiltzailea;
	private String pasahitza;
	private String telefonoa;
	private String emaila;
	private String kargua;

	public Langilea(String erabiltzailea, String pasahitza) {
		this.erabiltzailea = erabiltzailea;
		this.pasahitza = pasahitza;
	}

	public Langilea(int idLangilea, String izena, String abizenak, String erabiltzailea, String pasahitza,
			String telefonoa, String emaila, String kargua) {
		this.idLangilea = idLangilea;
		this.izena = izena;
		this.abizenak = abizenak;
		this.erabiltzailea = erabiltzailea;
		this.pasahitza = pasahitza;
		this.telefonoa = telefonoa;
		this.emaila = emaila;
		this.kargua = kargua;
	}

	public Langilea(int idLangilea, String izena, String abizenak, String erabiltzailea, String pasahitza,
			String telefonoa, String kargua) {
		this.idLangilea = idLangilea;
		this.izena = izena;
		this.abizenak = abizenak;
		this.erabiltzailea = erabiltzailea;
		this.pasahitza = pasahitza;
		this.telefonoa = telefonoa;
		this.kargua = kargua;
	}

	public Langilea() {

	}

	/**
	 * @return the idLangilea
	 */
	public int getIdLangilea() {
		return idLangilea;
	}

	/**
	 * @param idLangilea the idLangilea to set
	 */
	public void setIdLangilea(int idLangilea) {
		this.idLangilea = idLangilea;
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

	/**
	 * @return the kargua
	 */
	public String getKargua() {
		return kargua;
	}

	/**
	 * @param kargua the kargua to set
	 */
	public void setKargua(String kargua) {
		this.kargua = kargua;
	}

	public void saioaHasi(String erabiltzailea, String pasahitza, JFrame loginWindow) {
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT erabiltzailea, pasahitza, kargua FROM 3erronka.langilea WHERE erabiltzailea = ? AND pasahitza = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setString(1, erabiltzailea);
				ps.setString(2, pasahitza);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						String erabiltzaileaDB = emaitza.getString("erabiltzailea");
						String pasahitzaDB = emaitza.getString("pasahitza");
						String karguaDb = emaitza.getString("kargua");
						

						if (erabiltzailea.equals(erabiltzaileaDB) && pasahitza.equals(pasahitzaDB)) {
							if ("Admin".equals(karguaDb)) {
								MenuAdminNagusia menuAdmin = new MenuAdminNagusia();
								menuAdmin.setVisible(true);
								loginWindow.dispose();
							} else if ("Langi".equals(karguaDb)) {
								MenuLangiNagusia menuLangi = new MenuLangiNagusia();
								menuLangi.setVisible(true);
								loginWindow.dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Erabiltzaile eta pasahitz okerrak", "Arazoak",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Arazoa saioa hastean, saiatu berriro!", "Errorea",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	public boolean langileaEzabatu(int idLangilea) {
		boolean ongiEzabatuta = false;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String ezabatuKontsulta = "UPDATE langilea SET aktibo = False WHERE idLangilea = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idLangilea);

				int filasAfectadas = ps.executeUpdate();

				if (filasAfectadas > 0) {
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

	public List<Langilea> langileakBistaratu() {
	    List<Langilea> langileak = new ArrayList<>();
	    String sql = "SELECT idLangilea, izena, abizenak, erabiltzailea, pasahitza, telefonoa, emaila, kargua FROM langilea WHERE aktibo = True";
	    
	    try (Connection conn = Konexioa.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Langilea lang = new Langilea(
	                rs.getInt("idLangilea"), 
	                rs.getString("izena"), 
	                rs.getString("abizenak"),
	                rs.getString("erabiltzailea"), 
	                rs.getString("pasahitza"), 
	                rs.getString("telefonoa"),
	                rs.getString("emaila"), 
	                rs.getString("kargua")
	            );
	            langileak.add(lang); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return langileak;
	}

	public List<String> KarguAukeraGuztiak() {
		List<String> karguak = new ArrayList<>();
		String query = "SELECT DISTINCT kargua FROM langilea";

		try (Connection conn = Konexioa.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				karguak.add(rs.getString("kargua"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return karguak;
	}

	public boolean sortuLangilea(Langilea lang) {
		
		String sql = "INSERT INTO langilea (izena, abizenak, erabiltzailea, pasahitza, telefonoa, emaila, kargua) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, this.getIzena());
			stmt.setString(2, this.getAbizenak());
			stmt.setString(3, this.getErabiltzailea());
			stmt.setString(4, this.getPasahitza());
			stmt.setString(5, this.getTelefonoa());
			stmt.setString(6, this.getEmaila());
			stmt.setString(7, this.getKargua());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean eguneratuLangilea(Langilea lang) {
	    boolean eginDa = false;
	    String sql = "UPDATE langilea SET izena = ?, abizenak = ?, erabiltzailea = ?, pasahitza = ?, telefonoa = ?, emaila = ?, kargua = ? WHERE idLangilea = ?";
	    try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, this.getIzena());
	        stmt.setString(2, this.getAbizenak());
	        stmt.setString(3, this.getErabiltzailea());
	        stmt.setString(4, this.getPasahitza());
	        stmt.setString(5, this.getTelefonoa());
	        stmt.setString(6, this.getEmaila());
	        stmt.setString(7, this.getKargua());
	        stmt.setInt(8, this.getIdLangilea());
	        if (stmt.executeUpdate() > 0) {
	        	eginDa = true;
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return eginDa;
	}
	
	public static boolean erabiltzaileaKonprobatu(String Erab) throws SQLException {
		boolean existitu = false;
		Connection konexioadb = Konexioa.getConnection();

		String kontsultaErab = "SELECT erabiltzailea FROM langilea WHERE erabiltzailea = ?";

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

		String kontsultaErab = "SELECT emaila FROM langilea WHERE emaila = ?";

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

		String kontsultaErab = "SELECT telefonoa FROM langilea WHERE telefonoa = ?";

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
	
	public static Langilea langileaBilatu(int idLangilea) {
	    Langilea langilea = null;
	    
	    try (Connection konexioadb = Konexioa.getConnection()) {
	        String kontsultaErab = "SELECT * FROM 3erronka.langilea WHERE idLangilea = ?";

	        try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
	            ps.setInt(1, idLangilea);

	            try (ResultSet emaitza = ps.executeQuery()) {
	                if (emaitza.next()) {
	                    int id = emaitza.getInt("idLangilea");
	                    String izena = emaitza.getString("izena");
	                    String abizenak = emaitza.getString("abizenak");
	                    String erabiltzailea = emaitza.getString("erabiltzailea");
	                    String pasahitza = emaitza.getString("pasahitza");
	                    String telefonoa = emaitza.getString("telefonoa");
	                    String emaila = emaitza.getString("emaila");
	                    String kargua = emaitza.getString("kargua");

	                    langilea = new Langilea(id, izena, abizenak, erabiltzailea, pasahitza, telefonoa, emaila, kargua);
	                }
	            }
	        }
	    } catch (SQLException e1) {
	        JOptionPane.showMessageDialog(null, "Arazoak datu basera konektatzean", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        e1.printStackTrace();
	    }
	    return langilea;
	}
}
