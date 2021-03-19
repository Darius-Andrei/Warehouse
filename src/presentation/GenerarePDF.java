package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Order;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * Clasa GenerarePDF reprezinta clasa corespunzatoare generarii 
 * PDF-urilor necesare pentru chitante, rapoarte si comenzi nereusite
 * 
 * @author Matiesi Darius
 *
 */
public class GenerarePDF {
		/**
		 * Crearea Facturii
		 * @param o Comanda reusita pentru factura
		 * @param y Parametru folosit in formarea numelui PDF-ului
		 * @throws FileNotFoundException
		 * @throws DocumentException
		 * 
		 */
		public void BillPDF(Order o, int y) throws FileNotFoundException, DocumentException
		{
			Document doc = new Document();
			PdfWriter.getInstance(doc,new FileOutputStream("Chitanta emisa pentru " + o.getNumeClient() + y + ".pdf"));
			doc.open();
			doc.add(new Paragraph("Tranzactie reusita!"));
			doc.add(new Paragraph("Produs achizitionat: " + o.getNumeProdus()));
			doc.add(new Paragraph("Cantitate achizitionata: " + o.getQuantity() + " buc"));
			doc.add(new Paragraph("Total de plata: " + o.getPrice() + " lei"));
			doc.add(new Paragraph("Va mai asteptam!"));
			doc.close();
		}
		/**
		 * Crearea unui PDF corespunzator erorii comenzii
		 * @param errMessage String-ul care corespunde 
		 * @param y Parametru folosit in formarea numelui PDF-ului
		 * @throws FileNotFoundException
		 * @throws DocumentException
		 */
		public void ErrorPDF(String errMessage, int y) throws FileNotFoundException, DocumentException
		{
			Document doc = new Document();
			PdfWriter.getInstance(doc,new FileOutputStream("Eroare comanda" + y + ".pdf"));
			doc.open();
			doc.add(new Paragraph("Comanda nu fost plasata!"));
			doc.add(new Paragraph(errMessage));
			doc.close();
		}
		/**
		 * Crearea unui PDF pentru comanda Raport client
		 * @param columns numarul de coloane ale tabelului in cauza
		 * @param rst continutul tabelui in cauza
		 * @param y Parametru folosit in formarea numelui PDF-ului
		 * @throws FileNotFoundException
		 * @throws DocumentException
		 * @throws SQLException
		 */
		public void ClientTablePDF(int columns,ResultSet rst, int y) throws FileNotFoundException, DocumentException, SQLException
		{
			Document doc = new Document();
			PdfWriter.getInstance(doc,new FileOutputStream("ReportClienti" + y + ".pdf"));
			doc.open();
			PdfPTable table = new PdfPTable(columns);
			table.setComplete(true);
			PdfPCell header = new PdfPCell(new Paragraph("Nume"));
			header.setBackgroundColor(BaseColor.RED);
			header.setBorderWidth(1);
			table.addCell(header);
			PdfPCell header2 = new PdfPCell(new Paragraph("Adresa"));
			header2.setBackgroundColor(BaseColor.RED);
			header2.setBorderWidth(1);
			table.addCell(header2);
			while(rst.next())
			{
				for(int i = 1; i <= columns; i++)
				{
					Object obj = rst.getObject(i);
					PdfPCell c1 = new PdfPCell(new Paragraph(obj.toString()));
					c1.setHorizontalAlignment(1);
					table.addCell(c1);
				}	
			}		
			doc.add(table);
			doc.close();
		}
		/**
		 * Crearea unui PDF pentru comanda Raport product
		 * @param columns numarul de coloane ale tabelului in cauza
		 * @param rst continutul tabelui in cauza
		 * @param y Parametru folosit in formarea numelui PDF-ului
		 * @throws FileNotFoundException
		 * @throws DocumentException
		 * @throws SQLException
		 */
		public void ProductTablePDF(int columns,ResultSet rst, int y) throws FileNotFoundException, DocumentException, SQLException
		{
			Document doc = new Document();
			PdfWriter.getInstance(doc,new FileOutputStream("ReportProduse" + y + ".pdf"));
			doc.open();
			PdfPTable table = new PdfPTable(columns);
			table.setComplete(true);
			PdfPCell header = new PdfPCell(new Paragraph("Produs"));
			header.setBackgroundColor(BaseColor.ORANGE);
			header.setBorderWidth(1);
			table.addCell(header);
			PdfPCell header2 = new PdfPCell(new Paragraph("Cantitate"));
			header2.setBackgroundColor(BaseColor.ORANGE);
			header2.setBorderWidth(1);
			table.addCell(header2);
			PdfPCell header3 = new PdfPCell(new Paragraph("Pret buc."));
			header3.setBackgroundColor(BaseColor.ORANGE);
			header3.setBorderWidth(1);
			table.addCell(header3);
			while(rst.next())
			{
				for(int i = 1; i <= columns; i++)
				{
					Object obj = rst.getObject(i);
					PdfPCell c1 = new PdfPCell(new Paragraph(obj.toString()));
					c1.setHorizontalAlignment(1);
					table.addCell(c1);
				}	
			}	
			doc.add(table);
			doc.close();
		}
		/**
		 * Crearea unui PDF pentru comanda Raport order
		 * @param columns numarul de coloane ale tabelului in cauza
		 * @param rst continutul tabelui in cauza
		 * @param y Parametru folosit in formarea numelui PDF-ului
		 * @throws FileNotFoundException
		 * @throws DocumentException
		 * @throws SQLException
		 */
		public void OrderTablePDF(int columns,ResultSet rst, int y) throws FileNotFoundException, DocumentException, SQLException
		{
			Document doc = new Document();
			PdfWriter.getInstance(doc,new FileOutputStream("ReportOrder" + y + ".pdf"));
			doc.open();
			PdfPTable table = new PdfPTable(columns);
			table.setComplete(true);
			PdfPCell header = new PdfPCell(new Paragraph("Nume Client"));
			header.setBackgroundColor(BaseColor.GREEN);
			header.setBorderWidth(1);
			table.addCell(header);
			PdfPCell header2 = new PdfPCell(new Paragraph("Produs"));
			header2.setBackgroundColor(BaseColor.GREEN);
			header2.setBorderWidth(1);
			table.addCell(header2);
			PdfPCell header3 = new PdfPCell(new Paragraph("Cantitate"));
			header3.setBackgroundColor(BaseColor.GREEN);
			header3.setBorderWidth(1);
			table.addCell(header3);
			PdfPCell header4 = new PdfPCell(new Paragraph("Pret total"));
			header4.setBackgroundColor(BaseColor.GREEN);
			header4.setBorderWidth(1);	
			table.addCell(header4);
			while(rst.next())
			{
				for(int i = 1; i <= columns; i++)
				{
					Object obj = rst.getObject(i);
					PdfPCell c1 = new PdfPCell(new Paragraph(obj.toString()));
					c1.setHorizontalAlignment(1);
					table.addCell(c1);
				}	
			}
			doc.add(table);
			doc.close();
		}
	}
