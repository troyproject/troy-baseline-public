package com.troy.commons.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;

import java.util.Collection;
import java.util.Map;

/**
 * 断言<br>
 * 断言某些对象或值是否符合规定，否则抛出异常。经常用于做变量检查
 *
 * @author dp
 */
public class Assert extends cn.hutool.core.lang.Assert {

    private static final StateTypeSuper STATETYPESUPER = StateTypeSuper.FAIL_PARAMETER;

    /**
     * 断言是否为真，如果为 {@code false} 抛出 {@code ServiceException} 异常<br>
     *
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, StateTypeSuper.FAIL_PARAMETER);
     * </pre>
     *
     * @param expression 布尔值
     * @param stateTypeSuper 错误码
     * @throws IllegalArgumentException if expression is {@code false}
     */
    public static void isTrue(boolean expression, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (false == expression) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
    }

    public static void isFalse(boolean expression, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (expression) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
    }
    
    public static void isNull(Object object, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (object != null) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
    }

    public static <T> T notNull(T object, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (object == null) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return object;
    }

    public static String notEmpty(String text, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (StrUtil.isEmpty(text)) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return text;
    }

    public static String notBlank(String text, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (StrUtil.isBlank(text)) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return text;
    }

    public static String notContain(String textToSearch, String substring, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (StrUtil.isNotEmpty(textToSearch) && StrUtil.isNotEmpty(substring) && textToSearch.contains(substring)) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return substring;
    }

    public static Object[] notEmpty(Object[] array, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (ArrayUtil.isEmpty(array)) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return array;
    }

    public static <T> T[] noNullElements(T[] array, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (ArrayUtil.hasNull(array)) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return array;
    }

    public static <T> Collection<T> notEmpty(Collection<T> collection, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (CollectionUtil.isEmpty(collection)) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return collection;
    }

    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        if (CollectionUtil.isEmpty(map)) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return map;
    }

    public static <T> T isInstanceOf(Class<?> type, T obj, StateTypeSuper stateTypeSuper, String errorMsg) throws ServiceException {
        stateTypeSuper = stateTypeSuper == null ? STATETYPESUPER : stateTypeSuper;
        notNull(type, stateTypeSuper, errorMsg);
        if (false == type.isInstance(obj)) {
            throw new ServiceException(stateTypeSuper, errorMsg);
        }
        return obj;
    }
}
