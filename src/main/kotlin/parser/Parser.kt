package parser

import lexer.EToken
import lexer.Lexer
import parser.AST.*

class Parser {
    private val lexer: Lexer = Lexer()
    private var currentToken: EToken = EToken.START
    private var terms: MutableList<Expression> = mutableListOf()
    private var operators: MutableList<Expression> = mutableListOf()
    private var ast: MutableList<Expression> = mutableListOf()


    fun parse(query: String) {
        lexer.initQuery(query)
        currentToken = lexer.getToken()

        if (currentToken != EToken.START)
            throw Exception("Bad query!")

        /*
        START        : TERM | NOT | (
        AFTER TERM   : OR   | AND | )   |
        AFTER OR/AND : TERM | (   | NOT |
        AFTER (      : TERM | NOT | (   |
        AFTER )      : )    | AND | OR  |
        AFTER NOT    : TERM | (   |
         */

    }

    private fun exp() : Expression {
        currentToken = lexer.getToken()

        if(currentToken != EToken.TERM_NODE || currentToken != EToken.LEFT_BRACKET || currentToken != EToken.NOT_OPERATOR)
            throw Exception("Bad query!")

        val exp = Term()

        return Exp1(exp)
    }

    /*

 from https://cs.stackexchange.com/questions/10558/grammar-for-describing-boolean-expressions-with-and-or-and-not

 exp→term {OR term};
 term→factor {AND factor};
 factor→NOT factor;
 factor→LPAREN exp RPAREN;


 exp→term | exp1
 exp1->{OR term} | e
 term→factor | term1
 term1->{AND factor} | e
 factor→NOT factor | factor1
 factor1→ exp | e

 exp  -> term | OR term (exp1)
 exp1 -> e | term | exp1
 term -> factor | AND factor (term1)
 term1 -> e | factor | And factor (term1)
 factor -> Not term(factor1) | factor1
 factor1 -> e | exp


*/


}