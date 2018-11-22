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
@Table(name = "ParticipantList", catalog = "wadProyect", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipantList.findAll", query = "SELECT p FROM ParticipantList p")
    , @NamedQuery(name = "ParticipantList.findById", query = "SELECT p FROM ParticipantList p WHERE p.id = :id")
    , @NamedQuery(name = "ParticipantList.findByIdUser", query = "SELECT p FROM ParticipantList p WHERE p.idUser = :idUser")
    , @NamedQuery(name = "ParticipantList.findByIdExchange", query = "SELECT p FROM ParticipantList p WHERE p.idExchange = :idExchange")
    , @NamedQuery(name = "ParticipantList.findByAcceptInvite", query = "SELECT p FROM ParticipantList p WHERE p.acceptInvite = :acceptInvite")
    , @NamedQuery(name = "ParticipantList.findByIsInGroup", query = "SELECT p FROM ParticipantList p WHERE p.isInGroup = :isInGroup")
    , @NamedQuery(name = "ParticipantList.findByTheme", query = "SELECT p FROM ParticipantList p WHERE p.theme = :theme")})
public class ParticipantList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idUser", nullable = false)
    private int idUser;
    @Basic(optional = false)
    @Column(name = "idExchange", nullable = false)
    private int idExchange;
    @Basic(optional = false)
    @Column(name = "acceptInvite", nullable = false)
    private boolean acceptInvite;
    @Basic(optional = false)
    @Column(name = "isInGroup", nullable = false)
    private boolean isInGroup;
    @Column(name = "theme", length = 100)
    private String theme;

    public ParticipantList() {
    }

    public ParticipantList(Integer id) {
        this.id = id;
    }

    public ParticipantList(Integer id, int idUser, int idExchange, boolean acceptInvite, boolean isInGroup) {
        this.id = id;
        this.idUser = idUser;
        this.idExchange = idExchange;
        this.acceptInvite = acceptInvite;
        this.isInGroup = isInGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdExchange() {
        return idExchange;
    }

    public void setIdExchange(int idExchange) {
        this.idExchange = idExchange;
    }

    public boolean getAcceptInvite() {
        return acceptInvite;
    }

    public void setAcceptInvite(boolean acceptInvite) {
        this.acceptInvite = acceptInvite;
    }

    public boolean getIsInGroup() {
        return isInGroup;
    }

    public void setIsInGroup(boolean isInGroup) {
        this.isInGroup = isInGroup;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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
        if (!(object instanceof ParticipantList)) {
            return false;
        }
        ParticipantList other = (ParticipantList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.ParticipantList[ id=" + id + " ]";
    }
    
}
