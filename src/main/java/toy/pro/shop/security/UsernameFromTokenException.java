package toy.pro.shop.security;

public class UsernameFromTokenException extends Throwable {
    public UsernameFromTokenException(String user) {
        super(user);
    }
}
