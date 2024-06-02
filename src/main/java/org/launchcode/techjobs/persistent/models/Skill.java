package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

	@NotEmpty
	@Size(max=100)
	private String description;

	@ManyToMany(mappedBy="skills")
	private List<Job> jobs = new ArrayList<>();

	public Skill() {}

	public String getDescription() { return description; }
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Job> getJobs() { return jobs; }
	public void setJobs(List<Job> jobs) { this.jobs = jobs; }
}
