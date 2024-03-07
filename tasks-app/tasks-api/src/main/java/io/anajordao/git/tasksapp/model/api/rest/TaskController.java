package io.anajordao.git.tasksapp.model.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.anajordao.git.tasksapp.model.entity.Task;
import io.anajordao.git.tasksapp.model.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private final TaskRepository repository;

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody @Valid Task task){
        return repository.save(task);
    }

    // READ
    @GetMapping
    public List<Task> readAllTasks(){
        return repository.findAll();
    }

    // READ BY ID
    @GetMapping("{id}")
    public Task readTaskById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task wasn't found!"));
    }

    // UPDATE
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable Integer id, @RequestBody @Valid Task updatedTask){
        repository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setGroup(updatedTask.getGroup());
            task.setLimitDate(updatedTask.getLimitDate());
            task.setGroup(updatedTask.getGroup());
            return repository.save(updatedTask);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task wasn't found!"));
    }

    // DELETE
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Integer id){
        repository.deleteById(id);
    }

}
