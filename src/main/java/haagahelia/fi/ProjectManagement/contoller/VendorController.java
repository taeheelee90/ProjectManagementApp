package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import haagahelia.fi.ProjectManagement.model.Vendor;
import haagahelia.fi.ProjectManagement.repository.VendorRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class VendorController {

	private final VendorRepository repository;

	// Read Vendors
	@RequestMapping(value = "/vendorlist")
	public String employeeList(Model model) {
		model.addAttribute("vendors", repository.findAll());
		return "vendor/vendorlist";
	}

	// Add Vendor
	@GetMapping(value = "/vendoradd")
	public String addEmployee(Model model) {
		model.addAttribute("vendor", new Vendor());
		model.addAttribute("vendors", repository.findAll());
		return "vendor/addvendor";

	}

	// Submit Vendor
	@PostMapping(value = "/vendorsubmit")
	public String submitEmployee(Vendor vendor, Model model) {
		model.addAttribute("vendor", repository.save(vendor));
		return "redirect:vendorlist";
	}

	// Update Vendor
	@GetMapping(value = "/vendoreedit/{id}")
	public String updateEmployee(@PathVariable("id") Long vendorId, Model model) {
		model.addAttribute("vendor", repository.findById(vendorId));
		return "vendor/updatevendor";
	}

	// Delete Vendor
	@GetMapping(value = "/vendordelete/{id}")
	public String deleteEmployee(@PathVariable("id") Long vendorId) {
		repository.deleteById(vendorId);
		return "redirect:../vendorlist";
	}
}
