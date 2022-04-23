package lexer

import java.util.*


class Lexer {

    private var tokens : Queue<String> = ArrayDeque()
    private var currToken : String = String()

    fun initQuery(query: String) {
        val tokens = StringTokenizer(query)
        while (tokens.hasMoreTokens()) {
            this.tokens.add(tokens.nextToken())
        }
    }

    fun getToken() : EToken {
        currToken = tokens.poll() ?: return EToken.END_OF_QUERY
        return when (currToken) {
            "("  -> EToken.LEFT_BRACKET
            ")"  -> EToken.RIGHT_BRACKET
            else -> when (currToken.lowercase()) {
                "or"  -> EToken.OR_OPERATOR
                "and" -> EToken.AND_OPERATOR
                "not" -> EToken.NOT_OPERATOR
                else  -> EToken.TERM_NODE
            }
        }
    }

    fun getCurrentTerm() : String = currToken

}