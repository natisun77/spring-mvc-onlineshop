package com.nataliia.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinTable(name = "orders_to_users",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @ManyToMany
    @JoinTable(name = "orders_to_goods",
            joinColumns = {@JoinColumn(name = "order_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
    private List<Good> goodsInOrder = new ArrayList<>();

    @Column(name = "address")
    private String address;

    @Column(name = "formOfPayment")
    private String formOfPayment;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Good> getGoodsInOrder() {
        return goodsInOrder;
    }

    public void setGoodsInOrder(List<Good> goodsInOrder) {
        this.goodsInOrder = goodsInOrder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(String formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (goodsInOrder != null ? !goodsInOrder.equals(order.goodsInOrder) : order.goodsInOrder != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        return formOfPayment != null ? formOfPayment.equals(order.formOfPayment) : order.formOfPayment == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (goodsInOrder != null ? goodsInOrder.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (formOfPayment != null ? formOfPayment.hashCode() : 0);
        return result;
    }
}
