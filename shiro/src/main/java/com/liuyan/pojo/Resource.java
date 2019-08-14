/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 16:37
 **/

package com.liuyan.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 16:37
 **/
@Entity
@Table(name = "resource", schema = "public", catalog = "shiro")
public class Resource {
    private String id;
    private String urlName;
    private String urlIdent;

    @Id
    @GeneratedValue(generator = "autoid")
    @GenericGenerator(name = "autoid", strategy = "uuid")
    @Column(name = "id", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url_name", nullable = true, length = 100)
    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    @Basic
    @Column(name = "url_ident", nullable = true, length = 50)
    public String getUrlIdent() {
        return urlIdent;
    }

    public void setUrlIdent(String urlIdent) {
        this.urlIdent = urlIdent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource that = (Resource) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (urlName != null ? !urlName.equals(that.urlName) : that.urlName != null) return false;
        if (urlIdent != null ? !urlIdent.equals(that.urlIdent) : that.urlIdent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (urlName != null ? urlName.hashCode() : 0);
        result = 31 * result + (urlIdent != null ? urlIdent.hashCode() : 0);
        return result;
    }
}


