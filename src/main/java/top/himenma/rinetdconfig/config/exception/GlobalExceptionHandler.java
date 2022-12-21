package top.himenma.rinetdconfig.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.himenma.rinetdconfig.common.api.CommonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

/**
 * 全局异常处理器
 */
@Slf4j
@SuppressWarnings("ALL")
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult bindException(HttpServletRequest request,
                                      HttpServletResponse response,
                                      BindException exception) {
        return CommonResult.failed(exception.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult methodArgumentNotValidException(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     MethodArgumentNotValidException exception) {
        return CommonResult.failed(exception.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult methodArgumentNotValidException(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     MissingServletRequestParameterException exception) {
        return CommonResult.failed(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult methodArgumentNotValidException(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     ConstraintViolationException exception) {
        System.out.println(exception.getLocalizedMessage());
        Iterator<ConstraintViolation<?>> iterator = exception.getConstraintViolations().iterator();
        if (iterator.hasNext()) {
            ConstraintViolationImpl next = (ConstraintViolationImpl)iterator.next();
            return CommonResult.failed(next.getMessage());
        }
        return CommonResult.failed(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult exception(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception exception) {
        return CommonResult.failed(exception.getMessage());
    }

}
