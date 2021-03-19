package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author Matiesi Darius
 *Clasa ConnectionFactory creeaza conexiunea in program intre baza de date si 
 *java
 */
public class ConnectionFactory 
{
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	/**
	 * Driverul necesar pentru conexiune
	 */
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	/**
	 * UML-ul bazei de date 
	 */
	private static final String DBURL = "jdbc:mysql://localhost:3306/warehousedb";
	/**
	 * Utilizatorul bazei de date
	 */
	private static final String USER = "root";
	/**
	 * Parola bazei de date
	 */
	private static final String PASS = "";
	/**
	 * instanta a ConnectionFactory
	 */
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	/**
	 * Constructorul clasei ConnectionFactory
	 */
	private ConnectionFactory() 
	{
		try 
		{
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Creeaza fizic conexiunea
	 * @return Conexiunea stabilita
	 */
	private Connection createConnection() 
	{
		Connection connection = null;
		try 
		{
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * Returneaza conexiunea mai mult creata 
	 * @return conexiunea
	 */
	public static Connection getConnection() 
	{
		return singleInstance.createConnection();
	}

	public static void close(Connection connection) 
	{
		if (connection != null) {
			try 
			{
				connection.close();
			} catch (SQLException e) 
			{
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}
	/**
	 * Opreste un statement din functiune
	 * @param statement Statementu-ul de oprit
	 */
	public static void close(Statement statement) 
	{
		if (statement != null) 
		{
			try 
			{
				statement.close();
			} catch (SQLException e) 
			{
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}
	/**
	 * Opreste conexiunea cu datele obtinute din tabel
	 * @param resultSet Datele din tabel
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e)
			{
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
}

