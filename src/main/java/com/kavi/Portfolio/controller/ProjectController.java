package com.kavi.Portfolio.controller;

import com.kavi.Portfolio.model.Project;
import com.kavi.Portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Controller
@RequestMapping("/projects")  // Base path for all project operations
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // **READ**: Display all projects
    @GetMapping
    public String showProjects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projects";  // Thymeleaf template (projects.html)
    }

    // **CREATE**: Show form to add a new project
    @GetMapping("/add")
    public String showAddProjectForm(Model model) {
        model.addAttribute("project", new Project());  // Empty project object for form binding
        return "add_project";  // Thymeleaf template for adding a project
    }

    // **CREATE**: Handle form submission to add project
    @PostMapping("/add")
    public String addProject(@ModelAttribute Project project) {
        projectRepository.save(project);
        return "redirect:/projects";  // Redirect back to projects list
    }

    // **UPDATE**: Show form to edit an existing project
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID: " + id));
        model.addAttribute("project", project);
        return "edit_project";  // Thymeleaf template for editing a project
    }

    // **UPDATE**: Handle form submission to update project
    @PostMapping("/edit/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute Project updatedProject) {
        updatedProject.setId(id);  // Preserve ID
        projectRepository.save(updatedProject);
        return "redirect:/projects";  // Redirect back to projects list
    }

    // **DELETE**: Delete a project
    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "redirect:/projects";  // Redirect back to projects list
    }
}
