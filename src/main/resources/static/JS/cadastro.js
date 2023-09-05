function validaCampoVazio(campo){
        if(campo.trim() == ''){
            return true;
        }else{
            return false;
        }
    }

    function createToastArea(){
       if($(".toaster").length == 0){
            $("body").prepend(''+
               '<div aria-live="polite" aria-atomic="true"'+
               'class="position-absolute top-0 end-0 erros">'+
               '<div class="toaster position-absolute top-0 end-0">'+
               '</div>'+
               '</div>');
       }
    }

    function showToast(alerta) {
        createToastArea();
        let tipo
        if(alerta.sucesso){
            tipo = ' bg-success ';
        }else{
            tipo = ' bg-danger ';
        }
        const toastElement = $('<div class="mt-1 toast align-items-center'+tipo+'text-white border-0" role="alert" aria-live="assertive" aria-atomic="true">'+
                                    '<div class="d-flex">'+
                                        '<div class="toast-body">'+
                                            alerta.mensagem+
                                        '</div>'+
                                        '<button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>'+
                                    '</div>'+
                               '</div>');

            toastElement.on('hidden.bs.toast', function () {
                $(this).remove();
            });

            $(".toaster").append(toastElement);
            const newToast = new bootstrap.Toast(toastElement);
            newToast.show();
        }

    function validaEnvio(){
        let podeEnviar = true;
        let nome = $("#nome").val();
        let cpf = $("#cpf").val();
        let email = $("#email").val();
        let dataNasc = $("#dataNasc").val();
        let telefone = $("#telefone").val();
        let senha = $("#senha").val();
        let confSenha = $("#confSenha").val();

        $("#errorMensage").text('')
        $(".error-field").removeClass("error-field");
        if(validaCampoVazio(nome)){
            podeEnviar = false;
            showToast({sucesso: false, mensagem : "O nome precisa ser preenchido"});
            $(".gNome").addClass("error-field");
        }if(!validarCPF(cpf)){
             podeEnviar = false;
            showToast({sucesso: false, mensagem : "O cpf precisa ser preenchido"});
            $(".gCpf").addClass("error-field");
        }if(validaCampoVazio(email) && validaCampoVazio(telefone)){
            podeEnviar = false;
            showToast({sucesso: false, mensagem : "O telefone ou o email precisa ser preenchido"});
            $(".gTelefone").addClass("error-field");
            $(".gEmail").addClass("error-field");
        }if(validaCampoVazio(dataNasc)){
             podeEnviar = false;
            showToast({sucesso: false, mensagem : "A data de nascimento precisa ser preenchido"});
            $(".gDataNasc").addClass("error-field");
        }if(validaCampoVazio(senha) || senha != confSenha){
              podeEnviar = false;
            showToast({sucesso: false, mensagem : "A senha precisa ser informada e igual a confirmação de senha"});
            $(".gSenha").addClass("error-field");
            $(".gConfSenha").addClass("error-field");
        }

        if(podeEnviar){
            $.ajax({
                type: "POST",
                url: "/cadastro",
                data:{
                    nome:nome,
                    email:email,
                    cpf:cpf,
                    telefone:telefone,
                    dataNasc:dataNasc,
                    senha:senha,
                    confSenha:confSenha,
                },
                success: function(data){
                    $("#errorMensage").text('')
                    if(data.sucesso){
                        window.location.href="/";
                    }else{
                        $("#errorMensage").append(data.mensagem);
                    }
                },
                error: function(){
                    alert("Deu ruim");
                }
            });
        }
    }