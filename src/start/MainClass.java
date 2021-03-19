package start;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import com.itextpdf.text.DocumentException;
import bll.ClientBLL;
import bll.Client_to_comandaBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import bll.Produs_to_comandaBLL;
import model.Client;
import model.Client_to_comanda;
import model.Order;
import model.Product;
import model.Produs_to_comanda;
import presentation.GenerarePDF;
import presentation.ReadFromFile;
/**
 * In aceasta clasa se face separarea comenzilor si
 * interpretarea acestora, apelarea functiilor de generare a PDF-urilor
 * @author Matiesi Darius
 * 
 */
public class MainClass 
{	
	protected static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
	/**
	 * Variabila instanta folosita in formarea numelui PDF-ului
	 */
	public static int y=0;
	/**
	 * Variabila instanta folosita in formarea PDF-urilor corespunzatoare 
	 * Raport client
	 */
	public static int contor1=1;
	/**
	 * Variabila instanta folosita in formarea PDF-urilor corespunzatoare 
	 * Raport produs
	 */
	public static int contor2=1;
	/**
	 * Variabila instanta folosita in formarea PDF-urilor corespunzatoare
	 * Raport order
	 */
	public static int contor3=1;
	/**
	 * Variabila instanta folosita pentru citirea din fisier
	 */
	public static FileWriter fw;
	/**
	 * Variabila instanta folosita apelarea metodelor corespunzatoare 
	 * crearii PDF-ului
	 */
	public static GenerarePDF pdf;
	/**
	 * Creeaza PDF-urile corespunzatoare si populeaza tabele dupa cerinta
	 * @param args argumentele main-ului
	 * @throws SQLException
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws SQLException, IOException, DocumentException
	{		
		ArrayList<Client> persoane = new ArrayList<Client>();
		ArrayList<Client_to_comanda> ccObj = new ArrayList<Client_to_comanda>();
		ArrayList<Produs_to_comanda> pcObj = new ArrayList<Produs_to_comanda>();
		ArrayList<Order> orderObj = new ArrayList<Order>();
		ArrayList<Product> warehouse =new ArrayList<Product>();
		
		ReadFromFile rf = new ReadFromFile();
		fw = new FileWriter(args[0],true);
		Iterator<String> it = rf.readFileInList(args[0]).iterator();//modificare aici
		String[] split = new String[100];
		int i = 0;
		while(it.hasNext())
		{
			for(String s: it.next().split(": "))
			{
				split[i++] = s;
			}
		}
		for(int j = 0; j < i; j++)
		{
			if(split[j].equals("Report client"))
			{
				ClientBLL cbll = new ClientBLL();
				cbll.selectClient(y);
				y++;
			}
			if(split[j].equals("Report product"))
			{
				ProductBLL pbll = new ProductBLL();
				pbll.selectProduct(y);
				y++;
			}
			if(split[j].equals("Report order"))
			{
				OrderBLL obll = new OrderBLL();
				obll.selectOrder(y);
				y++;
			}
			
			if(split[j].equals("Insert client"))
			{
				String[] split2 = new String[5];
				int k = 0;
				for(String sp2: split[j+1].split(":"))
				{
					for(String sp3: sp2.split(", "))
					{
						split2[k++] = sp3;
					}
				}
				Client c = new Client(split2[0],split2[1],contor1);
				ClientBLL cbll = new ClientBLL();
				cbll.insertClient(c);
				contor1++;
				persoane.add(c);
			}
			
			if(split[j].equals("Delete client"))
			{				
				Client_to_comandaBLL ccbll = new Client_to_comandaBLL();
				String[] split2 = new String[5];
				int k = 0;
				for(String sp2: split[j+1].split(":"))
				{
					for(String sp3: sp2.split(", "))
					{
						split2[k++] = sp3;
					}
				}
				ClientBLL cbll = new ClientBLL();
				Iterator<Client> it2 = persoane.iterator();
				int idToDelete = 0;
				for(Client c : persoane)
				{
					if(c.getNume().equals(split2[0]))
					{
						idToDelete = c.getIdClient();
					}
				}
				while(it2.hasNext())
				{
					if(it2.next().getNume().equals(split2[0]))
					{
						it2.remove();
					}
				}
				Iterator<Client_to_comanda> it3 = ccObj.iterator();
				while(it3.hasNext())
				{
					if(it3.next().getIdClient() == idToDelete)
					{
						it3.remove();
					}
				}
				ccbll.deleteLink(idToDelete);
				cbll.deleteClient(split2[0],split2[1]);
			}
			
			if(split[j].equals("Insert product"))
			{				
				String[] split2 = new String[5];
				int k = 0;
				for(String sp2: split[j+1].split(":"))
				{
					for(String sp3: sp2.split(", "))
					{
						split2[k++] = sp3;
					}
				}
				Product p = new Product(split2[0],Integer.parseInt(split2[1]),Double.parseDouble(split2[2]),contor2);
				ProductBLL pbll = new ProductBLL();
				int valid = 0;
				for(Product pc : warehouse)
				{
					if(p.getNume().equals(pc.getNume()))
					{
						pc.setQuantity(pc.getQuantity() + p.getQuantity());
						pbll.updateProduct(pc.getNume(), pc.getQuantity());
						valid = 1;
						break;
					}
				}
				
				if(valid == 0)
				{
					contor2++;
					warehouse.add(p);
					pbll.insertProduct(p);
				}
			}
			
			if(split[j].equals("Delete Product"))
			{				
				Produs_to_comandaBLL l2bll = new Produs_to_comandaBLL();
				String[] split2 = new String[5];
				int k = 0;
				for(String sp2: split[j+1].split(":"))
				{
					for(String sp3: sp2.split(", "))
					{
						split2[k++] = sp3;
					}
				}
				ProductBLL pbll = new ProductBLL();
				int prodToDelete = 0;
				for(Product p : warehouse)
				{
					if(p.getNume().equals(split2[0]))
					{
						prodToDelete = p.getIdProdus();
					}
				}
				Iterator<Product> it2 = warehouse.iterator();
				while(it2.hasNext())
				{
					if(it2.next().getNume().equals(split2[0]))
					{
						it2.remove();
					}
				}
				Iterator<Produs_to_comanda> it3 = pcObj.iterator();
				while(it3.hasNext())
				{
					if(it3.next().getIdProdus() == prodToDelete)
					{
						it3.remove();
					}
				}
				l2bll.deleteLink2(prodToDelete);
				pbll.deleteProduct(split2[0]);
			}
			
			if(split[j].equals("Order"))
			{				
				String[] split2 = new String[5];
				int k = 0;
				for(String sp2: split[j+1].split(", "))
				{
					split2[k++] = sp2;
				}
				Order order = new Order(split2[0],split2[1],Integer.parseInt(split2[2]),0,contor3);
				OrderBLL obll = new OrderBLL();
				ProductBLL pbll = new ProductBLL();
				int valid = -1;
				Client_to_comandaBLL ccbll = new Client_to_comandaBLL();
				Produs_to_comandaBLL pcbll = new Produs_to_comandaBLL();
				for(Client c: persoane)
				{
					if(c.getNume().equals(split2[0]))
					{
						for(Product prod: warehouse)
						{
							if(prod.getNume().equals(split2[1]))
							{
								if(prod.getQuantity() >= Integer.parseInt(split2[2]))
								{
									order.setPrice(order.getQuantity() * prod.getPret());
									pbll.updateProduct(order.getNumeProdus(), prod.getQuantity() - order.getQuantity());
									prod.setQuantity(prod.getQuantity() - order.getQuantity());
									obll.insertOrder(order);
									orderObj.add(order);
									ccbll.insertLink(contor3, c.getIdClient());
									Client_to_comanda linkAdd = new Client_to_comanda(contor3,c.getIdClient());
									ccObj.add(linkAdd);
									pcbll.insertLink2(contor3, prod.getIdProdus());
									Produs_to_comanda link2Add = new Produs_to_comanda(contor3,prod.getIdProdus());
									pcObj.add(link2Add);
									contor3++;
									valid = 0;
									pdf = new GenerarePDF();
									pdf.BillPDF(order,y);
									y++;
									break;
								}
								valid = 2;
							}
							if(valid == -1)
							{
								valid = 1;
							}
						}
					}
					if(valid == 0)
					{
						break;
					}
					if(valid != 2 && valid != 1)
					{
						valid = 3;
					}
				}
				if(valid == 1)
				{
					pdf = new GenerarePDF();
					pdf.ErrorPDF("Produsul nu este disponibil",y);
					y++;
				}
				if(valid == 2 || warehouse.isEmpty() == true)
				{
					pdf = new GenerarePDF();
					pdf.ErrorPDF("Cantitate insuficienta",y);
					y++;
				}
				if(valid == 3 || persoane.isEmpty() == true)
				{
					pdf = new GenerarePDF();
					pdf.ErrorPDF("Persoana invalida!",y);
					y++;
				}
			}
		}
	}
}