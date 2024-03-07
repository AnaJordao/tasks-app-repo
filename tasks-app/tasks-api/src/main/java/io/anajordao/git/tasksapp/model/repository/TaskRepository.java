package io.anajordao.git.tasksapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.anajordao.git.tasksapp.model.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    
}
