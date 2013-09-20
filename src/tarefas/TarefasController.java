package tarefas;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TarefasController {
	
	@Autowired
	private TarefaDao dao;

	@RequestMapping("/novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("/adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		dao.adiciona(tarefa);
		return "tarefa/adiciona";
	}

	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		model.addAttribute("tarefas", dao.getLista());
		return "tarefa/lista";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}

	@RequestMapping("recuperarTarefa")
	public String recuperar(Model model, Tarefa tarefa) {
		model.addAttribute("tarefa", dao.getTarefaById(tarefa.getId()));
		return "tarefa/alterarTarefa";
	}

	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}

	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) {
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.getTarefaById(id));
		return "tarefa/lista";
	}
}
