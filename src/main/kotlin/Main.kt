import preprocessor.Preprocessor
import parser.Parser

fun main(args: Array<String>) {

    val preprocessor: Preprocessor = Preprocessor()
    val fileName: String = "some fileName"
    preprocessor.preprocess(fileName)


    val parser: Parser = Parser()
    val query: String = "some query"
    parser.parse(query)

}

//1. Extrakce a preprocesing termů z dokumentů.


//2. Efektivní uložení dokumentů v datové struktuře (invertovaný seznam).
//3. Vyhodnocovací/dotazovací modul využívající strukturu z předchozího kroku.