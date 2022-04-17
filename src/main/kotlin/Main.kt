import Model.File
import preprocessor.Preprocessor

fun main(args: Array<String>) {
    println("Hello World!")

    val preprocessor: Preprocessor = Preprocessor()
    val file: File = File("data1.txt")

    preprocessor.preprocess(file)
}

//1. Extrakce a preprocesing termů z dokumentů.


//2. Efektivní uložení dokumentů v datové struktuře (invertovaný seznam).
//3. Vyhodnocovací/dotazovací modul využívající strukturu z předchozího kroku.