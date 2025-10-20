package com.example.demoproject.exception;

import com.example.demoproject.common.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Map<String,String>>handleValidationException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> errors = new HashMap<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()){
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return Result.error(400,"数据验证失败",errors);
    }


    @ExceptionHandler(BusinessException.class)
    public Result<Object> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        e.printStackTrace();
        return Result.error(500,"系统内部错误: "+ e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<Void> handleAccessDeniedException(AccessDeniedException e){
        return Result.error(403,"权限不足: "+ e.getMessage());
    }
}
