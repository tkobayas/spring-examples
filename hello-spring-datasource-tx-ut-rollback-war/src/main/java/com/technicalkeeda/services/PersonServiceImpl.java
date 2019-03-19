package com.technicalkeeda.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicalkeeda.bean.Person;
import com.technicalkeeda.dao.PersonDao;

@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDao personDao;

	public void addPerson(Person person) {

		try {
			UserTransaction ut = InitialContext.doLookup("java:comp/UserTransaction");
			System.out.println("ut = " + ut);
			ut.begin();

			personDao.addPerson(person);
			
			// Do usual datasource
			DataSource ds = (DataSource) InitialContext.doLookup("java:jboss/datasources/MySQLDS2");
	        try (Connection conn = ds.getConnection();
	                PreparedStatement ps = conn.prepareStatement("insert into mytable (data) values ('AAA')");) {

	            ps.execute();

	        } catch (SQLException e) {
	            throw new RuntimeException("Error while database access", e);
	        }

//			ut.commit();
	        
	        ut.rollback();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editPerson(Person person, int personId) {
		personDao.editPerson(person, personId);
	}

	public void deletePerson(int personId) {
		personDao.deletePerson(personId);
	}

	public Person find(int personId) {
		return personDao.find(personId);
	}

	public List<Person> findAll() {
		return personDao.findAll();
	}
}