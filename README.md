# SudokuApp, Ohjelmistotekniikan harjoitustyö

Sovellus mahdollistaa käyttäjien pelata sudoku pelejä.

[sudokuApp](https://github.com/HegePI/ot-harjoitustyo/tree/master/sudokuapp)

## Dokumentaatio

[Käyttöohje](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kaytto_ohje.md)

[Vaatimusmäärittely](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakalenteri](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakalenteri.md)

[Arkkitehtuuri suunnitelma](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset

[viikko 6](https://github.com/HegePI/ot-harjoitustyo/releases)

## Komentorivi käskyt

### Testaus
`mvn test`

### Testikattavuusraportti
`mvn test jacoco:report`
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




