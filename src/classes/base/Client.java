package src.classes.base;

import src.classes.annotations.PrimaryKey;

public class Client extends Table{

    String name;
    String lastName;
    String email;
    @PrimaryKey
    Integer idClient;

    public Client(String name, String lastName, String email, Integer idClient) throws Exception {
        this.name=name;
        this.lastName=lastName;
        this.email=email;
        this.idClient=idClient;
    }
}
