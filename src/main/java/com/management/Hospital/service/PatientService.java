package com.management.Hospital.service;

import java.util.List;

import com.management.Hospital.dto.PatientDTO;
import com.management.Hospital.exception.PatientAdmissionException;

public interface PatientService {

	List<PatientDTO> getListOfPatients(String diagnosis) throws PatientAdmissionException;
    
    PatientDTO admitPatient(PatientDTO patientDTO) throws PatientAdmissionException;
}
