package com.defterp.modules.accounting.entities;

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
@Table(name = "journal")
@NamedQueries({
        @NamedQuery(name = "Journal.findAll", query = "SELECT j FROM Journal j"),
        @NamedQuery(name = "Journal.findById", query = "SELECT j FROM Journal j WHERE j.id = :id"),
        @NamedQuery(name = "Journal.findByName", query = "SELECT j FROM Journal j WHERE j.name = :name"),
        @NamedQuery(name = "Journal.findByCode", query = "SELECT j FROM Journal j WHERE j.code = :code"),
        @NamedQuery(name = "Journal.findByType", query = "SELECT j FROM Journal j WHERE j.type = :type"),
        @NamedQuery(name = "Journal.findByActive", query = "SELECT j FROM Journal j WHERE j.active = :active")})

public class Journal extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64, message = "{LongString}")
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64, message = "{LongString}")
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64, message = "{LongString}")
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "journal")
    private List<JournalItem> journalItems;
    @OneToMany(mappedBy = "journal")
    private List<JournalEntry> journalEntries;
    @OneToMany(mappedBy = "journal")
    private List<Payment> payments;
    @OneToMany(mappedBy = "journal")
    private List<Invoice> invoices;

    public Journal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<JournalItem> getJournalItems() {
        return journalItems;
    }

    public void setJournalItems(List<JournalItem> journalItems) {
        this.journalItems = journalItems;
    }

    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }


    @Override
    public String toString() {
        return "--- Journal[ id=" + super.getId() + " ] ---";
    }

}
