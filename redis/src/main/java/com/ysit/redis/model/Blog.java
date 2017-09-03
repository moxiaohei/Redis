package com.ysit.redis.model;

import java.io.Serializable;

/**
 * @author mengyongfei
 * @date 2017年8月18日 下午2:08:40
 * 实体类 需要实现序列化
 */
public class Blog implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String context;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
}
