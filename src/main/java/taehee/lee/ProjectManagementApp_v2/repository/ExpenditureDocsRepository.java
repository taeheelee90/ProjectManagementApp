package taehee.lee.ProjectManagementApp_v2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import taehee.lee.ProjectManagementApp_v2.domain.project.ExpenditureDocs;



@RepositoryRestResource
public interface ExpenditureDocsRepository extends CrudRepository<ExpenditureDocs, Long> {

}
