/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MacBookPro
 */
@Entity
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findByNombre", query = "SELECT a FROM Profesor a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Profesor.findByRenta", query = "SELECT a FROM Profesor a WHERE a.renta = :renta"),
    @NamedQuery(name = "Profesor.findByAno", query = "SELECT a FROM Profesor a WHERE a.ano_ingreso <= :ano AND a.vigente = :vigente"),
    @NamedQuery(name = "Profesor.findByAnoRetiro", query = "SELECT a FROM Profesor a WHERE a.ano_ingreso <= :ano AND a.vigente = :vigente AND a.anoRetiro >= :ano"),
    @NamedQuery(name = "Profesor.findByContrato", query = "SELECT a FROM Profesor a WHERE a.contrato = :contrato"),
    @NamedQuery(name = "Profesor.findByRut", query = "SELECT a FROM Profesor a WHERE a.rut_profesor = :rut"),
    @NamedQuery(name = "Profesor.findByJerarquia", query = "SELECT a FROM Profesor a WHERE a.jerarquia = :jerarquia"),
    @NamedQuery(name = "Profesor.findByGrado", query = "SELECT a FROM Profesor a WHERE a.grado = :grado"),
    @NamedQuery(name = "Profesor.findByJerarYGrado", query = "SELECT a FROM Profesor a WHERE a.jerarquia = :jerarquia AND a.grado = :grado"),
    @NamedQuery(name = "Profesor.findByJerarYContrato", query = "SELECT a FROM Profesor a WHERE a.jerarquia = :jerarquia AND a.contrato = :contrato"),
    
})
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rut_profesor;
    private int anoRetiro;
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private float renta;
    private String contrato;
    private boolean vigente;
    private int ano_ingreso;
   
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_nacimiento;
    
    @ManyToOne
    private Jerarquia jerarquia;
    
    @ManyToOne
    private GradoAcademico grado;
    
    @ManyToMany(mappedBy="profesorrolList")
    private List<Rol> rolList;
    
    @ManyToMany(mappedBy="profesorasignaturaList")
    private List<Asignatura> asignaturaList;

    public Long getRut_profesor() {
        return rut_profesor;
    }

    public void setRut_profesor(Long rut_profesor) {
        this.rut_profesor = rut_profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

   
    public float getRenta() {
        return renta;
    }

    public void setRenta(float renta) {
        this.renta = renta;
    }

    public int getAno_ingreso() {
        return ano_ingreso;
    }

    public void setAno_ingreso(int ano_ingreso) {
        this.ano_ingreso = ano_ingreso;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Jerarquia getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(Jerarquia jerarquia) {
        this.jerarquia = jerarquia;
    }

    public GradoAcademico getGrado() {
        return grado;
    }

    public void setGrado(GradoAcademico grado) {
        this.grado = grado;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public int getAnoRetiro() {
        return anoRetiro;
    }

    public void setAnoRetiro(int anoRetiro) {
        this.anoRetiro = anoRetiro;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rut_profesor != null ? rut_profesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.rut_profesor == null && other.rut_profesor != null) || (this.rut_profesor != null && !this.rut_profesor.equals(other.rut_profesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Profesor[ id=" + rut_profesor + " ]";
    }
    
}
