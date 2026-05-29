package sample.thymeleaf.web;

import java.sql.Date;
import sample.common.dao.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskForm {

    private Long id;
    
    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 100, message = "タイトルは100文字以内で入力してください")
    private String title;
    
    @Size(max = 1000, message = "内容は1000文字以内で入力してください")
    private String content;
    
    @Size(max = 50, message = "登録者は50文字以内で入力してください")
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