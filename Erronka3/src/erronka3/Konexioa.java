package erronka3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Konexioa {
//    private static final String URL = "jdbc:mysql://localhost:3306/3erronka";
//    private static final String ERABILTZAILEA = "root";
//    private static final String PASAHITZA = "1MG2024";
    
    private static final String URL = "jdbc:mysql://172.16.237.211:3306/3erronka";
    private static final String ERABILTZAILEA = "administratzailea";
    private static final String PASAHITZA = "1MG3@2024";

    private Konexioa() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, ERABILTZAILEA, PASAHITZA);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Arazoak datu basearekin konexioa egitean.", "Errorea",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }
}
