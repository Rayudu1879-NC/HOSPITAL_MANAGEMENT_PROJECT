package com.management.Hospital.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	//add all the valid validation annotations to the properties

	private Integer patientId;
	private String patientName;
	private String gender;
	private LocalDate dateOfBirth;
	private LocalDate admissionDate;
	private String diagnosis;
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public LocalDate getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}


	//this line is added newly1
	//this is the second line added from the github master branch itself
	//this is the third line adding from the github

	//added fourth line to newscb in local
	//added fifth line to newscb

	//added new one 6th line

	//added 7th line

	//9th line
	
}
