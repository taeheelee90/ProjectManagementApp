package haagahelia.fi.ProjectManagement.model.project;

/*
 * Project Status are automatically calculated when start date and end date are provided.
 * CANCEL is only available for ADMIN.
 * CANCELLED PROJECT can not be updated any more.
 * 
 */



public enum ProjectStatus {
	WAITING, PROCEEDING, COMPLETE, CANCEL;
}
