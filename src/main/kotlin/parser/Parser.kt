package parser

import lexer.EToken
import lexer.Lexer
import parser.AST.BinaryOperator
import parser.AST.Expression
import parser.AST.TermNode

class Parser {
    private val lexer : Lexer = Lexer()
    private var currentToken : EToken = EToken.START
    private var terms : MutableList<Expression> = mutableListOf()
    private var operators : MutableList<Expression> = mutableListOf()
    private var ast : MutableList<Expression> = mutableListOf()


    fun parse (query: String) {
        lexer.initQuery(query)
        currentToken = lexer.getToken()

        if(currentToken == EToken.END_OF_QUERY || currentToken == EToken.OR_OPERATOR || currentToken == EToken.AND_OPERATOR || currentToken == EToken.RIGHT_BRACKET)
            throw Exception("Bad query!")

        while (currentToken != EToken.END_OF_QUERY)

        if(currentToken == EToken.TERM_NODE){
            ast.add(TermNode(lexer.getCurrentTerm()))
        } else if (currentToken == EToken.OR_OPERATOR || )

//        while (currentToken != EToken.END_OF_QUERY)

        /*
        START        : TERM | NOT | (

        AFTER TERM   : OR   | AND | )   |
        AFTER OR/AND : TERM | (   | NOT |
        AFTER (      : TERM | NOT | (   |
        AFTER )      : )    | AND | OR  |
        AFTER NOT    : TERM | (   |
         */

    }

    private fun start() {
        currentToken = lexer.getToken()
        when(currentToken) {
            EToken.TERM_NODE     -> term()
            EToken.NOT_OPERATOR  -> notOperator()
            else -> throw Exception("Bad query!")
        }
    }

    private fun leftBracket() {
        currentToken = lexer.getToken()
        when(currentToken){
            EToken.LEFT_BRACKET  -> leftBracket()
            EToken.NOT_OPERATOR  -> notOperator()
            EToken.TERM_NODE     -> term()
            EToken.RIGHT_BRACKET -> rightBracket()
            else -> throw Exception("Bad query!")
        }
    }

    private fun rightBracket() {
        currentToken = lexer.getToken()
        when(currentToken){
            EToken.RIGHT_BRACKET -> rightBracket()
            EToken.AND_OPERATOR  -> andOperator()
            EToken.OR_OPERATOR   -> orOperator()
            else -> throw Exception("Bad query!")
        }
    }

    private fun term() {
        // get right operand
        currentToken = lexer.getToken()
        when(currentToken){
            EToken.RIGHT_BRACKET -> rightBracket()
            EToken.AND_OPERATOR  -> andOperator()
            EToken.OR_OPERATOR   -> orOperator()
            else -> throw Exception("Bad query!")
        }
    }

    private fun orOperator() {
        // get right operand
        currentToken = lexer.getToken()
        when(currentToken) {
            EToken.TERM_NODE     -> term()
            EToken.RIGHT_BRACKET -> rightBracket()
            EToken.NOT_OPERATOR  -> notOperator()
            else -> throw Exception("Bad query!")
        }
    }

    private fun andOperator() {
        // get right operand
        currentToken = lexer.getToken()
        when(currentToken) {
            EToken.TERM_NODE     -> term()
            EToken.RIGHT_BRACKET -> rightBracket()
            EToken.NOT_OPERATOR  -> notOperator()
            else -> throw Exception("Bad query!")
        }
    }

    private fun notOperator() {
        currentToken = lexer.getToken()

        // get right operand
        when(currentToken) {
            EToken.TERM_NODE    -> term()
            EToken.LEFT_BRACKET -> leftBracket()
            else -> throw Exception("Bad query!")
        }
    }

    private fun addBinaryNodeToAST() {
        ast.add(BinaryOperator(ast.add(ast.last())))
    }

    private fun addUnaryNodeToAST() {


    }