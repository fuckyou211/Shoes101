package com.shoes101.pojo;

import java.io.Serializable;

public class Colorpic implements Serializable {
    private Integer colorpicid;

    private Integer skuid;

    private String colorpicaddredd;

    private static final long serialVersionUID = 1L;

    public Integer getColorpicid() {
        return colorpicid;
    }

    public void setColorpicid(Integer colorpicid) {
        this.colorpicid = colorpicid;
    }

    public Integer getSkuid() {
        return skuid;
    }

    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }

    public String getColorpicaddredd() {
        return colorpicaddredd;
    }

    public void setColorpicaddredd(String colorpicaddredd) {
        this.colorpicaddredd = colorpicaddredd == null ? null : colorpicaddredd.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", colorpicid=").append(colorpicid);
        sb.append(", skuid=").append(skuid);
        sb.append(", colorpicaddredd=").append(colorpicaddredd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}