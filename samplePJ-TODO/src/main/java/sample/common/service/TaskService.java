package sample.common.service;

import sample.common.dao.entity.Task;

public interface TaskService {
    Task getOwnTask(Long id, String username);
    void updateOwn(Task form, String username);
    void deleteOwn(Long id, String username);
}