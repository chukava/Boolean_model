package parser

import lexer.EToken
import lexer.Lexer
import parser.AST.*
import preprocessor.Preprocessor

class Parser(_preprocessor: Preprocessor) {
    private val lexer: Lexer = Lexer()
    private var currentToken: EToken = EToken.START
    private val preprocessor: Preprocessor

    init {
        this.preprocessor = _preprocessor
    }


    fun parse(query: String): Expression {
        lexer.initQuery(query)
        currentToken = lexer.getToken()

        return exp()
    }


    private fun exp(): Expression { // term , -> exp1

        if (currentToken == EToken.TERM_NODE || currentToken == EToken.LEFT_BRACKET || currentToken == EToken.NOT_OPERATOR) {
            val expression = term()
            return exp1(expression)
        }

        throw Exception("Bad query!")
    }

    private fun exp1(leftOperand: Expression): Expression { // OR term | e
        if (currentToken == EToken.END_OF_QUERY || currentToken == EToken.RIGHT_BRACKET)
            return leftOperand

        if (currentToken == EToken.OR_OPERATOR) {
            currentToken = lexer.getToken()
            return OrOperator(leftOperand, exp1(term()))
        }
        throw Exception("Bad query!")
    }


    private fun term(): Expression { // factor , term1
        if (currentToken == EToken.TERM_NODE || currentToken == EToken.LEFT_BRACKET || currentToken == EToken.NOT_OPERATOR) {
            val expression = factor()
            return term1(expression)
        }

        throw Exception("Bad query!")
    }

    private fun term1(leftOperand: Expression): Expression { // and | e

        if (currentToken == EToken.END_OF_QUERY || currentToken == EToken.OR_OPERATOR || currentToken == EToken.RIGHT_BRACKET) // e
            return leftOperand
        else if (currentToken == EToken.AND_OPERATOR) { // right operand for and expected -> term / left bracket or not expected
            currentToken = lexer.getToken()
            return AndOperator(leftOperand, term1(factor()))
        }

        throw Exception("Bad query!")
    }


    private fun factor(): Expression { // not & factor1 | factor1

        if (currentToken == EToken.NOT_OPERATOR) { // not -> left bracket | term node
            currentToken = lexer.getToken()
            return NotOperator(factor1())
        } else if (currentToken == EToken.TERM_NODE || currentToken == EToken.LEFT_BRACKET) //  left bracket | term node expected
            return factor1()

        throw Exception("Bad query!")
    }


    private fun factor1(): Expression { // term | exp

        if (currentToken == EToken.TERM_NODE) {    // term
            val nodeTerm = lexer.getCurrentTerm()
            currentToken = lexer.getToken()
            return TermNode(nodeTerm, preprocessor.termTable.getFilesByTerm(nodeTerm))
        } else if (currentToken == EToken.LEFT_BRACKET) { // left bracket -> expression expected
            currentToken = lexer.getToken()
            val exp = exp()
            currentToken = lexer.getToken()
            return exp
        }

        throw Exception("Bad query!")
    }

}


/*

    START        : TERM | NOT | (
    AFTER TERM   : OR   | AND | )   |
    AFTER OR/AND : TERM | (   | NOT |
    AFTER (      : TERM | NOT | (   |
    AFTER )      : )    | AND | OR  |
    AFTER NOT    : TERM | (   |

from https://cs.stackexchange.com/questions/10558/grammar-for-describing-boolean-expressions-with-and-or-and-not

exp→term {OR term};
term→factor {AND factor};
factor→NOT factor;
factor→LPAREN exp RPAREN;


exp→ term & exp1
exp1-> {OR term} | e
term→ factor & term1
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