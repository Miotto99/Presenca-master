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
            return "Home/home";
        }
    }

    @GetMapping("/cadastro")
    public String GetCadastro() { return "Pessoa/cadastro";}

    @PostMapping("/cadastro")
    public String postcadastro(@RequestParam("Nome") String Nome,
                               @RequestParam("Email") String Email,
                               @RequestParam("CPF") String CPF,
                               @RequestParam("Telefone")String Telefone,
                               @RequestParam("Datanasc")String Datanasc,
                               @RequestParam("Senha")String Senha,
                               @RequestParam("Confirmsenha")String Confirmsenha){
        return "redirect:/";
    }
}
