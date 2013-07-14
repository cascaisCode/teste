package com.example.persistence;

import java.util.Date;

import android.net.Uri;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName= "noticia")
public class Noticia {

	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField
	private String description;
	
	@DatabaseField
	private Uri uriFoto;
	
	@DatabaseField
	private Uri uriVideo;
	
	@DatabaseField
	private Uri uriAudio;

	@DatabaseField(foreign = true)
	private User user;
	
	@DatabaseField
	private Date date;
	
	public Uri getUriFoto() {
		return uriFoto;
	}

	public void setUriFoto(Uri uriFoto) {
		this.uriFoto = uriFoto;
	}

	public Uri getUriVideo() {
		return uriVideo;
	}

	public void setUriVideo(Uri uriVideo) {
		this.uriVideo = uriVideo;
	}

	public Uri getUriAudio() {
		return uriAudio;
	}

	public void setUriAudio(Uri uriAudio) {
		this.uriAudio = uriAudio;
	}

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
