package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select USER_ID, USER_PASSWORD, USER_NAME, USER_EMAIL, USER_TELNO from USER";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getString("USER_ID"),
                        rs.getString("USER_PASSWORD"),
                        rs.getString("USER_NAME"),
                        rs.getString("USER_EMAIL"),
                        rs.getString("USER_TELNO"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select USER_ID, USER_PASSWORD, USER_NAME, USER_EMAIL, USER_TELNO from USER where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getString("USER_ID"),
                        rs.getString("USER_PASSWORD"),
                        rs.getString("USER_NAME"),
                        rs.getString("USER_EMAIL"),
                        rs.getString("USER_TELNO")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(String userId){
        String getUserQuery = "select USER_ID, USER_PASSWORD, USER_NAME, USER_EMAIL, USER_TELNO from USER where USER_ID = ?";
        String getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getString("USER_ID"),
                        rs.getString("USER_PASSWORD"),
                        rs.getString("USER_NAME"),
                        rs.getString("USER_EMAIL"),
                        rs.getString("USER_TELNO")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into USER (USER_NAME, USER_ID, USER_PASSWORD, USER_EMAIL, USER_TELNO, WITHDRAWAL_YN, CREATE_DT) VALUES (?,?,?,?,?,'N', now())";
        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getUserId(), postUserReq.getPassword(), postUserReq.getEmail(), postUserReq.getTel()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select USER_EMAIL from USER where USER_EMAIL = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update USER set USER_NAME = ? where USER_IDX = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select USER_IDX, USER_PASSWORD, USER_EMAIL, USER_NAME, USER_ID from USER where USER_ID = ?";
        String getPwdParams = postLoginReq.getId();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("ID"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("email")
                ),
                getPwdParams
                );

    }
    public int modifyWithdrawUser(PatchUserReq patchUserReq){
        String modifyWithdrawUserQuery = "update USER set WITHDRAWAL_YN = 'Y' where USER_IDX = ? ";
        Object[] modifyWithdrawUserParams = new Object[]{patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyWithdrawUserQuery,modifyWithdrawUserParams);
    }

}
