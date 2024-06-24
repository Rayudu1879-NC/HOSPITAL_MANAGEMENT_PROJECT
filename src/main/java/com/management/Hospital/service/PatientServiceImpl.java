package com.management.Hospital.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.Hospital.dto.PatientDTO;
import com.management.Hospital.entity.Patient;
import com.management.Hospital.exception.PatientAdmissionException;
import com.management.Hospital.repository.PatientRepository;
import com.management.Hospital.validator.PatientValidator;

@Service("patientService")
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<PatientDTO> getListOfPatients(String diagnosis) throws PatientAdmissionException {
		List<Patient> patients = patientRepository.findByDiagnosis(diagnosis);
		if (patients.isEmpty()) {
			throw new PatientAdmissionException("PatientService.PATIENT_UNAVAILABLE");
		}
		List<PatientDTO> patientDTOs = new ArrayList<>();
		for (Patient patient : patients) {
			PatientDTO patientDTO = PatientDTO.prepareDTO(patient);
			patientDTOs.add(patientDTO);
		}
		patientDTOs.sort((p1, p2) -> p2.getAdmissionDate().compareTo(p1.getAdmissionDate()));
		return patientDTOs;
	}

	@Override
	public PatientDTO admitPatient(PatientDTO patientDTO) throws PatientAdmissionException {
		PatientValidator.validatePatient(patientDTO);
		Patient patient = PatientDTO.prepareEntity(patientDTO);
		Patient savedPatient = patientRepository.save(patient);
		patientDTO.setPatientId(savedPatient.getPatientId());
		return patientDTO;
	}
}