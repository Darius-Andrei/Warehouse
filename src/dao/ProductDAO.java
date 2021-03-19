package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.itextpdf.text.DocumentException;

import connection.ConnectionFactory;
import model.Product;
import presentation.GenerarePDF;
/**
 * Clasa ProductDAO contine toate operatiile efectuate asupra
 * tabelului produs al bazei de date asociata
 * @author Matiesi Darius
 *
 */
public class ProductDAO 
{
	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
	/**
	 * Stringul reprezinta comanda din MySQL pentru inserare in tabelul produs
	 */
	private static final String insertStatementString = "INSERT into produs (idProdus,numeProdus,cantitate,pret)" + "values(?,?,?,?)";
	/**
	 * Stringul reprezinta comanda din MySQL pentru delete in tabelul produs
	 */
	private static final String deleteStatementString = "DELETE from produs where numeProdus=?";
	/**
	 * Stringul reprezinta update din MySQL pentru update in tabelul produs
	 */
	private static final String updateStatementString = "UPDATE produs SET cantitate=? where numeProdus=?";
	/**
	 * Stringul reprezinta select din MySQL pentru select in tabelul produs
	 */
	private static final String selectStatementString = "SELECT numeProdus,cantitate,pret from Produs";
	/**
	 * Se stabileste conexiunea si se face introducerea propriu zisa in tabelul produs
	 * @param p Produsul de introdus
	 */
	public static void insert(Product p)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		
		try
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, p.getIdProdus());
			insertStatement.setString(2, p.getNume());
			insertStatement.setInt(3, p.getQuantity());
			insertStatement.setDouble(4, p.getPret());
			insertStatement.executeUpdate();
			rs = insertStatement.getGeneratedKeys();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.close(rs);
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/**
	 * Se stabileste conexiunea si se face stergerea fizica din tabelul produs
	 * @param nume Numele produsului de sters
	 */
	public static void delete(String nume)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try
		{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, nume);
			deleteStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/**
	 * Se stabileste conexiunea si se face update fizica din tabelul produs
	 * @param nume Numele produsului pentru update
	 * @param dif Cantitatea produsului pentru update
	 */
	public static void update(String nume, int dif)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try
		{
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, dif);
			updateStatement.setString(2, nume);
			updateStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/**
	 * Se stabileste conexiunea si se face selectarea fizica din tabelul produs
	 * @param y Parametru folosit in formarea numelui PDF-ului
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void select(int y) throws FileNotFoundException, DocumentException
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rst = null;
		
		try
		{
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rst = selectStatement.executeQuery();
			GenerarePDF pdf = new GenerarePDF();
			pdf.ProductTablePDF(3, rst, y);
		}
		
	    catch (SQLException ex) 
		{
	    	System.err.println("SQLException: " + ex);
	    }
		finally
		{
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
