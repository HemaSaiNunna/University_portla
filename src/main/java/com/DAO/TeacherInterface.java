package com.DAO;

import com.Model.RegisterModel;
import com.Model.LoginModel;
import java.util.List;

public interface TeacherInterface {
    public String insertData(RegisterModel rm);
    public String selectTeacherData(LoginModel lm);
    public RegisterModel selectById(String username);
    public List<RegisterModel> getAllTeachers();
    public String updateTeacherDetails(RegisterModel rm);
    public boolean isUsernameExist(String username);
    public boolean deleteTeacherByUsername(String username);
}
