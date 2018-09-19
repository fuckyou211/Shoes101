package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name ="demo")
public class Demo implements Serializable {
    @Id
    @GeneratedValue
    private Integer did;

    private String dtext;

    private String dstate;

    private static final long serialVersionUID = 1L;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDtext() {
        return dtext;
    }

    public void setDtext(String dtext) {
        this.dtext = dtext == null ? null : dtext.trim();
    }

    public String getDstate() {
        return dstate;
    }

    public void setDstate(String dstate) {
        this.dstate = dstate == null ? null : dstate.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", did=").append(did);
        sb.append(", dtext=").append(dtext);
        sb.append(", dstate=").append(dstate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Demo() {
    }
}