# Testausdokumentti

Ohjelmaa on testattu automatisoiduin yksikkö -ja integraatiotestien avulla JUnitilla ja manuaalisesti hoidetuin järjestelmätestien avulla. 

## Yksikkö/Integraatiotestit

### logic

Luokille sudokuChecker.java, SudokuSolver.java ja osalle Sudoku.java luokan metodeille on toteutettu yksikkötestejä, koska ne käyttävät vain oman luokkansa toiminnallisuuksia/metodeja hyväkseen. Testien ulkopuolelle on jätetty setter ja getter metodit ja User.java luokka, koska näissä luokissa ei ole tärkeää yksikkötestattavaa. User.java muodostuu lähes kokonaan settereistä ja gettereistä.

Tärkeimmät logic -pakkauksen luokkien testit muodostuvat SudokuserviceTest.java, UserSudokuTest.java ja SudokuTest.java integraatiotesteistä, joista tärkeimpänä on SudokuServiceTest.java. Tämä testaa, että käyttöliittymältä tulevat käskyt suoritetaan oikein. SudokuServiceTest.kava käyttää myös testauksessaan hyödyksi Sudoku.java ja UserSudoku.java luokkia, koska ne käyttävät hyödykseen SudokuChecker, SudokuSolver ja User luokkia.

Integraatiotestit käyttävät pysyväistallenuksessa hyväkseen DAO -pakkauksen luokista Database, UserDao, SudokuDao ja UserSudokuDao luotuja olioita. Jokaisen integraatiotestin alussa tietokannassa olevat tiedot poistetaan.

### DAO

Kaikissa DAO -pakkauksen luokkien testauksissa luodaan Database -olio, jonka avulla saadaan tallennettua dataa tietokantaan testeissä. Ennen jokaista testitapausta tietokannasta poistetaan kaikki jo tallennettu tieto.

Database -luokka testataan siten, että ennen testiä poistetaan kansiosta sudoku.db tiedosto, jos siellä sellainen on. Tässä testissä testataan, että saako luokka luotua sudoku.db tiedoston.

### Testauskattavuus

Käyttöliittymää poisjättäen testauskattavuus on 91% ja haarautumiskattavuus on 87%.

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testaus_kattavuus.png)

Testaamatta jäivät try - catch tilanteiden haarat, joissa sql -kyselyissä on tapahtunut virhe/yhteyttä tietokantaan ei saada. Osa haaroista on myös tilanteita, joissa equals -metodilla verrattaessa kaksi oliota ovat eri.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on tehty manuaalisesti.

## Asennus ja konfiguraatio

Sovellusta on testattu käyttöohjeen kuvaamalla tavalla linux ja windows ympäristössä siten, että käynnistyshakemistossa on ollut sudoku.db tiedosto. Lisäksi on testattu myös tilanteessa, jossa sudoku.db tiedostoa ei ole ollut olemassa, jolloin sovellus on luonut sen itse.

## Toiminnallisuudet

Sovellus toteuttaa kaikki vaatimusmäärittelyssä listattuja tomintoja ja ne on käyty testeissä läpi. Lisäksi järjestelmätestauksen aikana on yritetty syöttää vääränlaista dataa, esim. kirjautuessa tai käyttäjää luodessa käyttäjänimi ja salasana ovat olleet tyhjinä.

## Sovlluksen laatuongelmat

Tuntemattomasta syystä joskus uloskirjautuessa login näkymä näkyy mustana ja vähän ajan kuluttua se näkyy taas selvänä.