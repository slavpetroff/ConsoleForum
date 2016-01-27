package models;

import interfaces.Answer;

import interfaces.User;

import java.util.Collection;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
public class Question implements interfaces.Question {
    private int id;
    private String title;
    private String body;
    private User author;
    private Collection<Answer> answers;
    private boolean isOpened;

    public Question(String title, String body, Collection<Answer> answers) {
        this.setTitle(title);
        this.setBody(body);
        this.setAnswers(answers);
        this.setOpened(false);
    }

    //region Properties
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getBody() {
        return body;
    }

    private void setBody(String body) {
        this.body = body;
    }

    @Override
    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answerToAdd){
        this.getAnswers().add(answerToAdd);
    }

    private void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }
    //endregion

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("[ Question ID: %d ]\n",this.getId()));
        stringBuilder.append(String.format("Posted by: %s\n",this.getAuthor().getUsername()));
        stringBuilder.append(String.format("Question Title: %s\n",this.getTitle()));
        stringBuilder.append(String.format("Question Body: %s\n",this.getBody()));
        stringBuilder.append(String.format("====================\n"));
        if(this.getAnswers().isEmpty()){
            stringBuilder.append("No answers");
        } else {
            this.getAnswers().stream().forEach(stringBuilder::append);
//            stringBuilder.append(String.format("%s",this.getAnswers().toString()));
        }
        return stringBuilder.toString();
    }
}
