package model.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				Departamento dep = instaniateDepartamento(rs);
				Vendedor obj = instanciarVendedor(rs,dep);
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

	private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor obj = new Vendedor();
		obj.setId(rs.getInt("ID"));
		obj.setName(rs.getString("Nome"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("SalarioBase"));
		obj.setBirthDate(rs.getDate("Aniversario"));
		obj.setDepartamento(dep);
		return obj;
	}


	private Departamento instaniateDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartamentoID"));
		dep.setName(rs.getString("DepNome"));
		return dep;
	}


	@Override
	public List<Vendedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*,departamento.Nome as DepNome " + " FROM vendedor INNER JOIN departamento "
							+ " ON vendedor.DepartamentoID = departamento.ID "
							+ "ORDER BY Nome");
		
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			
			Map<Integer,Departamento>map = new HashMap<>();
			
			while(rs.next()) {
				Departamento dep = map.get(rs.getInt("DepartamentoID"));
				
				if(dep == null) {
				dep = instaniateDepartamento(rs);
				map.put(rs.getInt("DepartamentoID"), dep);
				
				
				}
				Vendedor obj = instanciarVendedor(rs,dep);
				lista.add(obj);
			}
			return lista;
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}


	@Override
	public List<Vendedor> findByDeartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*,departamento.Nome as DepNome " + " FROM vendedor INNER JOIN departamento "
							+ " ON vendedor.DepartamentoID = departamento.ID " + " WHERE DepartamentoID=? "
							+ "ORDER BY Nome");
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			
			Map<Integer,Departamento>map = new HashMap<>();
			
			while(rs.next()) {
				Departamento dep = map.get(rs.getInt("DepartamentoID"));
				
				if(dep == null) {
				dep = instaniateDepartamento(rs);
				map.put(rs.getInt("DepartamentoID"), dep);
				
				
				}
				Vendedor obj = instanciarVendedor(rs,dep);
				lista.add(obj);
			}
			return lista;
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
