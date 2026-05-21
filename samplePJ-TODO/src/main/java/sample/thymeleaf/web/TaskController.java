package sample.thymeleaf.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sample.common.dao.entity.Login;
import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.common.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;
	private final TaskMapper taskMapper;
	
	public TaskController(TaskService taskService, TaskMapper taskMapper) {
	    this.taskService = taskService;
	    this.taskMapper = taskMapper;
	}
	
	@GetMapping
	public String list(Model model, HttpSession session) {
		Login user = (Login) session.getAttribute("loginUser");
		
		if(user == null) {
			return "redirect:/login";
		}
		
		System.out.println("ログイン中のユーザーID: " + user.getId());
		
		List<Task> tasks = taskMapper.findByUserId(user.getUsername());
		model.addAttribute("tasks" , tasks);
		
		return "tasks/list";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, HttpSession session, Model model) {
		Login user = currentUser(session);
		Task task = taskService.getOwnTask(id, user.getUsername());
		
		model.addAttribute("task", task);	
		return "tasks/form-edit";
	}
	
	@PostMapping("/update")
	public String update(@Validated TaskForm form, BindingResult br, HttpSession session) {
		if (br.hasErrors()) return "tasks/form-edit";
		Login user = currentUser(session);
		taskService.updateOwn(form.toEntity(), user.getUsername());
	    return "redirect:/tasks";
	}
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("task", new Task());
		return "tasks/form-new";
	}
	
	@PostMapping("/create")
	public String create(Task task, HttpSession session) {
		Login user = (Login)session.getAttribute("loginUser");
		task.setUsername(user.getUsername());
		taskMapper.insert(task);
		return "redirect:/tasks";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		Login user = currentUser(session);
	    taskService.deleteOwn(id, user.getUsername());
		return "redirect:/tasks";
	}
	
	private Login currentUser(HttpSession session) {
	    return (Login) session.getAttribute("loginUser");
	}
}
