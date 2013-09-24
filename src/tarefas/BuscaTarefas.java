package tarefas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BuscaTarefas {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefa");
		EntityManager manager = factory.createEntityManager();
		
		Query query = manager.createQuery("select t from Tarefa as t where t.finalizado = :paramFinalizado");
		query.setParameter("paramFinalizado", true);
		
		@SuppressWarnings("unchecked")
		List<Tarefa> lista = query.getResultList();
		
		for(Tarefa t : lista){
			System.out.println(t.getDescricao());
		}
		manager.close();
	}

}
