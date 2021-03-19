package model;

/**
  * Clasa Client reprezinta modelul folosit in tabelul
 * client al bazei de date asociate
 * @author Matiesi Darius
 *
 */
public class Client 
{
	/**
	 * Reprezinta id-ul clientului- cheie primara in tabelul client
	 */
	private int idClient;
	/**
	 * Reprezinta numele clientului
	 */
	private String nume;
	/**
	 * Reprezinta adresa clientului
	 */
	private String adresa;
	/**
	 * Constructorul clasei Client
	 * @param nume Numele clientului
	 * @param adresa Adresa clientului
	 * @param idClient Id-ul clientului
	 */
	public Client(String nume, String adresa, int idClient)
	{
		this.nume = nume;
		this.adresa = adresa;
		this.idClient=idClient;
	}

	public int getIdClient() 
	{
		return idClient;
	}

	public void setIdClient(int idClient) 
	{
		this.idClient = idClient;
	}

	public String getNume() 
	{
		return nume;
	}

	public void setNume(String nume) 
	{
		this.nume = nume;
	}

	public String getAdresa() 
	{
		return adresa;
	}

	public void setAdresa(String adresa) 
	{
		this.adresa = adresa;
	}
}
