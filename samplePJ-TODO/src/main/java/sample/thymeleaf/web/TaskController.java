package sample.thymeleaf.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskMapper taskMapper;
	
	@GetMapping
	public String list(Model model) {
		List<Task> tasks = taskMapper.findAll();
		
		model.addAttribute("tasks" , tasks);
		
		return "tasks/list";
	}
}
