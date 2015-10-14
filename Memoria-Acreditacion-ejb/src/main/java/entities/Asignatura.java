/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MacBookPro
 */
@Entity
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findByNombre", query = "SELECT a FROM Asignatura a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Asignatura.findByNombrelist", query = "SELECT a FROM Asignatura a WHERE a.nombre = :nombre"),

})


public class Asignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_asignatura;
    
    private String nombre;
    private float cant_horas;
    private int semestre;
    private int ano;
    private int codigo;
    private String coordinacion;
    
    @ManyToOne
    private Carrera carrera;

    @JoinTable(name = "profesor_asignatura", joinColumns = {
        @JoinColumn(name = "asignatura_id_asignatura", referencedColumnName = "id_asignatura")}, inverseJoinColumns = {
        @JoinColumn(name = "profesor_rut_profesor", referencedColumnName = "rut_profesor")})
    @ManyToMany
    private List<Profesor> profesorasignaturaList;    

    public Long getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(Long id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCant_horas() {
        return cant_horas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCoordinacion() {
        return coordinacion;
    }

    public void setCoordinacion(String coordinacion) {
        this.coordinacion = coordinacion;
    }
    
    
    public void setCant_horas(float cant_horas) {
        this.cant_horas = cant_horas;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
   
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public List<Profesor> getProfesorasignaturaList() {
        return profesorasignaturaList;
    }

    public void setProfesorasignaturaList(List<Profesor> profesorasignaturaList) {
        this.profesorasignaturaList = profesorasignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_asignatura != null ? id_asignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.id_asignatura == null && other.id_asignatura != null) || (this.id_asignatura != null && !this.id_asignatura.equals(other.id_asignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
