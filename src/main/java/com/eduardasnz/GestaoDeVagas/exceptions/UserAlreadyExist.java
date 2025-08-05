package com.eduardasnz.GestaoDeVagas.exceptions;

public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist() {
        super("User already exist");
    }
}
