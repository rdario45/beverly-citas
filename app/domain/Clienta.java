package domain;

import acl.BeverlyAttrib;

public class Clienta {

    @BeverlyAttrib(type = "S")
    private String id;
    @BeverlyAttrib(type = "S")
    private String name;
    @BeverlyAttrib(type = "S")
    private String phone;

    public Clienta() {}

    public Clienta(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
