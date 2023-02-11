package edu.uoc.pac3;

public class Team {
    private int id;
    private static int nextId  = 0;
    private String name;
    private String foundationYear;
    private String nif;
    private String email;
    private StatisticsTeam statisticsTeam;
    private int capacity;

    public Team() throws Exception {
        this("Lorem club", "2000", "G12345678", "info@yourmail.com", 21);
    }

    public Team(String name, String foundationYear, String nif, String email, int capacity) throws Exception{
        setName(name);
        setFoundationYear(foundationYear);
        setNif(nif);
        setEmail(email);
        setCapacity(capacity);
        //we always initialize the statistics to 0
        this.statisticsTeam = new StatisticsTeam();
        setId();
    }

    public int getId() {
        return id;
    }

    private void setId() {
        this.id = getNextId();
        incNextId();
    }

    public static int getNextId() {
        return nextId;
    }

    private void incNextId(){
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception{
        if(name.length()>50) {
            throw new Exception("[ERROR] Team's name cannot be longer than 50 characters");
        }else {
            this.name = name;
        }
    }

    public String getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(String foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) throws Exception{
        String regex = "[ABG][0-9]{8}";
        if(!nif.matches(regex)) {
            throw new Exception("[ERROR] Team's NIF pattern is incorrect");
        }
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if (email.contains("@") && (email.endsWith(".es") || email.endsWith(".com"))) {
            this.email = email;
        }else {
            throw new Exception("[ERROR] Team's email pattern is incorrect");
        }
    }

    public StatisticsTeam getStatisticsTeam() {
        return statisticsTeam;
    }

    public void resetStatistics() throws Exception{
        this.statisticsTeam = new StatisticsTeam();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) throws Exception{
        if(capacity > 0) {
            this.capacity = capacity;
        }else {
            throw new Exception("[ERROR] Team's capacity must be greater than 0");
        }
    }

    public String getInfo() {
        return "Name: "+getName()+" - Foundation year: "+ getFoundationYear()+" - NIF: "+getNif();
    }
}
