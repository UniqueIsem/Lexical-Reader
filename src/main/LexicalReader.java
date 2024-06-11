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
            
            //if current character is whitespace and we are not inside a string literal
            if (Character.isWhitespace(currentChar) && !inStringLiteral) {
                //if token isn't empty
                if (tokenBuilder.length() > 0) {
                    analyzeToken(tokenBuilder.toString());
                    tokenBuilder.setLength(0);
                }
            } else { //append the char into the token
                tokenBuilder.append(currentChar);
                if (currentChar == '"') { //token may be a string literal
                    inStringLiteral = !inStringLiteral;
                }
            }
        }

        if (tokenBuilder.length() > 0) { //necessary????
            analyzeToken(tokenBuilder.toString());
        }

        setResult();
    }

    private void analyzeToken(String token) {
        State state = State.START;
        State previousState = State.START;
        StringBuilder wordBuilder = new StringBuilder();
        boolean errorFound = false;

        for (char currentChar : token.toCharArray()) { //analyze each char on the token char array
            previousState = state;
            state = transition(state, previousState, currentChar);
            
            if (state == State.ERROR) {
                errorFound = true;
            }
            
            if (!errorFound && (state == State.IDENTIFIER || state == State.KEYWORD)) { // if state equals identifier or keyword
                wordBuilder.append(currentChar);
            }
        }
        
        if (errorFound) {
            errores++;
            System.out.println("Token que genero error: " + token);
            return; //Stop analyzing if detects an error
        }
        
        if (wordBuilder.length() > 0) { 
            if (keywords.contains(wordBuilder.toString())) { //wordBuilder matches with a 'keywords' item
                palReservada++;
                System.out.println("Palabra reservada: " + wordBuilder);
            } else {
                identificador++;
                System.out.println("Identificador: " + wordBuilder);
            }
        }
        
        switch (state) {
            case ARITHMETIC_INT_DECR:
                opAritmetico++;
                System.out.println("Op aritmetico: " + token);
                break;
            case ERROR:
                errores++;
                System.out.println("Token que genero error: " + token);
                break;
            case IS_COMMENT:
                comentario++;
                System.out.println("Comentario: " + token);
                break;
            case IS_LINE_COMMENT:
                comentarioLinea++;
                System.out.println("Comentario de linea: " + token);
                break;
            case IS_INTEGER:
                numEntero++;
                System.out.println("Numero entero: " + token);
                break;
            case IS_INTEGER_NEGATIVE:
                numEntero++;
                System.out.println("Numero entero negativo: " + token);
                break;
            case IS_DECREMENT:
                decremento++;
                System.out.println("Decremento: " + token);
                break;
            case IS_ARITMETHIC:
                opAritmetico++;
                System.out.println("Operador aritmetico: " + token);
                break;
            case IS_BRACE:
                llaves++;
                System.out.println("Llave: " + token);
                break;
            case IS_PARENTHESIS:
                parentesis++;
                System.out.println("Parentesis: " + token);
                break;
            case IS_OP_REL:
                opRelacional++;
                System.out.println("Op relacional: " + token);
                break;
            case IS_ASSIGN:
                asignacion++;
                System.out.println("Asignacion: " + token);
                break;
            case IS_DECIMAL:
                numDecimal++;
                System.out.println("Decimal: " + token);
                break;
            case IS_DECIMAL_NEGATIVE:
                numDecimal++;
                System.out.println("Decimal negativo: " + token);
                break;
            case MINOR_MINOREQ:
                opRelacional++;
                System.out.println("Op relacional: " + token);
                break;
            case MAJOR_MAJOREQ:
                opRelacional++;
                System.out.println("Op relacional: " + token);
                break;
            case DIFERRENT:
                opLogico++;
                System.out.println("Op logico: " + token);
                break;
            case EQUAL_OR_ASSIGN:
                asignacion++;
                System.out.println("Asignacion: " + token);
                break;
            case INTEGER_OR_DECIMAL:
                numEntero++;
                System.out.println("Numero entero: " + token);
                break;
            case IS_OP_LOG:
                opLogico++;
                System.out.println("Op logico: " + token);
                break;
            case IS_INCREMENT:
                incremento++;
                System.out.println("Incremento: " + token);
                break;
            case IS_STRING:
                cadena++;
                System.out.println("Cadena: " + token);
                break;
            case COMMENT_OR_DIVISION:
                opAritmetico++;
                System.out.println("Operador aritmetico: " + token);
                break;
        }
    }

    private State transition(State state, State previousState, char currentChar) {
        switch (state) {
            case START:
                if (currentChar == '/') {
                    return State.COMMENT_OR_DIVISION;
                } else if (Character.isDigit(currentChar)) {
                    return State.INTEGER_OR_DECIMAL;
                } else if (currentChar == '-') {
                    return State.ARITHMETIC_INT_DECR;
                } else if (currentChar == '{' || currentChar == '}') {
                    return State.IS_BRACE;
                } else if (currentChar == '(' || currentChar == ')') {
                    return State.IS_PARENTHESIS;
                } else if (currentChar == '=') {
                    return State.EQUAL_OR_ASSIGN;
                } else if (Character.isLetter(currentChar) || currentChar == '_') {
                    return State.IDENTIFIER;
                } else if (currentChar == '<') {
                    return State.MINOR_MINOREQ;
                } else if (currentChar == '>') {
                    return State.MAJOR_MAJOREQ;
                } else if (currentChar == '!') {
                    return State.DIFERRENT;
                } else if (currentChar == '&') {
                    return State.LOGIC_AND;
                } else if (currentChar == '|') {
                    return State.LOGIC_OR;
                } else if (currentChar == '+') {
                    return State.INCR_OR_ERROR;
                } else if (currentChar == '"') {
                    return State.P_STRING;
                } else {
                    return State.ERROR; //char not valid on initial state
                }
            case P_STRING:
                if (currentChar == '"') {
                    return State.IS_STRING;
                } else {
                    return State.P_STRING;
                }
            case INCR_OR_ERROR:
                if (currentChar == '+') {
                    return State.IS_INCREMENT;
                } else {
                    return State.ERROR;
                }
            case LOGIC_OR:
                if (currentChar == '|') {
                    return State.IS_OP_LOG;
                } else {
                    return State.ERROR;
                }
            case LOGIC_AND:
                if (currentChar == '&') {
                    return State.IS_OP_LOG;
                } else {
                    return State.ERROR;
                }
            case DIFERRENT:
                if (currentChar == '=') {
                    return State.IS_OP_REL;
                } else {
                    return State.ERROR;
                }
            case MAJOR_MAJOREQ:
                if (currentChar == '=') {
                    return State.IS_OP_REL;
                } else {
                    return State.ERROR;
                }
            case MINOR_MINOREQ:
                if (currentChar == '=') {
                    return State.IS_OP_REL;
                } else {
                    return State.ERROR;
                }
            case IDENTIFIER:
                if (Character.isLetterOrDigit(currentChar) || currentChar == '_') {
                    return State.IDENTIFIER;
                } else {
                    return State.ERROR; //invalid char at identifier
                }
            case INTEGER_OR_DECIMAL:
                if (Character.isDigit(currentChar)) {
                    return State.INTEGER_OR_DECIMAL;
                } else if (currentChar == '.') {
                    return State.DECIMAL_OR_ERROR;
                } else {
                    return State.IS_INTEGER;
                }
            case DECIMAL_OR_ERROR:
                if (currentChar == '.') {
                    return State.ERROR;
                } else if (Character.isDigit(currentChar)) {
                    return State.IS_DECIMAL;
                }
            case EQUAL_OR_ASSIGN:
                if (currentChar == '=') {
                    return State.IS_OP_REL;
                } else {
                    return State.IS_ASSIGN;
                }
            case ARITHMETIC_INT_DECR:
                if (Character.isDigit(currentChar)) {
                    return State.INTEGER_OR_DECIMAL;
                } else if (currentChar == '-') {
                    return State.IS_DECREMENT;
                } else {
                    return State.IS_ARITMETHIC;
                }
            case INT_DEC_NEGATIVE:
                if (currentChar == '.') {
                    return State.DECIMAL_OR_ERROR;
                } else if (Character.isDigit(currentChar)) {
                    return State.INTEGER_OR_DECIMAL;
                }
            case COMMENT_OR_DIVISION:
                if (currentChar == '/') {
                    return State.LINE_COMMENT;
                } else if (currentChar == '*') {
                    return State.COMMENT_OR_ERROR;
                } else {
                    return State.ERROR;
                }
            case COMMENT_OR_ERROR:
                if (currentChar == '*') {
                    return State.COMMENT_END;
                } else {
                    return State.COMMENT_OR_ERROR;
                }
            case COMMENT_END:
                if (currentChar == '/') {
                    return State.IS_COMMENT;
                } else {
                    return State.ERROR;
                }
            case LINE_COMMENT:
                return State.IS_LINE_COMMENT;
            case IS_COMMENT:
                return State.IS_COMMENT;
            case IS_LINE_COMMENT:
                return State.IS_LINE_COMMENT;
            case IS_INTEGER:
                return State.IS_INTEGER;
            case IS_INTEGER_NEGATIVE:
                return State.IS_INTEGER_NEGATIVE;
            case IS_DECREMENT:
                return State.IS_DECREMENT;
            case IS_ARITMETHIC:
                return State.IS_ARITMETHIC;
            case IS_BRACE:
                return State.IS_BRACE;
            case IS_PARENTHESIS:
                return State.IS_PARENTHESIS;
            case IS_OP_REL:
                return State.IS_OP_REL;
            case IS_ASSIGN:
                return State.IS_ASSIGN;
            case IS_DECIMAL:
                return State.IS_DECIMAL;
            case IS_DECIMAL_NEGATIVE:
                return State.IS_DECIMAL_NEGATIVE;
            case IS_OP_LOG:
                return State.IS_OP_LOG;
            case ERROR:
                return State.ERROR;
            default:
                return State.ERROR;
        }
    }

    private enum State {
        START,
        COMMENT_OR_DIVISION,
        LINE_COMMENT,
        COMMENT_OR_ERROR,
        COMMENT_END,
        DIFERRENT,
        ARITHMETIC_INT_DECR,
        EQUAL_OR_ASSIGN,
        INTEGER_OR_DECIMAL,
        INT_DEC_NEGATIVE,
        DECIMAL_OR_ERROR,
        IDENTIFIER,
        MINOR_MINOREQ,
        MAJOR_MAJOREQ,
        KEYWORD,
        INCR_OR_ERROR,
        LOGIC_AND,
        LOGIC_OR,
        P_STRING,
        IS_COMMENT,
        IS_LINE_COMMENT,
        IS_INTEGER,
        IS_INTEGER_NEGATIVE,
        IS_DECREMENT,
        IS_ARITMETHIC,
        IS_BRACE,
        IS_PARENTHESIS,
        IS_OP_REL,
        IS_ASSIGN,
        IS_DECIMAL,
        IS_DECIMAL_NEGATIVE,
        IS_OP_LOG,
        IS_INCREMENT,
        IS_STRING,
        ERROR,
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
