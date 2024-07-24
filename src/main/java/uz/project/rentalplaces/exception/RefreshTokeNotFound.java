package uz.project.rentalplaces.exception;

public class RefreshTokeNotFound extends RuntimeException {
    public RefreshTokeNotFound(String reFreshTokenNotFound) {
        super(reFreshTokenNotFound);
    }
}
