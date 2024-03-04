package website.skillforge.be.exception.exceptions;

public class AuthenticationException extends RuntimeException
{
    public AuthenticationException(String message)
    {
        super(message);
    }
}
