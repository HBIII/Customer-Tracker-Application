package springmvccrud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springmvccrud.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
			
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> tempquery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		List<Customer> customers = tempquery.getResultList();
	
		return customers;
	}

	@Override
	public void saveCustomer(Customer tempCustomer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(tempCustomer);
		
	}

	@Override
	public Customer getCustomer(int tempId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer tempCustomer=currentSession.get(Customer.class, tempId);
		
		return tempCustomer;
	}

	@Override
	public void deleteCustomer(int tempId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query tempQuery=currentSession.createQuery("delete from Customer where id=:customerId");
		
		tempQuery.setParameter("customerId", tempId);
		
		tempQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        if (theSearchName != null && theSearchName.trim().length() > 0) 
        {
            // search for firstName or lastName ... case insensitive
        	
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else 
        {
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}


}
