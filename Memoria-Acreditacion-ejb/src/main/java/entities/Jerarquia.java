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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MacBookPro
 */
@Entity
@Table(name = "jerarquia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jerarquia.findByNombre", query = "SELECT a FROM Jerarquia a WHERE a.nombre = :nombre"),
})
public class Jerarquia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_jerarquia;
    
    private String nombre;
    
    @OneToMany(mappedBy="jerarquia")
    private List<Profesor> profesorList;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_jerarquia != null ? id_jerarquia.hashCode() : 0);
        return hash;
    }

    public Long getId_jerarquia() {
        return id_jerarquia;
    }

    public void setId_jerarquia(Long id_jerarquia) {
        this.id_jerarquia = id_jerarquia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jerarquia)) {
            return false;
        }
        Jerarquia other = (Jerarquia) object;
        if ((this.id_jerarquia == null && other.id_jerarquia != null) || (this.id_jerarquia != null && !this.id_jerarquia.equals(other.id_jerarquia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
