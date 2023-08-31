package com.MundoSenai.Presenca.Controller;

import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Model.M_Resposta;
import com.MundoSenai.Presenca.Service.S_Pessoa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
            redirectAttributes.addFlashAttribute("nome", pessoa.getNome());
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
    @ResponseBody
    public M_Resposta postcadastro(@RequestParam("nome") String nome,
                                     @RequestParam("cpf") String cpf,
                                     @RequestParam("email") String email,
                                     @RequestParam("telefone") String telefone,
                                     @RequestParam("dataNasc") String datanasc,
                                     @RequestParam("senha") String senha,
                                     @RequestParam("confSenha") String confirmsenha,
                                     RedirectAttributes redirectAttributes) {

        return S_Pessoa.cadastrarPessoa(nome, cpf, email, telefone, datanasc, senha, confirmsenha);

    }
}
