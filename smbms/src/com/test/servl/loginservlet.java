package com.test.servl;
import com.test.dao.dblink;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public class loginservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp)
        String id = req.getParameter("id");
        String pass = req.getParameter("password");
        PrintWriter printWriter =resp.getWriter();
        try {
            if (id!=null && pass != null){
                int con = dblink.logincheck(id,pass);
                if (con >0){
                    printWriter.print("./page/mar.html");
                    HttpSession session = req.getSession();
                    session.setAttribute("vist",id);

                }


                else
                    printWriter.print("no");
                return;
            }
            printWriter.print("data miss");
        }finally {
            printWriter.close();
        }



        
    }
}
