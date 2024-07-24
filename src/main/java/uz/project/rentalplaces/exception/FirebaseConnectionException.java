package uz.project.rentalplaces.exception;

public class FirebaseConnectionException extends RuntimeException {
    public FirebaseConnectionException(String firebaseException) {
        super(firebaseException);
    }
}
