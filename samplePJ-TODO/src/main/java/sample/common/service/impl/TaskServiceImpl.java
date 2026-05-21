package sample.common.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.common.exception.ForbiddenException;
import sample.common.exception.TaskNotFoundException;
import sample.common.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Task getOwnTask(Long id, String username) {
        Task t = taskMapper.findById(id);
        if (t == null) throw new TaskNotFoundException(id);
        if (!t.getUsername().equals(username)) throw new ForbiddenException();
        return t;
    }

    @Override
    @Transactional
    public void updateOwn(Task form, String username) {
        Task current = getOwnTask(form.getId(), username);
        current.setTitle(form.getTitle());
        current.setContent(form.getContent());
        current.setName(form.getName());
        current.setStartDate(form.getStartDate());
        current.setEndDate(form.getEndDate());
        taskMapper.update(current);
    }

    @Override
    @Transactional
    public void deleteOwn(Long id, String username) {
        getOwnTask(id, username);
        taskMapper.delete(id);
    }
}