/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.AdminDAO;
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
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    private String username;
    private String password;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage faceMessage;
    
    public LoginBean() {
        faceContext = FacesContext.getCurrentInstance(); 
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    
    public String login(){
        /*System.out.println(username);
        System.out.println(password );*/
        
        AdminDAO adminDao = new AdminDAO();
        Admin admin = adminDao.searchByUsername(username);
        
        if (admin != null){
            System.out.println(admin.getUsername());
            System.out.println(admin.getPassword());
            if(admin.getPassword().equals(password)){
               httpServletRequest.getSession().setAttribute("sessionUsuario", admin);
               faceMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Acceso Correcto", null);
               faceContext.addMessage(null, faceMessage);
               return "home";
            }else{
                faceMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario o Contraseña incorrecto", null);
                faceContext.addMessage(null, faceMessage);
                return "index";
            }
        }else{
            faceMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario o Contraseña incorrecto", null);
            faceContext.addMessage(null, faceMessage);
            return "index";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
