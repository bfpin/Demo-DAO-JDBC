package application;

import java.util.Date;

import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Departamento dpt = new Departamento (1, "Jamalbee");
		Vendedor vend = new Vendedor(21,"marcao","marcao@postin",new Date(),2000.00,dpt);
		
		System.out.println(vend);
	}

}
