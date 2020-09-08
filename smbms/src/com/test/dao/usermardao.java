package com.test.dao;
import com.test.pojo.databean;
import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import com.test.pojo.returndataresult;
public class usermardao {
      public String name ="";
      public int job=0;
      public int page =0;
      public int howpage = 0;
      StringBuilder sql = null;
      String nep = "select a.userCode `编码`,a.userName `姓名` ,a.gender `性别` ,YEAR(NOW())-YEAR(a.birthday) `年龄`,a.phone `电话` , b.roleName `角色` ";
    public usermardao(String name, int job, int page, int howpage) {
        this.name = name;
        this.job = job;
        this.page = page;
        this.howpage = howpage;
        sql = new StringBuilder("FROM smbms_user a LEFT JOIN  smbms_role b ON a.userRole = b.id where ");
        if ( !name.equals("") && name!=null){
            sql.append(String.format(" a.userName='%s'",name));

        }else{
            sql.append(" 1=1");
        }
        if (job!=0)
            sql.append(String.format(" and b.userRole=%d ",job));
        else
            sql.append(" and 1=1");
    }
    public int count (){
        Connection connection = null;
        try {

            connection=  dblink.GetConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) " + sql.toString());
            if (resultSet.next()){
                return resultSet.getInt(1);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (connection != null)
                dblink.ReleaseConnection(connection);
        }
        return 0;
    }
    public returndataresult go () {
        String ensql = nep + sql.toString() + String.format(" LIMIT %d,%d",(page-1)*howpage,howpage);
        Connection connection = null;
        try {
           connection = dblink.GetConnection();
           Statement statement =
           connection.createStatement();
           ResultSet resultSet =
           statement.executeQuery(ensql);
           returndataresult tmp = new returndataresult();
           tmp.setCount(this.count());
           tmp.init(resultSet);
           return tmp;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (connection!=null)
                dblink.ReleaseConnection(connection);
        }
        return  null;
        //return  new Object[10];
    }
}
