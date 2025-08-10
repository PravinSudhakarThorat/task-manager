package com.example.taskmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  @Column(columnDefinition = "TEXT")
  private String description;
  private String status;
  private String priority;
  private LocalDateTime dueDate;

  @ManyToOne
  @JoinColumn(name="assignee_id")
  private User assignee;

  @ManyToOne
  @JoinColumn(name="project_id")
  private Project project;

  public Task() {}
  // getters & setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getTitle(){return title;}
  public void setTitle(String title){this.title=title;}
  public String getDescription(){return description;}
  public void setDescription(String description){this.description=description;}
  public String getStatus(){return status;}
  public void setStatus(String status){this.status=status;}
  public String getPriority(){return priority;}
  public void setPriority(String priority){this.priority=priority;}
  public LocalDateTime getDueDate(){return dueDate;}
  public void setDueDate(LocalDateTime dueDate){this.dueDate=dueDate;}
  public User getAssignee(){return assignee;}
  public void setAssignee(User assignee){this.assignee=assignee;}
  public Project getProject(){return project;}
  public void setProject(Project project){this.project=project;}
}
