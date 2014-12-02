package com.audaxys.test.dom;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;


/**
 */
@Entity
public class MusicCollection implements Serializable {

	private static final long serialVersionUID = -4842790705131368523L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Person owner;
	
	@OneToMany(mappedBy = "musicCollection", fetch=FetchType.LAZY, orphanRemoval=true, cascade=CascadeType.ALL)
    private Set<AudioCd> cdSet;
    
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
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public Set<AudioCd> getCdSet() {
		return cdSet;
	}
	public void setCdSet(Set<AudioCd> cdSet) {
		this.cdSet = cdSet;
	}
    public void addAudioCd(AudioCd audioCd) {
    	if(cdSet == null)
    		cdSet = new HashSet<AudioCd>();
    	audioCd.setMusicCollection(this);
    	cdSet.add(audioCd);
    }
    public void removeAudioCd(AudioCd audioCd) {
    	cdSet.remove(audioCd);
    }
}
