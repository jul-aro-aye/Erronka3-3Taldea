package erronka3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Ekintza {
	private int idEkintza;
	private String izenburua;
	private String informazioa;
	private LocalDate data;
	private String ekintzaHerria;
	/**
	 * @return the idEkintza
	 */
	public int getIdEkintza() {
		return idEkintza;
	}
	/**
	 * @param idEkintza the idEkintza to set
	 */
	public void setIdEkintza(int idEkintza) {
		this.idEkintza = idEkintza;
	}
	/**
	 * @return the izenburua
	 */
	public String getIzenburua() {
		return izenburua;
	}
	/**
	 * @param izenburua the izenburua to set
	 */
	public void setIzenburua(String izenburua) {
		this.izenburua = izenburua;
	}
	/**
	 * @return the informazioa
	 */
	public String getInformazioa() {
		return informazioa;
	}
	/**
	 * @param informazioa the informazioa to set
	 */
	public void setInformazioa(String informazioa) {
		this.informazioa = informazioa;
	}
	/**
	 * @return the data
	 */
	public LocalDate getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	/**
	 * @return the ekintzaHerria
	 */
	public String getEkintzaHerria() {
		return ekintzaHerria;
	}
	/**
	 * @param ekintzaHerria the ekintzaHerria to set
	 */
	public void setEkintzaHerria(String ekintzaHerria) {
		this.ekintzaHerria = ekintzaHerria;
	}
	public Ekintza() {
	}
	
	public Ekintza(int idEkintza, String izenburua, String informazioa, LocalDate data, String ekintzaHerria) {
		this.idEkintza = idEkintza;
		this.izenburua = izenburua;
		this.informazioa = informazioa;
		this.data = data;
		this.ekintzaHerria = ekintzaHerria;
	}
	
	public List<Ekintza> ekintzakBistaratu() {
	    List<Ekintza> ekintzak = new ArrayList<>();
	    String sql = "SELECT idEkintza, izenburua, informazioa, data, h.izena FROM ekintza e INNER JOIN herria h ON h.idHerria = e.Herria_idHerria";
	    
	    try (Connection conn = Konexioa.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	        	Ekintza ekintza = new Ekintza(
	                    rs.getInt("idEkintza"),
	                    rs.getString("izenburua"),
	                    rs.getString("informazioa"),
	                    rs.getDate("data").toLocalDate(),
	                    rs.getString("h.izena")
	                );
	            ekintzak.add(ekintza);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return ekintzak;
	}
	
	public static Ekintza ekintzaBilatu(int idEkintza) {
		Ekintza ekintza = null;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String kontsultaErab = "SELECT idEkintza, izenburua, informazioa, data, h.izena FROM ekintza e "
					+ "INNER JOIN herria h ON h.idHerria = e.Herria_idHerria WHERE idEkintza = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(kontsultaErab)) {
				ps.setInt(1, idEkintza);

				try (ResultSet emaitza = ps.executeQuery()) {
					if (emaitza.next()) {
						int id = emaitza.getInt("idEkintza");
						String izenburua = emaitza.getString("izenburua");
						String informazioa = emaitza.getString("informazioa");
						LocalDate data = emaitza.getDate("data").toLocalDate();
						String herria = emaitza.getString("h.izena");

						ekintza = new Ekintza(id, izenburua, informazioa, data, herria);
					}
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arazoak datu basera konektattzean", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return ekintza;
	}
	public boolean ekintzaEzabatu(int idEkintza) {
		boolean ongiEzabatuta = false;
		
		try {
			Connection konexioadb = Konexioa.getConnection();
			String ezabatuKontsulta = "DELETE FROM ekintza WHERE idEkintza = ?";

			try (PreparedStatement ps = konexioadb.prepareStatement(ezabatuKontsulta)) {
				ps.setInt(1, idEkintza);

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
	
	public boolean sortuEkintza(Ekintza ekintza) throws SQLException {
		
		int idHerria = lortuIdHerria(Konexioa.getConnection(), ekintza.getEkintzaHerria());

		if (idHerria == -1) {
			System.out.println("Herria no encontrada");
			return false;
		}

		String sql = "INSERT INTO ekintza (izenburua, informazioa, data, Herria_idHerria) VALUES (?, ?, ?, ?)";

		try (Connection conn = Konexioa.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			java.sql.Date sqlDate = java.sql.Date.valueOf(ekintza.getData());

			stmt.setString(1, this.getIzenburua());
			stmt.setString(2, this.getInformazioa());
			stmt.setDate(3, sqlDate);
			stmt.setInt(4, idHerria);

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int lortuIdHerria(Connection conn, String nombreHerria) throws SQLException {
	    String sql = "SELECT idHerria FROM herria WHERE izena = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, nombreHerria);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("idHerria");
	        }
	    }
	    return -1;
	}

	public boolean eguneratuEkintza(Ekintza ekintza) {
		
		String updateQuery = "UPDATE ekintza SET "
                + "izenburua = ?, informazioa = ?, data = ?, "
                + "Herria_idHerria = (SELECT idHerria FROM herria WHERE izena = ?) "
                + "WHERE idEkintza = ?";

		try (Connection konexioadb = Konexioa.getConnection();
				PreparedStatement ps = konexioadb.prepareStatement(updateQuery)) {

			ps.setString(1, ekintza.getIzenburua());
			ps.setString(2, ekintza.getInformazioa());
			ps.setDate(3, java.sql.Date.valueOf(ekintza.getData()));
			ps.setString(4, ekintza.getEkintzaHerria());
			ps.setInt(5, ekintza.getIdEkintza());

			int updatedRows = ps.executeUpdate();
			return updatedRows > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

	}
	
}
