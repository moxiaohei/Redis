package com.ysit.redis.daoImpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.ysit.redis.dao.BlogDao;
import com.ysit.redis.model.Blog;

public class BlogDaoImpl implements BlogDao{

	
	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;
	
	@Override
	public void saveBlog(final Blog blog) {
		redisTemplate.execute(new RedisCallback<Object>() {

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer redisSerializer = redisTemplate.getValueSerializer();
				redisSerializer.serialize(blog);
				connection.set(redisTemplate.getStringSerializer().serialize("blog.id."+blog.getId()), 
						redisSerializer.serialize(blog));
				return null;
			}
			
		});
	}
	
	@Override
	public Blog getBlog(final long id) {
		return redisTemplate.execute(new RedisCallback<Blog>() {
			@Override
			public Blog doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("blog.id."+id);
				if(connection.exists(key)){
					byte[] value = connection.get(key);
					Blog blog;
					@SuppressWarnings("rawtypes")
					RedisSerializer redisSerializer = redisTemplate.getValueSerializer();
					blog = (Blog)redisSerializer.deserialize(value);
					return blog;
				}
				return null;
			}
		});
	}

}
