package facades;

import java.util.Date;
import dto.PersonDTO;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getPersonCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }

    }

    public PersonDTO addPerson(String fName, String lName, String phone) {
        EntityManager em = getEntityManager();
        try {
            Date date = new Date();
            Person person = new Person(fName, lName, phone, date, date);
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

            // add main test
            PersonDTO personDTO = new PersonDTO(person);

            return personDTO;

        } finally {
            em.close();
        }
    }

    public PersonDTO deletePerson(int id) {
        EntityManager em = getEntityManager();
        try {
            Person entity = em.find(Person.class, id);
            PersonDTO removed = new PersonDTO(entity);
          em.remove(entity);
            return removed;

        } finally {
            em.close();
        }
    }

    public PersonDTO getPerson(int id) {
        EntityManager em = getEntityManager();
        try {
            Person entity = em.find(Person.class, id);
            PersonDTO personDTO = new PersonDTO(entity.getFirstName(), entity.getLastName(), entity.getPhone());
            return personDTO;

        } finally {
            em.close();
        }
    }

    public List<PersonDTO> getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            List<PersonDTO> returnList = new ArrayList();
            TypedQuery<Person> persons = em.createQuery("SELECT p FROM Person p",Person.class);
            List<Person> list = persons.getResultList();
            for (Person p : list ){
                returnList.add(new PersonDTO(p));
            }
            return returnList;

        } finally {
            em.close();
        }
    }

    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = getEntityManager();
        try {
             Person entity = em.find(Person.class, p.getId());
             Date date = new Date();
             Person edit = new Person(p.getfName(), p.getlName(), p.getPhone(), entity.getCreated(), date);
             em.merge(edit);
             em.getTransaction().commit();
             // confirm via test or method? 
             Person getEdit = em.find(Person.class ,p.getId());
             PersonDTO confirm = new PersonDTO(getEdit);
             // return is to confrim changes was corret uses if(for check) + custom exception to as throw = fail. 
             return confirm;

        } finally {
            em.close();
        }
    }

}
