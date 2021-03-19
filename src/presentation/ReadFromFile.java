package presentation;
import java.util.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*; 
/**
 * Clasa ReadFromFile corespunde citirii din fisier
 * @author Matiesi Darius 
 *
 */
public class ReadFromFile 
{
	/**
	 * Returneaza o lista de String-uri corespunzatoare liniilor din fisier
	 * @param fileName
	 * @return liniile corespunzatoare
	 */
	public List<String> readFileInList(String fileName)
	{
		List<String> lines = Collections.emptyList();
		try
		{
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return lines;
	}

}
