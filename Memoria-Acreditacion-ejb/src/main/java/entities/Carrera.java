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
@Table(name = "carrera")
@XmlRootElement@NamedQueries({
    @NamedQuery(name = "Carrera.findByNombre", query = "SELECT a FROM Carrera a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Carrera.findByNombrelist", query = "SELECT a FROM Carrera a WHERE a.nombre = :nombre"),
})


public class Carrera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_carrera;
    
    private String nombre;
    
    @OneToMany(mappedBy="carrera")
    private List<Asignatura> asignaturaList;

    public Long getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(Long id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_carrera != null ? id_carrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.id_carrera == null && other.id_carrera != null) || (this.id_carrera != null && !this.id_carrera.equals(other.id_carrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
