package parser

import lexer.EToken
import lexer.Lexer

class Parser {
    private val lexer : Lexer = Lexer()
    private var currentToken : EToken = EToken.START

    fun parse (query: String) {
        lexer.initQuery(query)
        currentToken = lexer.getToken()

        if(currentToken == EToken.END_OF_QUERY || currentToken == EToken.OR_OPERATOR || currentToken == EToken.AND_OPERATOR || currentToken == EToken.RIGHT_BRACKET)
            throw Exception("Bad query!")


    }


}