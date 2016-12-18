package com.sidney.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sidney.mybatis.inter.IUserOperation;
import com.sidney.mybatis.model.Article;

@Controller
@RequestMapping(value="/article")
public class UserController {
	@Autowired
	IUserOperation iUserOperation;
	@RequestMapping(value="/list")
	public ModelAndView listall(HttpServletRequest request,HttpServletResponse response){
		List<Article> articleList = iUserOperation.getUserArticles(1);
		ModelAndView vie = new ModelAndView("list");
		vie.addObject("articles", articleList);
		return vie;
	}
}
