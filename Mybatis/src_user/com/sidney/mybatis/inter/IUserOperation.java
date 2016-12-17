package com.sidney.mybatis.inter;

import java.util.List;

import com.sidney.mybatis.model.Article;
import com.sidney.mybatis.model.User;

public interface IUserOperation {
	public User selectUserByID(int id);
	public List<User> selectUsers(String userName);
	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	public List<Article> getUserArticles(int id);
}
