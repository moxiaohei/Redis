package com.ysit.redis.dao;

import com.ysit.redis.model.Blog;

public interface BlogDao {
	
	public Blog getBlog(long id);
	
	public void saveBlog(Blog blog);
	
}
