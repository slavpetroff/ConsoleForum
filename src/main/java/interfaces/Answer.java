package interfaces;

/**
 * Created by Stanislav Petrov on 23.1.2016 г..
 */
public interface Answer {

    int getId();

    User getAuthor();

    String getBody();

    void setBody(String body);

    boolean isBestAnswer();

    void setAsBestAnswer(boolean bestAnswer);
}
