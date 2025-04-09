package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Barraka {
	private int idBarraka;
	private String barrakaIzena;
	private String barrakaModalitatea;
	private int barrakaKapazitatea;
	private double barrakaPrezioa;
	private String barrakaLangilea;
	
	public Barraka() {
	}

	public Barraka(int idBarraka, String barrakaIzena, String barrakaModalitatea, int barrakaKapazitatea,
			double barrakaPrezioa, String barrakaLangilea) {
		this.idBarraka = idBarraka;
		this.barrakaIzena = barrakaIzena;
		this.barrakaModalitatea = barrakaModalitatea;
		this.barrakaKapazitatea = barrakaKapazitatea;
		this.barrakaPrezioa = barrakaPrezioa;
		this.barrakaLangilea = barrakaLangilea;
	}

	/**
	 * @return the idBarraka
	 */
	public int getIdBarraka() {
		return idBarraka;
	}

	/**
	 * @param idBarraka the idBarraka to set
	 */
	public void setIdBarraka(int idBarraka) {
		this.idBarraka = idBarraka;
	}

	/**
	 * @return the barrakaIzena
	 */
	public String getBarrakaIzena() {
		return barrakaIzena;
	}

	/**
	 * @param barrakaIzena the barrakaIzena to set
	 */
	public void setBarrakaIzena(String barrakaIzena) {
		this.barrakaIzena = barrakaIzena;
	}

	/**
	 * @return the barrakaModalitatea
	 */
	public String getBarrakaModalitatea() {
		return barrakaModalitatea;
	}

	/**
	 * @param barrakaModalitatea the barrakaModalitatea to set
	 */
	public void setBarrakaModalitatea(String barrakaModalitatea) {
		this.barrakaModalitatea = barrakaModalitatea;
	}

	/**
	 * @return the barrakaKapazitatea
	 */
	public int getBarrakaKapazitatea() {
		return barrakaKapazitatea;
	}

	/**
	 * @param barrakaKapazitatea the barrakaKapazitatea to set
	 */
	public void setBarrakaKapazitatea(int barrakaKapazitatea) {
		this.barrakaKapazitatea = barrakaKapazitatea;
	}

	/**
	 * @return the barrakaPrezioa
	 */
	public double getBarrakaPrezioa() {
		return barrakaPrezioa;
	}

	/**
	 * @param barrakaPrezioa the barrakaPrezioa to set
	 */
	public void setBarrakaPrezioa(double barrakaPrezioa) {
		this.barrakaPrezioa = barrakaPrezioa;
	}

	/**
	 * @return the barrakaLangilea
	 */
	public String getBarrakaLangilea() {
		return barrakaLangilea;
	}

	/**
	 * @param barrakaLangilea the barrakaLangilea to set
	 */
	public void setBarrakaLangilea(String barrakaLangilea) {
		this.barrakaLangilea = barrakaLangilea;
	}

	public List<Barraka> barrakakBistaratu() {
		List<Barraka> barrakak = new ArrayList<>();
		String sql = "SELECT idBarraka, b.izena, modalitatea, kapazitatea, prezioa, l.erabiltzailea FROM barraka b INNER JOIN langilea l ON l.idLangilea = b.Langilea_idLangilea;";

		try (Connection conn = Konexioa.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Barraka bar = new Barraka(rs.getInt("idBarraka"), rs.getString("b.izena"), rs.getString("modalitatea"),
						rs.getInt("kapazitatea"), rs.getDouble("prezioa"), rs.getString("l.erabiltzailea"));
				barrakak.add(bar);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return barrakak;
	}

	public List<String> langiAukeraGuztiak() {
		List<String> langileak = new ArrayList<>();
	    String query = "SELECT DISTINCT erabiltzailea FROM langilea";

	    try (
	        Connection conn = Konexioa.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)) {
	        while (rs.next()) {
	        	langileak.add(rs.getString("erabiltzailea"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return langileak;
	}

	public static Barraka barrakaBilatu(int idBarraka) {
		Barraka barra = null;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT idBarraka, b.izena, modalitatea, kapazitatea, prezioa, l.erabiltzailea FROM barraka b "
					+ "INNER JOIN langilea l ON l.idLangilea = b.Langilea_idLangilea WHERE idBarraka = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idBarraka);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int id = emaitza.getInt("idBarraka");
						String izena = emaitza.getString("b.izena");
						String modalitatea = emaitza.getString("modalitatea");
						int kapazitatea = emaitza.getInt("kapazitatea");
						double prezioa = emaitza.getDouble("prezioa");
						String langilea = emaitza.getString("l.erabiltzailea");

						barra = new Barraka(id, izena, modalitatea, kapazitatea, prezioa, langilea);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return barra;
	}

	public boolean eguneratuBarraka(Barraka barraka) {
		boolean eginDa = false;
		String sql = "UPDATE barraka SET izena = ?, modalitatea = ?, kapazitatea = ?, prezioa = ?, Langilea_idLangilea = (SELECT idLangilea FROM langilea WHERE erabiltzailea = ?) WHERE idBarraka = ?";
		try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, this.getBarrakaIzena());
			stmt.setString(2, this.getBarrakaModalitatea());
			stmt.setInt(3, this.getBarrakaKapazitatea());
			stmt.setDouble(4, this.getBarrakaPrezioa());
			stmt.setString(5, this.getBarrakaLangilea());
			
			stmt.setInt(6, this.getIdBarraka());
			if (stmt.executeUpdate() > 0) {
				eginDa = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eginDa;
	}
	
	public boolean sortuBarraka(Barraka barra) {

		String sql = "INSERT INTO barraka (izena, modalitatea, kapazitatea, prezioa, Langilea_idLangilea) VALUES (?, ?, ?, ?, (SELECT idLangilea FROM langilea WHERE erabiltzailea = ?))";
		try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, this.getBarrakaIzena());
			stmt.setString(2, this.getBarrakaModalitatea());
			stmt.setInt(3, this.getBarrakaKapazitatea());
			stmt.setDouble(4, this.getBarrakaPrezioa());
			stmt.setString(5, this.getBarrakaLangilea());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean barrakaEzabatu(int idBarraka) {
		boolean ongiEzabatuta = false;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String ezabatuKontsulta = "DELETE FROM barraka WHERE idBarraka = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idBarraka);

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
	
	public static boolean izenaKonprobatu(String izena) throws SQLException {
		boolean existitu = false;
		Connection konexioadb = Konexioa.getConnection();

		String kontsultaErab = "SELECT izena FROM barraka WHERE izena = ?";

		try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
			ps.setString(1, izena);

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
	
}
