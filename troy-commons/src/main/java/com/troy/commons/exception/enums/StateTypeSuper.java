package com.troy.commons.exception.enums;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName StateTypeSuper
 * @Description 状态
 * @date 2017年7月20日 下午1:32:06
 * @history 版本 作者 时间 注释
 */
public interface StateTypeSuper {

    StateTypeSuper SUCCESS_DEFAULT = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "成功";
        }

        @Override
        public String getCode() {
            return "0";
        }
    };

    /////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                      9000--9999 定义通用性失败状态                        //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////

    StateTypeSuper FAIL_DEFAULT = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "失败";
        }

        @Override
        public String getCode() {
            return "9999";
        }
    };

    StateTypeSuper FAIL_PARAMETER = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "参数错误";
        }

        @Override
        public String getCode() {
            return "9998";
        }
    };

    StateTypeSuper FAIL_VALUE_INVALID = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "{0} 是无效的";
        }

        @Override
        public String getCode() {
            return "9998";
        }
    };

    StateTypeSuper FAIL_VALUE_INVALID_DETAILS = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "{0}[{1}] 是无效的";
        }

        @Override
        public String getCode() {
            return "9998";
        }
    };

    StateTypeSuper FAIL_NO_PERMISSION = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "没有权限";
        }

        @Override
        public String getCode() {
            return "9997";
        }
    };

    StateTypeSuper FAIL_REQUEST = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "不支持的请求方法";
        }

        @Override
        public String getCode() {
            return "9996";
        }
    };

    StateTypeSuper FAIL_UNKNOWN_CLIENT = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "未知的客户端";
        }

        @Override
        public String getCode() {
            return "9995";
        }
    };

    StateTypeSuper FAIL_UNKNOWN_SERVICE = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "未知的服务请求";
        }

        @Override
        public String getCode() {
            return "9994";
        }
    };

    StateTypeSuper FAIL_UNEXPECTED_RESULTS = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "意外的结果";
        }

        @Override
        public String getCode() {
            return "9993";
        }
    };

    StateTypeSuper FAIL_SUCCESS_BATCH_PROCESS = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "批量处理结果既有成功也有失败";
        }

        @Override
        public String getCode() {
            return "9992";
        }
    };

    StateTypeSuper FAIL_SERVICE_REPEATED = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "重复的业务请求";
        }

        @Override
        public String getCode() {
            return "9991";
        }
    };


    StateTypeSuper FAIL_AUTH_USER = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "用户认证失败";
        }

        @Override
        public String getCode() {
            return "9990";
        }
    };

    StateTypeSuper FAIL_AUTH_USERNAME_NOT_FOUND = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "用户不存在";
        }

        @Override
        public String getCode() {
            return "9989";
        }
    };

    StateTypeSuper FAIL_AUTH_USER_DISABLED = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "用户被禁用";
        }

        @Override
        public String getCode() {
            return "9988";
        }
    };

    StateTypeSuper FAIL_AUTH_USER_BAD_CREDENTIALS = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "坏的用户凭证";
        }

        @Override
        public String getCode() {
            return "9987";
        }
    };

    StateTypeSuper FAIL_AUTH_CLIENT = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "客户端认证失败";
        }

        @Override
        public String getCode() {
            return "9986";
        }
    };

    StateTypeSuper FAIL_GATEWAY = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "网关错误";
        }

        @Override
        public String getCode() {
            return "9985";
        }
    };

    StateTypeSuper FAIL_CALL_REMOTE = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "远程调用交易所异常{0}";
        }

        @Override
        public String getCode() {
            return "9984";
        }
    };

    StateTypeSuper FAIL_MQ_DELAY_LEVEL = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "延迟级别错误, Topic=%s, MessageKey=%s";
        }

        @Override
        public String getCode() {
            return "9983";
        }
    };

    StateTypeSuper FAIL_MQ_RETRY_THREE = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "MQ重试三次,仍然发送失败, Topic=%s, MessageKey=%s";
        }

        @Override
        public String getCode() {
            return "9982";
        }
    };

    StateTypeSuper FAIL_404 = new StateTypeSuper() {

        @Override
        public String getDepict() {
            return "资源不存在";
        }

        @Override
        public String getCode() {
            return "9981";
        }
    };

    String getCode();

    String getDepict();
}
