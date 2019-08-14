/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 12:43
 **/

package com.liuyan.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 12:43
 **/
@Entity
@Table(name = "users", schema = "public", catalog = "shiro")
public class Users {
    private String id;
    private String name;
    private String password;

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
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


