package taehee.lee.ProjectManagementApp_v2.domain.project;

/*
 * Project Status are automatically calculated when start date and end date are provided.
 * 
 * COMPLETE PROJECT can not be updated anymore.
 * 
 */



public enum ProjectStatus {
	WAITING, PROCEEDING, COMPLETE;
}