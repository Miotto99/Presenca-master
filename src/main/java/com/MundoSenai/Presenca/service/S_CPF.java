package com.MundoSenai.Presenca.service;

public class S_CPF {
    public static boolean validarCPF(String cpf) {
       cpf = NumberCleaner.cleanerNumber(cpf);

       if(cpf.length() != 11){
           return false;
       }

       if(cpf.matches( "(\\d)\\1{10}" )){
           return false;
       }

        String finalCpf = cpf;
        if (cpf == null || cpf.length() != 11 || cpf.chars().allMatch(c -> c == finalCpf.charAt(0))) {
            return false;
        }

        int d1 = calcularDigito(cpf.substring(0, 9), new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2});
        int d2 = calcularDigito(cpf.substring(0, 9) + d1, new int[] {11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

        return cpf.equals(cpf.substring(0, 9) + d1 + d2);
    }

    private static int calcularDigito(String str, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Integer.parseInt(str.substring(i, i + 1)) * pesos[i];
        }
        int resultado = 11 - (soma % 11);
        return (resultado > 9) ? 0 : resultado;

    }
}
