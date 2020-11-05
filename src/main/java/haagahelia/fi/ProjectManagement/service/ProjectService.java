package haagahelia.fi.ProjectManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class ProjectService {

	private ProjectRepository repository;
	
	/*
	 * Cancel
	 * 
	*/
	
	@Transactional
	public void cancelProject (Long projectId) {
		
		// Search and cancel order
		repository.findById(projectId).ifPresent(p -> p.cancel());
		
		
	}
	

}
