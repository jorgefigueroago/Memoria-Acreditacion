package managedbeans;

import entities.Asignatura;
import entities.GradoAcademico;
import entities.Jerarquia;
import entities.Profesor;
import entities.Rol;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.ProfesorFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionbeans.AsignaturaFacadeLocal;
import sessionbeans.RolFacadeLocal;

@Named("profesorController")
@SessionScoped
public class ProfesorController implements Serializable {

    @EJB
    private ProfesorFacadeLocal ejbFacade;
    @EJB
    private AsignaturaFacadeLocal asgFacade;
    @EJB
    private RolFacadeLocal rolFacade;
    
    private List<Profesor> items = null;
    private List<Profesor> profeJerarGrado = null;
    private List<Asignatura> cursos = null;
    private List<Asignatura> cursos_totales = null;
    private List<Rol> roles = null;
    private List<Rol> roles_asignados = null;
    private Profesor selected;
    private Asignatura curso_add;
    private Asignatura curso_del;
    private Rol rol_add;
    private Rol rol_del;
    private Jerarquia jerarq_actual;
    private GradoAcademico grado_actual;
    private Date fecha_actual;
    private List<Profesor> profe_jerarq = null;
    private List<Profesor> profe_grado = null;
    private List<renta> listR = null;
    private List<ContratoNumHora> listCNH = null;
    private List<edad> listEdad = null;
  
    

    public Date getFecha_actual() {
        fecha_actual = new Date();
        return fecha_actual;
    }

    public void setFecha_actual(Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    
    
    public ProfesorController() {
    }
    
       
        
    public List<edad> getListEdad(){
        listEdad = new ArrayList();
        edad nuevo1 = new edad();
        edad nuevo2 = new edad();
        edad nuevo3 = new edad();
        edad nuevo4 = new edad();
        edad nuevo5 = new edad();
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        
        Date fecha = new Date();
        int diac;
        int mesc;
        int yearc;
        diac = fecha.getDate();
        mesc = fecha.getMonth() + 1;
        yearc = fecha.getYear() + 1900;
        
        Date fecha2 = new Date();
        int year;
        int year2;
        int year3;
        year = fecha2.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
            
        List <Profesor> pyear1aux1 = getFacade().findByAno(year3);
        List <Profesor> pyear1aux2 = getFacade().findByAnoRetiro(year3);
        List <Profesor> pyear1 = new ArrayList<Profesor>();
        pyear1.addAll(pyear1aux1);
        pyear1.addAll(pyear1aux2);
        List <Profesor> pyear2aux1 = getFacade().findByAno(year2);
        List <Profesor> pyear2aux2 = getFacade().findByAnoRetiro(year2);
        List <Profesor> pyear2 = new ArrayList<Profesor>();
        pyear2.addAll(pyear2aux1);
        pyear2.addAll(pyear2aux2);
        List <Profesor> pyear3aux1 = getFacade().findByAno(year);
        List <Profesor> pyear3aux2 = getFacade().findByAnoRetiro(year);
        List <Profesor> pyear3 = new ArrayList<Profesor>();
        pyear3.addAll(pyear3aux1);
        pyear3.addAll(pyear3aux2);
            
            
        int total1 = pyear1.size();
        int total2 = pyear2.size();
        int total3 = pyear3.size();
        
        nuevo1.rangos = "Número de docentes menores de 35 años de edad";
        nuevo2.rangos = "Número de docentes entre 35 y 50 años de edad";
        nuevo3.rangos = "Número de docentes entre 50 y 65 años de edad";
        nuevo4.rangos = "Número de docentes con más de 65 años de edad";
        nuevo5.rangos = "TOTAL";
        
        
        
        for (int i = 0; i < pyear1.size(); i++) {
            int edad;
            int dia;
            int mes;
            int ano;
            dia = pyear1.get(i).getFecha_nacimiento().getDate();
            mes = pyear1.get(i).getFecha_nacimiento().getMonth() + 1;
            ano = pyear1.get(i).getFecha_nacimiento().getYear() + 1900;
            
           
                if (mes == mesc) {
                    if (dia > diac) {
                        edad = yearc - ano - 1;
                    }
                    else {
                        edad = yearc - ano;
                    }
                }
                else{
                    if (mes > mesc) {
                        edad = yearc - ano - 1;
                    }
                    else {
                        edad = yearc - ano ;
                    }
                }

                if (edad < 35) {
                    cont1+=1;
                }
                else{
                    if (35 <= edad && edad < 50) {
                        cont2+=1;
                    }
                    else{
                        if (50 <= edad && edad < 65) {
                            cont3+=1;
                        }
                        else {
                            cont4+=1;
                        }
                    }
                }
            
        
        }
            nuevo1.edad = cont1;
            nuevo2.edad = cont2;
            nuevo3.edad = cont3;
            nuevo4.edad = cont4;
            nuevo5.edad = total1;

            cont1 = 0;cont2 = 0;cont3 = 0;cont4 = 0;
            
        
        
        
        for (int i = 0; i < pyear2.size(); i++) {
            int edad;
            int dia;
            int mes;
            int ano;
            dia = pyear2.get(i).getFecha_nacimiento().getDate();
            mes = pyear2.get(i).getFecha_nacimiento().getMonth() + 1;
            ano = pyear2.get(i).getFecha_nacimiento().getYear() + 1900;
            
            
               if (mes == mesc) {
                    if (dia > diac) {
                        edad = yearc - ano - 1;
                    }
                    else {
                        edad = yearc - ano;
                    }
                }
                else{
                    if (mes > mesc) {
                        edad = yearc - ano - 1;
                    }
                    else {
                        edad = yearc - ano ;
                    }
                }

                if (edad < 35) {
                    cont1+=1;
                }
                else{
                    if (35 <= edad && edad < 50) {
                        cont2+=1;
                    }
                    else{
                        if (50 <= edad && edad < 65) {
                            cont3+=1;
                        }
                        else {
                            cont4+=1;
                        }
                    }
                }
            
            
        }
        nuevo1.edad2 = cont1;
        nuevo2.edad2 = cont2;
        nuevo3.edad2 = cont3;
        nuevo4.edad2 = cont4;
        nuevo5.edad2 = total2;
        
        cont1 = 0;cont2 = 0;cont3 = 0;cont4 = 0;
        
        
        for (int i = 0; i < pyear3.size(); i++) {
            int edad;
            int dia;
            int mes;
            int ano;
            dia = pyear3.get(i).getFecha_nacimiento().getDate();
            mes = pyear3.get(i).getFecha_nacimiento().getMonth() + 1;
            ano = pyear3.get(i).getFecha_nacimiento().getYear() + 1900;
            
              if (mes == mesc) {
                    if (dia > diac) {
                        edad = yearc - ano - 1;
                    }
                    else {
                        edad = yearc - ano;
                    }
                }
                else{
                    if (mes > mesc) {
                        edad = yearc - ano - 1;
                    }
                    else {
                        edad = yearc - ano ;
                    }
                }

                if (edad < 35) {
                    cont1+=1;
                }
                else{
                    if (35 <= edad && edad < 50) {
                        cont2+=1;
                    }
                    else{
                        if (50 <= edad && edad < 65) {
                            cont3+=1;
                        }
                        else {
                            cont4+=1;
                        }
                    }
                }
            
        }
        nuevo1.edad3 = cont1;
        nuevo2.edad3 = cont2;
        nuevo3.edad3 = cont3;
        nuevo4.edad3 = cont4;
        nuevo5.edad3 = total3;
        
        cont1 = 0;cont2 = 0;cont3 = 0;cont4 = 0;
        
        
        
        
        
        listEdad.add(nuevo1);
        listEdad.add(nuevo2);
        listEdad.add(nuevo3);
        listEdad.add(nuevo4);
        listEdad.add(nuevo5);
        
        
        return listEdad;
    }

    public List<renta> getListR() {
        listR = new ArrayList<>();
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
        renta nuevo = new renta();
        renta nuevo2 = new renta();
        nuevo.nombre = "Promedio de renta para jornada completa (UF)";
        nuevo2.nombre = "Promedio del valor hora contrato (UF)";
        float renta1 = 0;
        float renta2 = 0;
        float renta3 = 0;
        int cont = 0;
        double horaContrato;
        double horaContrato2;
        double horaContrato3;
        
        List <Profesor> pyear1aux1 = getFacade().findByAno(year3);
        List <Profesor> pyear1aux2 = getFacade().findByAnoRetiro(year3);
        List <Profesor> pyear1 = new ArrayList<Profesor>();
        //pyear1.addAll(pyear1aux1);
        pyear1.addAll(pyear1aux2);
        List <Profesor> pyear2aux1 = getFacade().findByAno(year2);
        List <Profesor> pyear2aux2 = getFacade().findByAnoRetiro(year2);
        List <Profesor> pyear2 = new ArrayList<Profesor>();
        //pyear2.addAll(pyear2aux1);
        pyear2.addAll(pyear2aux2);
        List <Profesor> pyear3aux1 = getFacade().findByAno(year);
        List <Profesor> pyear3aux2 = getFacade().findByAnoRetiro(year);
        List <Profesor> pyear3 = new ArrayList<Profesor>();
       // pyear3.addAll(pyear3aux1);
        pyear3.addAll(pyear3aux2);
        
        for (int i = 0; i < pyear1.size(); i++) {
            if ("Completa".equals(pyear1.get(i).getContrato())) {
                renta1+= pyear1.get(i).getRenta();
                cont+= 1;
            }

        }
        renta1 = renta1 / cont;
        cont = 0;
        for (int i = 0; i < pyear2.size(); i++) {
            if ("Completa".equals(pyear2.get(i).getContrato())) {
                renta2+= pyear2.get(i).getRenta();
                cont+= 1;
            }
            
        }
        renta2 = renta2 / cont;
        cont = 0;
        for (int i = 0; i < pyear3.size(); i++) {
            if ("Completa".equals(pyear3.get(i).getContrato())) {
                renta3+= pyear3.get(i).getRenta();
                cont+= 1;
            }
        
        }
        renta3 = renta3 / cont;
        cont = 0;
        
        horaContrato = renta1/44;
        horaContrato2 = renta2/44;
        horaContrato3 = renta3/44;
        nuevo.renta1 = renta1;
        nuevo.renta2 = renta2;
        nuevo.renta3 = renta3;
        nuevo2.renta1 = Math.rint(horaContrato*100)/100;
        nuevo2.renta2 = Math.rint(horaContrato2*100)/100;
        nuevo2.renta3 = Math.rint(horaContrato3*100)/100;
        listR.add(nuevo);
        listR.add(nuevo2);
        return listR;
    }
    
    /*************************LISTO***********************
     * 
     *****************************************************/
    public List<ContratoNumHora> getListCNH(){
        listCNH = new ArrayList<>();
        Date fecha = new Date();
        int year;
        int year2;
        int year3;
        year = fecha.getYear() + 1900;
        year2 = year - 1;
        year3 = year - 2;
        int totald = 0;
        int totalc1 = 0; int totalc2 = 0; int totalc3 = 0;
        int totalp1 = 0; int totalp2 = 0; int totalp3 = 0;
        int totalh1 = 0; int totalh2 = 0; int totalh3 = 0;
        float horasc1 = 0; float horasc2 = 0; float horasc3 = 0;
        float horasp1 = 0; float horasp2 = 0; float horasp3 = 0;
        float horash1 = 0; float horash2 = 0; float horash3 = 0;
        float totalhoras = 0;
        
        List<Profesor> total = getFacade().findAll();
        for (int i = 0; i < total.size() ; i++) {
            for (int j = 0; j < total.get(i).getAsignaturaList().size(); j++) {
                
                if("Completa".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro()>= year3)){
                        horasc1 = total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalc1+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        horasc2+=total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalc2+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)) {
                        horasc3+=total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalc3+= 1;

                        }

                    }
                
            
                if("Parcial".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year3)){
                        horasp1 = total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalp1+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        horasp2+=total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalp2+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)){
                        horasp3+=total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalp3+= 1;

                        }

                    }
                
        
                if("Por Hora".equals(total.get(i).getContrato())){
                    if((total.get(i).getAno_ingreso() <= year3) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year3)){
                        horash1 += total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalh1+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year2) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year2)){
                        horash2+=total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalh2+= 1;
                        }
                    if((total.get(i).getAno_ingreso() <= year) &&  (total.get(i).isVigente() == true || total.get(i).getAnoRetiro() >= year)){
                        horash3+=total.get(i).getAsignaturaList().get(j).getCant_horas();
                        totalh3+= 1;

                        }

                    }
                }
        
                
            }
        
        int totald1 = totalc1 + totalp1 + totalh1;
        int totald2 = totalc2 + totalp2 + totalh2;
        int totald3 = totalc3 + totalp3 + totalh3;
        float totalhoras1 = horasc1 + horasp1 + horash1;
        float totalhoras2 = horasc2 + horasp2 + horash2;
        float totalhoras3 = horasc3 + horasp3 + horash3; 
        
        ContratoNumHora nuevo1 = new ContratoNumHora();
        ContratoNumHora nuevo2 = new ContratoNumHora();
        ContratoNumHora nuevo3 = new ContratoNumHora();
        ContratoNumHora nuevo4 = new ContratoNumHora();
        ContratoNumHora nuevo5 = new ContratoNumHora();
        ContratoNumHora nuevo6 = new ContratoNumHora();
        nuevo1.nombre = "Número de docentes Jornada Completa";
        nuevo2.nombre = "Horas de docentes Jornada Completa";
        nuevo3.nombre = "Número de docentes Jornada Parcial";
        nuevo4.nombre = "Horas de docentes Jornada Parcial";
        nuevo5.nombre = "Número de docentes Jornada Por Hora";
        nuevo6.nombre = "Horas de docentes contratados Por Hora";
        
        nuevo1.horast1 = totalc1;
        nuevo1.horast2 = totalc2;
        nuevo1.horast3 = totalc3;
        nuevo2.horast1 = horasc1;
        nuevo2.horast2 = horasc2;
        nuevo2.horast3 = horasc3;
        nuevo3.horast1 = totalp1;
        nuevo3.horast2 = totalp2;
        nuevo3.horast3 = totalp3;
        nuevo4.horast1 = horasp1;
        nuevo4.horast2 = horasp2;
        nuevo4.horast3 = horasp3;
        nuevo5.horast1 = totalh1;
        nuevo5.horast2 = totalh2;
        nuevo5.horast3 = totalh3;
        nuevo6.horast1 = horash1;
        nuevo6.horast2 = horash2;
        nuevo6.horast3 = horash3;
        
        listCNH.add(nuevo1);
        listCNH.add(nuevo2);
        listCNH.add(nuevo3);
        listCNH.add(nuevo4);
        listCNH.add(nuevo5);
        listCNH.add(nuevo6);
        
        ContratoNumHora docentes = new ContratoNumHora();
        ContratoNumHora horas = new ContratoNumHora();
        
        docentes.nombre = "TOTAL DOCENTES";
        docentes.horast1 = totald1;
        docentes.horast2 = totald2;
        docentes.horast3 = totald3;
        
        horas.nombre = "TOTAL HORAS";
        horas.horast1 = totalhoras1;
        horas.horast2 = totalhoras2;
        horas.horast3 = totalhoras3;
        
        
        listCNH.add(docentes);
        listCNH.add(horas);
        totalhoras = 0;
        
        
        
        return listCNH;
    }
    
    
    public void desvincular(){
        selected.setVigente(false);
        Date fecha = new Date();
        int ano = fecha.getYear() +1900;
        selected.setAnoRetiro(ano);
        ejbFacade.edit(selected);
    }
    
    
    public Profesor getSelected() {
        return selected;
    }

    public Jerarquia getJerarq_actual() {
        return jerarq_actual;
    }

    public void setJerarq_actual(Jerarquia jerarq_actual) {
        this.jerarq_actual = jerarq_actual;
    }

    public List<Profesor> getProfe_jerarq() {
        profe_jerarq = ejbFacade.findByJerarquia(jerarq_actual);
        return profe_jerarq;
    }

    public GradoAcademico getGrado_actual() {
        return grado_actual;
    }

    public void setGrado_actual(GradoAcademico grado_actual) {
        this.grado_actual = grado_actual;
    }

    public List<Profesor> getProfe_grado() {
        return profe_grado;
    }

    public void setProfe_grado(List<Profesor> profe_grado) {
        this.profe_grado = profe_grado;
    }

    public void setProfe_jerarq(List<Profesor> profe_jerarq) {
        this.profe_jerarq = profe_jerarq;
    }

    public void setSelected(Profesor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProfesorFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Asignatura getCurso_add() {
        return curso_add;
    }

    public void setCurso_add(Asignatura curso_add) {
        this.curso_add = curso_add;
    }

    public Asignatura getCurso_del() {
        return curso_del;
    }

    public void setCurso_del(Asignatura curso_del) {
        this.curso_del = curso_del;
    }
    
    public List<Asignatura> getCursos_totales() {
        cursos_totales = asgFacade.findAll();
        return cursos_totales;
    }

    public void setCursos_totales(List<Asignatura> cursos_totales) {
        this.cursos_totales = cursos_totales;
    }
    
    public void addAsignatura(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (selected.getAsignaturaList().contains(curso_add)){
            System.out.println("La asignatura ya existe");
            JsfUtil.addErrorMessage("La asignatura ya existe");
            context.addMessage(null, new FacesMessage("La asignatura ya existe"));
        }
        else{
            curso_add.getProfesorasignaturaList().add(selected);
            selected.getAsignaturaList().add(curso_add);
            asgFacade.edit(curso_add);
            ejbFacade.edit(selected);
        }
        
    }
    
    public void delAsignatura(){
        curso_del.getProfesorasignaturaList().remove(selected);
        selected.getAsignaturaList().remove(curso_del);
        asgFacade.edit(curso_del);
        ejbFacade.edit(selected);
    }
    
    public Rol getRol_add() {
        return rol_add;
    }

    public void setRol_add(Rol rol_add) {
        this.rol_add = rol_add;
    }

    public Rol getRol_del() {
        return rol_del;
    }

    public List<Rol> getRoles() {
        roles = rolFacade.findAll();
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void setRol_del(Rol rol_del) {
        this.rol_del = rol_del;
    }
    
    public List<Rol> getRoles_asignados() {
        roles_asignados = selected.getRolList();
        return roles_asignados;
    }

    public void setRoles_asignados(List<Rol> roles_asignados) {
        this.roles_asignados = roles_asignados;
    }
    
    public int calcularHorasJerarquia(){
        int horas = 0;
        for (int i = 0; i < profe_jerarq.size(); i++) {
            for (int j = 0; j < profe_jerarq.get(i).getAsignaturaList().size(); j++) {
                horas = (int) (horas + profe_jerarq.get(i).getAsignaturaList().get(j).getCant_horas());
            }
        }
        return horas;
    }
    
    
    
    public int calcularHorasGrado(){
        int horas = 0;
        for (int i = 0; i < profe_grado.size(); i++) {
            for (int j = 0; j < profe_grado.get(i).getAsignaturaList().size(); j++) {
                horas = (int) (horas + profe_grado.get(i).getAsignaturaList().get(j).getCant_horas());
            }
        }
        return horas;
    }
    
    
    public void addRol(){
        
        rol_add.getProfesorrolList().add(selected);
        selected.getRolList().add(rol_add);
        rolFacade.edit(rol_add);
    }
    
    public void delRol(){
        rol_del.getProfesorrolList().remove(selected);
        selected.getRolList().remove(rol_del);
        rolFacade.edit(rol_del);
    }    

    public Profesor prepareCreate() {
        selected = new Profesor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if ("TITULAR".equals(selected.getJerarquia().getNombre())) {
            selected.setRenta(90);
            }
        else{    
            if ("ASOCIADO".equals(selected.getJerarquia().getNombre())) {
                selected.setRenta(80);
            }
            else{    
                if ("ASISTENTE".equals(selected.getJerarquia().getNombre())) {
                    selected.setRenta(70);
                }
                else{    
                    if ("INSTRUCTOR".equals(selected.getJerarquia().getNombre())) {
                        selected.setRenta(60);
                    }
                    else{    
                        if ("AYUDANTE".equals(selected.getJerarquia().getNombre())) {
                            selected.setRenta(50);
                        }   
                        else{    
                            if ("ADJUNTO CATEGORÍA I".equals(selected.getJerarquia().getNombre())) {
                                selected.setRenta(40);
                            }
                            else{    
                                if ("ADJUNTO CATEGORÍA II".equals(selected.getJerarquia().getNombre())) {
                                    selected.setRenta(40);
                                } 
                                else{    
                                    if ("INSTRUCTOR CATEGORÍA I".equals(selected.getJerarquia().getNombre())) {
                                        selected.setRenta(30);
                                    }
                                    else{    
                                        if ("INSTRUCTOR CATEGORÍA II".equals(selected.getJerarquia().getNombre())) {
                                        selected.setRenta(20);
                                        }
                                        else{    
                                            if ("AYUDANTE PROFESOR".equals(selected.getJerarquia().getNombre())) {
                                                selected.setRenta(10);
                                            }    
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        selected.setVigente(true);
        System.out.println("Profesor:" + selected.getNombre() + ", Jerarquia: " + selected.getJerarquia() + "Renta:" + selected.getRenta());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfesorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProfesorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Asignatura> getCursos() {
        cursos = selected.getAsignaturaList();
        return cursos;
    }

    public List<Profesor> getItems() {
        
            items = getFacade().findAll();
        
        return items;
    }

    public List<Profesor> getProfeJerarGrado(Jerarquia j , GradoAcademico g) {
        profeJerarGrado = getFacade().findByJerarYGrado(j, g);
        return profeJerarGrado;
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

    public Profesor getProfesor(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Profesor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Profesor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Profesor.class)
    public static class ProfesorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesorController controller = (ProfesorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesorController");
            return controller.getProfesor(getKey(value));
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
            if (object instanceof Profesor) {
                Profesor o = (Profesor) object;
                return getStringKey(o.getRut_profesor());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Profesor.class.getName()});
                return null;
            }
        }

    }

}
