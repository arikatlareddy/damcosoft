package arikatla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import arikatla.dao.CustomerDAO;
import arikatla.entity.Customer;

@Service
public class CustomerServiceimpl implements CustomerService {
	

	// need to inject customer DAO
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		
	customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return customerDAO.getCustomer(theId);
		
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		 customerDAO.deleteCustomer(theId);
	}

}
