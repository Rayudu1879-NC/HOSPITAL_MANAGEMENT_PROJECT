package com.management.Hospital.validator;

import java.time.LocalDate;

import com.management.Hospital.dto.PatientDTO;
import com.management.Hospital.exception.PatientAdmissionException;

public class PatientValidator {
	
	public PatientValidator() {
		super();
	}
	public static void validatePatient(PatientDTO patientDTO) throws PatientAdmissionException
	{
		if(isValidDateOfBirth(patientDTO.getDateOfBirth())==false) 
		{
			throw new PatientAdmissionException("PatientValidator.INVALID_DOB");
		}
	}
	public static Boolean isValidDateOfBirth(LocalDate dateOfBirth) throws PatientAdmissionException
	{
		LocalDate today= LocalDate.now();
		if(dateOfBirth.isAfter(today) || dateOfBirth.isBefore(today.minusYears(100)))
		{
			return false;
		}
	return true;
	}
	


}
