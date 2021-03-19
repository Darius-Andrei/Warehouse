package bll;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import dao.OrderDAO;
import model.Order;

/**
 * @author Matiesi Darius
 * Clasa OrderBLL mentine toate operatiile care incapsuleaza logica utilizarii
 * tabelului "comanda" al bazei de date asociata
 */
public class OrderBLL 
{
	/**
	 * @param o Comanda de introdus
	 * Cheama metoda "insertOrder" din clasa OrderDAO
	 */
	public void insertOrder(Order o)
	{
		OrderDAO.insert(o);
	}
	/**
	 * @param y Parametru folosit in formarea numelui PDF-ului
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * Cheama metoda "select" din clasa OrderDAO
	 */
	public void selectOrder(int y) throws FileNotFoundException, DocumentException
	{
		OrderDAO.select(y);
	}
}
