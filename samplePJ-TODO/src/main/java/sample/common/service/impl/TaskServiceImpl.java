package sample.common.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.common.exception.ForbiddenException;
import sample.common.exception.TaskNotFoundException;
import sample.common.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	private static final int MAX_PAGE_SIZE = 100;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Task> listOwn(String username, int page, int size) {
        int s = Math.min(Math.max(size, 1), MAX_PAGE_SIZE);
        int p = Math.max(page, 0);
        return taskMapper.findByUserId(username, p * s, s); 
    }
    
    @Transactional(readOnly = true)
    @Override
    public int countOwn(String username) { 
        return taskMapper.countByUserId(username); 
    }
    
    @Transactional(readOnly = true)
    @Override
    public Task getOwnTask(Long id, String username) {
        Task t = taskMapper.findById(id);
        if (t == null) throw new TaskNotFoundException(id);
        if (!t.getUsername().equals(username)) throw new ForbiddenException();
        return t;
    }
    
    @Transactional
    @Override
    public void createOwn(Task task, String username) {
        task.setUsername(username);
        taskMapper.insert(task);
    }
    
    @Transactional
    @Override
    public void updateOwn(Task task, String username) {
        getOwnTask(task.getId(), username);
        taskMapper.update(task);
    }
    
    @Transactional
    @Override
    public void deleteOwn(Long id, String username) {
        getOwnTask(id, username);
        taskMapper.delete(id);
    }
}