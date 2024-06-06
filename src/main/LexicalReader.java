package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalReader {
    
    public final String[] palabraReservada = {"int", "double", "char", "String", "public", "private", "protected", "if", "for", "while"};
    public final char[] caracterEspecial = {'"', '(', ')', '{', '}', '|', '&', '!', '<', '>', '=', '/', '*', '-', '+', '%'};

    public void startReading(String text) {
        while (true) {
            String resultado = analyze(text);
            System.out.println(resultado);
        }
    }

    private String analyze(String line) {
        line = line.trim();

        if (line.endsWith(";")) {
            line = line.substring(0, line.length() - 1).trim();
            String[] tokens = line.split("\\s+");

            if (tokens.length == 2 && isType(tokens[0]) && isIdentifier(tokens[1])) {
                return "Declaración de variable " + tokens[0];
            } else if (tokens.length == 4 && isType(tokens[0]) && isIdentifier(tokens[1]) && tokens[2].equals("=") && isValue(tokens[3])) {
                return "Inicialización de variable " + tokens[0];
            }
        } else if (line.endsWith(")") && line.contains("(") && line.contains(")") ) {
            String[] tokens = line.split("\\s+");
            
            if (tokens.length >= 2 && tokens[0].equals("public") && isIdentifier(tokens[1]) && line.contains("{") && line.contains("}")) {
                return "Constructor";
            } else if ((tokens[0].equals("public") || tokens[0].equals("private") || tokens[0].equals("protected"))
                && isType(tokens[1]) && isIdentifier(tokens[2]) && line.contains("{") && line.contains("}")) {
                return "Método";
            }
        } else if (line.startsWith("if") && line.contains("(") && line.contains(")") && line.contains("{") && line.contains("}")) {
            return "Condicional";
        } else if (line.startsWith("for") && line.contains("(") && line.contains(")") && line.contains("{") && line.contains("}")) {
            return "Bucle for";
        } else if (line.startsWith("while") && line.contains("(") && line.contains(")") && line.contains("{") && line.contains("}")) {
            return "Bucle while";
        }

        return "Sintaxis incorrecta o tipo no reconocido";
    }
    
    private boolean isType(String token) {
        for (String palabraReservada : palabraReservada) {
            if (palabraReservada.equals(token)) {
                return true;
            }
        }
        return false;
    }

    private boolean isIdentifier(String token) {
        // Un identificador válido empieza con una letra y puede contener letras, dígitos y '_'
        if (token == null || token.isEmpty()) {
            return false;
        }
        if (!Character.isLetter(token.charAt(0))) {
            return false;
        }
        for (char c : token.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }

    private boolean isValue(String token) {
        // Simplificación para el ejemplo: acepta números enteros y cadenas entre comillas dobles
        if (token.matches("\\d+")) {
            return true; // Número entero
        }
        if (token.startsWith("\"") && token.endsWith("\"")) {
            return true; // Cadena entre comillas dobles
        }
        return false;
    }
    
    public static void main(String[] args) {
        new LexicalReader();
    }
}
