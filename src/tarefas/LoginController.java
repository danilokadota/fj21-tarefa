package tarefas;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioDao dao;

	@RequestMapping("loginForm")
	public String loginForm() {
		return "tarefa/formulario-login";
	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		if (dao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "tarefa/menu";
		}
		return "redirect:loginForm";
	}

	@RequestMapping("novoCadastro")
	public String cadastro() {
		return "tarefa/cadastro";
	}

	@RequestMapping("cadastro")
	public String novoCadastro(Usuario usuario) {
		dao.adiciona(usuario);
		return "redirect:loginForm";
	}

}
