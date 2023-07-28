package com.MundoSenai.Presenca.controller;

import com.MundoSenai.Presenca.service.S_Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class C_Pessoas {
    @GetMapping("/")
    public String HelloWorld() {
        return "Login/login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,@RequestParam("senha") String senha) {
        if(S_Pessoa.getPessoaLogin(usuario, senha) == null){
            return "Login/login";
        }else{
            return "Teste";
        }
    }
}
