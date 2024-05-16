package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author motokidoi
 */

import com.example.domain.Employee;
import com.example.form.FindForm;
import com.example.repository.EmployeeRepository;
@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員情報を全件取得する。
     * @return 従業員情報を全件
     */
    public List<Employee> showList(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }
    /**
     * 検索条件に合う従業員情報を取得する。
     * @return 従業員情報を全件
     */
    public List<Employee> showFindByForm(FindForm form){
        List<Employee> employeeList = employeeRepository.findByForm(form);
        return employeeList;
    }
/**
 * 従業員情報を表示
 * @param 従業員id
 * @return 従業員情報
 */
    public Employee showDetail(Integer id){
        Employee employee = employeeRepository.load(id);
        return employee;
    }
/**
 * 従業員情報を更新
 * @param employee 従業員情報
 */
    public void update(Employee employee){
        employeeRepository.update(employee);
    }
} 
