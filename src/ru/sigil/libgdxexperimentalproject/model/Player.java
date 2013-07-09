package ru.sigil.libgdxexperimentalproject.model;

//Singleton
public class Player {
    private String login;
    private String password;
    private int id;

    private static final Player INSTANCE = new Player();

    private Player() {
    }

    public static Player getInstance() {
        return INSTANCE;
    }

    public static void init(String login, String password, int id) {
        getInstance().setId(id);
        getInstance().setLogin(login);
        getInstance().setPassword(password);
    }

    public String getLogin() {
        return login;
    }

    void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }
}
