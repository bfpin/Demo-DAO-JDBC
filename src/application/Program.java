package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Dao.DaoFactory;
import model.Dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Departamento dpt = new Departamento (1, "Jamalbee");
		Vendedor vend = new Vendedor(21,"marcao","marcao@postin",new Date(),2000.00,dpt);
		
		VendedorDao venderDao = DaoFactory.createVendedorDao();
		
		System.out.println("=== Teste 1 - vendedor findById ===");
		Vendedor vendedor = venderDao.findById(2);
		System.out.println(vendedor);
		
		
		System.out.println("\n === Teste 2 - vendedor findByDepartament ===");
		Departamento departamento = new Departamento(2,null);
		List<Vendedor>lista = venderDao.findByDeartamento(departamento);
		for(Vendedor obj : lista) {
		System.out.println(obj);
		}
		
		
		System.out.println("\n === Teste 3 - vendedor findAll ===");
		lista = venderDao.findAll();
		for (Vendedor obj : lista) {
		System.out.println(obj);
		}
		
		System.out.println("\n === Teste 4 - vendedor insert  ===");
		Vendedor newVendedor = new Vendedor (null, "Marrone","maronim@gmail.com",new Date(),4000.00,departamento);
	    venderDao.insert(newVendedor);
	    System.out.println("Inserido ! Novo id =  "+ newVendedor.getId());
	    
	    System.out.println("\n === Teste 5- vendedor Update  ===");
		vend = venderDao.findById(2);
		vend.setName("Benedito");
		venderDao.insert(vend);
		
		System.out.println("Update Completo");
		
		
		
		 System.out.println("\n === Teste 6- vendedor Update  ===");
		System.out.println("Entre com o ID da linha a ser deletada: ");
		int id = sc.nextInt();
		venderDao.deleteById(id);
		System.out.println("Linha deletada !!");
		
		sc.close();
		
	}

}
