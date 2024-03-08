<<<<<<<< HEAD:user-service/src/main/java/com/example/userService/helper/UserNotFoundException.java
package com.example.userService.helper;
========
package com.example.userService.exception;
>>>>>>>> 989a6ed5f75bfc662860396ca78079fdabe2458d:user-service/src/main/java/com/example/userService/exception/UserNotFoundException.java

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User with this username not found in database !!");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
