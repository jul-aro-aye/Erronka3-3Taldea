package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Herria {
	private int idHerria;
	private String izena;
	private int distantzia;
	private double tarifa;
	
	/**
	 * @return the idHerria
	 */
	public int getIdHerria() {
		return idHerria;
	}

	/**
	 * @param idHerria the idHerria to set
	 */
	public void setIdHerria(int idHerria) {
		this.idHerria = idHerria;
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
	 * @return the distantzia
	 */
	public int getDistantzia() {
		return distantzia;
	}

	/**
	 * @param distantzia the distantzia to set
	 */
	public void setDistantzia(int distantzia) {
		this.distantzia = distantzia;
	}

	/**
	 * @return the tarifa
	 */
	public double getTarifa() {
		return tarifa;
	}

	/**
	 * @param tarifa the tarifa to set
	 */
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	public Herria() {
	}
	
	public Herria(int idHerria, String izena) {
		this.idHerria = idHerria;
		this.izena = izena;
	}

	public Herria(int idHerria, String izena, int distantzia, double tarifa) {
		this.idHerria = idHerria;
		this.izena = izena;
		this.distantzia = distantzia;
		this.tarifa = tarifa;
	}
	
	public static boolean herriaKonprobatu(String izena) throws SQLException {
		boolean existitu = false;
		Connection konexioadb = Konexioa.getConnection();

		String kontsultaErab = "SELECT izena FROM herria WHERE izena = ?";

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
	
	public List<String> herriAukeraGuztiak() {
		List<String> herriak = new ArrayList<>();
		String query = "SELECT DISTINCT idHerria, izena FROM herria";

		try (Connection conn = Konexioa.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				herriak.add(rs.getString("izena"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return herriak;
	}
	
	public List<Herria> herriakBistaratu() {
	    List<Herria> herriak = new ArrayList<>();
	    String sql = "SELECT * FROM herria";
	    
	    try (Connection conn = Konexioa.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Herria herri = new Herria(
	                rs.getInt("idHerria"), 
	                rs.getString("izena"), 
	                rs.getInt("distantzia"),
	                rs.getDouble("tarifa")
	            );
	            herriak.add(herri);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return herriak;
	}
	
	public boolean herriaEzabatu(int idHerria) {
		boolean ongiEzabatuta = false;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String ezabatuKontsulta = "DELETE FROM herria WHERE idHerria = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idHerria);

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

	public static Herria herriaBilatu(int idHerria) {
		Herria herria = null;
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT * FROM herria WHERE idHerria = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idHerria);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int id = emaitza.getInt("idHerria");
	                    String izena = emaitza.getString("izena");
	                    int distantzia = emaitza.getInt("distantzia");
	                    double tarifa = emaitza.getDouble("tarifa");
	                    
	                    herria = new Herria(id, izena, distantzia, tarifa);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return herria;
	}

	public boolean eguneratuHerria(Herria herria) throws SQLException {
		
	    String sql = "UPDATE herria SET izena = ?, distantzia = ?, tarifa = ? WHERE idHerria = ?";
	    try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, this.getIzena());
	        stmt.setInt(2, this.getDistantzia());
	        stmt.setDouble(3, this.getTarifa());
	        stmt.setInt(4, this.getIdHerria());

	        return stmt.executeUpdate() > 0;
	    }
	}

	public boolean sortuHerria(Herria herria) {
		
		String sql = "INSERT INTO herria (izena, distantzia, tarifa) VALUES (?, ?, ?)";
		try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, herria.getIzena());
			stmt.setInt(2, herria.getDistantzia());
			stmt.setDouble(3, herria.getTarifa());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
