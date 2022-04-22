package k5s.hotmovie.error;

public class InvalidAuthenticationException extends RuntimeException{
    public InvalidAuthenticationException(String message) {
        super(message);
    }
}
