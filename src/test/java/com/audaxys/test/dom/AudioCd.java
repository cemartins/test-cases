package com.audaxys.test.dom;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 */
/**
 * @author cem
 *
 */
@Entity
public class AudioCd implements Serializable {

	private static final long serialVersionUID = 4665837089141365794L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(optional = true, fetch= FetchType.LAZY)
	private MusicCollection musicCollection;
	
	private String author;
	private String albumName;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public MusicCollection getMusicCollection() {
		return musicCollection;
	}

	public void setMusicCollection(MusicCollection musicCollection) {
		this.musicCollection = musicCollection;
	}

	public String toString() {
		return "author: " + author + " Album: " + albumName;
	}

	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj == null || ! (obj instanceof AudioCd))
			return false;
		AudioCd audioCd = (AudioCd) obj;
		if(id != null)
			return id.equals(audioCd.getId());
		else
			return false;
	}
}
