package tarefas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {
	@RequestMapping("/ok")
	public String execute(){
		System.out.println("Executando a lógica com spring MVC");
		return "ok";
	}
}
