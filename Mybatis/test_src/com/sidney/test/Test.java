package com.sidney.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sidney.mybatis.inter.IUserOperation;
import com.sidney.mybatis.model.User;

public class Test {
	   private static SqlSessionFactory sqlSessionFactory;
	    private static Reader reader; 

	    static{
	        try{
	            reader= Resources.getResourceAsReader("Configuration.xml");
	            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	        }catch(Exception e){
	        	System.out.println("error here");
	            e.printStackTrace();
	        }
	    }

	    public static SqlSessionFactory getSession(){
	        return sqlSessionFactory;
	    }
	    
	    public static void main(String[] args) {
	    	 Test testUser=new Test();
	         //testUser.getUserList("%");
	    	 //testUser.addUser();
	    	// testUser.deleteUser(4);
	    	// testUser.getUserList("%");
	    	 // testUser.updateUser(5);
	    	 
	    	 testUser.testSelectUserByID(5);
	    }
	    
	    public void updateUser(int id){
	        //先得到用户,然后修改，提交。
	        SqlSession session = sqlSessionFactory.openSession();
	        try {
	            IUserOperation userOperation=session.getMapper(IUserOperation.class);
	            User user = userOperation.selectUserByID(id);            
	            user.setUserAddress("原来是魔都的浦东创新园区");
	            userOperation.updateUser(user);
	            session.commit();
	            
	        } finally {
	            session.close();
	        }
	    }
	    
	    public void deleteUser(int id){
	        SqlSession session = sqlSessionFactory.openSession();
	        try {
	            IUserOperation userOperation=session.getMapper(IUserOperation.class);          
	            userOperation.deleteUser(id);
	            session.commit();            
	        } finally {
	            session.close();
	        }
	    }
	    
	    public void addUser(){
	        User user=new User();
	        user.setUserAddress("广东省深圳市南山区深南花园1栋801");
	        user.setUserName("张三");
	        user.setUserAge("25");
	        SqlSession session = sqlSessionFactory.openSession();
	        try {
	            IUserOperation userOperation=session.getMapper(IUserOperation.class);
	            userOperation.addUser(user);
	            session.commit();
	            System.out.println("当前增加的用户 id为:"+user.getId());
	        } finally {
	            session.close();
	        }
	    }
	    
	    
	    public void getUserList(String userName){
	        SqlSession session = sqlSessionFactory.openSession();
	        try {
	            IUserOperation userOperation=session.getMapper(IUserOperation.class);          
	            List<User> users = userOperation.selectUsers(userName);
	            for(User user:users){
	                System.out.println(user.getId()+":"+user.getUserName()+":"+user.getUserAddress());
	            }
	            
	        } finally {
	            session.close();
	        }
	    }
	    
	    public static void testSelectUserByID(int id){
	        SqlSession session = sqlSessionFactory.openSession();
	        try {
	  
	        //	User user = (User) session.selectOne("com.sidney.mybatis.models.UserMapper.selectUserByID", 1);
	        IUserOperation userOperation = session.getMapper(IUserOperation.class);
	        User user = userOperation.selectUserByID(id);
	        	
	        	System.out.println(user.getUserAddress());
	        	System.out.println(user.getUserName());
	        
	        } finally {
	        	session.close();
	        }
	    }
}
