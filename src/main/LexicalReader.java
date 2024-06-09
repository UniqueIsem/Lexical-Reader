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
        keywords.add("main");
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
        keywords.add("print");
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
                case 0: // Detecta si es letra, dígito, caracter especial, espacio o error
                    if (Character.isLetter(c)) {
                        state = 1;
                        token.append(c);
                    } else if (Character.isDigit(c) || (c == '-' && i + 1 < text.length() && Character.isDigit(text.charAt(i + 1)))) {
                        state = 2;
                        token.append(c);
                    } else if (Character.isWhitespace(c)) {
                        state = 0;
                    } else if (c == '/') {
                        state = 5;
                    } else if (c == '"') {
                        state = 8;
                    } else if (specialChar.contains(c)) {
                        if ((c == '+' || c == '-') && i + 1 < text.length() && text.charAt(i + 1) == c) {
                            if (c == '+') {
                                incremento++;
                            } else {
                                decremento++;
                            }
                            i++;
                        } else {
                            token.append(c);
                            state = 3;
                        }
                    } else {
                        errores++;
                        System.out.println("Unknown token: " + c);
                    }
                    break;

                case 1: // Letras
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        token.append(c);
                    } else {
                        if (keywords.contains(token.toString())) {
                            palReservada++;
                            System.out.println("Keyword: " + token.toString());
                        } else {
                            identificador++;
                            System.out.println("Identifier: " + token.toString());
                        }
                        token.setLength(0);
                        state = 0;
                        i--;
                    }
                    break;

                case 2: // Dígitos
                    if (Character.isDigit(c)) {
                        token.append(c);
                    } else if (c == '.') {
                        if (i + 1 < text.length() && Character.isDigit(text.charAt(i + 1))) {
                            token.append(c);
                            state = 4;
                        } else {
                            numEntero++;
                            System.out.println("Number: " + token.toString());
                            token.setLength(0);
                            state = 0;
                            i--;
                        }
                    } else {
                        numEntero++;
                        System.out.println("Number: " + token.toString());
                        token.setLength(0);
                        state = 0;
                        i--;
                    }
                    break;

                case 3: // Caracteres especiales
                    if (c == '=' || (token.charAt(0) == '&' && c == '&') || (token.charAt(0) == '|' && c == '|')) {
                        token.append(c);
                    }
                    String tok = token.toString();
                    if (tok.equals("==") || tok.equals("!=") || tok.equals(">=") || tok.equals("<=") || tok.equals("<") || tok.equals(">")) {
                        opRelacional++;
                    } else if (tok.equals("&&") || tok.equals("||")) {
                        opLogico++;
                    } else if (tok.equals("=")) {
                        asignacion++;
                    } else if (tok.equals("+") || tok.equals("-") || tok.equals("*") || tok.equals("/") || tok.equals("%")) {
                        opAritmetico++;
                    } else if (tok.equals("(") || tok.equals(")")) {
                        parentesis++;
                    } else if (tok.equals("{") || tok.equals("}")) {
                        llaves++;
                    } else {
                        errores++;
                    }
                    System.out.println("Special character: " + token.toString());
                    token.setLength(0);
                    state = 0;
                    break;

                case 4: // Número decimal
                    if (Character.isDigit(c)) {
                        token.append(c);
                    } else {
                        numDecimal++;
                        System.out.println("Decimal number: " + token.toString());
                        token.setLength(0);
                        state = 0;
                        i--;
                    }
                    break;

                case 5: // Comentario o operador de división
                    if (c == '/') {
                        comentarioLinea++;
                        state = 6;
                    } else if (c == '*') {
                        comentario++;
                        state = 7;
                    } else {
                        opAritmetico++;
                        token.append('/');
                        i--;
                        state = 3;
                    }
                    break;

                case 6: // Comentario de línea
                    if (c == '\n') {
                        state = 0;
                    }
                    break;

                case 7: // Comentario de varias líneas
                    if (c == '*' && i + 1 < text.length() && text.charAt(i + 1) == '/') {
                        i++;
                        state = 0;
                    }
                    break;

                case 8: // Literal de cadena
                    if (c == '"') {
                        cadena++;
                        state = 0;
                    } else if (c == '\\' && i + 1 < text.length() && text.charAt(i + 1) == '"') {
                        i++; // Salta la comilla escapada
                    } else if (i + 1 == text.length()) {
                        errores++;
                    }
                    break;

                default:
                    System.out.println("Invalid state: " + state);
            }
        }

        // Manejo de cualquier token remanente al final del texto
        if (token.length() > 0) {
            if (state == 1) {
                if (keywords.contains(token.toString())) {
                    palReservada++;
                    System.out.println("Keyword: " + token.toString());
                } else {
                    identificador++;
                    System.out.println("Identifier: " + token.toString());
                }
            } else if (state == 2) {
                numEntero++;
                System.out.println("Number: " + token.toString());
            } else if (state == 3) {
                System.out.println("Special character: " + token.toString());
            } else if (state == 8) {
                errores++;
                System.out.println("Unterminated string literal: " + token.toString());
            }
        }

        setResult();
    }

    private void throwError() {

    }

    private void setResult() {
        result = "Palabras reservadas: " + palReservada + "\n"
                + "Identificadores : " + identificador + "\n"
                + "Operadores Relacionales : " + opRelacional + "\n"
                + "Operadores Lógicos : " + opLogico + "\n"
                + "Operadores Aritmeticos : " + opAritmetico + "\n"
                + "Asignaciones : " + asignacion + "\n"
                + "Número Entero : " + numEntero + "\n"
                + "Números Decimales : " + numDecimal + "\n"
                + "Incremento : " + incremento + "\n"
                + "Decremento : " + decremento + "\n"
                + "Cadena de Caracteres : " + cadena + "\n"
                + "Comentario : " + comentario + "\n"
                + "Comentario de Linea : " + comentarioLinea + "\n"
                + "Paréntesis : " + parentesis + "\n"
                + "Llaves : " + llaves + "\n"
                + "Errores : " + errores + "\n";
    }

    public String getResult() {
        return result;
    }
}
