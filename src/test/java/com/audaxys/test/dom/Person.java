package com.audaxys.test.dom;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = -7969309518311916608L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@ManyToOne(fetch= FetchType.LAZY)
	private AudioCd favoriteCd;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "name: " + name;
	}
	
	public AudioCd getFavoriteCd() {
		return favoriteCd;
	}
	public void setFavoriteCd(AudioCd favoriteCd) {
		this.favoriteCd = favoriteCd;
	}
	
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj == null || ! (obj instanceof Person))
			return false;
		Person audioCd = (Person) obj;
		if(id != null)
			return id.equals(audioCd.getId());
		else
			return false;
	}

}
