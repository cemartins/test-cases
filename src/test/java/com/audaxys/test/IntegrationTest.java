package com.audaxys.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.audaxys.test.dom.AudioCd;
import com.audaxys.test.dom.MusicCollection;
import com.audaxys.test.dom.Person;
import com.audaxys.test.util.DbUtil;

/**
 * Demonstrates a problem while using nested transactions.<br>
 * Hibernate throws an exception "illegally attempted to associate a proxy with two open Sessions"
 * @author Carlos Martins
 */
public class IntegrationTest {
	
	private SessionFactory sessionFactory;

	@Before
	public void initialize() {
		
		final Configuration configuration = new Configuration();
		
		// configuration for hibernate 4
		configuration.addAnnotatedClass( Person.class );
		configuration.addAnnotatedClass( MusicCollection.class );
		configuration.addAnnotatedClass( AudioCd.class );
		
		sessionFactory = configuration.buildSessionFactory( new StandardServiceRegistryBuilder().build() );

		DbUtil.initializeDb(sessionFactory);
	}
	

	@Test
	public void testSaveInNestedSession() {
		
		// open first session
		Session session1 = sessionFactory.openSession();
		Transaction transaction1 = session1.beginTransaction();
		
		// get the existing music collection
		MusicCollection mc = DbUtil.getMusicCollection(session1, "X Collection");
		
		// create and save a copy of this collection in a nested transaction (will break)
		replicateMusicCollection(mc);
		
		transaction1.commit();
		session1.close();
	}
	
	/**
	 * Save a copy of the MusicCollection in a new transaction
	 * for isolation purposes
	 * @param mc
	 */
	private void replicateMusicCollection(MusicCollection mc) {

		// open nested session
		Session session2 = sessionFactory.openSession();
		Transaction transaction2 = session2.beginTransaction();

		// create a new transient Music Collection
		MusicCollection newMusic = new MusicCollection();
		newMusic.setName("Collection Y");
		newMusic.setOwner(mc.getOwner());
		
		for(AudioCd cd : mc.getCdSet()) {
			AudioCd newCd = new AudioCd();
			newCd.setAlbumName(cd.getAlbumName());
			newCd.setAuthor(cd.getAuthor());
			
			newMusic.addAudioCd(newCd);
		}
		
		try {
			session2.save(newMusic);
			transaction2.commit();
		}
		catch(HibernateException e) {
			
			e.printStackTrace();
			transaction2.rollback();

			throw e;
		}
		
		session2.close();
	}
	
}
