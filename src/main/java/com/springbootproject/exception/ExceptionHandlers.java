package com.springbootproject.exception;

import com.springbootproject.exception.student.StudentWithSuchAnIdDoesNotExistException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleElementNotFoundException(HttpServletRequest req, ElementNotFoundException e) {
        return initGenericErrorModelAndView(req, e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EnrollmentStatusException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handleEnrollmentStatusException(HttpServletRequest req, EnrollmentStatusException e) {
        return initGenericErrorModelAndView(req, e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidInputDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleInvalidInputDataException(HttpServletRequest req, InvalidInputDataException e) {
        return initGenericErrorModelAndView(req, e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        return initGenericErrorModelAndView(req, e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentWithSuchAnIdDoesNotExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handleStudentWithSuchAnIdDoesNotExistException(HttpServletRequest req, StudentWithSuchAnIdDoesNotExistException e) {
        return initGenericErrorModelAndView(req, e, HttpStatus.NOT_FOUND);
    }


    private ModelAndView initGenericErrorModelAndView(HttpServletRequest req, Exception e, HttpStatus status) {
        log.error("Exception {} occurred during processing of {} request to URL {}", e.getMessage(), req.getMethod(), req.getRequestURL());
        String filename = "" + status.value() + ".png";
        log.info("Error image with filename {} should be used", filename);

        return new ModelAndView("errors/generic-error")
                .addObject("errorMessage", e.getMessage())
                .addObject("filename", filename);
    }
}
