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
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinTable(name = "booking_to_user",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @ManyToMany
    @JoinTable(name = "booking_to_product",
            joinColumns = {@JoinColumn(name = "booking_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = false)})
    private List<Product> productsInBooking = new ArrayList<>();

    @Column(name = "address")
    private String address;

    @Column(name = "form_of_payment")
    private String formOfPayment;

    public Booking() {
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

    public List<Product> getProductsInBooking() {
        return productsInBooking;
    }

    public void setProductsInBooking(List<Product> productsInBooking) {
        this.productsInBooking = productsInBooking;
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

        Booking booking = (Booking) o;

        if (id != booking.id) return false;
        if (user != null ? !user.equals(booking.user) : booking.user != null) return false;
        if (productsInBooking != null ? !productsInBooking.equals(booking.productsInBooking) : booking.productsInBooking != null)
            return false;
        if (address != null ? !address.equals(booking.address) : booking.address != null) return false;
        return formOfPayment != null ? formOfPayment.equals(booking.formOfPayment) : booking.formOfPayment == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (productsInBooking != null ? productsInBooking.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (formOfPayment != null ? formOfPayment.hashCode() : 0);
        return result;
    }
}
