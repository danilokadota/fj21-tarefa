package tarefas;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaTarefa {

	public static void main(String[] args) {
	Tarefa tarefa = new Tarefa();
	tarefa.setDescricao("Estudar JPa e Hibernate");
	tarefa.setFinalizado(true);
	tarefa.setDataFinalizacao(Calendar.getInstance());
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefa");
	EntityManager manager = factory.createEntityManager();
	
	manager.getTransaction().begin();
	manager.persist(tarefa);
	manager.getTransaction().commit();
	
	System.out.println("ID da tarefa: " + tarefa.getId());
	
	manager.close();
	}

}
