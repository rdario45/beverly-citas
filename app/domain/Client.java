package domain;

public class Client{

    private Integer id;
    private String name;
    private String address;
    private String telephone;
    private Integer referred;
    private double discount;
    private String status;

    public Client() {}

    public Client(Integer id, String name, String address, String telephone, Integer referred, double discount, String status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.referred = referred;
        this.discount = discount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getReferred() {
        return referred;
    }

    public void setReferred(Integer referred) {
        this.referred = referred;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
