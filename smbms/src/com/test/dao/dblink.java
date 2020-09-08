package com.test.dao;





import javax.sound.sampled.Port;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class dblink {
    private static Lock lock = new ReentrantLock(true);
    private static String table="";
    private static String user ="";
    private static String password = "";
    private static int CountLink = 0;
    private static String ip = "127.0.0.1";
    private static int prot = 3306;
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static  volatile Stack<Connection> startpool = new Stack<>();
    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Properties properties = new Properties();

            //System.out.println(dblink.class.getClassLoader().getResourceAsStream("dp.ini"));
            InputStream in =dblink.class.getClassLoader().getResourceAsStream("dp.ini");
            properties.load(in);
            System.out.println();
            init(properties.getProperty("table"),properties.getProperty("ip"),
                    Integer.parseInt(properties.getProperty("prot")),properties.getProperty("user"),properties.getProperty("password"),
            Integer.parseInt(properties.getProperty("CountLink")));


        }catch (SQLException | IOException e  ){
            e.printStackTrace();
        }


    }
//    public  dblink() throws SQLException {
//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
////        try {
////            Class.forName("com.mysql.cj.jdbc.Driver");
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }
//    }
    public static void init (String table,String ip,int prot, String user, String password,int countLink) throws SQLException {
        //this();
        dblink.table = table;
        dblink.user = user;
        dblink.password = password;
        CountLink = countLink;
        dblink.ip = ip;
        dblink.prot = prot;
        for (int i =0;i<countLink;i++){ //jdbc:mysql://127.0.0.1:3306/exten
            startpool.push(DriverManager.getConnection("jdbc:mysql://"+ip+":"+prot+"/"+table+"?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true"
                    ,user,password));
        }
        connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+prot+"/"+table+"?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true"
                ,user,password);

        preparedStatement =  connection.prepareStatement("select count(*) from  smbms_user where userCode= ? and userPassword = ?");

    }
    public static synchronized int logincheck(String id ,String pass){
        try {
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
    public static Connection GetConnection () throws SQLException{
        lock.lock();
        try {
            if (startpool.empty())
                return DriverManager.getConnection("jdbc:mysql://"+ip+":"+String.valueOf(prot)+"/"+table+"?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true"
                        ,user,password);
            return startpool.pop();
        }finally {
            lock.unlock();
        }

    }
    public static void ReleaseConnection(Connection connection) {
        if (connection == null)
            return;
        if (startpool.size() >= CountLink)
            try {
                connection.close();
                return;
            }catch (SQLException e){
                e.printStackTrace();
            }
        lock.lock();
        try {
            if (!connection.getAutoCommit()){
                connection.rollback();
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                connection.setAutoCommit(true);

            }
            startpool.push(connection);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
    public static void ExitPool(){
        while (!startpool.empty()){
            Connection connection = startpool.pop();
            if (connection!=null)
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

        }
    }
}
