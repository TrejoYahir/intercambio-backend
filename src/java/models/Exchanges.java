/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yahir
 */
@Entity
@Table(name = "Exchanges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exchanges.findAll", query = "SELECT e FROM Exchanges e")
    , @NamedQuery(name = "Exchanges.findById", query = "SELECT e FROM Exchanges e WHERE e.id = :id")
    , @NamedQuery(name = "Exchanges.findByExchangeName", query = "SELECT e FROM Exchanges e WHERE e.exchangeName = :exchangeName")
    , @NamedQuery(name = "Exchanges.findByMaxAmount", query = "SELECT e FROM Exchanges e WHERE e.maxAmount = :maxAmount")
    , @NamedQuery(name = "Exchanges.findByLimitDate", query = "SELECT e FROM Exchanges e WHERE e.limitDate = :limitDate")
    , @NamedQuery(name = "Exchanges.findByExchangeDate", query = "SELECT e FROM Exchanges e WHERE e.exchangeDate = :exchangeDate")
    , @NamedQuery(name = "Exchanges.findByExchangeDescription", query = "SELECT e FROM Exchanges e WHERE e.exchangeDescription = :exchangeDescription")
    , @NamedQuery(name = "Exchanges.findByAccessCode", query = "SELECT e FROM Exchanges e WHERE e.accessCode = :accessCode")
    , @NamedQuery(name = "Exchanges.findByIdCreator", query = "SELECT e FROM Exchanges e WHERE e.idCreator = :idCreator")})
public class Exchanges implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "exchangeName")
    private String exchangeName;
    @Basic(optional = false)
    @Column(name = "maxAmount")
    private float maxAmount;
    @Basic(optional = false)
    @Column(name = "limitDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitDate;
    @Basic(optional = false)
    @Column(name = "exchangeDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exchangeDate;
    @Basic(optional = false)
    @Column(name = "exchangeDescription")
    private String exchangeDescription;
    @Basic(optional = false)
    @Column(name = "accessCode")
    private String accessCode;
    @Basic(optional = false)
    @Column(name = "idCreator")
    private int idCreator;

    public Exchanges() {
    }

    public Exchanges(Integer id) {
        this.id = id;
    }

    public Exchanges(Integer id, String exchangeName, float maxAmount, Date limitDate, Date exchangeDate, String exchangeDescription, String accessCode, int idCreator) {
        this.id = id;
        this.exchangeName = exchangeName;
        this.maxAmount = maxAmount;
        this.limitDate = limitDate;
        this.exchangeDate = exchangeDate;
        this.exchangeDescription = exchangeDescription;
        this.accessCode = accessCode;
        this.idCreator = idCreator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public float getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(float maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getExchangeDescription() {
        return exchangeDescription;
    }

    public void setExchangeDescription(String exchangeDescription) {
        this.exchangeDescription = exchangeDescription;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exchanges)) {
            return false;
        }
        Exchanges other = (Exchanges) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Exchanges[ id=" + id + " ]";
    }
    
}
