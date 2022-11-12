package Supplier.entity;

public class Suppliers {
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private String productsSupply;

    public Suppliers() {
    }

    public Suppliers(String id, String name, String phoneNumber, String address, String productsSupply) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.productsSupply = productsSupply;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductsSupply() {
        return productsSupply;
    }

    public void setProductsSupply(String productsSupply) {
        this.productsSupply = productsSupply;
    }
}
