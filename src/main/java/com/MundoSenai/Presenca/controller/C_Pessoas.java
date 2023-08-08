package com.MundoSenai.Presenca.controller;

import com.MundoSenai.Presenca.model.M_Pessoa;
import com.MundoSenai.Presenca.service.S_Pessoa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha,
                            HttpSession session) {
        session.setAttribute("usuario",S_Pessoa.getPessoaLogin(usuario, senha));
        if(session.getAttribute("usuario") == null){
            return "Login/login";
        }else{
            return "redirect/home";
        }
    }

    @ModelAttribute("usuario")
    public M_Pessoa getUsuario(HttpSession session){
        return (M_Pessoa) session.getAttribute("usuario");
    }

    @GetMapping("/Home")
    public String getHome(@ModelAttribute("usuario")String usuario){
        if(usuario != null){
            return "Home/home";
        }else{
            return "redirect:/"
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
        model.addAttribute("nome", nome);
        model.addAttribute("email", email);
        model.addAttribute("cpf", cpf);
        model.addAttribute("telefone", telefone);
        model.addAttribute("datanasc", datanasc);
        return "Pessoa/cadastro";
    }
}
