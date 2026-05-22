package sample.common.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {
	List<Task> findByUserId(@Param("username") String username, 
            				@Param("offset") int offset, 
            				@Param("limit") int limit);
	int countByUserId(String username);
	void insert(Task task);
	Task findById(Long id);
	void update(Task task);
	void delete(Long id);
}
