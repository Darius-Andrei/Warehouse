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
import model.Order;
import presentation.GenerarePDF;
/**
 * Clasa OrderDAO contine toate operatiile efectuate asupra
 * tabelului comanda al bazei de date asociata
 * @author Matiesi Darius
 *
 */
public class OrderDAO 
{
	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	/**
	 * Stringul reprezinta comanda din MySQL pentru inserare in tabelul comanda
	 */
	private static final String insertStatementString = "INSERT into comanda (idComanda,numeClient,numeProdus,cantitate,pret)" + "values(?,?,?,?,?)";
	/**
	 * Stringul reprezinta comanda din MySQL pentru select in tabelul comanda
	 */
	private static final String selectStatementString = "SELECT numeClient,numeProdus,cantitate,pret from comanda";
	
	/**
	 * Se stabileste conexiunea si se face introducerea propriu zisa in tabelul comanda
	 * @param o Comanda pentru a fi introdusa
	 */
	public static void insert(Order o)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		
		try
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, o.getIdOrder());
			insertStatement.setString(2, o.getNumeClient());
			insertStatement.setString(3, o.getNumeProdus());
			insertStatement.setInt(4, o.getQuantity());
			insertStatement.setDouble(5, o.getPrice());
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
	 * Se stabileste conexiunea si se face selectarea fizica din tabelul comanda
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
			pdf.OrderTablePDF(4, rst, y);
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
