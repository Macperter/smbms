package com.test.servl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.test.dao.dblink;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getname  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        HttpSession session = req.getSession();
        Connection connection = null;
        try {
            connection = dblink.GetConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet =
            statement.executeQuery("select username from smbms_user where usercode = '" + (String) session.getAttribute("vist")+"'");
            System.out.println(session.getAttribute("vist"));
            if (resultSet.next()){
                String tmp =  resultSet.getString(1);
                System.out.println(tmp );
                resp.getWriter().print(tmp);
                resp.getWriter().close();
                statement.close();
            }else {
                resp.getWriter().print("");
                resp.getWriter().close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dblink.ReleaseConnection(connection);
        }

    }
}
