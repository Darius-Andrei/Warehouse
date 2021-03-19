package model;
/**
 * Clasa Client_to_comanda reprezinta modelul folosit in tabelul
 * legatura_client_comanda al bazei de date asociate
 * @author Matiesi Darius
 *
 */
public class Client_to_comanda {
		/**
		 * Reprezinta id-ul comenzii- cheie primara in tabelul legatura_client_comanda
		 */
		private int idComanda;
		/**
		 * Reprezinta id-ul Clientului- cheie primara in tabelul legatura_client_comanda
		 */
		private int idClient;
		/**
		 * Constructorul clasei Client_to_comanda
		 * @param idComanda Id-ul comenzii 
		 * @param idClient Id-ul clientului
		 */
		public Client_to_comanda(int idComanda, int idClient)
		{
			this.idComanda = idComanda;
			this.idClient = idClient;
		}
		public int getIdComanda() {
			return idComanda;
		}

		public void setIdComanda(int idComanda) {
			this.idComanda = idComanda;
		}

		public int getIdClient() {
			return idClient;
		}

		public void setIdClient(int idClient) {
			this.idClient = idClient;
		}
}
