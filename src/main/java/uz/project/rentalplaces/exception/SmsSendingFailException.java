package uz.project.rentalplaces.exception;

public class SmsSendingFailException extends RuntimeException {
    public SmsSendingFailException(String massage) {
        super(massage);
    }
}
