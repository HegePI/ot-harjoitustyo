# Vaatimusmäärittely

## **Sudoku -sovellus**

### Sovelluksen tarkoitus

Sovelluksen tarkoituksena on tarjota käyttäjälle mahdollisuus pelata Sudokua vaihdettavalla vaikeusasteella. Käyttäjä voi itse valita vaikeusasteen ennen pelin aloittamista. Pelaajalla on myös mahdollisuus tallentaa oma sudokupelinsä tietokantaan.

### Käyttäjät 

Pelissä on vain normaaleja käyttäjiä. Käyttäjien tiedot talletetaan järjestelmään. Talletettuja tietoja ovat aluksi vain nimi ja salasana ja pelaajan sudokut.

### Sudokut

Pelissä on erilaisia sudokuja vaihtelevilla vaikeusasteilla. Kun pelaaja aloittaa uuden sudokun, niin luodaan UserSudoku -olio, joka pitää sisällään tiedon siitä, kuka pelaa sudokua, onko se suoritettu, sen vaikeusaste, keskeneräinen peli ja pelin alkuperäinen versio. Tämä olio sitten tallennetaan tietokantaan ja pelaajalla on mahdollisuus kesken pelin tallentaa edistyminen.

### Käyttöliittymäluonnos

![alt text](https://github.com/HegePI/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sovellusnakyma.png)

### Perusversion tarjoama toiminnallisuus

#### Ennen kirjautumista

* Mahdollisuus kirjautua sisään tai luoda uusi käyttäjä peliin.

#### Kirjautumisen jälkeen

* Mahdollisuus valita peli eri vaikeusasteella.

* Pelin valinnan jälkeen aukeaa näkymä, jossa peliä pelataan.

   - Pelaaja voi tallenna napilla tallentaa sudokun tilanteen.
   - Pelaaja voi tarkistaa sudokunsa
   - Pelaaja voi automaattisesti ratkaista sudokunsa
   - Käyttäjä voi palata takaisin pelin valinta näkymään pelinäkymästä.

* Mahdollisuus tarkastella aloittamiaan sudokuja käyttäjäinfo -näkymästä

   - käyttäjäinfo -näkymästä pelaaja voi jatkaa tallentamaansa peliä

* Mahdollisuus kirjautua ulos sovelluksesta.

### Jatkokehitys ideoita

Ajanpuitteissa ohjelmaa voidaan täydentää seuraavilla toiminnalisuuksilla:

* Pisteytys järjestelmä, joka talletetaan tietokantaan tai tiedostoon.

   - Käyttäjien listaaminen pisteiden perusteella.

* Mahdollisuus generoida lisää sudokuja, jotka tallennetaan tietokantaan.

* Pelaajalla mahdollisuus poistaa omat tietonsa sovelluksesta.

* Sovelluksen kaunistaminen