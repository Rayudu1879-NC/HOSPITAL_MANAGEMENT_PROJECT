package com.management.Hospital.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.management.Hospital.dto.PatientDTO;
import com.management.Hospital.exception.PatientAdmissionException;
import com.management.Hospital.service.PatientService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class PatientAPI {

	@Autowired
	private PatientService patientservice;
	
	@GetMapping(value="patients/(diagnosis)")
	public ResponseEntity<List<PatientDTO>>getPatientsByDiagnosis(@PathVariable @Pattern(regexp="[A-Za-z0-9]+(\\s[A-Za-z0-9]+)*", message="(patient.diagnosis.invalid}") String diagnosis) throws PatientAdmissionException 
	{
		List<PatientDTO> patientDTOs=patientservice.getListOfPatients(diagnosis);
		return new ResponseEntity<List<PatientDTO>>(patientDTOs, HttpStatus.OK);
	}
	@PostMapping(value="patients", consumes="application/json")
	public ResponseEntity<PatientDTO>admitPatient (@Valid @RequestBody PatientDTO patientDTO) throws PatientAdmissionException
	{
	PatientDTO patientDT02=patientservice.admitPatient(patientDTO);
	return new ResponseEntity<PatientDTO>(patientDT02,HttpStatus.CREATED);
	}
}
