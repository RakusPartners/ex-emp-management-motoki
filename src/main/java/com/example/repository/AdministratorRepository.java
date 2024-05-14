package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;
/**
 * administrator  テーブルを操作するリポジトリ
 * @author motoki doi
 */
@Repository
public class AdministratorRepository {
    /** Administratorオブジェクトを生成するRowMapper */
    private static final RowMapper<Administrator> ADMIN_ROW_MAPPER = (rs,i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 管理者情報を挿入するメソッド。
     * @param administrator
     */
    public void insert(Administrator administrator){
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
    
        String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name,:mailAddress,:password)";
        template.update(sql, param);
        
    }
    /**
     * メールアドレスウトパスワードから管理し情報を取得するメソッド（１件もない時にはnullを返す）
     * @param mailAddress
     * @param password
     * @return 管理者情報
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:password";
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
        List<Administrator> administratorList = template.query(sql, param, ADMIN_ROW_MAPPER);
        if(administratorList.size()==0){
            return null;
        }
        return administratorList.get(0);
    }

    public Administrator findMailAddress(String mailAddress){
        String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress";
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
        List<Administrator> administratorList = template.query(sql,param,ADMIN_ROW_MAPPER);
        if(administratorList.size()==0){
            return null;
        }
        return administratorList.get(0);
    }

    
}
