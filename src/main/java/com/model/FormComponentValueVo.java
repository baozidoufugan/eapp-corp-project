package com.model;

import com.taobao.api.internal.mapping.ApiField;

import java.io.Serializable;

/**
 * @author zhaozhongyuan
 * 2020/2/19/1:33 下午
 */
public class FormComponentValueVo implements Serializable {
    private static final long serialVersionUID = -9134051397655522705L;

    @ApiField("name")
    private String name;
    @ApiField("value")
    private Object value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
