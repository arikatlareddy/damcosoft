package arikatla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import arikatla.entity.Customer;
import arikatla.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// Inject DAO to the controller
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// get customers from Service

		List<Customer> theCustomers = customerService.getCustomers();

		// Add customers to DAO model

		theModel.addAttribute("customers", theCustomers);

		return "list-customers";

	}

	@GetMapping("/showFormForAdd")
	public String ShowFormForAdd(Model theModel) {

		// create a model Attribute to bind form data

		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);
		return "customer-form";

	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		// save the customer using service
		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";

	}

	@GetMapping("showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		// get the customer from our Service

		Customer theCustomer = customerService.getCustomer(theId);
		// set customer as a modelAttribute to pre populate the form

		theModel.addAttribute("customer", theCustomer);
		// Send over to our actual form

		return "customer-form";

	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
}
