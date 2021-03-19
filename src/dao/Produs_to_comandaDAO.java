package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import connection.ConnectionFactory;
/**
 *Clasa Produs_to_comandaDAO contine toate operatiile efectuate asupra
 * tabelului legatura_produs_comanda al bazei de date asociata
 * @author Matiesi Darius
 *
 */
public class Produs_to_comandaDAO {
		protected static final Logger LOGGER = Logger.getLogger(Produs_to_comandaDAO.class.getName());
		/**
		 *  Stringul reprezinta comanda din MySQL pentru inserare in tabelul legatura_produs_comanda
		 */
		private static final String insertStatementString = "INSERT into legatura_produs_comanda (idComanda,idProdus)" + "values(?,?)";
		/**
		 *  Stringul reprezinta comanda din MySQL pentru stergere in tabelul legatura_produs_comanda
		 */
		private static final String deleteStatementString = "DELETE from legatura_produs_comanda where idProdus=?";
		/**
		 * Se stabileste conexiunea si se face introducerea propriu zisa in tabelul legatura_produs_comanda
		 * @param idComanda  Id-ul comenzii de introdus in tabel
		 * @param idProdus	Id-ul produsului de introdus in tabel
		 */
		public static void insertLink2(int idComanda, int idProdus)
		{
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement insertStatement = null;
			ResultSet rs = null;
			
			try
			{
				insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
				insertStatement.setInt(1, idComanda);
				insertStatement.setInt(2, idProdus);
				insertStatement.executeUpdate();
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
		 * Se preia conexiunea si se face stergerea propriu-zisa din tabelul legatura_produs_comanda
		 * @param idProdus Id-ul produsului de sters
		 */
		public static void deleteLink2(int idProdus)
		{
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement deleteStatement = null;
			
			try
			{
				deleteStatement = dbConnection.prepareStatement(deleteStatementString);
				deleteStatement.setInt(1, idProdus);
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
	}
