package models;

import interfaces.User;

/**
 * Created by Stanislav Petrov on 26.1.2016 г..
 */
public class Answer implements interfaces.Answer {

    private int id;
    private User author;
    private String body;
    private boolean isBestAnswer;

    public Answer(User author, String body) {
        this.setAuthor(author);
        this.setBody(body);
    }

    //region Properties
    public boolean isBestAnswer() {
        return isBestAnswer;
    }

    public void setAsBestAnswer(boolean bestAnswer) {
        isBestAnswer = bestAnswer;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    private void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    //endregion

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.isBestAnswer){
            stringBuilder.append(String.format("********************\n" +
                            "[ Answer ID: %d ]\n" +
                            "Posted by: %s\n" +
                            "Answer Body: %s\n" +
                            "--------------------\n" +
                            "********************\n",this.getId(),
                    this.getAuthor().getUsername(),
                    this.getBody()));
        } else {
            stringBuilder.append(String.format("[ Answer ID: %d ]\n" +
                    "Posted by: %s\n" +
                    "Answer Body: %s\n" +
                    "--------------------\n",this.getId(),
                    this.getAuthor().getUsername(),
                    this.getBody()));
        }

        return stringBuilder.toString();
    }
}
