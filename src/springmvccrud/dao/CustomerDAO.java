package springmvccrud.dao;

import java.util.List;

import springmvccrud.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer tempCustomer);

	public Customer getCustomer(int tempId);

	public void deleteCustomer(int tempId);

	public List<Customer> searchCustomers(String theSearchName);
}
