package haagahelia.fi.ProjectManagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project, Long> {

	/*
	 * pet> ownerRepository
	 * 검색을 위한 쿼리 넣을 것
	 * findByKeyWord (String keyword)
	 * 
	 * pet> PetRepository
	 * List<ProjectStatus> findByStatus
	 * */

}
