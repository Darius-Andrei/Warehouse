package model;
/**
 * Clasa Produs_to_comanda reprezinta modelul folosit in tabelul
 * legatura_produs_comanda al bazei de date asociate
 * @author Matiesi Darius
 *
 */
public class Produs_to_comanda {
	/**
	 * Reprezinta id-ul comenzii- cheie primara in tabelul legatura_produs_comanda
	 */
		private int idComanda;
		/**
	 	* Reprezinta id-ul produsului - cheie primara in tabelul legatura_produs_comanda
		*/
		private int idProdus;
		/**
		 * Constructorul clasei Produs_to_comanda
		 * @param idComanda Id-ul comenzii
		 * @param idProdus Id-ul produsului
		 */
		public  Produs_to_comanda(int idComanda, int idProdus)
		{
			this.idComanda = idComanda;
			this.idProdus = idProdus;
		}
		
		public int getIdComanda() {
			return idComanda;
		}

		public void setIdComanda(int idComanda) {
			this.idComanda = idComanda;
		}

		public int getIdProdus() {
			return idProdus;
		}

		public void setIdProdus(int idProdus) {
			this.idProdus = idProdus;
		}
}
