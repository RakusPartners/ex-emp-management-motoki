package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * @author motokidoi
 */
@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者情報を挿入する
     * 
     * @param administrator
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }
    /**
     * ログイン処理を行う
     * @param mailAddress
     * @param password
     * @return
     */
    public Administrator login(String mailAddress,String password){
        Administrator administrator = administratorRepository.findByMailAddressAndPassword(mailAddress, password);
        return administrator;
    }

    public Administrator findByMailAddress(String mailAddress){
        Administrator administrator = administratorRepository.findMailAddress(mailAddress);
        return administrator;
    }
}
