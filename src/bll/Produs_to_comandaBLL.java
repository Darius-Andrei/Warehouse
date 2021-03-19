package bll;
import dao.Produs_to_comandaDAO;
/**
 * Clasa Produs_to_comandaBLL mentine toate operatiile care incapsuleaza logica utilizarii
 * tabelului legatura_produs_comanda al bazei de date asociata
 * @author Matiesi Darius
 *
 */
public class Produs_to_comandaBLL {
	
		/**
		 * @param idComanda  Id-ul comenzii corespunzatoare clientului
		 * @param idProdus Id-ul produsului de inserat 
		 * Cheama metoda "insertLink" din clasa Client_to_comandaDAO
		 */
		public void insertLink2(int idComanda, int idProdus)
		{
			Produs_to_comandaDAO.insertLink2(idComanda, idProdus);
		}
		
		/**
		 * @param idClient Id-ul clientului de sters
		 * Cheama metoda "deleteLink" din clasa Client_to_comandaDAO
		 */	
		public void deleteLink2(int idProdus)
		{
			Produs_to_comandaDAO.deleteLink2(idProdus);
		}

}
