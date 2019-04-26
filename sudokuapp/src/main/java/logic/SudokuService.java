package logic;

import dao.Database;
import dao.Sudokudao;
import dao.UserSudokuDao;
import dao.Userdao;
import java.sql.*;
import java.util.*;

public class SudokuService {

    static Database db;
    static Userdao users;
    static Sudokudao sudokus;
    static UserSudokuDao UserSudokuDao;
    static Scanner lukija;
    static User loggedIn;

    public SudokuService() throws ClassNotFoundException, SQLException {
        db = new Database();
        users = new Userdao(db);
        sudokus = new Sudokudao(db);
        UserSudokuDao = new UserSudokuDao(db);
    }

    /**
     * Metodi, joka luo uuden User -olion ja antaa sen UserDao -oliolle users,
     * joka tallentaa sen tietokantaan. Palauttaa true, jos lisääminen onnistui
     * ja muuten false.
     *
     *
     * @param name - käyttäjän nimi
     * @param pswd - käyttäjän salasana
     * @return -tieto siitä, onnistuiko lisäys. True, jos onnistui, muuten
     * false.
     * @throws SQLException - saattaa palauttaa SQLExceptionin, jos ei saa
     * yhteyttä tietokantaan tai tietokantaa ei ole olemassa
     */
    public boolean CreateNewUser(String name, String pswd) throws SQLException {
        boolean succes = false;

        User newUser = new User(name, pswd);
        succes = users.addUser(newUser);

        return succes;
    }

    /**
     * Palutaa tietokannasta parametrien mukaisesti halutun käyttäjän tiedot.
     * Metodi kutsuu UserDao -olion users metodia getByNameAndPswd, joka
     * suorittaa kyselyn ja palauttaa käyttäjän tiedot User -oliossa.
     *
     * @param name -halutun käyttäjän käyttäjänimi
     * @param pswd -halutun käyttäjän salasana
     * @return - halutun käyttäjän tiedot User -oliossa
     * @throws SQLException saattaa palauttaa SQLExceptionin, jos ei saa
     * yhteyttä tietokantaan tai tietokantaa ei ole olemassa
     */
    public User Login(String name, String pswd) throws SQLException {
        User user = users.getByNameAndPswd(name, pswd);
        return user;
    }

    /**
     * Palauttaa olion, jolla on sama id kuin parametrissa. Metodi kutsuu
     * SudokuDao -olion sudokus metodia getById, joka hakee ja palauttaa halutun
     * Sudoku -olion.
     *
     * @param id - halutun sudokun yksilöivä id
     * @return haluttu sudoku olio
     */
    public Sudoku getSudoku(int id) {
        Sudoku sudoku = sudokus.getById(id);
        return sudoku;
    }

    /**
     * Palauttaa kaikki tietokannassa olevat sudoku oliot. Kutsuu SudokuDao
     * -olion sudokus metodia getAll, joka palauttaa kaikki tietokannasta
     * löytyvät Sudoku -oliot ArrayListassa.
     *
     * @return - Sudoku -oliot sisältävän Arraylista
     * @throws SQLException saattaa palauttaa SQLExceptionin, jos ei saa
     * yhteyttä tietokantaan tai tietokantaa ei ole olemassa
     */
    public ArrayList<Sudoku> getAllSudokus() throws SQLException {
        ArrayList<Sudoku> allSudokus = this.sudokus.getAll();
        return allSudokus;
    }

    /**
     * Luo uuden UserSudoku -olion, joka tallennetaan tietokantaan. Metodi
     * tallentaa olion ensin tietokantaa, ja hakee sen sieltä sitten samantein
     * samalla testaten, että lisäys onnistui. Metodi palauttaa luodun
     * UserSudoku -olion. Käyttää tallentamisen apuna UserSudoku -dao oliota
     * UserSudokuDao.
     *
     * @param s - Sudoku -olio, jota käyttäjä haluaa pelata
     * @param userId - käyttäjän tunniste, joka haluaa pelata sudokua
     * @return - Luotu UserSudoku -olio
     * @throws SQLException saattaa palauttaa SQLExceptionin, jos ei saa
     * yhteyttä tietokantaan tai tietokantaa ei ole olemassa
     */
    public UserSudoku play(Sudoku s, int userId) throws SQLException {
        UserSudoku us = new UserSudoku(s.getSudoku(), s.getSudoku());
        us.setId(s.getSudokuId());
        us.setUserId(userId);
        us.setDifficulty(s.getDifficulty());
        System.out.println(us.toString());
        UserSudokuDao.add(us);
        UserSudoku us2 = UserSudokuDao.getByIds(s.getSudokuId(), userId);
        System.out.println(us2.toString());
        return us2;
    }

    /**
     * Tallentaa pelaajan pelin nykyisen tilanteen. Antaa UserSudoku -dao olio
     * UserSudokuDaolle UserSudoku -olion, jonka tiedot se tallentaa
     * tietokantaan. Palauttaa tiedon onnistuiko tallennus.
     *
     * @param us - talletettava UserSudoku -olio
     * @return - palauttaa tiedon onnistuiko tallennus. Jos onnistui palauttaa
     * true, muuten false
     */
    public boolean save(UserSudoku us) {
        boolean succes = UserSudokuDao.save(us);
        return succes;
    }

    /**
     * Palauttaa id:n yksilöivän User -olion tietokannasta. Voi palauttaa Null.
     *
     * @param id - käyttäjä olion yksilöivä id
     * @return - löydetty User olio
     * @throws SQLException saattaa palauttaa SQLExceptionin, jos ei saa
     * yhteyttä tietokantaan tai tietokantaa ei ole olemassa
     */
    public User getUserById(int id) throws SQLException {
        User user = users.getById(id);
        return user;
    }
}
