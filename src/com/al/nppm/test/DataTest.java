package com.al.nppm.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.*;

public class DataTest {

    public static void main(String[] args) {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://10.6.12.16:3306/bill";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "mysql2013";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
//            if(!con.isClosed())
//                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            //  customer  cust_attr
            String tableName="ord_party";
            String sql = "SELECT column_name,data_type FROM information_schema.columns WHERE table_name='"+tableName+"' AND table_schema='bill' ";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            
            JSONObject result=new JSONObject();
            result.put("tableName", tableName.toUpperCase());
            result.put("tableOrder", 1);
            
            JSONArray rowDataList =new JSONArray();
                       
            JSONObject rowData=new JSONObject();
            rowData.put("operType", 1);
            
            
            String name = null;
            
            StringBuffer sb=new StringBuffer();
            while(rs.next()){
                //获取stuname这列数据
                name = rs.getString("column_name");
                sb.append(name+" ");
                sb.append(change(name)+",");
            }
            System.out.println(sb.toString());
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
//            System.out.println("数据库数据成功获取！！");
        }
    }
    
    public static String change(String name){

        String[] s=name.split("_");
        StringBuffer tmp=new StringBuffer();
        for(int i=0;i<s.length;i++){
        	
        	if(i==0){
        		tmp.append(s[i].toLowerCase());
        	}else{
        		tmp.append(toUpperCaseFirstOne(s[i].toLowerCase()));
        	}
        }
        return tmp.toString();
  	}
    
    public static String toUpperCaseFirstOne(String s){
    	  if(Character.isUpperCase(s.charAt(0)))
    	    return s;
    	  else
    	    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    	}

}