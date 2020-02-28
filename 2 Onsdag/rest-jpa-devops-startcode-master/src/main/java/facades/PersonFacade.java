package facades;

import java.util.Date;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import exceptions.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

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

    @Override
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
//  removes almost all persons in DB ask teacher

    @Override
    public PersonDTO deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        PersonDTO removed = null;
        try {
            System.out.println("ID = " + id);
            em.getTransaction().begin();
            Person entity = em.find(Person.class, id);
            if (entity != null) {
                removed = new PersonDTO(entity);
                em.remove(entity);
            } else {
                throw new PersonNotFoundException("Person not found");
            }
            em.getTransaction().commit();
            return removed;

        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO getPerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();

        try {
            Person entity = em.find(Person.class, id);
            if (entity != null) {
                PersonDTO personDTO = new PersonDTO(id, entity.getFirstName(), entity.getLastName(), entity.getPhone());
                return personDTO;

            } else {
                throw new PersonNotFoundException("Person not found");
            }

        } finally {
            em.close();
        }
    }
    /////// return methods..\\\\\\\\\\\\\\\\\\\\\
//    public List<PersonDTO> getAllPersons() {
//        EntityManager em = getEntityManager();
//        
//        try {
//            List<PersonDTO> returnList = new ArrayList();
//            TypedQuery<Person> persons = em.createQuery("SELECT p FROM Person p", Person.class);
//            List<Person> list = persons.getResultList();
//            for (Person p : list) {
//                returnList.add(new PersonDTO(p));
//            }
//            return returnList;
//
//        } finally {
//            em.close();
//        }
//    }

//    public List<PersonDTO> getAllPersons() {
//        EntityManager em = getEntityManager();
//        
//        try {
//            List<PersonDTO> returnList = new ArrayList();
//            TypedQuery<Person> persons = em.createQuery("SELECT p FROM Person p", Person.class);
//            List<Person> list = persons.getResultList();
//            for (Person p : list) {
//                returnList.add(new PersonDTO(p));
//            }
//            return returnList;
//
//        } finally {
//            em.close();
//        }
//    }
    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Person> persons = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> list = persons.getResultList();
            PersonsDTO done = new PersonsDTO(list);
            return done;

        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException {
        EntityManager em = getEntityManager();

        try {
            Person tryFind = em.find(Person.class, p.getId());
            if (tryFind != null) {
                em.getTransaction().begin();
                Person entity = em.find(Person.class, p.getId());
                entity.setFirstName(p.getfName());
                entity.setLastName(p.getlName());
                entity.setPhone(p.getPhone());
                entity.setDate();
                em.getTransaction().commit();
                // confirm via test or method? 
                Person getEdit = em.find(Person.class, p.getId());
                PersonDTO confirm = new PersonDTO(getEdit);
                // return is to confrim changes was corret uses if(for check) + custom exception to as throw = fail. 
                return confirm;

            } else {
                throw new PersonNotFoundException("Person not found");
            }

        } finally {
            em.close();
        }
    }

}
