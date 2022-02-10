package it.unipi.dii.dsmt.therappist.Utils;

import it.unipi.dii.dsmt.therappist.dto.UserDTO;

import javax.servlet.http.HttpSession;

//se vuoi usarli ci sono se non vuoi usarli do not worry
//alla fine ci sta una classe per gestire la sessione
//passare il model son d'accordo che è brutto ma alla fine
//la sessione non riguarda front-end o back-end riguarda
//tutta l'applicazione quindi passarla come argomento
//non dovrebbe essere un mega problema no????
//so che non è il massimo ma mi sembrava una classe carinissima
public class SessionUtils {

    public static void setSession(HttpSession session, UserDTO user){

        session.setAttribute("user",user);
    }

    public static UserDTO getUserSession(HttpSession session){

        return (UserDTO) session.getAttribute("user");
    }

    public static void updateSession(HttpSession session, UserDTO user){

        session.removeAttribute("user");
        session.setAttribute("user",user);
    }
    public static void unsetSession(HttpSession session){

        session.removeAttribute("user");

    }
}
