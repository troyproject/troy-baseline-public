package com.troy.commons.error;

import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.exception.enums.StateTypeSuper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一处理异常
 *
 * @author ydp
 *
 */
@Slf4j
@RestController
@RequestMapping("/error")
public class GlobalErrorController extends AbstractErrorController {

    @Autowired
    private I18nController i18n;

    @Autowired
    public GlobalErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    public <DT extends ResData> Res<DT> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if(status.compareTo(HttpStatus.NOT_FOUND) == 0) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_404, i18n.getMessage(request, StateTypeSuper.FAIL_404));
        }
        return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, i18n.getMessage(request, StateTypeSuper.FAIL_DEFAULT));
    }
}
