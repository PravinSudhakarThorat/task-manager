package com.example.taskmanager.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String status;

  @ManyToMany
  @JoinTable(name="project_users",
    joinColumns = @JoinColumn(name="project_id"),
    inverseJoinColumns = @JoinColumn(name="user_id"))
  private Set<User> members;

  public Project() {}
  // getters & setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getName(){return name;}
  public void setName(String name){this.name=name;}
  public String getDescription(){return description;}
  public void setDescription(String description){this.description=description;}
  public String getStatus(){return status;}
  public void setStatus(String status){this.status=status;}
  public Set<User> getMembers(){return members;}
  public void setMembers(Set<User> members){this.members=members;}
}
