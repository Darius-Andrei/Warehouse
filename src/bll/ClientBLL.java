package bll;

import java.io.FileNotFoundException;
import com.itextpdf.text.DocumentException;

import dao.ClientDAO;
import model.Client;
/**
 * 
 * @author Matiesi Darius
 *  Clasa ClientBLL mentine toate operatiile care incapsuleaza logica utilizarii
 * tabelului Client al bazei de date asociata
 */
public class ClientBLL 
{
	/**
	 * @param c Clientul care trebuie inserat
	 * Cheama metoda "insertClient" din clasa ClientDAO
	 */
	public void insertClient(Client c)
	{
		ClientDAO.insert(c);
	}
	/**
	 * @param nume Numele clientului de sters
	 * @param adresa Adresa clientului de sters
	 * Cheama metoda "deleteClient" din clasa ClientDAO
	 */
	public void deleteClient(String nume, String adresa)
	{
		ClientDAO.delete(nume, adresa);
	}
	/**
	 * @param y Parametru folosit in formarea numelui PDF-ului
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void selectClient(int y) throws FileNotFoundException, DocumentException
	{
		ClientDAO.select(y);
	}
}
