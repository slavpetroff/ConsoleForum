package interfaces;

import java.util.Collection;

/**
 * Created by Stanislav Petrov on 23.1.2016 г..
 */
public interface Question {

    int getId();

    void setId(int id);

    String getTitle();

    String getBody();

    User getAuthor();

    void setAuthor(User author);

    Collection<Answer> getAnswers();

    void addAnswer(Answer answerToAdd);

    boolean isOpened();

    void setOpened(boolean opened);
}
