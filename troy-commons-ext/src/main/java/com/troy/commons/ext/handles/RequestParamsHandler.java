package com.troy.commons.ext.handles;

/**
 * gateway中传递userId处理
 *
 * @author dp
 */
//@Component
//@Aspect
//public class RequestParamsHandler {
//
//    @Order(10)
//    @Before("@within(restController)")
//    public void validUserStatus(JoinPoint joinPoint, RestController restController) {
//        HttpServletRequest request = null;
//        List<Object> objectList = Lists.newArrayList();
//        String userId = null;
//        Object[] args = joinPoint.getArgs();
//        int length = args == null ? 0 : args.length;
//        // 遍历controller接收的参数
//        for (int i = 0; i < length; i++) {
//            if (args[i] instanceof HttpServletRequest) {
//                request = (HttpServletRequest) args[i];
//            } else {
//                objectList.add(args[i]);
//            }
//        }
//        if (request != null) {
//            userId = request.getHeader("userId");
//        }
//        final String userIdFinal = userId;
//        if (!CollectionUtils.isEmpty(objectList)) {
//            objectList.stream().forEach(object -> {
//                Method method;
//                try {
//                    if (object instanceof Map) {
//                        if (userIdFinal != null) {
//                            ((Map) object).put("userId", userIdFinal);
//                        }
//                    } else {
//                        method = object.getClass().getDeclaredMethod("setUserId", String.class);
//                        if (method != null && userIdFinal != null) {
//                            method.invoke(object, userIdFinal);
//                        }
//                    }
//                } catch (Exception e) {
//
//                }
//            });
//
//        }
//    }
//}
