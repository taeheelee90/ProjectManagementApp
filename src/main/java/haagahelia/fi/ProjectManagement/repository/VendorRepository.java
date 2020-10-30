package haagahelia.fi.ProjectManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import haagahelia.fi.ProjectManagement.model.Vendor;

@RepositoryRestResource
public interface VendorRepository extends CrudRepository <Vendor, Long> {

}
