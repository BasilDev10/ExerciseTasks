package com.example.exercisecontroller.Controller;

import com.example.exercisecontroller.ApiResponse.ApiResponse;
import com.example.exercisecontroller.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    @GetMapping("/get/{title}")
    public Task getTask(@PathVariable String title) {
        for(Task task : tasks) {
            if(task.getTitle().equals(title)) return task;
        }
        return null;

    }
    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task) {
        // I know this wrong but I'm doing this because we still not using Entity Annotation
        task.setId(tasks.size()+1);
        tasks.add(task);
        return new ApiResponse("Added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        if(index > tasks.size()-1) return new ApiResponse("not found");
        tasks.set(index, task);
        return new ApiResponse("Updated successfully");
    }

    @PutMapping("/update-status/{index}")
    public ApiResponse updateTaskStatus(@PathVariable int index) {
        if(index > tasks.size()-1) return new ApiResponse("not found");

        Task task = tasks.get(index);
        if(task.getStatus().equals("Not Done")){
            task.setStatus("Done");
        }else new ApiResponse("Task Aleardy done");

        return new ApiResponse("Updated status successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        if(index > tasks.size()-1) return new ApiResponse("not found");
        tasks.remove(index);
        return new ApiResponse("Deleted successfully");
    }

}
