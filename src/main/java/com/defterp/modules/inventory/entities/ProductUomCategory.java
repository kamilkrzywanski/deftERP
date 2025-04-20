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
@Table(name = "product_uom_category")
@NamedQueries({
        @NamedQuery(name = "ProductUomCategory.findAll", query = "SELECT p FROM ProductUomCategory p"),
        @NamedQuery(name = "ProductUomCategory.findById", query = "SELECT p FROM ProductUomCategory p WHERE p.id = :id"),
        @NamedQuery(name = "ProductUomCategory.findByName", query = "SELECT p FROM ProductUomCategory p WHERE p.name = :name")})

public class ProductUomCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64, message = "{LongString}")
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "category")
    private List<ProductUom> uoms;

    public ProductUomCategory() {
    }

    public ProductUomCategory(Integer id, String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductUom> getUoms() {
        return uoms;
    }

    public void setUoms(List<ProductUom> uoms) {
        this.uoms = uoms;
    }


    @Override
    public String toString() {
        return "--- ProductUomCategory[ id=" + super.getId() + " ] ---";
    }

}
