package com.troy.commons.ext.handles;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqData;
import com.troy.commons.exception.business.BusinessException;
import com.troy.commons.exception.enums.StateTypeSuper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import java.util.Iterator;
import java.util.Set;

/**
 * controller 入参校验切面
 * 用法：使用hibernate.validator进行校验，入参DTO必须要继承ReqData；
 * @author dp
 */
@Order(2)
@Slf4j
@Aspect
@Component
public class RequestValidateAspectHandel {

    @Resource
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PutMapping)"
    )
//    @Pointcut("execution(* com.*..controller.*.*(..))")
    private void parameterPointCut() {

    }

    /**
     * 处理
     *
     * @param joinPoint
     * @param req
     */
    @Before("parameterPointCut() && args(req, ..)")
    public <DT extends ReqData> void validateParameter(JoinPoint joinPoint, Req<DT> req) {
        DT reqData = req.getData();// req为spring包装后的对象，不为空
        Assert.notNull(reqData, "入参不规范Req<DT>");
        Set<ConstraintViolation<DT>> validErrors = this.localValidatorFactoryBean.validate(reqData, new Class[]{Default.class});
        Iterator iterator = validErrors.iterator();
        StringBuilder errorMsg = new StringBuilder();

        while (iterator.hasNext()) {
            ConstraintViolation constraintViolation = (ConstraintViolation) iterator.next();
            String error = constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage();
            errorMsg.append(iterator.hasNext() ? error + "; " : error);
        }
        if (!validErrors.isEmpty()) {
            throw new BusinessException(StateTypeSuper.FAIL_PARAMETER, errorMsg.toString());
        }
    }
}
