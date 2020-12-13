package com.imooc.enums;

/**
 * @author 龙伟
 * @Description:
 * @date 2020/12/13 21:27
 */
public enum  Sexx {
    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密");

    public final Integer type;
    public final String value;

    Sexx(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
