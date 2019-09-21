package com.xpwi.datastructures.simplesort.model;

/**
 * 实例类
 *
 * @author xpwi
 */
public class UserModel {
    private int uuid;
    private String naem;
    private int age;


    public UserModel(int uuid, String naem, int age) {
        super();
        this.uuid = uuid;
        this.naem = naem;
        this.age = age;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserModel [uuid=" + uuid + ", naem=" + naem + ", age=" + age
                + "]";
    }
}
