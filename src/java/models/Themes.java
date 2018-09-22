/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yahir
 */
@Entity
@Table(name = "Themes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Themes.findAll", query = "SELECT t FROM Themes t")
    , @NamedQuery(name = "Themes.findById", query = "SELECT t FROM Themes t WHERE t.id = :id")
    , @NamedQuery(name = "Themes.findByThemeName", query = "SELECT t FROM Themes t WHERE t.themeName = :themeName")
    , @NamedQuery(name = "Themes.findByIdExchange", query = "SELECT t FROM Themes t WHERE t.idExchange = :idExchange")})
public class Themes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "themeName")
    private String themeName;
    @Basic(optional = false)
    @Column(name = "idExchange")
    private int idExchange;

    public Themes() {
    }

    public Themes(Integer id) {
        this.id = id;
    }

    public Themes(Integer id, String themeName, int idExchange) {
        this.id = id;
        this.themeName = themeName;
        this.idExchange = idExchange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public int getIdExchange() {
        return idExchange;
    }

    public void setIdExchange(int idExchange) {
        this.idExchange = idExchange;
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
        if (!(object instanceof Themes)) {
            return false;
        }
        Themes other = (Themes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Themes[ id=" + id + " ]";
    }
    
}
