package taehee.lee.ProjectManagementApp_v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;



@RepositoryRestResource
@Transactional (readOnly = true)
public interface AppUserRepository extends JpaRepository <AppUser, Long>{


	boolean existsByEmail (String email);
	
	boolean existsByUsername (String username);
	
	AppUser findByEmail (String email);	

	AppUser findByUsername(String username);

}