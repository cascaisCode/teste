package com.example.persistence;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName= "noticia")
public class Noticia {

	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField
	private String description;
	
	@DatabaseField
	private String uri;

	@DatabaseField(foreign = true)
	private User user;
	
	@DatabaseField
	private Date date;
	
	public Noticia(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
