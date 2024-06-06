package main;

import java.util.HashSet;
import java.util.Set;

public class LexicalReader {
    
    private Set<String> keywords;
    private Set<Character> specialChar;
    int palReservada, identificador, opRelacional, opLogico, opAritmetico, asignacion,
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
                case 0:
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
                    } else {
                        System.out.println("Unknown token: " + c);
                    }
                    break;

                case 1:
                    if (Character.isLetterOrDigit(c)) {
                        token.append(c);
                    } else {
                        if (keywords.contains(token.toString())) {
                            System.out.println("Keyword: " + token.toString());
                        } else {
                            System.out.println("Identifier: " + token.toString());
                        }
                        token.setLength(0);
                        state = 0;
                        i--; // Reanaliza este carácter
                    }
                    break;

                case 2:
                    if (Character.isDigit(c)) {
                        token.append(c);
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
    }
    
    
    
    /*private String analyze(String line) {
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
    }*/
    
    private boolean isType(String token) {
        for (String palabraReservada : keywords) {
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
    
    private void setResult() {
        
    }
    
    public String getResult() {
        return result;
    }
}
