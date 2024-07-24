package uz.project.rentalplaces.exception;

public class TimeExceededException extends RuntimeException{
    public TimeExceededException(String massage) {
        super(massage);
    }
}
