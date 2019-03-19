package com.technicalkeeda.services;

import java.util.List;

import javax.naming.InitialContext;
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

			ut.commit();
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