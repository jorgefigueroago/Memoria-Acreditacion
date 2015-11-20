/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MacBookPro
 */
@Entity
@Table(name = "tipousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipousuario.findAll", query = "SELECT t FROM Tipousuario t"),
    @NamedQuery(name = "Tipousuario.findByIdTipo", query = "SELECT t FROM Tipousuario t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "Tipousuario.findByNombreTipo", query = "SELECT t FROM Tipousuario t WHERE t.nombreTipo = :nombreTipo"),
    @NamedQuery(name = "Tipousuario.findByPass", query = "SELECT t FROM Tipousuario t WHERE t.pass = :pass")})
public class Tipousuario implements Serializable {
    @JoinTable(name = "usuario_tipousuario", joinColumns = {
        @JoinColumn(name = "tipos_id_tipo", referencedColumnName = "id_tipo")}, inverseJoinColumns = {
        @JoinColumn(name = "usuario_rut_usuario", referencedColumnName = "rut_usuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo")
    private Long idTipo;
    @Size(max = 255)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "pass")
    private String pass;

    public Tipousuario() {
    }

    public Tipousuario(Long idTipo) {
        this.idTipo = idTipo;
    }

    public Tipousuario(Long idTipo, String pass) {
        this.idTipo = idTipo;
        this.pass = pass;
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipousuario)) {
            return false;
        }
        Tipousuario other = (Tipousuario) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tipousuario[ idTipo=" + idTipo + " ]";
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
}
