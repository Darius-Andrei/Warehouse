package model;

/**
 * Clasa Order reprezinta modelul folosit in tabelul
* comanda al bazei de date asociate
* @author Matiesi Darius
*
*/
public class Order 
{
	/**
	* Reprezinta id-ul comenzii- cheie primara in tabelul comanda
	 */
	private int idOrder;
	/**
	* Reprezinta numele clientului implicat in comanda
	 */
	private String numeClient;
	/**
	 * Reprezinta numele produsului comandat
	 */
	private String numeProdus;
	/**
	 * Reprezinta cantitatea produsului comandat
	 */
	private int quantity;
	/**
	 * Reprezinta pretul produsului comandat
	 */
	private double price;

	/**
	 * Constructorul clasei Order
	 * @param numeClient Numele clientului din comanda
	 * @param numeProdus Numele produsului din comanda
	 * @param quantity Cantitatea de produs comandata
	 * @param price Pretul final al comenzii
	 * @param idOrder Id-ul comenzii 
	 */
	public Order( String numeClient, String numeProdus, int quantity, double price,int idOrder)
	{
		this.numeClient = numeClient;
		this.numeProdus = numeProdus;
		this.quantity = quantity;
		this.price = 0;
		this.idOrder= idOrder;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getIdOrder() 
	{
		return idOrder;
	}

	public void setIdOrder(int idOrder) 
	{
		this.idOrder = idOrder;
	}

	public String getNumeClient() 
	{
		return numeClient;
	}

	public void setNumeClient(String numeClient) 
	{
		this.numeClient = numeClient;
	}

	public String getNumeProdus() 
	{
		return numeProdus;
	}

	public void setNumeProdus(String numeProdus) 
	{
		this.numeProdus = numeProdus;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
}
