package springmvccrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springmvccrud.dao.CustomerDAO;
import springmvccrud.dao.CustomerDAOImpl;
import springmvccrud.entity.Customer;
import springmvccrud.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model tempModel) {
		
		List<Customer> tempCustomers = customerService.getCustomers();
		
		tempModel.addAttribute("customers",tempCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model tempModel)
	{
		Customer tempCustomer = new Customer();
		
		tempModel.addAttribute("customer", tempCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer tempCustomer) {
		
		customerService.saveCustomer(tempCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int tempId, Model tempModel)
	{
		Customer tempCustomer=customerService.getCustomer(tempId);
		
		tempModel.addAttribute("customer", tempCustomer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int tempId)
	{
		
		customerService.deleteCustomer(tempId);
		
		return "redirect:/customer/list";
	}
	
	@PostMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {

        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
                
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";        
    }
}
