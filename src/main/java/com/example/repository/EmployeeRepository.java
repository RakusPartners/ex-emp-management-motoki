package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;
import com.example.form.FindForm;
import com.example.util.formatDateAndString;

/**
 * 
 * Employeesテーブルを操作するリポジトリ
 * 
 * @author motokidoi
 */
@Repository
public class EmployeeRepository{
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getTimestamp("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 
     * @return 従業員リスト
     */
    public List<Employee> findAll() {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees ORDER BY hire_date DESC";
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }

    /**
     * 
     * @param id
     * @return employee
     */
    public Employee load(Integer id) {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        List<Employee> employeeList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);
        if (employeeList.size() == 0) {
            return null;
        }
        return employeeList.get(0);
    }

    /**
     * 
     * @param employee
     */
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String updateSql = "UPDATE employees SET name=:name, image=:image, gender=:gender, hire_date=:hireDate, mail_address=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependents_count=:dependentsCount WHERE id=:id";
        template.update(updateSql, param);
    }

    public List<Employee> findByForm (FindForm form){
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees WHERE name LIKE '%'||:name||'%' AND hire_date >= :startDate AND hire_date <= :endDate AND dependents_count >= :dependentsCounts ORDER BY hire_date DESC";
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", form.getName()).addValue("startDate", formatDateAndString.stringToDate(form.getStartDate())).addValue("endDate", formatDateAndString.stringToDate(form.getEndDate())).addValue("dependentsCounts", Integer.valueOf(form.getDependentsCounts()));
        List<Employee> employeeList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }
}
