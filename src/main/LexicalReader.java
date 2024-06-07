package main;

import java.util.HashSet;
import java.util.Set;

public class LexicalReader {
    
    private Set<String> keywords;
    private Set<Character> specialChar;
    private int palReservada, identificador, opRelacional, opLogico, opAritmetico, asignacion,
            numEntero, numDecimal, incremento, decremento, cadena, comentario, comentarioLinea,
            parentesis, llaves, errores;
    public String result;

    public LexicalReader() {
        initKeywords();
        initSpecialCharacters();
    }
    
    private void initKeywords() {
        keywords = new HashSet<>();
        keywords.add("int");
        keywords.add("double");
        keywords.add("char");
        keywords.add("String");
        keywords.add("public");
        keywords.add("private");
        keywords.add("protected");
        keywords.add("if");
        keywords.add("switch");
        keywords.add("case");
        keywords.add("default");
        keywords.add("for");
        keywords.add("break");
        keywords.add("continue");
        keywords.add("do");
        keywords.add("while");
    }
    
    private void initSpecialCharacters() {
        specialChar = new HashSet<>();
        specialChar.add('"');
        specialChar.add('(');
        specialChar.add(')');
        specialChar.add('{');
        specialChar.add('}');
        specialChar.add('|');
        specialChar.add('&');
        specialChar.add('!');
        specialChar.add('<');
        specialChar.add('>');
        specialChar.add('=');
        specialChar.add('/');
        specialChar.add('*');
        specialChar.add('-');
        specialChar.add('+');
        specialChar.add('%');
    }

    public void analyze(String text) {
        int state = 0;
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            switch (state) {
                case 0: //detects if letter, digit, special char, space or error
                    if (Character.isLetter(c)) {
                        state = 1;
                        token.append(c);
                    } else if (Character.isDigit(c)) {
                        state = 2;
                        token.append(c);
                    } else if (Character.isWhitespace(c)) {
                        state = 0;
                    } else if (specialChar.contains(c)) {
                        state = 3;
                        token.append(c);
                    } else if (token.isEmpty()) {
                        state = 4;
                    } else {
                        System.out.println("Unknown token: " + c);
                    }
                    break;

                case 1: //Letters
                    if (Character.isLetterOrDigit(c) || c == '_') { //keeps iterating chars to get word
                        token.append(c);
                    } else if (specialChar.contains(c)) { //error detecting special char
                        errores++;
                        System.out.println("Error: " + token.toString());
                    } else { //full word
                        if (keywords.contains(token.toString())) { //detects a keyword
                            palReservada++;
                            System.out.println("Keyword: " + token.toString());
                        } else { //detects a identifyer
                            identificador++;
                            System.out.println("Identifier: " + token.toString());
                        }
                        //reboot when its a space, tab or endline
                        token.setLength(0);
                        state = 0;
                        i--; //skip space iteration
                    }
                    break;

                case 2: //Digits
                    if (Character.isDigit(c)) {
                        token.append(c);
                    } else if (Character.isLetter(c) || specialChar.contains(c)) {
                        errores++;
                    } else if (c == '.') {
                        
                    } else {
                        System.out.println("Number: " + token.toString());
                        token.setLength(0);
                        state = 0;
                        i--; // Reanaliza este carácter
                    }
                    break;

                case 3:
                    // Un estado simple para manejar caracteres especiales de un solo carácter
                    System.out.println("Special character: " + token.toString());
                    token.setLength(0);
                    state = 0;
                    i--; // Reanaliza este carácter
                    break;

                default:
                    System.out.println("Invalid state: " + state);
            }
        }

        // Manejo de cualquier token remanente al final del texto
        if (token.length() > 0) {
            if (state == 1) {
                if (keywords.contains(token.toString())) {
                    System.out.println("Keyword: " + token.toString());
                } else {
                    System.out.println("Identifier: " + token.toString());
                }
            } else if (state == 2) {
                System.out.println("Number: " + token.toString());
            } else if (state == 3) {
                System.out.println("Special character: " + token.toString());
            }
        }
        
        setResult();
    }
    
   
    private void throwError() {
        
    }
    
    private void setResult() {
        result = "Palabras reservadas: " + palReservada + "\n" +
                 "Identificadores : " + identificador + "\n" +
                 "Operadores Relacionales : " + opRelacional + "\n" +
                 "Operadores Lógicos : " + opLogico + "\n" +
                 "Operadores Aritmeticos : " + opAritmetico + "\n" +
                 "Asignaciones : " + asignacion +  "\n" +
                 "Número Entero : " + numEntero +  "\n" +
                 "Números Decimales : " + numDecimal + "\n" +
                 "Incremento : " + incremento + "\n" +
                 "Decremento : " + decremento + "\n" +
                 "Cadena de Caracteres : " + cadena + "\n" +
                 "Comentario : " + comentario + "\n" +
                 "Comentario de Linea : " + comentarioLinea +  "\n" +
                 "Paréntesis : " + parentesis + "\n" +
                 "Llaves : " + llaves + "\n" +
                 "Errores : " + errores + "\n";
    }
    
    public String getResult() {
        return result;
    }
}
