package com.test.servl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dao.usermardao ;
import com.test.pojo.returndataresult;

public class usermar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        // 用户 管理 传三个参数 要查询的用户名 /name 用户角色 /job 以及页吗/page 每页5条数据
        // 返回  {
        //  count:xx,//记录的条数;
        //  data:[] 查询的结果
        // }
        String name = req.getParameter("name");
        int job = Integer.parseInt(req.getParameter("job"));
        int page = Integer.parseInt(req.getParameter("page"));
        usermardao p = new usermardao(name,job,page,5);

        returndataresult go = p.go();
        ObjectMapper om = new ObjectMapper();
        String josn = om.writeValueAsString(go);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out =
        resp.getWriter();
        System.out.println(josn);
        out.print(josn);
        out.close();
    }
}
