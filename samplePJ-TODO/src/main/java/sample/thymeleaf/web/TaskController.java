package sample.thymeleaf.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sample.common.dao.entity.Login;
import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskMapper taskMapper;
	
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
	public String edit(@PathVariable("id") Long id, Model model) {
		
		Task task = taskMapper.findById(id);
		
		model.addAttribute("task", task);
		
		return "tasks/form-edit";
	}
	
	@PostMapping("/update")
	public String update(Task task) {
	    taskMapper.update(task);
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
	public String delete(@PathVariable("id") Long id) {
		taskMapper.delete(id);
		return "redirect:/tasks";
	}
	
}
