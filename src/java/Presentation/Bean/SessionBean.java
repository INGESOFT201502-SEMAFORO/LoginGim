/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.Entity.Admin;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Juan
 */
@Named(value = "sessionBean")
@RequestScoped
public class SessionBean {
    
    private Admin admin;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage faceMessage;
    
    public SessionBean() {
        faceContext = FacesContext.getCurrentInstance(); 
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("sessionUsuario") != null){
            admin = (Admin) httpServletRequest.getSession().getAttribute("sessionUsuario");
        }
    }
    
    public String logout(){
        httpServletRequest.getSession().removeAttribute("sessionUsuario");
        faceMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Session cerrada correctmente", null);
        faceContext.addMessage(null, faceMessage);
        return "index";
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    
    
}
