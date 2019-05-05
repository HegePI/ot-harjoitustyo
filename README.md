# SudokuApp, Ohjelmistotekniikan harjoitustyö

Sovellus mahdollistaa käyttäjien pelata sudoku pelejä ja tallentaa sudoku pelin tilanne.

[sudokuApp](https://github.com/HegePI/ot-harjoitustyo/tree/master/sudokuapp)

## Dokumentaatio

[Käyttöohje](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kaytto_ohje.md)

[Vaatimusmäärittely](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri suunnitelma](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Työaikakalenteri](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakalenteri.md)

## Releaset

[viikko 6](https://github.com/HegePI/ot-harjoitustyo/releases/tag/1.0)
[viikko 7](https://github.com/HegePI/ot-harjoitustyo/releases/tag/2.0)

## Komentorivi käskyt

### Testaus
`mvn test`

### Testikattavuusraportti
`mvn jacoco:report`

testikattavuusraportti löytyy **target/site/jacoco/index.html**

### Jarrin generointi
`mvn package`

Jarri löytyy **target/sudokuApp-1.0-SNAPSHOT.jar**

### Javadoc generointi
`mvn javadoc:javadoc`

Javadocit löytyvät **target/site/apidocs/index.html**

### Checkstylen suorittaminen
`mvn jxr:jxr checkstyle:checkstyle`

Checkstyle raportti löytyy **target/site/checkstyle.html**




