package exceptionHelper;


public class SignInFailed extends Exception {
    public SignInFailed(String message) {
        super(message);
    }
}
