/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 12:43
 **/

package com.liuyan.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 12:43
 **/
@Entity
public class Permission {
    private String id;
    private String name;
    private String descr;

    @Id
    @GeneratedValue(generator = "autoid")
    @GenericGenerator(name = "autoid", strategy = "uuid")
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "descr", nullable = true, length = 100)
    public String getDescr() {
        return descr;
    }

    public void setDescr(String url) {
        this.descr = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        return result;
    }
}


