package com.management.Hospital.exception;

public class PatientAdmissionException extends Exception	{

	 public PatientAdmissionException() {
	        super();
	    }

	    public PatientAdmissionException(String message) {
	        super(message);
	    }

	    public PatientAdmissionException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public PatientAdmissionException(Throwable cause) {
	        super(cause);
	    }
}
