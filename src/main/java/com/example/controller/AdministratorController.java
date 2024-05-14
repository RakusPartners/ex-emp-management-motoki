package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

/**
 * @author motokidoi
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private HttpSession session;

    /**
     * "/administrator/insert.html"へフォワードする
     * 
     * @param form
     * @return "/administrator/insert"
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する
     * 
     * @param form
     * @return
     */
    @PostMapping("/insert")
    public String insert(@Validated InsertAdministratorForm form,BindingResult result) {
        if(result.hasErrors()){
            return toInsert(form);
        }
        
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }

    /**
     * "/administrator/login.html"にフォワードする
     * @param form
     * @return
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "administrator/login";
    }
/**
 * 
 * @param form LoginForm
 * @param model Mode
 * @return redirect:/employee/showList
 */
    @PostMapping("/login")
    public String login(LoginForm form,Model model){
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
        if(administrator == null){
            model.addAttribute("message", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }
/**
 * 
 * @param form LoginForm
 * @return redirect:/
 */ 
    @SuppressWarnings("finally")
    @GetMapping("/logout")
    public String logout(LoginForm form){
        try {
            session.invalidate();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            return "redirect:/";
        }
    }
}
