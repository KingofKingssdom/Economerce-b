//package com.caNhan.E_conomy.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "Orders")
//public class Order {
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name = "fullName")
//    private String fullName;
//    @Column(name ="phone")
//    private String phone;
//    @Column(name = "address")
//    private String address;
//    @Column(name = "Status")
//    private String status;
//    @Column(name = "description")
//    private String description;
//    @Column(name = "order_date", nullable = false)
//    private LocalDateTime orderDate;
//    // Các quan hệ mapping
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @OneToMany(mappedBy = "order")
//    @JsonIgnore
//    private List<OrderItem> orderItems;
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public LocalDateTime getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(LocalDateTime orderDate) {
//        this.orderDate = orderDate;
//    }
//}
