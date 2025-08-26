package model.Dao;

import db.DB;
import model.Dao.impl.VendedorDaoJDBC;

public class DaoFactory {

	public static VendedorDao createVendedorDao() {
		return new VendedorDaoJDBC(DB.getConnection());
	};
}
