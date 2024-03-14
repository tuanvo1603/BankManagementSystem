package com.example.userService.exception;

public class RoleFoundException extends Exception{
    public RoleFoundException(){super("This role is already exist");}

    public RoleFoundException(String msg)
    {
        super(msg);
    }
}
