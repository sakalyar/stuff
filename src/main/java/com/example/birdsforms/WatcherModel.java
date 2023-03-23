package com.example.birdsforms;

public class WatcherModel {

    private int id;
    private String login;
    private String password;
    private String birds;
    private boolean rareBirdsDetected;
    private int numberOfBirds;

    public WatcherModel (int id, String login, String password, int numberOfBirds,
                         boolean rareBirdsDetected, String birds) {
        this.id  = id;
        this.login = login;
        this.password = password;
        this.rareBirdsDetected = rareBirdsDetected;
        this.numberOfBirds = numberOfBirds;
        this.birds = birds;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return login;
    }

    public void setName(String login) {
        this.login = login;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getBirds() {
        return birds;
    }

    public void setBirds(String birds) {
        this.birds = birds;
    }

    public int getNumberOfBirds() {
        return numberOfBirds;
    }

    public void setNumberOfBirds(int numberOfBirds) {
        this.numberOfBirds = numberOfBirds;
    }

    public boolean getRareBirdsDetector() { return rareBirdsDetected; }

    public void setRareBirdsDetected(boolean rareBirdsDetected) { this.rareBirdsDetected = rareBirdsDetected; }

    public WatcherModel(){}

    @Override
    public String toString() {
        return "WatcherModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", numberOfBirds=" + numberOfBirds +
                ", rareBirdsDetected: " + rareBirdsDetected +
                ", birds=" + birds +
                '}';
    }

}
