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
        StringBuilder tokenBuilder = new StringBuilder();
        boolean inStringLiteral = false;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isWhitespace(currentChar) && !inStringLiteral) {
                if (tokenBuilder.length() > 0) {
                    analyzeToken(tokenBuilder.toString());
                    tokenBuilder.setLength(0);
                }
            } else {
                tokenBuilder.append(currentChar);
                if (currentChar == '"') {
                    inStringLiteral = !inStringLiteral;
                }
            }
        }

        if (tokenBuilder.length() > 0) {
            analyzeToken(tokenBuilder.toString());
        }

        setResult();
    }

    private void analyzeToken(String token) {
        // Verificar palabras reservadas
        if (keywords.contains(token)) {
            palReservada++;
            System.out.println("Keyword: " + token);
            return;
        }

        // Verificar si es un identificador
        if (isIdentifier(token)) {
            identificador++;
            System.out.println("Identifier: " + token);
            return;
        }

        // Verificar si es un número entero
        if (isInteger(token)) {
            numEntero++;
            System.out.println("Number: " + token);
            return;
        }

        // Verificar si es un número decimal
        if (isDecimal(token)) {
            numDecimal++;
            System.out.println("Decimal number: " + token);
            return;
        }

        // Verificar operadores específicos
        switch (token) {
            case "++":
                incremento++;
                System.out.println("Increment: " + token);
                return;
            case "--":
                decremento++;
                System.out.println("Decrement: " + token);
                return;
            case "(":
            case ")":
                parentesis++;
                System.out.println("Parenthesis: " + token);
                return;
            case "{":
            case "}":
                llaves++;
                System.out.println("Brace: " + token);
                return;
            case "=":
                asignacion++;
                System.out.println("Assignment: " + token);
                return;
            case "==":
            case "!=":
            case "<=":
            case ">=":
            case "<":
            case ">":
                opRelacional++;
                System.out.println("Relational operator: " + token);
                return;
            case "&&":
            case "||":
                opLogico++;
                System.out.println("Logical operator: " + token);
                return;
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                opAritmetico++;
                System.out.println("Arithmetic operator: " + token);
                return;
            default:
                // Verificar si es un literal de cadena
                if (token.startsWith("\"") && token.endsWith("\"") && token.length() > 1) {
                    cadena++;
                    System.out.println("String literal: " + token);
                    return;
                }
                // Verificar comentarios
                if (token.startsWith("//")) {
                    comentarioLinea++;
                    System.out.println("Line comment: " + token);
                    return;
                }
                if (token.startsWith("/*") && token.endsWith("*/")) {
                    comentario++;
                    System.out.println("Block comment: " + token);
                    return;
                }

                // Si llega aquí, es un error
                errores++;
                System.out.println("Unknown token: " + token);
        }
    }

    private boolean isIdentifier(String token) {
        if (!Character.isLetter(token.charAt(0)) && token.charAt(0) != '_') {
            return false;
        }
        for (int i = 1; i < token.length(); i++) {
            char c = token.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }

    // Método para verificar números enteros (incluye negativos)
    private boolean isInteger(String token) {
        int start = 0;
        if (token.charAt(0) == '-') {
            if (token.length() == 1) {
                return false; // Solo un signo menos no es un número entero válido
            }
            start = 1;
        }
        for (int i = start; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Método para verificar números decimales (incluye negativos)
    private boolean isDecimal(String token) {
        int dotCount = 0;
        int start = 0;
        if (token.charAt(0) == '-') {
            if (token.length() == 1) {
                return false; // Solo un signo menos no es un número decimal válido
            }
            start = 1;
        }
        for (int i = start; i < token.length(); i++) {
            char c = token.charAt(i);
            if (c == '.') {
                dotCount++;
                if (dotCount > 1) {
                    return false;
                }
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return dotCount == 1;
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
