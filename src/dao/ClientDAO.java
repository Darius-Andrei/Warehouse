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
import model.Client;
import presentation.GenerarePDF;
/**
 * Clasa ClientDAO contine toate operatiile efectuate asupra
 * tabelului client al bazei de date asociata
 * @author Matiesi Darius
 *
 */
public class ClientDAO 
{
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	/**
	 *  Stringul reprezinta comanda din MySQL pentru inserare in tabelul client
	 */
	private static final String insertStatementString = "INSERT into client (idClient,numeClient,adresa)" + "values(?,?,?)";
	/**
	 * Stringul reprezinta comanda din MySQL pentru stergere in tabelul client
	 */
	private static final String deleteStatementString = "DELETE from client where numeClient=? and adresa=?";
	/**
	 * Stringul reprezinta comanda din MySQL pentru select in tabelul client
	 */
	private static final String selectStatementString = "SELECT numeClient,adresa from client";
	
	/**
	 * Se stabileste conexiunea si se face introducerea propriu zisa in tabelul client
	 * @param c Clientul de inserat
	 */
	public static void insert(Client c)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		
		try
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, c.getIdClient());
			insertStatement.setString(2, c.getNume());
			insertStatement.setString(3, c.getAdresa());
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
	 * Se stabileste conexiunea si se face stergerea fizica din tabelul client
	 * @param nume Numele clientului de sters
	 * @param adresa Adresa clientului de sters
	 */
	public static void delete(String nume, String adresa)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try
		{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, nume);
			deleteStatement.setString(2, adresa);
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
	 * Se stabileste conexiunea si se face selectul fizic pe tabelul clent
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
			pdf.ClientTablePDF(2, rst, y);
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
