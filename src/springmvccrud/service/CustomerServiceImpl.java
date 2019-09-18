package springmvccrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springmvccrud.dao.CustomerDAO;
import springmvccrud.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer tempCustomer) {
		customerDAO.saveCustomer(tempCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int tempId) {
		return customerDAO.getCustomer(tempId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int tempId) {
		
		customerDAO.deleteCustomer(tempId);	
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		
        return customerDAO.searchCustomers(theSearchName);
	}

}
