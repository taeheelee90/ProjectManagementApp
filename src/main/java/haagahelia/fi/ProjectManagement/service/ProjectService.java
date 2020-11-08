package haagahelia.fi.ProjectManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectPredicate;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class ProjectService {

	private ProjectRepository repository;
	
	
	/*
	 * Search Project
	
	
	public List<Project> findProjects (ProjectSearch projectSearch){	
		
		return repository.findAllByNameAndStatus(projectSearch);
	}

	 */

}
