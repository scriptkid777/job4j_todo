package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.task.TaskService;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService hibernateTaskService;

    @GetMapping("/create")
    public String create() {
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task) {
        hibernateTaskService.create(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable("id") Integer id) {
        var taskOptional = hibernateTaskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задание не найдено");
            return "errors/error";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("/{id}/update")
    public String update(Model model, @PathVariable("id") Integer id) {
        var taskOptional = hibernateTaskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задание не найдено");
            return "errors/error";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        try {
            boolean isUpdated = hibernateTaskService.update(task);
            if (!isUpdated) {
                model.addAttribute("message", "Не удалось обновить задачу");
                return "errors/error";
            }
            return "redirect:/tasks";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/error";
        }
    }

    @GetMapping("/complete/{id}")
    public String done(Model model, @PathVariable("id") Integer id) {
        var taskOptional = hibernateTaskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задание не найдено");
            return "errors/error";
        }
        hibernateTaskService.complete(taskOptional.get());
        return "redirect:/tasks";
    }


    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        if (hibernateTaskService.findById(id).isEmpty()) {
            model.addAttribute("message", "Задание не найдено");
            return "errors/error";
        }
        hibernateTaskService.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tasks", hibernateTaskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/completed")
    public String getCompleted(Model model) {
        model.addAttribute("tasks", hibernateTaskService.findCompleted());
        return "tasks/list";
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        model.addAttribute("tasks", hibernateTaskService.findNew());
        return "tasks/list";
    }

}
