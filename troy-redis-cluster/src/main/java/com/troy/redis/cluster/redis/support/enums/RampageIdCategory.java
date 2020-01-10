package com.troy.redis.cluster.redis.support.enums;

public enum RampageIdCategory {
    RAMPAGE_ADMIN_ID("thoth:admin:id:_id:{id}"), RAMPAGE_MSGCENTER_ID("thoth:msgcenter:id:_id:{id}");
    private String value;

    RampageIdCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
