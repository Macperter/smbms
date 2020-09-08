package com.test.pojo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class returndataresult {
    public int count = 0;
    public Object[] data = null;

    public  void  init (ResultSet resultSet){
        List<Map<String,String> > tm = new LinkedList<>();
        try {
            while (resultSet.next()){
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                Map<String,String> en = new TreeMap<>((e1,e2)->{
                    if (e1.equals(e2))
                        return 0;
                    return 1;
                });
                for (int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    en.put(resultSetMetaData.getColumnName(i),resultSet.getString(i));
                }
                tm.add(en);
            }
            System.out.println(tm.toArray());
            data =  tm.toArray();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
