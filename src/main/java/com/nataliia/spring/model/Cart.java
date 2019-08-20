package com.nataliia.spring.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @ManyToMany
    @JoinTable(name = "goods_carts",
            joinColumns = {@JoinColumn(name = "cart_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
    private List<Good> goodsInCart;

    public Cart() {
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

    public List<Good> getGoodsInCart() {
        return goodsInCart;
    }

    public void setGoodsInCart(List<Good> goodsInCart) {
        this.goodsInCart = goodsInCart;
    }

    public void addGood(Good good) {
        goodsInCart.add(good);
    }

    public void deleteGood(Good good) {
        goodsInCart.remove(good);
    }

    public void resetCart() {
        goodsInCart.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (id != cart.id) return false;
        return user != null ? user.equals(cart.user) : cart.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
