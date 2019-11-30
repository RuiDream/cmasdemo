package com.example.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author : LJ
 * @version V1.0
 */
@Entity /*声明类为实体或表*/
@Table(name = "user_model") /*声明表名*/
@EntityListeners(AuditingEntityListener.class)
public class UserModel {

    @Id  /*指定的类的属性，用于识别（一个表中的主键）*/
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*指定如何标识属性可以被初始化，例如自动、手动、或从序列表中获得的值。*/
    /*4种主键生成策略：GenerationType.TABLE,GenerationType.SEQUENCE,GenerationType.IDENTITY和GenerationType.AUTO
    * GenerationType.IDENTITY   此种主键生成策略就是通常所说的主键自增长,数据库在插入数据时,会自动给主键赋值
    * */
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT 'user_model主键'")
    private Long user_id;

    public Long getUserId() {
        return user_id;
    }
    public void setUserId(Long id) {
        this.user_id = id;
    }

    @Column(name = "username", length = 32)/*指定持久属性栏属性。*/
    private String username;

    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }


    @Column(name = "createtime")
    private String  createtime;
    public String getCreateTime() {
        return createtime;
    }
    public void setCreateTime(String createtime){this.createtime=createtime;}


    @Column(name = "access_level")
    private Integer access_level; /*0 - doctor; 1 - admin*/
    public Integer getAccessLevel() {
        return access_level;
    }
    public void setAccessLevel(Integer accessLevel) {
        this.access_level = accessLevel;
    }

    @Column(name = "password", length = 32)
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

//    @OneToMany(mappedBy = "user")
//    private Set<Task> tasks = new HashSet<>();
//
//    @OneToMany(mappedBy = "videoIndex")
//    private Set<Label> labels = new HashSet<>();

    public UserModel(){}

}

