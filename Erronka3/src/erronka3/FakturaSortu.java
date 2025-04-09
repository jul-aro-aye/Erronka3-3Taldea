package erronka3;

import java.io.FileOutputStream;
import java.sql.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class FakturaSortu {

	public static void sortuPDF(int eskaeraId) throws Exception {

        try (Connection conn = Konexioa.getConnection()) {

            PreparedStatement eskaeraStmt = conn.prepareStatement(
                "SELECT e.izena AS eskaeraIzena, e.data, b.izena, b.abizenak, b.telefonoa, b.emaila " +
                "FROM eskaera e " +
                "JOIN bezeroa b ON e.bezeroa_idBezeroa = b.idBezeroa " +
                "WHERE e.idEskaera = ?"
            );
            
            eskaeraStmt.setInt(1, eskaeraId);
            ResultSet eskaeraRS = eskaeraStmt.executeQuery();

            String bezeroIzena = "", bezeroAbizenak = "", telefonoa = "", emaila = "", eskaeraIzena = "";
            Date data = null;

            if (eskaeraRS.next()) {
                eskaeraIzena = eskaeraRS.getString("eskaeraIzena");
                data = eskaeraRS.getDate("data");
                bezeroIzena = eskaeraRS.getString("izena");
                bezeroAbizenak = eskaeraRS.getString("abizenak");
                telefonoa = eskaeraRS.getString("telefonoa");
                emaila = eskaeraRS.getString("emaila");
            }

            // PDF sortu
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("aurrekontua.pdf"));
            document.open();

            Paragraph title = new Paragraph("Aurrekontua", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            Paragraph enpresaInfo = new Paragraph("Hornitzailea: AeroPark Enpresa\n" +
                    "Helbidea: Oriamendi kalea, 9\n" +
                    "Telefonoa: 920 09 16 89\n" +
                    "Emaila: info@aeropark.com");
            document.add(enpresaInfo);
            document.add(Chunk.NEWLINE);

            Paragraph bezeroInfo = new Paragraph(
                    "Bezeroa: " + bezeroIzena + " " + bezeroAbizenak + "\n" +
                    "Telefonoa: " + telefonoa + "\n" +
                    "Emaila: " + emaila);
            document.add(bezeroInfo);
            document.add(Chunk.NEWLINE);

            Paragraph eskaeraInfo = new Paragraph(
                    "Eskaera izena: " + eskaeraIzena + "\n" +
                    "Data: " + data);
            document.add(eskaeraInfo);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(3);
            table.addCell("Barraka");
            table.addCell("Prezioa");
            table.addCell("Guztira");

            // Eskatutako barrakak
            PreparedStatement barrakakStmt = conn.prepareStatement(
                "SELECT b.izena, b.prezioa " +
                "FROM eskatu es " +
                "JOIN barraka b ON es.barraka_idBarraka = b.idBarraka " +
                "WHERE es.eskaera_idEskaera = ?"
            );
            barrakakStmt.setInt(1, eskaeraId);
            ResultSet barrakaRS = barrakakStmt.executeQuery();

            double subtotal = 0;

            while (barrakaRS.next()) {
                String barrakaIzena = barrakaRS.getString("izena");
                double prezioa = barrakaRS.getDouble("prezioa");

                table.addCell(barrakaIzena);
                table.addCell(prezioa + " EUR");
                table.addCell(prezioa + " EUR");

                subtotal += prezioa;
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            double iva = subtotal * 0.21;
            double total = subtotal + iva;

            Paragraph totals = new Paragraph(String.format(
                    "Guztira (BEZ gabe): %.2f EUR\nBEZa (21%%): %.2f EUR\nGuztira (BEZarekin): %.2f EUR",
                    subtotal, iva, total), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
            document.add(totals);
            document.add(Chunk.NEWLINE);

            Paragraph conditions = new Paragraph("Baldintzak: \n" +
                    "Aurrekontua 30 egunez egongo da indarrean.\n" +
                    "Ordainketa aurrekontua onartu eta 15 eguneko epean egin behar da.");
            document.add(conditions);
            document.add(Chunk.NEWLINE);

            Paragraph signature = new Paragraph("Bezeroaren sinadura: _______________________");
            document.add(signature);

            document.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
