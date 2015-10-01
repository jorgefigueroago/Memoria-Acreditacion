package managedbeans;

import entities.GradoAcademico;
import entities.Jerarquia;
import entities.Profesor;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.GradoAcademicoFacadeLocal;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionbeans.JerarquiaFacadeLocal;
import sessionbeans.ProfesorFacadeLocal;

@Named("gradoAcademicoController")
@SessionScoped
public class GradoAcademicoController implements Serializable {

    @EJB
    private GradoAcademicoFacadeLocal ejbFacade;
    @EJB
    private ProfesorFacadeLocal profeFacade;
    @EJB
    private JerarquiaFacadeLocal jeraFacade;
    
    private List<GradoAcademico> items = null;
    private GradoAcademico selected;
    
    private List<gradoContrato> listaGC = null;
    
    private List<gradoHora> listaGH = null;
    
    private List<gradoNum> listaGN = null;
    
    private List<GradoAcademico> lista_nombres = null;
    
     /*************************************************
      *               INICIO
                    JERARQUIAS
      *************************************************/   
    
    private List<JerarquiaGrado> listaJT = null;
    private List<JerarquiaGrado> listaJAsoc = null;
    private List<JerarquiaGrado> listaJAsis = null;
    private List<JerarquiaGrado> listaJInst = null;
    private List<JerarquiaGrado> listaJAyud = null;
    private List<JerarquiaGrado> listaJAdj1 = null;
    private List<JerarquiaGrado> listaJAdj2 = null;
    private List<JerarquiaGrado> listaJInst1 = null;
    private List<JerarquiaGrado> listaJInst2 = null;
    private List<JerarquiaGrado> listaJAyudP = null;
    
    
    public GradoAcademicoController() {
    }
    
    public List<JerarquiaGrado> getListaJT(){
        listaJT = new ArrayList<>();
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        Jerarquia jtitular = (Jerarquia) jeraFacade.findByNombre("TITULAR");
        
        List<Profesor> doctor;
        List<Profesor> magister;
        List<Profesor> titulado;
        
        doctor = profeFacade.findByJerarYGrado(jtitular, gdoctor);
        magister = profeFacade.findByJerarYGrado(jtitular, gmagister);
        titulado = profeFacade.findByJerarYGrado(jtitular, gtitulado);
        
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
        
        JerarquiaGrado nuevod = new JerarquiaGrado();
        JerarquiaGrado nuevom = new JerarquiaGrado();
        JerarquiaGrado nuevot = new JerarquiaGrado();
        JerarquiaGrado nuevototal = new JerarquiaGrado();
        
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        
        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
                cont3+=1;
            }
            if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
                cont2+=1;
                }
            if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                    cont1+=1;
                    
            }
        }
        nuevod.year1 = cont1;
        nuevod.year2 = cont2;
        nuevod.year3 = cont3;
        
        cont1 = 0; cont2 = 0; cont3 = 0;
        
        
        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
                cont3+=1;
            }
            
            if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
                cont2+=1;
                }
            if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                    cont1+=1;
                    
                }
            
        }
        
        nuevom.year1 = cont1;
        nuevom.year2 = cont2;
        nuevom.year3 = cont3;
        
        cont1 = 0; cont2 = 0; cont3 = 0;
        
        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
                cont3+=1;
            }
            if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
                cont2+=1;
            }
            if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                    cont1+=1;
                    
            }
           
        }
        
        nuevot.year1 = cont1;
        nuevot.year2 = cont2;
        nuevot.year3 = cont3;
       
        
        nuevod.jerarquia = "Número de docentes Jerarquía Titular con grado de Doctor";
        nuevom.jerarquia = "Número de docentes Jerarquía Titular con grado Magister";
        nuevot.jerarquia = "Número de docentes Jerarquía Titular licenciados o con título profesional";
        nuevototal.jerarquia = "Total docentes Jerarquía Titular";
        nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
        nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
        nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;
        
        listaJT.add(nuevod);
        listaJT.add(nuevom);
        listaJT.add(nuevot);
        listaJT.add(nuevototal);
        
        return listaJT;        
        
    }
    
    
    public List<JerarquiaGrado> getListaJAsoc(){
    listaJAsoc = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jasociado = (Jerarquia) jeraFacade.findByNombre("ASOCIADO");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jasociado, gdoctor);
    magister = profeFacade.findByJerarYGrado(jasociado, gmagister);
    titulado = profeFacade.findByJerarYGrado(jasociado, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Asociado con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Asociado con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Asociado licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Asociado";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

     listaJAsoc.add(nuevod);
    listaJAsoc.add(nuevom);
    listaJAsoc.add(nuevot);
    listaJAsoc.add(nuevototal);

    return listaJAsoc;        

    }
    
    public List<JerarquiaGrado> getListaJAsis(){
    listaJAsis = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jasistente = (Jerarquia) jeraFacade.findByNombre("ASISTENTE");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jasistente, gdoctor);
    magister = profeFacade.findByJerarYGrado(jasistente, gmagister);
    titulado = profeFacade.findByJerarYGrado(jasistente, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3)  && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2)  && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year)  && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2)  && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Asistente con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Asistente  con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Asistente  licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Asistente";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAsis.add(nuevod);
    listaJAsis.add(nuevom);
    listaJAsis.add(nuevot);
    listaJAsis.add(nuevototal);

    return listaJAsis;        

    } 
    
    
    public List<JerarquiaGrado> getListaJInst(){
    listaJInst = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jinstructor = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jinstructor, gdoctor);
    magister = profeFacade.findByJerarYGrado(jinstructor, gmagister);
    titulado = profeFacade.findByJerarYGrado(jinstructor, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Instructor con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Instructor  con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Instructor  licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Instructor";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJInst.add(nuevod);
    listaJInst.add(nuevom);
    listaJInst.add(nuevot);
    listaJInst.add(nuevototal);

    return listaJInst;        

    }
    
    public List<JerarquiaGrado> getListaJInst1(){
    listaJInst1 = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jinstructor1 = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR CATEGORÍA I");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jinstructor1, gdoctor);
    magister = profeFacade.findByJerarYGrado(jinstructor1, gmagister);
    titulado = profeFacade.findByJerarYGrado(jinstructor1, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if ((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Instructor Categoría I con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Instructor Categoría I con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Instructor Categoría I licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Instructor Categoría I";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJInst1.add(nuevod);
    listaJInst1.add(nuevom);
    listaJInst1.add(nuevot);
    listaJInst1.add(nuevototal);

    return listaJInst1;        

    }
    
    public List<JerarquiaGrado> getListaJInst2(){
    listaJInst2 = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jinstructor2 = (Jerarquia) jeraFacade.findByNombre("INSTRUCTOR CATEGORÍA II");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jinstructor2, gdoctor);
    magister = profeFacade.findByJerarYGrado(jinstructor2, gmagister);
    titulado = profeFacade.findByJerarYGrado(jinstructor2, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Instructor Categoría II con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Instructor Categoría II con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Instructor Categoría II licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Instructor Categoría II";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJInst2.add(nuevod);
    listaJInst2.add(nuevom);
    listaJInst2.add(nuevot);
    listaJInst2.add(nuevototal);

    return listaJInst2;        

    }
    
    public List<JerarquiaGrado> getListaJAyud(){
    listaJAyud = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jayudante = (Jerarquia) jeraFacade.findByNombre("AYUDANTE");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jayudante, gdoctor);
    magister = profeFacade.findByJerarYGrado(jayudante, gmagister);
    titulado = profeFacade.findByJerarYGrado(jayudante, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Ayudante con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Ayudante  con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Ayudante  licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Ayudante";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAyud.add(nuevod);
    listaJAyud.add(nuevom);
    listaJAyud.add(nuevot);
    listaJAyud.add(nuevototal);

    return listaJAyud;        

    }
    
    
    public List<JerarquiaGrado> getListaJAyudP(){
    listaJAyudP = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jayudantep = (Jerarquia) jeraFacade.findByNombre("AYUDANTE PROFESOR");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jayudantep, gdoctor);
    magister = profeFacade.findByJerarYGrado(jayudantep, gmagister);
    titulado = profeFacade.findByJerarYGrado(jayudantep, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Ayudante Profesor con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Ayudante Profesor con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Ayudante Profesor licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Ayudante Profesor";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAyudP.add(nuevod);
    listaJAyudP.add(nuevom);
    listaJAyudP.add(nuevot);
    listaJAyudP.add(nuevototal);

    return listaJAyudP;        

    }
    
    public List<JerarquiaGrado> getListaJAdj1(){
    listaJAdj1 = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jadjunto1 = (Jerarquia) jeraFacade.findByNombre("ADJUNTO CATEGORÍA I");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jadjunto1, gdoctor);
    magister = profeFacade.findByJerarYGrado(jadjunto1, gmagister);
    titulado = profeFacade.findByJerarYGrado(jadjunto1, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Adjunto Categoría I con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Adjunto Categoría I con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Adjunto Categoría I licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Adjunto Categoría I";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAdj1.add(nuevod);
    listaJAdj1.add(nuevom);
    listaJAdj1.add(nuevot);
    listaJAdj1.add(nuevototal);

    return listaJAdj1;        

    }
    
    public List<JerarquiaGrado> getListaJAdj2(){
    listaJAdj2 = new ArrayList<>();
    GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
    GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
    GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
    Jerarquia jadjunto2 = (Jerarquia) jeraFacade.findByNombre("ADJUNTO CATEGORÍA II");

    List<Profesor> doctor;
    List<Profesor> magister;
    List<Profesor> titulado;

    doctor = profeFacade.findByJerarYGrado(jadjunto2, gdoctor);
    magister = profeFacade.findByJerarYGrado(jadjunto2, gmagister);
    titulado = profeFacade.findByJerarYGrado(jadjunto2, gtitulado);

    Date fecha = new Date();
    int year;
    int year2;
    int year3;
    year = fecha.getYear() + 1900;
    year2 = year - 1;
    year3 = year - 2;

    JerarquiaGrado nuevod = new JerarquiaGrado();
    JerarquiaGrado nuevom = new JerarquiaGrado();
    JerarquiaGrado nuevot = new JerarquiaGrado();
    JerarquiaGrado nuevototal = new JerarquiaGrado();

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;

    for (int i = 0; i < doctor.size(); i++) {
        if((doctor.get(i).getAno_ingreso() <= year3) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((doctor.get(i).getAno_ingreso() <= year2) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((doctor.get(i).getAno_ingreso() <= year) && (doctor.get(i).isVigente() == true || doctor.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }
    }
    nuevod.year1 = cont1;
    nuevod.year2 = cont2;
    nuevod.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;


    for (int i = 0; i < magister.size(); i++) {
        if((magister.get(i).getAno_ingreso() <= year3) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }

        if((magister.get(i).getAno_ingreso() <= year2) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
            }
        if((magister.get(i).getAno_ingreso() <= year) && (magister.get(i).isVigente() == true || magister.get(i).getAnoRetiro() >= year)){
                cont1+=1;

            }

    }

    nuevom.year1 = cont1;
    nuevom.year2 = cont2;
    nuevom.year3 = cont3;

    cont1 = 0; cont2 = 0; cont3 = 0;

    for (int i = 0; i < titulado.size(); i++) {
        if((titulado.get(i).getAno_ingreso() <= year3) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year3)){
            cont3+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year2) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year2)){
            cont2+=1;
        }
        if((titulado.get(i).getAno_ingreso() <= year) && (titulado.get(i).isVigente() == true || titulado.get(i).getAnoRetiro() >= year)){
                cont1+=1;

        }

    }

    nuevot.year1 = cont1;
    nuevot.year2 = cont2;
    nuevot.year3 = cont3;


    nuevod.jerarquia = "Número de docentes Jerarquía Adjunto Categoría II con grado de Doctor";
    nuevom.jerarquia = "Número de docentes Jerarquía Adjunto Categoría II con grado Magister";
    nuevot.jerarquia = "Número de docentes Jerarquía Adjunto Categoría II licenciados o con título profesional";
    nuevototal.jerarquia = "Total docentes Jerarquía Adjunto Categoría II";
    nuevototal.year1 = nuevod.year1 + nuevom.year1 + nuevot.year1;
    nuevototal.year2 = nuevod.year2 + nuevom.year2 + nuevot.year2;
    nuevototal.year3 = nuevod.year3 + nuevom.year3 + nuevot.year3;

    listaJAdj2.add(nuevod);
    listaJAdj2.add(nuevom);
    listaJAdj2.add(nuevot);
    listaJAdj2.add(nuevototal);

    return listaJAdj2;        

    }
    
 /*************************************************
      *               FIN
                    JERARQUIAS
 *************************************************/   
    
    /***************LISTO*****************************
     * 
     ************************************************/
    
 
    public List<gradoNum> getListaGN() {
        listaGN = new ArrayList<>();
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
            
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gnotitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor;
        List<Profesor> magister;
        List<Profesor> titulado;
        List<Profesor> notitulado;

        doctor = profeFacade.findByGrado(gdoctor);
        magister = profeFacade.findByGrado(gmagister);
        titulado = profeFacade.findByGrado(gtitulado);
        notitulado = profeFacade.findByGrado(gnotitulado);
        
        gradoNum nuevod = new gradoNum();
        gradoNum nuevom = new gradoNum();
        gradoNum nuevot = new gradoNum();
        gradoNum nuevont = new gradoNum();
        gradoNum nuevototal = new gradoNum();

        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;

        for (int i = 0; i < doctor.size(); i++) {
            if((doctor.get(i).getAno_ingreso() <= year3) && ((doctor.get(i).getAnoRetiro() >= year3) || doctor.get(i).isVigente() == true)){
                cont3+=1;
            }
            if((doctor.get(i).getAno_ingreso() <= year2) && ((doctor.get(i).getAnoRetiro() >= year2) || doctor.get(i).isVigente() == true)){
                cont2+=1;
                }
            if((doctor.get(i).getAno_ingreso() <= year) && ((doctor.get(i).getAnoRetiro() >= year) || doctor.get(i).isVigente() == true)){
                    cont1+=1;

            }
        }
        nuevod.cant_prof1 = cont3;
        nuevod.cant_prof2 = cont2;
        nuevod.cant_prof3 = cont1;

        cont1 = 0; cont2 = 0; cont3 = 0;


        for (int i = 0; i < magister.size(); i++) {
            if((magister.get(i).getAno_ingreso() <= year3) && ((magister.get(i).getAnoRetiro() >= year3) || magister.get(i).isVigente() == true)){
                cont3+=1;
            }

            if((magister.get(i).getAno_ingreso() <= year2) && ((magister.get(i).getAnoRetiro() >= year2) || magister.get(i).isVigente() == true)){
                cont2+=1;
                }
            if ((magister.get(i).getAno_ingreso() <= year) && ((magister.get(i).getAnoRetiro() >= year) || magister.get(i).isVigente() == true)){
                    cont1+=1;

                }

        }

        nuevom.cant_prof1 = cont3;
        nuevom.cant_prof2 = cont2;
        nuevom.cant_prof3 = cont1;

        cont1 = 0; cont2 = 0; cont3 = 0;

        for (int i = 0; i < titulado.size(); i++) {
            if((titulado.get(i).getAno_ingreso() <= year3) && ((titulado.get(i).getAnoRetiro() >= year3) || titulado.get(i).isVigente() == true)){
                cont3+=1;
            }
            if((titulado.get(i).getAno_ingreso() <= year2) && ((titulado.get(i).getAnoRetiro() >= year2) || titulado.get(i).isVigente() == true)){
                cont2+=1;
                }
            if((titulado.get(i).getAno_ingreso() <= year) && ((titulado.get(i).getAnoRetiro() >= year) || titulado.get(i).isVigente() == true)){
                    cont1+=1;

            }
        }
        nuevot.cant_prof1 = cont3;
        nuevot.cant_prof2 = cont2;
        nuevot.cant_prof3 = cont1;

        cont1 = 0; cont2 = 0; cont3 = 0;
        
        for (int i = 0; i < notitulado.size(); i++) {
            if((notitulado.get(i).getAno_ingreso() <= year3)&& ((notitulado.get(i).getAnoRetiro() >= year3) || notitulado.get(i).isVigente() == true)){
                cont3+=1;
            }
            if((notitulado.get(i).getAno_ingreso() <= year2)&& ((notitulado.get(i).getAnoRetiro() >= year2) || notitulado.get(i).isVigente() == true)){
                cont2+=1;
            }
            if((notitulado.get(i).getAno_ingreso() <= year)&& ((notitulado.get(i).getAnoRetiro() >= year) || notitulado.get(i).isVigente() == true)){
                    cont1+=1;

            }

        }

        nuevont.cant_prof1 = cont3;
        nuevont.cant_prof2 = cont2;
        nuevont.cant_prof3 = cont1;


        nuevod.grado = "Número de Doctores (incluído PhD)";
        nuevom.grado = "Número de Magister";
        nuevot.grado = "Número de Licenciados o Titulados";
        nuevont.grado = "Número de No Titulados ni Graduados";
        nuevototal.grado = "TOTAL";
        
        nuevototal.cant_prof1 = nuevod.cant_prof1 + nuevom.cant_prof1 + nuevot.cant_prof1 + nuevont.cant_prof1;
        nuevototal.cant_prof2 = nuevod.cant_prof2 + nuevom.cant_prof2 + nuevot.cant_prof2 + nuevont.cant_prof2;
        nuevototal.cant_prof3 = nuevod.cant_prof3 + nuevom.cant_prof3 + nuevot.cant_prof3 + nuevont.cant_prof3;

        listaGN.add(nuevod);
        listaGN.add(nuevom);
        listaGN.add(nuevot);
        listaGN.add(nuevont);
        listaGN.add(nuevototal);
    
        return listaGN;
    }

    /***************LISTO*****************************
     * 
     ************************************************/
    
    public List<gradoHora> getListaGH() {
        listaGH = new ArrayList<>();
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
            
        GradoAcademico gdoctor = (GradoAcademico) ejbFacade.findByNombre("DOCTOR");
        GradoAcademico gmagister = (GradoAcademico) ejbFacade.findByNombre("MAGISTER");
        GradoAcademico gtitulado = (GradoAcademico) ejbFacade.findByNombre("LICENCIADO-TITULADO");
        GradoAcademico gnotitulado = (GradoAcademico) ejbFacade.findByNombre("NO TITULADO");
        List<Profesor> doctor;
        List<Profesor> magister;
        List<Profesor> titulado;
        List<Profesor> notitulado;

        doctor = profeFacade.findByGrado(gdoctor);
        magister = profeFacade.findByGrado(gmagister);
        titulado = profeFacade.findByGrado(gtitulado);
        notitulado = profeFacade.findByGrado(gnotitulado);
        
        gradoHora nuevod = new gradoHora();
        gradoHora nuevom = new gradoHora();
        gradoHora nuevot = new gradoHora();
        gradoHora nuevont = new gradoHora();
        gradoHora nuevototal = new gradoHora();

        float cont1 = 0;
        float cont2 = 0;
        float cont3 = 0;

        for (int i = 0; i < doctor.size(); i++) {
            for (int j = 0; j < doctor.get(i).getAsignaturaList().size(); j++) {
                if((doctor.get(i).getAno_ingreso() <= year3) && ((doctor.get(i).getAnoRetiro() >= year3) || doctor.get(i).isVigente() == true)){
                    cont3+= doctor.get(i).getAsignaturaList().get(j).getCant_horas();
                }
                if((doctor.get(i).getAno_ingreso() <= year2) && ((doctor.get(i).getAnoRetiro() >= year2) || doctor.get(i).isVigente() == true)){
                    cont2+=doctor.get(i).getAsignaturaList().get(j).getCant_horas();
                    }
                if((doctor.get(i).getAno_ingreso() <= year)  && ((doctor.get(i).getAnoRetiro() >= year) || doctor.get(i).isVigente() == true)){
                        cont1+=doctor.get(i).getAsignaturaList().get(j).getCant_horas();

                }
            }
        }
        nuevod.cant_horas1 = cont3;
        nuevod.cant_horas2 = cont2;
        nuevod.cant_horas3 = cont1;

        cont1 = 0; cont2 = 0; cont3 = 0;


        for (int i = 0; i < magister.size(); i++) {
            for (int j = 0; j < magister.get(i).getAsignaturaList().size(); j++) {
                if((magister.get(i).getAno_ingreso() <= year3) && ((magister.get(i).getAnoRetiro() >= year3) || magister.get(i).isVigente() == true)){
                    cont3+= magister.get(i).getAsignaturaList().get(j).getCant_horas();
                }
                if((magister.get(i).getAno_ingreso() <= year2) && ((magister.get(i).getAnoRetiro() >= year2) || magister.get(i).isVigente() == true)){
                    cont2+=magister.get(i).getAsignaturaList().get(j).getCant_horas();;
                    }
                if((magister.get(i).getAno_ingreso() <= year) && ((magister.get(i).getAnoRetiro() >= year) || magister.get(i).isVigente() == true)){
                        cont1+=magister.get(i).getAsignaturaList().get(j).getCant_horas();;

                }
            }
        }

        nuevom.cant_horas1 = cont3;
        nuevom.cant_horas2 = cont2;
        nuevom.cant_horas3 = cont1;

        cont1 = 0; cont2 = 0; cont3 = 0;

        for (int i = 0; i < titulado.size(); i++) {
            for (int j = 0; j < titulado.get(i).getAsignaturaList().size(); j++) {
                if((titulado.get(i).getAno_ingreso() <= year3)  && ((titulado.get(i).getAnoRetiro() >= year3) || titulado.get(i).isVigente() == true)){
                    cont3+= titulado.get(i).getAsignaturaList().get(j).getCant_horas();
                }
                if((titulado.get(i).getAno_ingreso() <= year2) && ((titulado.get(i).getAnoRetiro()>= year2) || titulado.get(i).isVigente() == true)){
                    cont2+=titulado.get(i).getAsignaturaList().get(j).getCant_horas();;
                    }
                if((titulado.get(i).getAno_ingreso() <= year) && ((titulado.get(i).getAnoRetiro() >= year) || titulado.get(i).isVigente() == true)){
                        cont1+=titulado.get(i).getAsignaturaList().get(j).getCant_horas();;

                }
            }
        }
        
        nuevot.cant_horas1 = cont3;
        nuevot.cant_horas2 = cont2;
        nuevot.cant_horas3 = cont1;

        cont1 = 0; cont2 = 0; cont3 = 0;
        
        for (int i = 0; i < notitulado.size(); i++) {
            for (int j = 0; j < notitulado.get(i).getAsignaturaList().size(); j++) {
                if((notitulado.get(i).getAno_ingreso() <= year3) && ((notitulado.get(i).getAnoRetiro() >= year3) || notitulado.get(i).isVigente() == true)){
                    cont3+= notitulado.get(i).getAsignaturaList().get(j).getCant_horas();
                }
                if((notitulado.get(i).getAno_ingreso() <= year2) && ((notitulado.get(i).getAnoRetiro() >= year2) || notitulado.get(i).isVigente() == true)){
                    cont2+=notitulado.get(i).getAsignaturaList().get(j).getCant_horas();;
                    }
                if((notitulado.get(i).getAno_ingreso() <= year) && ((notitulado.get(i).getAnoRetiro() >= year) || notitulado.get(i).isVigente() == true)){
                        cont1+=notitulado.get(i).getAsignaturaList().get(j).getCant_horas();;

                }
            }
        }

        nuevont.cant_horas1 = cont3;
        nuevont.cant_horas2 = cont2;
        nuevont.cant_horas3 = cont1;


        nuevod.grado = "Cantidad de horas semanales Doctores PhD";
        nuevom.grado = "Cantidad de horas semanales Magister";
        nuevot.grado = "Cantidad de horas semanales Licenciados o Titulados";
        nuevont.grado = "Cantidad de horas semanales No Titulados ni Graduados";
        nuevototal.grado = "TOTAL HORAS";
        
        nuevototal.cant_horas1 = nuevod.cant_horas1 + nuevom.cant_horas1 + nuevot.cant_horas1 + nuevont.cant_horas1;
        nuevototal.cant_horas2 = nuevod.cant_horas2 + nuevom.cant_horas2 + nuevot.cant_horas2 + nuevont.cant_horas2;
        nuevototal.cant_horas3 = nuevod.cant_horas3 + nuevom.cant_horas3 + nuevot.cant_horas3 + nuevont.cant_horas3;

        listaGH.add(nuevod);
        listaGH.add(nuevom);
        listaGH.add(nuevot);
        listaGH.add(nuevont);
        listaGH.add(nuevototal);
        
        return listaGH;
    }

    /***************LISTO*****************************
     * 
     ************************************************/
    
    public List<gradoContrato> getListaGC(){
        listaGC = new ArrayList<>();
        int tcc = 0;
        int tch = 0;
        int tcp = 0;
        Date fecha2 = new Date();
            int year;
            int year2;
            int year3;
            year = fecha2.getYear() + 1900;
            
        List<GradoAcademico> total = getFacade().findAll();
        for (int i = 0; i < total.size(); i++) {
            
            gradoContrato grado = new gradoContrato();
            grado.grado = total.get(i).getNombre();
            int cc = 0;
            int ch = 0;
            int cp = 0;
            
            
            List<Profesor> profesores = profeFacade.findByGrado(total.get(i));
            for (int j = 0; j < profesores.size(); j++) {
                if  (profesores.get(j).getAnoRetiro() >= year || profesores.get(j).isVigente() == true){
                    if ("Completa".equals(profesores.get(j).getContrato())){
                        cc+=1;
                        tcc+=1;
                    }
                    if ("Parcial".equals(profesores.get(j).getContrato())){
                        cp+=1;
                        tcp+=1;
                    }
                    if ("Por Hora".equals(profesores.get(j).getContrato())){
                        ch+=1;
                        tch+=1;
                    }
                }
            }
            grado.contratoC = cc;
            grado.contratoH = ch;
            grado.contratoP = cp;
            
            listaGC.add(grado);
        }
        gradoContrato gradot = new gradoContrato();
        gradot.grado = "Total";
        gradot.contratoC = tcc;
        gradot.contratoP = tcp;
        gradot.contratoH = tch;
        
        listaGC.add(gradot);
        
        return listaGC;
    }

    public GradoAcademico getSelected() {
        return selected;
    }

    public void setSelected(GradoAcademico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private GradoAcademicoFacadeLocal getFacade() {
        return ejbFacade;
    }

    public GradoAcademico prepareCreate() {
        selected = new GradoAcademico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();
        lista_nombres = ejbFacade.findByNombrelist(selected.getNombre());
        if(lista_nombres.isEmpty()){
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("JerarquiaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
        else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grado Académico ya existe", null));
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("GradoAcademicoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("GradoAcademicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<GradoAcademico> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public GradoAcademico getGradoAcademico(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<GradoAcademico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<GradoAcademico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = GradoAcademico.class)
    public static class GradoAcademicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GradoAcademicoController controller = (GradoAcademicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gradoAcademicoController");
            return controller.getGradoAcademico(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GradoAcademico) {
                GradoAcademico o = (GradoAcademico) object;
                return getStringKey(o.getId_grado_academico());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GradoAcademico.class.getName()});
                return null;
            }
        }

    }

}
