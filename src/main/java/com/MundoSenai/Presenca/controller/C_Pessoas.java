package com.MundoSenai.Presenca.controller;

import com.MundoSenai.Presenca.model.M_Pessoa;
import com.MundoSenai.Presenca.model.M_Resposta;
import com.MundoSenai.Presenca.service.S_Pessoa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class C_Pessoas {
    @GetMapping("/")
    public String HelloWorld() {
        return "Login/login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        M_Pessoa pessoa = S_Pessoa.getPessoaLogin(usuario, senha);
        session.setAttribute("usuario", pessoa);
        if (session.getAttribute("usuario") == null) {
            return "Login/login";
        } else {
            redirectAttributes.addFlashAttribute("nome",pessoa.getNome());
            return "redirect:/Home";
        }
    }

    @ModelAttribute("usuario")
    public M_Pessoa getUsuario(HttpSession session) {
        return (M_Pessoa) session.getAttribute("usuario");
    }

    @GetMapping("/Home")
    public String getHome(@ModelAttribute("usuario") String usuario) {
        if (usuario != null) {
            return "Home/home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/cadastro")
    public String GetCadastro() {
        return "Pessoa/cadastro";
    }

    @PostMapping("/cadastro")
    public RedirectView postcadastro(@RequestParam("Nome") String nome,
                                     @RequestParam("CPF") String cpf,
                                     @RequestParam("Email") String email,
                                     @RequestParam("Telefone") String telefone,
                                     @RequestParam("Datanasc") String datanasc,
                                     @RequestParam("Senha") String senha,
                                     @RequestParam("Confirmsenha") String confirmsenha,
                                     RedirectAttributes redirectAttributes){

    M_Resposta resposta = S_Pessoa.cadastrarPessoa(nome, cpf, email, telefone, datanasc, senha, confirmsenha);
        redirectAttributes.addFlashAttribute("mensagem",resposta.getMensagem());
        if(resposta.isSucesso())

    {
        return new RedirectView("/",true);
    }else

    {
        redirectAttributes.addFlashAttribute("nome", nome);
        redirectAttributes.addFlashAttribute("email", email);
        redirectAttributes.addFlashAttribute("cpf", cpf);
        redirectAttributes.addFlashAttribute("telefone", telefone);
        redirectAttributes.addFlashAttribute("datanasc", datanasc);
        return new RedirectView("/cadastro",true);
    }
}
}
