package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "shoescatalog")
public class Shoescatalog implements Serializable {
    @Id
    @GeneratedValue
    private Integer catalogid;

    private String catalogname;

    private Integer parentid;

    private Integer isleaf;

    private static final long serialVersionUID = 1L;

    public Integer getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(Integer catalogid) {
        this.catalogid = catalogid;
    }

    public String getCatalogname() {
        return catalogname;
    }

    public void setCatalogname(String catalogname) {
        this.catalogname = catalogname == null ? null : catalogname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Integer isleaf) {
        this.isleaf = isleaf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catalogid=").append(catalogid);
        sb.append(", catalogname=").append(catalogname);
        sb.append(", parentid=").append(parentid);
        sb.append(", isleaf=").append(isleaf);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Shoescatalog() {
    }
}