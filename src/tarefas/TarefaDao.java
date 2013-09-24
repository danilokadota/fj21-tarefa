package tarefas;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

@Repository
public class TarefaDao {
	
	private final Connection connection;
	
	@Autowired
	public DataSource dataSource;
	
	public TarefaDao(){
		this.connection = null;
//		try{
//			this.connection = (Connection) dataSource.getConnection();
//		}catch(SQLException e){
//			throw new RuntimeException(e);
//		}
	}
	
	public void adiciona(Tarefa tarefa) {
		String sql = "insert into tarefa (descricao,finalizado,dataFinalizacao) values (?,?,?)";
			PreparedStatement stmt = null; 
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new java.sql.Date(Calendar.getInstance()
					.getTimeInMillis()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void remove(Tarefa tarefa) {
		String sql = "delete from tarefa where id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setLong(1, tarefa.getId());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Tarefa getTarefaById(Long id) {
		String sql = "select * from tarefa where id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			Tarefa tarefa = new Tarefa();

			while (rs.next()) {
				tarefa.setId(id);
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				if (rs.getDate("dataFinalizacao") != null) {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("dataFinalizacao"));
					tarefa.setDataFinalizacao(data);
				}
			}
			rs.close();
			stmt.close();
			return tarefa;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void altera(Tarefa tarefa) {
		String sql = "update tarefa set descricao=?,finalizado=?,dataFinalizacao=null  where id=?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setLong(3, tarefa.getId());
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void finaliza(Long id) {
		String sql = "update tarefa set finalizado = true, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setDate(1, new Date(new java.util.Date().getTime()));
			stmt.setLong(2, id);

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<Tarefa> getLista() {
		PreparedStatement stmt = null;
		try {
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			stmt = this.connection
					.prepareStatement("select * from tarefa");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				if (rs.getDate("dataFinalizacao") != null) {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("dataFinalizacao"));
					tarefa.setDataFinalizacao(data);
				}

				tarefas.add(tarefa);

			}
			rs.close();
			stmt.close();
			return tarefas;
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
