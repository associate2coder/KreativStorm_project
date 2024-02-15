package com.springbootproject.controller;

import com.springbootproject.object.Course;
import com.springbootproject.service.AdminService;
import com.springbootproject.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/course")
public class AdminController {
    private final AdminService adminService;
    private final TeacherService teacherService;

    @Autowired
    public AdminController(AdminService adminService, TeacherService teacherService) {
        this.adminService = adminService;
        this.teacherService = teacherService;
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
    public String newPersonForm(Model model){
        model.addAttribute("course", new Course());
        model.addAttribute("teacherList", teacherService.getAllTeachers());
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
        model.addAttribute("teacherList", teacherService.getAllTeachers());
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
