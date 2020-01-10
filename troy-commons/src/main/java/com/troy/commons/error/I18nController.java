package com.troy.commons.error;

import com.troy.commons.enums.LangEnum;
import com.troy.commons.exception.enums.StateTypeSuper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@Controller
public class I18nController {

    private MessageSource messageSource;

    @Value("${spring.messages.basename}")
    private String basename;

    @Value("${spring.messages.cache-seconds}")
    private long cacheMillis;

    @Value("${spring.messages.encoding}")
    private String encoding;

    /**
     * 初始化
     *
     * @return
     */
    private MessageSource initMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        log.info("baseName====>:" + this.basename);
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding(encoding);
        messageSource.setCacheMillis(cacheMillis);
        return messageSource;
    }

    /**
     * 设置当前的返回信息
     *
     * @param request
     * @param stateTypeSuper
     * @return
     */
    public String getMessage(HttpServletRequest request, StateTypeSuper stateTypeSuper) {
        return getMessage(request, stateTypeSuper, null);
    }

    /**
     * 设置当前的返回信息
     *
     * @param request
     * @param stateTypeSuper
     * @param placeholders
     * @return
     */
    public String getMessage(HttpServletRequest request, StateTypeSuper stateTypeSuper, Object[] placeholders) {
        if (messageSource == null) {
            messageSource = initMessageSource();
        }
//        String lauage = request.getHeader("Accept-Language");
        String lauage = request.getHeader("lang");
        //默认没有就是请求地区的语言
        Locale locale;
        if (lauage == null) {
            locale = request.getLocale();
        } else if (LangEnum.EN_US.code().equals(lauage)) {
            locale = Locale.ENGLISH;
        } else if (LangEnum.ZH_CN.code().equals(lauage)) {
            locale = Locale.CHINA;
        } else if (LangEnum.KO_KR.code().equals(lauage)) {
            locale = Locale.KOREA;
        } else {
            //其余的不正确的默认就是本地的语言
            locale = Locale.ENGLISH;
        }
        String result = null;
        try {
            result = messageSource.getMessage(stateTypeSuper.getCode(), placeholders, locale);
        } catch (NoSuchMessageException e) {
            log.error("Cannot find the error message of internationalization, return the original error message.");
        }
        if (result == null) {
            return stateTypeSuper.getDepict();
        }
        return result;
    }
}
