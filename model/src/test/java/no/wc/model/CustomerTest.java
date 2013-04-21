package no.wc.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;
import java.util.List;

/**
 * User: MicJoh
 * Date: 10.04.13
 * Time: 23:29
 */
public class CustomerTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void initHibernate() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Doe");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();

        System.out.println(customer.getId());

        List<Customer> resultList = entityManager.createNamedQuery("all", Customer.class).getResultList();
        System.out.println(resultList.size());
        for (Customer customer1 : resultList) {
            System.out.printf("%s %s%n", customer1.getFirstName(), customer1.getLastName());
        }

    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
