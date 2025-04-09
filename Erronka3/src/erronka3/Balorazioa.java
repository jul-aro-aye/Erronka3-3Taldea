package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Balorazioa {
	private int idBalorazioa;
	private int izarKop;
	private String zergatia;
	private String bezeroa;
	private String barraka;
	
	public Balorazioa() {
	}

	public Balorazioa(int idBalorazioa, int izarKop, String zergatia, String bezeroa, String barraka) {
		this.idBalorazioa = idBalorazioa;
		this.izarKop = izarKop;
		this.zergatia = zergatia;
		this.bezeroa = bezeroa;
		this.barraka = barraka;
	}

	/**
	 * @return the idBalorazioa
	 */
	public int getIdBalorazioa() {
		return idBalorazioa;
	}

	/**
	 * @param idBalorazioa the idBalorazioa to set
	 */
	public void setIdBalorazioa(int idBalorazioa) {
		this.idBalorazioa = idBalorazioa;
	}

	/**
	 * @return the izarKop
	 */
	public int getIzarKop() {
		return izarKop;
	}

	/**
	 * @param izarKop the izarKop to set
	 */
	public void setIzarKop(int izarKop) {
		this.izarKop = izarKop;
	}

	/**
	 * @return the zergatia
	 */
	public String getZergatia() {
		return zergatia;
	}

	/**
	 * @param zergatia the zergatia to set
	 */
	public void setZergatia(String zergatia) {
		this.zergatia = zergatia;
	}

	/**
	 * @return the bezeroa
	 */
	public String getBezeroa() {
		return bezeroa;
	}

	/**
	 * @param bezeroa the bezeroa to set
	 */
	public void setBezeroa(String bezeroa) {
		this.bezeroa = bezeroa;
	}

	/**
	 * @return the barraka
	 */
	public String getBarraka() {
		return barraka;
	}

	/**
	 * @param barraka the barraka to set
	 */
	public void setBarraka(String barraka) {
		this.barraka = barraka;
	}
	
	public List<Balorazioa> balorazioakBistaratu() {
		List<Balorazioa> balorazioak = new ArrayList<>();
		String sql = "SELECT idBalorazioa, izarKopurua, zergatia, be.erabiltzailea, ba.izena FROM balorazioa b INNER JOIN bezeroa be ON be.idBezeroa = b.Bezeroa_idBezeroa INNER JOIN barraka ba ON ba.idBarraka = b.Barraka_idBarraka;";

		try (Connection conn = Konexioa.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Balorazioa balo = new Balorazioa(rs.getInt("idBalorazioa"), rs.getInt("izarKopurua"),
						rs.getString("zergatia"), rs.getString("be.erabiltzailea"), rs.getString("ba.izena"));
				balorazioak.add(balo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return balorazioak;
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
	
	public List<String> barrakaAukeraGuztiak() {
	    List<String> barrakak = new ArrayList<>();
	    String query = "SELECT DISTINCT izena FROM barraka";

	    try (
	        Connection conn = Konexioa.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)) {
	        while (rs.next()) {
	        	barrakak.add(rs.getString("izena"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return barrakak;
	}
	
	public static Balorazioa balorazioaBilatu(int idBalorazioa) {
		Balorazioa balo = null;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT idBalorazioa, izarKopurua, zergatia, be.erabiltzailea, ba.izena FROM balorazioa b "
					+ "INNER JOIN bezeroa be ON be.idBezeroa = b.Bezeroa_idBezeroa INNER JOIN barraka ba ON ba.idBarraka = b.Barraka_idBarraka WHERE idBalorazioa = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idBalorazioa);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int id = emaitza.getInt("idBalorazioa");
						int izarrak = emaitza.getInt("izarKopurua");
						String zergatia = emaitza.getString("zergatia");
						String bezeroa = emaitza.getString("be.erabiltzailea");
						String barraka = emaitza.getString("ba.izena");

						balo = new Balorazioa(id, izarrak, zergatia, bezeroa, barraka);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return balo;
	}
	
	public boolean balorazioaEzabatu(int idBalorazioa) {
		boolean ongiEzabatuta = false;

		try (Connection konexioadb = Konexioa.getConnection()) {
			String ezabatuKontsulta = "DELETE FROM balorazioa WHERE idBalorazioa = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idBalorazioa);

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
	
}
