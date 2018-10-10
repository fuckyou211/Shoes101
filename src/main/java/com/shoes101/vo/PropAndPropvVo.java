package com.shoes101.vo;

/**
 * 除颜色和尺码外的属性
 */

public class PropAndPropvVo {

    private String property;
    private String propertyvalue;

    @Override
    public String toString() {
        return "PropAndPropvVo{" +
                "property='" + property + '\'' +
                ", propertyvalue='" + propertyvalue + '\'' +
                '}';
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPropertyvalue() {
        return propertyvalue;
    }

    public void setPropertyvalue(String propertyvalue) {
        this.propertyvalue = propertyvalue;
    }
}
