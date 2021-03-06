/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

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
@Table(name = "Pairs", catalog = "wadProyect", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pairs.findAll", query = "SELECT p FROM Pairs p")
    , @NamedQuery(name = "Pairs.findById", query = "SELECT p FROM Pairs p WHERE p.id = :id")
    , @NamedQuery(name = "Pairs.findByIdUser1", query = "SELECT p FROM Pairs p WHERE p.idUser1 = :idUser1")
    , @NamedQuery(name = "Pairs.findByIdUser2", query = "SELECT p FROM Pairs p WHERE p.idUser2 = :idUser2")
    , @NamedQuery(name = "Pairs.findByIdExchange", query = "SELECT p FROM Pairs p WHERE p.idExchange = :idExchange")})
public class Pairs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idUser1", nullable = false)
    private int idUser1;
    @Basic(optional = false)
    @Column(name = "idUser2", nullable = false)
    private int idUser2;
    @Basic(optional = false)
    @Column(name = "idExchange", nullable = false)
    private int idExchange;

    public Pairs() {
    }

    public Pairs(Integer id) {
        this.id = id;
    }

    public Pairs(Integer id, int idUser1, int idUser2, int idExchange) {
        this.id = id;
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.idExchange = idExchange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
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
        if (!(object instanceof Pairs)) {
            return false;
        }
        Pairs other = (Pairs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Pairs[ id=" + id + " ]";
    }
    
}
