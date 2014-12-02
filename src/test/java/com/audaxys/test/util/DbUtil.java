package com.audaxys.test.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.audaxys.test.dom.AudioCd;
import com.audaxys.test.dom.MusicCollection;
import com.audaxys.test.dom.Person;

public class DbUtil {

	public static Person getPerson(Session session, String name) {
		Query query = session.createQuery("from Person where name=?");
		query.setString(0, name);
		Person person = (Person) query.uniqueResult();
		return person;
	}
	
	public static MusicCollection getMusicCollection(Session session, String name) {
		Query query = session.createQuery("from MusicCollection where name=?");
		query.setString(0, name);
		MusicCollection mc = (MusicCollection) query.uniqueResult();
		return mc;
	}

	public static void initializeDb(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		// create 2 CDs
		AudioCd cd1 = new AudioCd();
		cd1.setAlbumName("Thank you for the misuc");
		cd1.setAuthor("Abba");
		session.save(cd1);

		AudioCd cd2 = new AudioCd();
		cd2.setAlbumName("Gimme music");
		cd2.setAuthor("Mrs. X");
		session.save(cd2);
		
		// create 2 Person
		Person person1 = new Person();
		person1.setName("Mr. X");
		person1.setFavoriteCd(cd2);
		session.save(person1);

		Person person2 = new Person();
		person2.setName("Junior Little X");
		person2.setFavoriteCd(cd1);
		session.save(person2);

		// create 1 Collection
		MusicCollection mc = new MusicCollection();
		mc.setName("X Collection");
		mc.setOwner(person1);
		mc.addAudioCd(cd1);
		mc.addAudioCd(cd2);
		session.save(mc);
		
		transaction.commit();
		session.close();
	}
	
}
