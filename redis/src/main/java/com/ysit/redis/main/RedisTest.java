package com.ysit.redis.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ysit.redis.dao.BlogDao;
import com.ysit.redis.model.Blog;

public class RedisTest {
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		BlogDao bd = (BlogDao)ac.getBean("blogDAO");
		Blog blog = new Blog();
		blog.setId(1);
		blog.setContext("{\"name\":\"myf\",\"age\":12}");
		bd.saveBlog(blog);
		Blog blog2 = bd.getBlog(1);
		System.out.println(blog2.getContext());
	}
}
