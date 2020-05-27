package com.techjs.askitnow.exception.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.techjs.askitnow.exception.EmailAreadyRegisteredException;
import com.techjs.askitnow.exception.ErrorDetail;
import com.techjs.askitnow.exception.InvalidRequestException;
import com.techjs.askitnow.exception.ResourceNotFoundException;
import com.techjs.askitnow.exception.UnAuthorizedException;
import com.techjs.askitnow.exception.UsernameNotAvailableException;
import com.techjs.askitnow.exception.ValidationError;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException e) {
		ErrorDetail ed = new ErrorDetail();
		ed.setTimestamp(new Date().getTime());
		ed.setTitle("Resource Not Found");
		ed.setStatus(HttpStatus.NOT_FOUND.value());
		ed.setDetail(e.getMessage());
		ed.setDeveloperMessage(e.getClass().getCanonicalName());
		
		return new ResponseEntity<>(ed, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpServletRequest request) {
		ErrorDetail ed = new ErrorDetail();
		ed.setTimestamp(new Date().getTime());
		ed.setTitle("Validation Error");
		ed.setStatus(HttpStatus.BAD_REQUEST.value());
		ed.setDetail("Input Validation Faild");
		ed.setDeveloperMessage(manve.getClass().getCanonicalName());
		
		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		fieldErrors.forEach(fe -> {
			List<ValidationError> validationErrorsList = ed.getValidationErrors().get(fe.getField());
			if (validationErrorsList == null) {
				validationErrorsList = new ArrayList<>();
				ed.getValidationErrors().put(fe.getField(), validationErrorsList);
			}
			ValidationError ve = new ValidationError();
			ve.setCode(fe.getCode());
			ve.setMessage(fe.getDefaultMessage());
			validationErrorsList.add(ve);
		});
		
		return new ResponseEntity<>(ed, null, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmailAreadyRegisteredException.class)
	public ResponseEntity<?> handlerEmailAreadyExistException(EmailAreadyRegisteredException e) {
		ErrorDetail ed = new ErrorDetail();
		ed.setTimestamp(new Date().getTime());
		ed.setTitle("Email Aready Registered");
		ed.setStatus(HttpStatus.CONFLICT.value());
		ed.setDetail(e.getMessage());
		ed.setDeveloperMessage(e.getClass().getCanonicalName());
		return new ResponseEntity<>(ed, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UsernameNotAvailableException.class)
	public ResponseEntity<?> handlerUsernameNotAvailableException(UsernameNotAvailableException e) {
		ErrorDetail ed = new ErrorDetail();
		ed.setTimestamp(new Date().getTime());
		ed.setTitle("Username not available");
		ed.setStatus(HttpStatus.CONFLICT.value());
		ed.setDetail(e.getMessage());
		ed.setDeveloperMessage(e.getClass().getCanonicalName());
		return new ResponseEntity<>(ed, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<?> handleUnauthorizedException(UnAuthorizedException e) {
		ErrorDetail ed = new ErrorDetail();
		ed.setTimestamp(new Date().getTime());
		ed.setTitle("You're not authorized to perform this action");
		ed.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		ed.setDetail(e.getMessage());
		ed.setDeveloperMessage(e.getClass().getCanonicalName());
		return new ResponseEntity<>(ed, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<?> handleInvalidRequestException(InvalidRequestException e) {
		ErrorDetail ed = new ErrorDetail();
		ed.setTimestamp(new Date().getTime());
		ed.setTitle("Request contains invalid parameters");
		ed.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		ed.setDetail(e.getMessage());
		ed.setDeveloperMessage(e.getClass().getCanonicalName());
		return new ResponseEntity<>(ed, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException cve) {
		ErrorDetail ed = new ErrorDetail();
		ed.setTimestamp(new Date().getTime());
		ed.setTitle("Validation Error");
		ed.setStatus(HttpStatus.BAD_REQUEST.value());
		ed.setDetail("Input Validation Faild");
		ed.setDeveloperMessage(cve.getClass().getCanonicalName());
		
		Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
		constraintViolations.forEach(fe -> {
			List<ValidationError> validationErrorsList = ed.getValidationErrors().get(fe.getPropertyPath().toString());
			if (validationErrorsList == null) {
				validationErrorsList = new ArrayList<>();
				ed.getValidationErrors().put(fe.getPropertyPath().toString(), validationErrorsList);
			}
			ValidationError ve = new ValidationError();
			ve.setCode(fe.getRootBean().getClass().getName() + "." + fe.getPropertyPath().toString());
			ve.setMessage(fe.getMessage());
			validationErrorsList.add(ve);
		});
		
		return new ResponseEntity<>(ed, null, HttpStatus.BAD_REQUEST);
	}
	
}
