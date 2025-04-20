package com.defterp.modules.inventory.entities;

import com.defterp.modules.commonClasses.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

@Entity
@Table(name = "product_category")
@NamedQueries({
        @NamedQuery(name = "ProductCategory.findAll", query = "SELECT p FROM ProductCategory p"),
        @NamedQuery(name = "ProductCategory.findById", query = "SELECT p FROM ProductCategory p WHERE p.id = :id"),
        @NamedQuery(name = "ProductCategory.findByName", query = "SELECT p FROM ProductCategory p WHERE p.name = :name"),
        @NamedQuery(name = "ProductCategory.findByActive", query = "SELECT p FROM ProductCategory p WHERE p.active = :active")})

public class ProductCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40, message = "{LongString}")
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public ProductCategory() {

    }

    public ProductCategory(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "--- ProductCategory[ id=" + super.getId() + " ] ---";
    }

}
