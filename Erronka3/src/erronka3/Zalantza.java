package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Zalantza {
	private int idZalantza;
	private String galdera;
	private String egilea;
	private String erantzuna;
	private String erantzulea;

	/**
	 * @return the idZalantza
	 */
	public int getIdZalantza() {
		return idZalantza;
	}

	/**
	 * @param idZalantza the idZalantza to set
	 */
	public void setIdZalantza(int idZalantza) {
		this.idZalantza = idZalantza;
	}

	/**
	 * @return the galdera
	 */
	public String getGaldera() {
		return galdera;
	}

	/**
	 * @param galdera the galdera to set
	 */
	public void setGaldera(String galdera) {
		this.galdera = galdera;
	}

	/**
	 * @return the egilea
	 */
	public String getEgilea() {
		return egilea;
	}

	/**
	 * @param egilea the egilea to set
	 */
	public void setEgilea(String egilea) {
		this.egilea = egilea;
	}

	/**
	 * @return the erantzuna
	 */
	public String getErantzuna() {
		return erantzuna;
	}

	/**
	 * @param erantzuna the erantzuna to set
	 */
	public void setErantzuna(String erantzuna) {
		this.erantzuna = erantzuna;
	}

	/**
	 * @return the erantzulea
	 */
	public String getErantzulea() {
		return erantzulea;
	}

	/**
	 * @param erantzulea the erantzulea to set
	 */
	public void setErantzulea(String erantzulea) {
		this.erantzulea = erantzulea;
	}

	public Zalantza(int idZalantza, String galdera, String egilea, String erantzuna, String erantzulea) {
		this.idZalantza = idZalantza;
		this.galdera = galdera;
		this.egilea = egilea;
		this.erantzuna = erantzuna;
		this.erantzulea = erantzulea;
	}

	public Zalantza() {
	}

	public List<Zalantza> zalantzakBistaratu() {
		List<Zalantza> zalantzak = new ArrayList<>();
		String sql = "SELECT idZalantza, galdera, b.erabiltzailea, erantzuna, l.erabiltzailea FROM zalantza z INNER JOIN bezeroa b ON b.idBezeroa = z.Bezeroa_idBezeroa INNER JOIN langilea l ON l.idLangilea = z.Langilea_idLangilea;";

		try (Connection conn = Konexioa.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Zalantza zala = new Zalantza(rs.getInt("idZalantza"), rs.getString("galdera"),
						rs.getString("b.erabiltzailea"), rs.getString("erantzuna"), rs.getString("l.erabiltzailea"));
				zalantzak.add(zala);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zalantzak;
	}

	public List<String> erabAukeraGuztiak() {
	    List<String> erabiltzaileak = new ArrayList<>();
	    String query = "SELECT DISTINCT erabiltzailea FROM langilea";

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

	public static Zalantza zalantzaBilatu(int idZalantza) {
		Zalantza zalantza = null;
		try (Connection konexioadb = Konexioa.getConnection()) {
			String kontsultaErab = "SELECT idZalantza, galdera, b.erabiltzailea, erantzuna, l.erabiltzailea FROM zalantza z INNER JOIN bezeroa b ON b.idBezeroa = z.Bezeroa_idBezeroa INNER JOIN langilea l ON l.idLangilea = z.Langilea_idLangilea WHERE z.idZalantza = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idZalantza);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int id = emaitza.getInt("idZalantza");
						String galdera = emaitza.getString("galdera");
						String bezErab = emaitza.getString("b.erabiltzailea");
						String erantzuna = emaitza.getString("erantzuna");
						String erabLan = emaitza.getString("l.erabiltzailea");

						zalantza = new Zalantza(id, galdera, bezErab, erantzuna, erabLan);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektatzean", "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return zalantza;
	}

	public boolean zalantzaEzabatu(int idZalantza) {
		boolean ongiEzabatuta = false;

		try (Connection konexioadb = Konexioa.getConnection()) {
			String ezabatuKontsulta = "DELETE FROM zalantza WHERE idZalantza = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idZalantza);

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

	public boolean eguneratuZalantza(Zalantza zalantza) {
		boolean eginDa = false;
		String sql = "UPDATE zalantza SET erantzuna = ?, Langilea_idLangilea = (SELECT idLangilea FROM langilea WHERE erabiltzailea = ?) WHERE idZalantza = ?";
		try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, this.getErantzuna());
			stmt.setString(2, this.getErantzulea());
			stmt.setInt(3, this.getIdZalantza());
			if (stmt.executeUpdate() > 0) {
				eginDa = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eginDa;
	}

}
