package com.troy.redis.lock;

/**
 * 全局锁，包括锁的名称
 *
 * @author troy
 */
public class Lock {

    /**
     * 锁名称
     */
    private String name;

    /**
     * 锁名称对应的值
     */
    private String value = "TRUE";

    public Lock(String name) {
        this.name = name;
    }

    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Lock [name = " + name + ", value = " + value + "]";
    }
}