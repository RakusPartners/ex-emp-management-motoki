package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
/**
 * 
 * @param model
 * @return "employee/list.html"へフォワード
 */
    @GetMapping("/showList")
    public String showList(Model model){
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }
/**
 * 
 * @param id
 * @param model
 * @param form
 * @return "employee/detail.html"へフォワード
 */
    @GetMapping("/showDetail")
    public String showDetail(String id,Model model,UpdateEmployeeForm form){
        int intId = (int)Integer.valueOf(id);
        Employee employee = employeeService.showDetail(intId);
        model.addAttribute("employee", employee);
        return "employee/detail";
    }
/**
 * 
 * @param form
 * @return "/employee/showList"へリダイレクト
 */
    @PostMapping("/update")
    public String update(@Validated UpdateEmployeeForm form,BindingResult result,Model model){
        if(result.hasErrors()){
            return showDetail((String)form.getId(), model, form);
        }
        Employee employee = employeeService.showDetail(Integer.valueOf(form.getId()));
        employee.setDependentsCount(Integer.valueOf(form.getDependentsCount()));
        employeeService.update(employee);
        return "redirect:/employee/showList";
    }
} 
