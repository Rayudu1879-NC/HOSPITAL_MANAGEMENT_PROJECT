package com.management.Hospital.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.management.Hospital.exception.PatientAdmissionException;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

//	@Autowired
//	private Environment environment;
//	private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
//	
//	@ExceptionHandler(PatientAdmissionException.class)
//	public ResponseEntity<ErrorInfo> patientAdmissionExceptionHandler(PatientAdmissionException exception)
//	{
//		LOGGER.error(exception.getMessage(), exception);
//		ErrorInfo errorInfo = new ErrorInfo();
//		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
//		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
//		return new ResponseEntity<> (errorInfo, HttpStatus.BAD_REQUEST);
//	}
//	// add appropriate annotation
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception)
//	{
//	LOGGER.error(exception.getMessage(), exception);
//	ErrorInfo errorInfo= new ErrorInfo();
//	errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//	errorInfo.setErrorMessage(environment.getProperty("General. EXCEPTION_MESSAGE")); 
//	return new ResponseEntity<>(error Info,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	// add appropriate annotation
//	@ExceptionHandler({MethodArgument Not ValidException.class, ConstraintViolationException.class}) public ResponseEntity<ErrorInfo> validatorExceptionHandler (Exception exception)
//	{
//	LOGGER.error(exception.getMessage(), exception);
//	String errorMsg;
//	if (exception instanceof MethodArgument NotValidException)
//	{
//	MethodArgumentNotValidException manvException= (MethodArgument NotValidException) exception;
	
	
	@Autowired
    private Environment environment;

    private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);

    @ExceptionHandler(PatientAdmissionException.class)
    public ResponseEntity<ErrorInfo> patientAdmissionExceptionHandler(PatientAdmissionException exception) {
        LOGGER.error(exception.getMessage(), exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        String errorMsg;
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manvException = (MethodArgumentNotValidException) exception;
            List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
            errorMsg = fieldErrors.stream().map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).collect(Collectors.joining(", "));
        } else if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException cvException = (ConstraintViolationException) exception;
            errorMsg = cvException.getConstraintViolations().stream().map(cv -> cv.getPropertyPath() + ": " + cv.getMessage()).collect(Collectors.joining(", "));
        } else {
            // This branch should ideally not be hit, as we are handling only MethodArgumentNotValidException and ConstraintViolationException
            errorMsg = "Validation error occurred";
        }

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value()); // Assuming it's a bad request due to validation
        errorInfo.setErrorMessage(errorMsg);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }	
}
