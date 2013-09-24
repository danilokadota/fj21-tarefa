package tarefas;

import java.util.List;

public interface InterfaceTarefaDao {
	Tarefa getTarefaById(Long id);
	List<Tarefa> lista();
	void adiciona(Tarefa t);
	void altera (Tarefa t);
	void remove (Tarefa t);
	void finaliza (Long id);
}
