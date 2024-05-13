package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
/**
 * @author motokidoi
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    /**
     * "/administrator/insert.html"へフォワードする
     * @param form
     * @return "/administrator/insert"
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form){
        return "/administrator/insert";
    }
}
