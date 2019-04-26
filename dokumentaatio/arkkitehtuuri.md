# Kuvaus sovelluksen arkkitehtuurista

## Rakenne

Sovelluksen rakenne on toteutettu kolmitasoarkkitehtuurina, jossa päällimmäisenä on Ui, keskimmäisenä Logic ja alimpaisena DAO. Ui luo käyttöliittymät ikkunat ja välittää käyttäjän viestit logic tason SudokuService -luokalle, joka hoitaa annetun tehtävän antaa palautteen, jonka Ui näyttää käyttäjälle. SudokuService on injektoitu jokaiselle Ui paketin luokalle ja sudokuservicen käytössä on kaikki muut logic paketin luokat ja DAO -paketin luokat.

## Käyttöliittymä

Ui tarjoaa käyttäjälle neljä näkymää, jotka ovat
* sisäänkirjautumisnäkymä "login"
* uuden käyttäjän luomisnäkymä "luo uusi käyttäjä"
* Pelivalikko
* Pelinäkymä

 Näkymät ovat FXML:llä määritettyjä, jotka aina ladataan resursseista sitä tarvittaessa ja jonka pohjalta luodaan uusi Scene -olio, joka asetetaan Stageen. Jokaisella FXML näkymällä on omat Controller -olionsa, jotka kuuntelevat näkymissä tapahtuvia muutoksia ja reagoivat niihin sovitulla tavalla. Jokaisella Ui luokalla on käytössään SUdokuService -olio server, jonka avulla nämä laittavat käyttäjän pyynnöt eteenpäin.

## Sovelluslogiikka

Sovelluksen loogisen kokonaisuuden muodostavat luokat User ja Sudoku. User kuvaa käyttäjiä ja Sudoku kuvaa peliin talletettavia ja pelattavia sudokuja. Käyttäjä voi pelata kerrallaan yhtä sudoku peliä ja tallentaa edistymisensä muistiin. 

Sovelluksen toiminnallisista kokonaisuuksista vastaa SudokuService luokka. Tämä luokka tarjoaa käyttöliittymän toiminnoille omat metodit, esim.
* login()
* logout()
* play()
* save()

SudokuService pääsee sen tarvitsemiin tietoihin käsiksi Dao olioiden avulla, joita ovat UserDao, SudokuDao ja UserSudokuDao. Ohjelman alussa näiden luokkien oliot injektoidaan SudokuServicen käyttöön konstruktorikutsulla. 

Luokkakaavio kuvana:

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokkakaavio.png)

## Tietojen pysyväistallennus

Tietojen tallentamisesta vastaa dao -paketin sisältämät luokat Database, SudokuDao, UserSudokuDao ja UserDao. Databse vastaa yhteyden luomisesta tietokantaan ja tarvittaessa luo uuden tietokannan, jos sovellusta ajettavassa kansiossa sitä ei löydy. UserDao vastaa henkilöiden tietojen tallenuksesta, SudokuDao yksittäisten sudokujen ja UserSudokuDao vastaa käyttäjän keskeneräisen sudokuntallenuksesta.


## Päätoiminnallisuuksien kuvaukset

Alla on auki esiteltynä osa ohjelman päätoiminnallisuuksita

### Sisäänkirjautuminen

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/login.png?raw=true)

### Uuden käyttäjän luominen

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/createUser.png?raw=true)

### Pelin tallentaminen

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/save.png?raw=true)

GameControllerilla oleva UserSudoku -olio on asetettu sille siinä vaiheessa, kun pelaaja aloittaa pelin. Kun pelaaja muuttaa jonkun sudokun ruudun arvoa, niin se päivitetään samantien tähän olioon. UserSudokussa on tiedossa kaksi kaksiulotteista Integer taulua: toinen kertoo pelin nykyisen tilanteen ja toinen alkuetilanteen. Kun pelaaja tallentaa pelin, niin nykyinen tilanne tallentuu ja kaikki loput UserSudoku -olion tiedot pysyvät muuttumattomina