# Boolean model.  Kotlin + React
___


### Použitý Software: BE
`Kotlin - 1.5.10`

`Maven - build tools`

`Ktor.io - microservices`


____

### Použitý Software: FE
`React - 18.0.0` 

`Bootstrap - 5.1.3`

`Axios - 0.27.1`
____

### Popis microservis
```sh
POST: /saveQuery  
___
Accepts query and validates it. OK - responds with "Querry accepted." ERROR - "Bad query syntax."
```
$db = 
* when:  **s** - small data set of 200 diles
* when:  **l** - larhe data set of 2000 files

```sh
GET: /boolean-model/getResult-$db  
___
Returns a set of file Ids{0..(200|2000) as a result of query search using inverted indexes in db
```
```sh
GET: /sequence-search/getResult-$db
---
Returns a set of file Ids{0..(200|2000)} as a result of query search using sequantial search in db

```
```sh
GET: /getTimeDifference-$db  
---
Returns the duration of search using boolean method and sequantial and their difference in ns.
```
____
## Zdroje

[Zadání](https://moodle-vyuka.cvut.cz/pluginfile.php/493984/mod_page/content/24/i-1.pdf)

###SW:
* [Ktor server creation](https://ktor.io/docs/intellij-idea.html)
* [Kotlin serialization](https://kotlinlang.org/docs/serialization.html)
* [React & Axios api calling](https://www.digitalocean.com/community/tutorials/react-axios-react)
* [Bootstrap](https://getbootstrap.com/docs/5.1/getting-started/download/)

###Implementace a testování:

1. Files preprocessing:
* [Files preprocessing - stopWords](https://countwordsfree.com/stopwords)
* [Files preprocessing - terms simplifying](https://stanfordnlp.github.io/CoreNLP/pipeline.html)
2. Queries parsing:
* [Parsing a query gramamar](https://cs.stackexchange.com/questions/10558/grammar-for-describing-boolean-expressions-with-and-or-and-not
  )
* [Own semestral work BI-PA2 - expression calculator](in my git projects)
* in src/main/kotlin/parser/Parser.kt you can find full grammar
3. Evaluating:
* [AST](https://en.wikipedia.org/wiki/Abstract_syntax_tree)
* [Own semestral work BI-PA2 - expression calculator](in my git projects)
4. Datasets for testing:
* [BBC](http://mlg.ucd.ie/datasets/bbc.html)
* [Wikipedia](https://en.wikipedia.org/wiki/Main_Page)
* [CNN](https://lite.cnn.com/en)