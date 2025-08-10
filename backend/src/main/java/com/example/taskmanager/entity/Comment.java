package com.example.taskmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name="task_id")
  private Task task;

  @ManyToOne
  @JoinColumn(name="author_id")
  private User author;

  public Comment() {}
  // getters & setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getContent(){return content;}
  public void setContent(String content){this.content=content;}
  public LocalDateTime getCreatedAt(){return createdAt;}
  public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
  public Task getTask(){return task;}
  public void setTask(Task task){this.task=task;}
  public User getAuthor(){return author;}
  public void setAuthor(User author){this.author=author;}
}
