# Käyttöohje

Lataa ensin sudokuApp.jar

## konfigurointi

Tietokantojen konfigurointi tapahtuu soveeluksen sisällä, jolloin käyttäjän ei niistä tarvitse huolehtia.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

`java -jar sudokuApp.jar`

## Kirjautuminen

Ensimmäiseksi käyttäjälle tulee näkyviin kirjautumisnäkymä. Käyttäjä voi antaa siinä käyttäjänimensä ja salasanansa, jos ne ovat entuudestaan luotu. Muuten käyttäjä voi siirtyä näkymään, jossa hän voi luoda itselleen uuden käyttäjänimen ja tälle salasanan.

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/login_view.png)

## Uuden käyttäjän luominen


Näkymässä käyttäjä antaa uuden käyttäjänimen ja salasanan. Jos käyttäjän luominen onnistui, niin tästä ilmoitetaan näkymään tulevalla viestillä. Tämän jälkeen käyttäjä voi siiryä takaisin sisäänkirjautumisnäkymään, jossa hän voi kirjautua sisään.

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/newUser_view.png)

## Pelivalikko

Kun käyttäjä on kirjautunut sisään, niin aukeaa pelivalikko, jossa näkyy, että käyttäjä on kirjautunut sisään. Käyttäjä voi kirjautua ulos painamalla logout -nappia. Käyttäjä voi valita pelin painamalla jotain listassa olevaa peliä. Peli aukeaa automaattisesti pelinäkymään. Pelin käynnistämisen yhteydessä luodaan uusi UserSudoku -olio, joka liitetään pelin aloittaneeseen käyttäjään.

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/gameMenu.png)

## Pelinäkymä

Kun peli käynnistyy, niin näkyy pelaajalle pelin sudokuruudukko. Kaikki alkuperäiset sudokun luvut näkyvät punaisina. Valittu ruutu on vahvistettu sinisillä reunoilla. Pelaaja voi vaihtaa ruutua hiirennapilla painamalla haluaamansa ruutua, jonka jälkeen pelaaja voi antaa haluamansa luvun. (Huom! pelaaja saattaa joutua painamaan haluaamansa ruutua useamman kerran, jotta näkymän focus menee oikein. Pelaaja voi joutua myös samasta syystä painamaan haluamaansa lukua useamman kerran, jotta se ilmestyy ruutuun.) Pelaajan antamat luvut näkyvät mustina. Pelaaja voi tallentaa pelinsä tilanteen tallenna -napilla, jolloin pelintilanne tallennetaan UserSudoku -olioon, jonka tiedot tallennetaan tietokantaan. Pelaaja pääse takaisin pelivalikkoon takasin -napilla. 

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/gameView.png)