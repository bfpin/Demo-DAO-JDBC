package model.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.Dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao{

	private Connection conn;
	
	public VendedorDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
			"SELECT vendedor.*,departamento.Nome as DepNome "	
			+" FROM vendedor INNER JOIN departamento "		
			+" ON vendedor.DepartamentoID = departamento.ID "
			+" WHERE vendedor.ID=? "
					);
			st.setInt(1, id);
			rs = st.executeQuery();		
			if(rs.next()) {
				Departamento dep = new Departamento();
				dep.setId(rs.getInt("DepartamentoID"));
				dep.setName(rs.getString("DepNome"));
				Vendedor obj = new Vendedor();
				obj.setId(rs.getInt("ID"));
				obj.setName(rs.getString("Nome"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("SalarioBase"));
				obj.setBirthDate(rs.getDate("Aniversario"));
				obj.setDepartamento(dep);
				return obj;
			}
			return null;
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
