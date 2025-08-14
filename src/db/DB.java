package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	// Conexao com o banco de dados
	private static Connection conn = null;

	public static Connection getConnection() {
		// se o conn estiver nulo realizar metodo para conectar

		if (conn == null) {
			try {
				// metodo carregar propriedades
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

		return conn;
	}

	// fechar conexão
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

// Finalidade do metodo: abrir o arquivo db.properties pegar os dados e guardar em um objeto Properties
	private static Properties loadProperties() {
		// abrir o arquivo e transformar os dados em Stream
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			// Objeto Properties
			Properties props = new Properties();
			// alocação dos dados do arquivo no objeto
			props.load(fs);
			return props;
		} catch (IOException e) {
			// Excelção criada no projeto
			throw new DbException(e.getMessage());
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {

				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				throw new DbException(e.getMessage());
			}
		}
	}

}
