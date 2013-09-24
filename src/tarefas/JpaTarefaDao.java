package tarefas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class JpaTarefaDao implements InterfaceTarefaDao{


	@PersistenceContext
	EntityManager manager;

	@Override
	public void adiciona (Tarefa tarefa){
		manager.persist(tarefa);
	}
	@Override
	public void altera (Tarefa tarefa){
		manager.persist(tarefa);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Tarefa> lista() {
		return manager.createQuery("select t from Tarefa t").getResultList();
	}
	@Override
	public Tarefa getTarefaById (Long id){
		return manager.find(Tarefa.class, id);
	}
	@Override
	public void remove (Tarefa tarefa){
		Tarefa tarefaRemove = getTarefaById(tarefa.getId());
		manager.remove(tarefaRemove);
	}
	@Override
	public void finaliza(Long id){
		Tarefa tarefa = getTarefaById(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
	}

}
