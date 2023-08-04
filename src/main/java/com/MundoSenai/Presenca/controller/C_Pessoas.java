package com.MundoSenai.Presenca.controller;

import com.MundoSenai.Presenca.service.S_Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

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
    public String postcadastro(@RequestParam("Nome") String nome,
                               @RequestParam("Email") String email,
                               @RequestParam("CPF") String cpf,
                               @RequestParam("Telefone")String telefone,
                               @RequestParam("Datanasc") String datanasc,
                               @RequestParam("Senha")String senha,
                               @RequestParam("Confirmsenha")String confirmsenha,
                               Model model){
        String mensagem = S_Pessoa.cadastrarPessoa(nome, email, cpf, telefone, datanasc, senha, confirmsenha);
        model.addAttribute("mensagem", mensagem);
        return "Pessoa/cadastro";
    }
}
