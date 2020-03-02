package edu.scau.rorke.springbootdemo.exception;

/**
 * @author Rorke
 * @date 2020/3/1 16:29
 */
public class NickNameAlreadyExistException extends RuntimeException {
    public NickNameAlreadyExistException(String s) {
        super(s);
    }
}
