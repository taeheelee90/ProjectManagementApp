package haagahelia.fi.ProjectManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	private ProjectRepository repository;
	
     
}
