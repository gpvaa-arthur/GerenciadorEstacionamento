package service;

import javax.swing.*;

public class AutenticarSenhaService {
    public int autenticarSenha(JPasswordField campoSenha){
        if(new String(campoSenha.getPassword()).equals("123456")){
            return 1;
        }
        else{
            return -1;
        }
    }
}
