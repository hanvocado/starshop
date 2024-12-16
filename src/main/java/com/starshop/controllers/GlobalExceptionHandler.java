package com.starshop.controllers;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.starshop.models.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNoSuchElementException(NoSuchElementException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", "Không tìm thấy element.");
        return mav;
    }

    @ExceptionHandler(AppException.class)
    public ModelAndView handleAppException(AppException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorCode", ex.getCode());
        mav.addObject("errorMessage", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", "Sorry, có lỗi. Vui lòng thử lại");
        return mav;
    }
}
