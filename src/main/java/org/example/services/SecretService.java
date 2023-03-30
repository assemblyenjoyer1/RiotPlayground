package org.example.services;

public class SecretService {

    String password;
    int attempts = 3;
    public SecretService(){
        this.password = "milk";
    }

    public boolean comparePassword(String password){
        if(attempts > 0){
            return password.equals(this.password);
        }
        return false;
    }
}
