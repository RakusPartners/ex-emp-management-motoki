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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Employee;
import com.example.form.FindForm;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import com.example.util.formatDateAndString;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private HttpSession session;
/**
 * 
 * @param model
 * @return "employee/list.html"へフォワード
 */
    @GetMapping("/showList")
    public String showList(Model model,RedirectAttributes redirectAttributes){
        if(session.getAttribute("administratorName")==null){
            redirectAttributes.addFlashAttribute("message", "ログインされていません。ログインしてからもう一度お試しください。");
            return "redirect:/";
        }
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);
        FindForm form = new FindForm();
        model.addAttribute("findForm", form);
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
    public String showDetail(String id,Model model,UpdateEmployeeForm form,RedirectAttributes redirectAttributes){
        if(session.getAttribute("administratorName")==null){
            redirectAttributes.addFlashAttribute("message", "ログインされていません。ログインしてからもう一度お試しください。");
            return "redirect:/";
        }
        
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
    public String update(@Validated UpdateEmployeeForm form,BindingResult result,Model model,RedirectAttributes redirectAttributes){
        if(session.getAttribute("administratorName")==null){
            redirectAttributes.addFlashAttribute("message", "ログインされていません。ログインしてからもう一度お試しください。");
            return "redirect:/";
        }
        if(result.hasErrors()){
            model.addAttribute("message", "入力値に間違いがあります");
            return showDetail((String)form.getId(), model, form,redirectAttributes);
        }
        Employee employee = employeeService.showDetail(Integer.valueOf(form.getId()));
        employee.setAddress(form.getAddress());
        employee.setCharacteristics(form.getCharacteristics());
        employee.setDependentsCount(Integer.valueOf(form.getDependentsCount()));
        employee.setGender(form.getGender());
        employee.setHireDate(formatDateAndString.stringToDate(form.getHireDate()));
        employee.setMailAddress(form.getMailAddress());
        employee.setName(form.getName());
        employee.setSalary(Integer.valueOf(form.getSalary()));
        employee.setTelephone(form.getTelephone());
        employee.setZipCode(form.getZipCode());
        employeeService.update(employee);
        return "redirect:/employee/showList";
    }
    /**
 * 
 * @param model
 * @return "employee/list.html"へフォワード
 */
@GetMapping("/showFindByForm")
public String showFindByForm(FindForm findForm,Model model,RedirectAttributes redirectAttributes){
    if(session.getAttribute("administratorName")==null){
        redirectAttributes.addFlashAttribute("message", "ログインされていません。ログインしてからもう一度お試しください。");
        return "redirect:/";
    }
    if(findForm.getStartDate()==null||findForm.getStartDate().equals("")){
        findForm.setStartDate("0001-01-01");
    }
    if(findForm.getEndDate()==null||findForm.getEndDate().equals("")){
        findForm.setEndDate("9999-01-01");
    }
    if(findForm.getDependentsCounts()==null||findForm.getDependentsCounts().equals("")){
        findForm.setDependentsCounts("0");
    }
    List<Employee> employeeList = employeeService.showFindByForm(findForm);
    model.addAttribute("employeeList", employeeList);
    model.addAttribute("findForm", findForm);
    System.out.println(findForm.getName());
    return "employee/list";
}
} 
