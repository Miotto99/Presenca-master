package com.MundoSenai.Presenca.service;

import com.MundoSenai.Presenca.model.M_Pessoa;
import com.MundoSenai.Presenca.repository.R_Pessoa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa){
        this.r_pessoa = r_pessoa;
    }

    public static M_Pessoa getPessoaLogin(String usuario, String senha){
        return r_pessoa.findByUsuarioESenha(Long.valueOf(usuario), senha);
    }

    public static void cadastrarPessoa(String Nome,String Email, String CPF,  String Telefone, String Datanasc, String Senha, String Confirmsenha){
        M_Pessoa m_pessoa = new M_Pessoa();
        m_pessoa.setNome(Nome);
        m_pessoa.setEmail(Email);
        m_pessoa.setCpf(Long.valueOf(CPF));
        m_pessoa.setTelefone(Long.valueOf(Telefone));
        m_pessoa.setData_nasc(LocalDate.parse(Datanasc));
        m_pessoa.setSenha(Senha);
        r_pessoa.save(m_pessoa);

    }
}
