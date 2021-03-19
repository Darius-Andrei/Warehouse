package model;
/**
 * Clasa Product reprezinta modelul folosit in tabelul
 * produs al bazei de date asociate
 * @author Matiesi Darius
 *
 */
public class Product 
{
	/**
	 * Reprezinta id-ul produsului- cheie primara in tabelul produs
	 */
	private int idProdus;
	/**
	 * Reprezinta numele clientului
	 */
	private String nume;
	/**
	 * Reprezinta cantitatea produsului
	 */
	private int quantity;
	/**
	 * Reprezinta pretul produsului
	 */
	private double pret;
	/**
	 * Constructorul clasei Product
	 * @param nume numele produsului
	 * @param quantity cantitatea produsului
	 * @param pret reprezinta pretul produsului
	 * @param idProdus reprezinta id-ul produsului
	 */
	public Product(String nume, int quantity, double pret,int idProdus)
	{
		this.idProdus=idProdus;
		this.nume = nume;
		this.quantity = quantity;
		this.pret =pret ;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIdProdus() 
	{
		return idProdus;
	}

	public void setIdProdus(int idProdus) 
	{
		this.idProdus = idProdus;
	}

	public String getNume() 
	{
		return nume;
	}

	public void setNume(String nume) 
	{
		this.nume = nume;
	}

	public double getPret() 
	{
		return pret;
	}

	public void setPret(float pret) 
	{
		this.pret = pret;
	}
}
