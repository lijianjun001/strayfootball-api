package com.strayfootball.api.config;

/***
 * 角色
 * @author 祥仔
 */
public enum RoleType {
    TEACHER(1, "教练员"),PLAYER(2, "球员"),TEAMDOCTOR(3, "队医"),ADMIN(4, "管理员");

    private int value;
    private String name;

    RoleType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
