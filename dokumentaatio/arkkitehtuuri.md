# Kuvaus sovelluksen arkkitehtuurista

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

