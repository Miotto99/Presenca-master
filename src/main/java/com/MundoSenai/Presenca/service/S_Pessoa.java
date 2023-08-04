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

    public static String cadastrarPessoa(String Nome,String Email, String CPF,  String Telefone, String Datanasc, String Senha, String Confirmsenha){
        if(Senha.equals(Confirmsenha)){
            return "A senha e a confirmação de senha devem ser iguais";
        }if(S_CPF.validarCPF(CPF)){
            return "CPF inválido";
        }if(Nome == null || Nome.trim() == ""){
            return "Deve ser informado um nome";
        }if((Email == null || Email.trim() == "") && (NumberCleaner.cleanerNumber(Telefone) == null || NumberCleaner.cleanerNumber(Telefone).trim() == "")){
            return "O e-Mail ou o telefone precisam ser informados";
        }e{
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(Nome);
            m_pessoa.setEmail(Email);
            m_pessoa.setCpf(Long.valueOf(NumberCleaner.cleanerNumber(CPF)));
            m_pessoa.setTelefone(Long.valueOf(NumberCleaner.cleanerNumber(Telefone)));
            m_pessoa.setData_nasc(LocalDate.parse(Datanasc));
            m_pessoa.setSenha(Senha);
            r_pessoa.save(m_pessoa);
            mensagemRetorno = "Cadastro efetuado com sucesso"
        }
        return mensagemRetorno;
    }
}
