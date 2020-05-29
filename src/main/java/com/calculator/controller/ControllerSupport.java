package com.calculator.controller;

import com.calculator.constance.StatusConstants;
import com.calculator.model.Response;
import com.calculator.model.Status;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public interface ControllerSupport {

    default <T> Response<T> success() {
        return new Response<>(new Status(StatusConstants.HttpConstants.SUCCESS), null);
    }

    default <T> Response<T> success(T data) {
        return new Response<>(new Status(StatusConstants.HttpConstants.SUCCESS), data);
    }

    default <T> Response<T> badRequest(StatusConstants.HttpConstants httpConstants, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new Response<>(new Status(httpConstants), null);
    }

    default <T> Response<T> notFound(StatusConstants.HttpConstants httpConstants, HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return new Response<>(new Status(httpConstants), null);
    }

    default <T> Response<T> serverError(HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new Response<>(new Status(StatusConstants.HttpConstants.INTERNAL_SERVER_ERROR), null);
    }

    default <T> Response<T> serverError(StatusConstants.HttpConstants httpConstants, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new Response<>(new Status(httpConstants), null);
    }

}
