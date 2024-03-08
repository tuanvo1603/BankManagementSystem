<<<<<<<< HEAD:user-service/src/main/java/com/example/userService/helper/UserFoundException.java
package com.example.userService.helper;
========
package com.example.userService.exception;
>>>>>>>> 989a6ed5f75bfc662860396ca78079fdabe2458d:user-service/src/main/java/com/example/userService/exception/UserFoundException.java

public class UserFoundException extends Exception{

    public UserFoundException() {
        super("User with this Username is already there in DB !! try with another one");
    }

    public UserFoundException(String msg)
    {
        super(msg);
    }
}
