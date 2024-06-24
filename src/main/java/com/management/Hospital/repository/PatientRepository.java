package com.management.Hospital.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.management.Hospital.entity.Patient;

public interface PatientRepository extends CrudRepository<Patient,Integer>
{
	List<Patient> findByDiagnosis(String diagnosis);
}
