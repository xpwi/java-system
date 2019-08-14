package com.xiaopengwei.datastructures.arrays.object;

/**
 * 实例类
 *
 * @author XiaoPengwei.com
 */
public class UserModel {
    private int uuid;
    private String name;
    private int age;


    public UserModel(int uuid, String name, int age) {
        super();
        this.uuid = uuid;
        this.name = name;
        this.age = age;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getNaem() {
        return name;
    }

    public void setNaem(String naem) {
        this.name = naem;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserModel [uuid=" + uuid + ", name=" + name + ", age=" + age
                + "]";
    }
}
