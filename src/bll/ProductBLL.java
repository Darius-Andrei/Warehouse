package bll;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import dao.ProductDAO;
import model.Product;
/**
 * 
 * @author Matiesi Darius
 *	Clasa ProductBLL mentine toate operatiile care incapsuleaza logica utilizarii
 * tabelului "produs" al bazei de date asociata
 */
public class ProductBLL 
{
	/** 
	 * @param p Produsul de inserat
	 * Cheama metoda "insertProduct" din clasa ProductDAO
	 */
	public void insertProduct(Product p)
	{
		ProductDAO.insert(p);
	}
	/**
	 * @param nume Numele produsului de sters
	 * Cheama metoda "deleteProduct" din clasa ProductDAO
	 */
	public void deleteProduct(String nume)
	{
		ProductDAO.delete(nume);
	}
	/**
	 * @param nume Numele produsului pentru update
	 * @param dif Cantitatea produsului pentru update
	 */
	public void updateProduct(String nume, int dif)
	{
		ProductDAO.update(nume, dif);
	}
	/**
	 * @param y Parametru folosit in formarea numelui PDF-ului
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * Cheama metoda "select" din clasa ProductDAO
	 */
	public void selectProduct(int y) throws FileNotFoundException, DocumentException
	{
		ProductDAO.select(y);
	}
}
