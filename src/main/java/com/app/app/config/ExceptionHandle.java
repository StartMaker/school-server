package com.app.app.config;

import com.app.app.model.MolResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.dao.EmptyResultDataAccessException;

@ControllerAdvice
public class ExceptionHandle {

    MolResponse molResponse = new MolResponse();

//    @ExceptionHandler(MethodArgumentNotValidException.class)
////    @ResponseBody
////    public MolResponse handleBusinessException(MethodArgumentNotValidException exception) {
////        return molResponse.fail(exception.getBindingResult().getFieldError().getDefaultMessage());
////    }
////
////    @ExceptionHandler(EmptyResultDataAccessException.class)
////    @ResponseBody
////    public MolResponse handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
////        return molResponse.fail("查询内容不存在");
////    }
}
