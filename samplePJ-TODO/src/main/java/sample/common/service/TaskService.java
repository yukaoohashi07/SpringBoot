package sample.common.service;

import java.util.List;
import sample.common.dao.entity.Task;
import sample.thymeleaf.web.TaskForm;

public interface TaskService {
	List<Task> listOwn(String username, int page, int size);
    int countOwn(String username);
    
    Task getOwnTask(Long id, String username);
    void createOwn(Task task, String username);
    void updateOwn(Task task, String username);
    void deleteOwn(Long id, String username);
}