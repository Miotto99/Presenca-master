package com.MundoSenai.Presenca.service;

public class NumberCleaner {
    public static String cleanerNumber(String number){
         return number.replaceAll("[^0-9]","");
    }
}
