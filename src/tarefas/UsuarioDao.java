package tarefas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDao {
	private final Connection connection;

	public UsuarioDao() {
		ConnectionFactory dataSource = new ConnectionFactory();
		this.connection = dataSource.getConnection();
	}

	public boolean existeUsuario(Usuario usuario) {
		String sql = "select * from usuario where login = ? and senha = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			boolean existe = false;
			while (rs.next()) {
				existe = true;

			}
			rs.close();
			stmt.close();
			return existe;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	public void adiciona(Usuario usuario) {
		String sql = "insert into usuario (nome,login,senha) values (?,?,?)";
			PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getLogin());
			stmt.setString(3, usuario.getSenha());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
