package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Employer extends AbstractEntity {

	@NotEmpty(message="location must not be empty")
	@NotNull(message="location must not be null")
	@Size(max=50)
	private String location;

	@OneToMany // relates one Employer to many Job class object instances
	@JoinColumn(name="employer_id") // foreign key for jobs
	private List<Job> jobs = new ArrayList<>(); // list of Job class object instances
//	Spring Boot knows that Employer.id is "employer_id"
//	  because every model extending AbstractEntity has an "id" field
//	    therefore @JoinColumn must use a more specific name than "id"

	public Employer() {}

	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }

	public List<Job> getJobs() { return jobs; }
	public void setJobs(List<Job> jobs) { this.jobs = jobs; }
}
