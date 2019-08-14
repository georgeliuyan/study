/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 16:47
 **/

package com.liuyan.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 16:47
 **/
@Entity
@Table(name = "resource_permission", schema = "public", catalog = "shiro")
public class ResourcePermission {
    private String id;
    private String resourceId;
    private String resourceIdent;
    private String resourceUrl;
    private String permissionId;
    private String permissionIdent;

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
    @Column(name = "resource_id", nullable = true, length = 50)
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "resource_ident", nullable = true, length = 100)
    public String getResourceIdent() {
        return resourceIdent;
    }

    public void setResourceIdent(String resourceIdent) {
        this.resourceIdent = resourceIdent;
    }

    @Basic
    @Column(name = "resource_url", nullable = true, length = 100)
    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Basic
    @Column(name = "permission_id", nullable = true, length = 50)
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "permission_ident", nullable = true, length = 100)
    public String getPermissionIdent() {
        return permissionIdent;
    }

    public void setPermissionIdent(String permissionIdent) {
        this.permissionIdent = permissionIdent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourcePermission that = (ResourcePermission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
        if (resourceIdent != null ? !resourceIdent.equals(that.resourceIdent) : that.resourceIdent != null)
            return false;
        if (resourceUrl != null ? !resourceUrl.equals(that.resourceUrl) : that.resourceUrl != null) return false;
        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;
        if (permissionIdent != null ? !permissionIdent.equals(that.permissionIdent) : that.permissionIdent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
        result = 31 * result + (resourceIdent != null ? resourceIdent.hashCode() : 0);
        result = 31 * result + (resourceUrl != null ? resourceUrl.hashCode() : 0);
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        result = 31 * result + (permissionIdent != null ? permissionIdent.hashCode() : 0);
        return result;
    }
}


