/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage.beans;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import mainpackage.Person;

/**
 *
 * @author dkory
 */
@Stateless
public class CheckLogin implements CheckLoginLocal{
    
    @Override
    public boolean checkParameters(HttpServletRequest request) {
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        if(login.isEmpty()) {
            request.setAttribute("msg", "Login can't be null");
            return false;
        }
        if(password.isEmpty()) {
            request.setAttribute("msg", "Password can't be null");
            return false;
        }

        for(Person p : Person.personList) {
            if(p.getLogin().equals(login)) {
                if(p.getPassword().equals(password)) {
                    p.setSessionId(request.getSession().getId());
                    request.setAttribute("sessionId", p.getSessionId());
                    return true;
                }
            }
        }
        
        request.setAttribute("msg", "Login or password not found");
        return false;
    }
}
