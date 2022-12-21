package top.himenma.rinetdconfig.config.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import top.himenma.rinetdconfig.common.annotation.LogHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aspect
@Order(1)
@Configuration
public class ControllerLogAspect {

    public static ObjectMapper jsonMapper = new ObjectMapper();

    @Pointcut("@annotation(top.himenma.rinetdconfig.common.annotation.LogHandler)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LogHandler logHandler = method.getAnnotation(LogHandler.class);
        String title = logHandler.title();


        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        StringBuilder reqLog = new StringBuilder();
        ArrayList<Object> reqArgs = new ArrayList<>();

        buildLogArgs(signature, title, request, reqLog, reqArgs, joinPoint.getArgs());
        reqLog.append("Time-Consuming : {} ms\n");
        reqArgs.add((endTime - startTime    ));
        reqLog.append("------------------------------------------- End --------------------------------------------\n");
        log.info(reqLog.toString(), reqArgs.toArray());

        return proceed;
    }

    /**
     * 出现异常时 打印出异常信息。
     * */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Exception e) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LogHandler logHandler = method.getAnnotation(LogHandler.class);
        String title = logHandler.title();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        StringBuilder reqLog = new StringBuilder();
        List<Object> reqArgs = new ArrayList<>();
        buildLogArgs(joinPoint.getSignature(), title, request, reqLog, reqArgs, joinPoint.getArgs());
        reqLog.append("Exception       : {}\n");
        reqArgs.add(e.getLocalizedMessage());
        reqLog.append("------------------------------------------- End --------------------------------------------\n");
        log.error(reqLog.toString(), reqArgs.toArray());
    }

    private static void buildLogArgs(Signature signature, String title, HttpServletRequest request, StringBuilder reqLog, List<Object> reqArgs, Object[] args) {
        reqLog.append("\n");
        reqLog.append("------------------------------------------- Start ------------------------------------------\n");
        reqLog.append("Title:         : {} \n");
        reqArgs.add(title);
        reqLog.append("URL            : {} \n");
        reqArgs.add(request.getRequestURL().toString());
        reqLog.append("HTTP Method    : {}\n");
        reqArgs.add(request.getMethod());
        reqLog.append("Class Method   : {}.{}\n");
        reqArgs.add(signature.getDeclaringTypeName());
        reqArgs.add(signature.getName());
        List<Object> objects = new ArrayList<>();
        for (Object arg : args) {
            boolean response = (arg instanceof HttpServletResponse);
            boolean requestd = (arg instanceof HttpServletRequest);
            if (!requestd && !response) {
                objects.add(arg);
            }
        }
        if (!objects.isEmpty()) {
            for (int i = 0; i < objects.size(); i++) {
                Object object = objects.get(i);
                if (object instanceof MultipartFile){
                    objects.set(i,"multipartFile类型参数");
                }else if (object instanceof MultipartFile[]){
                    objects.set(i,"multipartFile[]类型参数");
                }
            }
            reqLog.append("Request Args   : {}\n");
            try {
                reqArgs.add(jsonMapper.writeValueAsString(objects));
            } catch (JsonProcessingException e) {
                log.warn("json转化异常：{}",objects);
            }
        }

    }
}
