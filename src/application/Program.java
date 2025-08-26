package application;

import java.util.Date;

import model.Dao.DaoFactory;
import model.Dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Departamento dpt = new Departamento (1, "Jamalbee");
		Vendedor vend = new Vendedor(21,"marcao","marcao@postin",new Date(),2000.00,dpt);
		
		VendedorDao venderDao = DaoFactory.createVendedorDao();
		
		Vendedor vendedor = venderDao.findById(2);
		
		
		System.out.println(vendedor);
	}

}
