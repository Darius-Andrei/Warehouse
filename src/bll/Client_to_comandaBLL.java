package bll;
import dao.Client_to_comandaDAO;

/**
 * Clasa Client_to_comandaBLL mentine toate operatiile care incapsuleaza logica utilizarii
 * tabelului legatura_client_comanda al bazei de date asociata
 * @author Matiesi Darius
 *
 */
public class Client_to_comandaBLL {
	/**
	 * @param idComanda  Id-ul comenzii corespunzatoare clientului
	 * @param idClient Id-ul clientului de inserat 
	 * Cheama metoda "insertLink" din clasa Client_to_comandaDAO
	 */
		public void insertLink(int idComanda, int idClient)
		{
			Client_to_comandaDAO.insertLink(idComanda, idClient);
		}
		/**
		 * @param idClient Id-ul clientului de sters
		 * Cheama metoda "deleteLink" din clasa Client_to_comandaDAO
		 */
		public void deleteLink(int idClient)
		{
			Client_to_comandaDAO.deleteLink(idClient);
		}
}
