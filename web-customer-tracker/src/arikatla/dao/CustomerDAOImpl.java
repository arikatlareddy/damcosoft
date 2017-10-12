package arikatla.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arikatla.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to Inject Hibernate Session Factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get the current Session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query sort by last Name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
						Customer.class);
		// Execute the query and get the result list
		List<Customer> customers = theQuery.getResultList();
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get the current Session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save/Update the customer
		
		currentSession.saveOrUpdate(theCustomer);
		
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
			//get Current Session
			Session currentSession =  sessionFactory.getCurrentSession();
			
			//retrieve the object from database by using Id
			
			Customer theCustomer = currentSession.get(Customer.class , theId);
			
			return theCustomer;
			
	
	}

	@Override
	public void deleteCustomer(int theId) {
		//get the current Session
		Session currentSession =  sessionFactory.getCurrentSession();
		
		// delete object with the primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}

	

	

}
