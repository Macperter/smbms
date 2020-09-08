package com.test.servl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.dao.dblink;

public class changpass extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        PrintWriter out = resp.getWriter();

        Connection connection = null;
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("vist");
        try {
            String old = req.getParameter("oldpass");
            String newpass = req.getParameter("newpass");
            System.out.println(old + ","+newpass);
            if (old!=null && newpass!=null){
                connection= dblink.GetConnection();
                Statement statement =
                connection.createStatement();
                ResultSet resultSet =
                statement.executeQuery("select COUNT(*) from smbms_user where userCode='"+id +
                        "' and userPassword='" + old +"'");
                if (resultSet.next()){
                    if (resultSet.getInt(1) >0){
                        int cout = statement.executeUpdate(String.format("UPDATE smbms_user SET userPassword = '%s' WHERE userCode = '%s'",newpass,id));
                        if (cout >0){
                            out.print("[666]");//修改成功
                            session.removeAttribute("vist");
                        }

                        else
                            out.print("[665]");
                        return;
                    }else{
                        out.print("[101]");//旧密码错误
                        return;
                    }
                }
            }
            out.print("[000]");
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (connection!=null)
                dblink.ReleaseConnection(connection);
            out.close();
        }
    }
}
