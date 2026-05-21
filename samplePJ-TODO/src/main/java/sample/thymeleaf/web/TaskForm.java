package sample.thymeleaf.web;

import java.sql.Date;
import sample.common.dao.entity.Task;

public class TaskForm {

    private Long id;
    private String title;
    private String content;
    private String name;
    private Date startDate;
    private Date endDate;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Task toEntity() {
        Task task = new Task();
        task.setId(this.id);
        task.setTitle(this.title);
        task.setContent(this.content);
        task.setName(this.name);
        task.setStartDate(this.startDate);
        task.setEndDate(this.endDate);
        return task;
    }
}