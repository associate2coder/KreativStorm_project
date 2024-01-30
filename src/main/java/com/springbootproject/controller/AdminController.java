package com.springbootproject.controller;

import com.springbootproject.object.Course;
import com.springbootproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/course")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("courseList", adminService.findAllCourses());
        return "admin/main";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("course", adminService.show(id));
        return "admin/show";
    }

    @GetMapping("new")
    public String newPersonForm(@ModelAttribute("course") Course course){
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("course") Course course){
        adminService.save(course);
        return "redirect:/admin/course";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("course", adminService.show(id));
        return "admin/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("course") Course course){
        adminService.save(course);
        return "redirect:/admin/course";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        adminService.delete(id);
        return "redirect:/admin/course";
    }
}
