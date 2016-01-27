package configFiles;

import interfaces.*;
import interfaces.io.UserInput;
import interfaces.io.UserOutput;
import models.ConsoleInput;
import models.ConsoleOutput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
@Configuration
public class MainConfig {

    //region Collections
    @Bean
    public Collection<User> usersCollection() {
        return new ArrayList<>();
    }

    @Bean
    public Collection<User> currentlyLoggedUsersCollection() {
        return new ArrayList<>();
    }

    @Bean
    public Collection<Question> questionsCollection() {
        return new ArrayList<>();
    }

    @Bean
    public Collection<Post> postsCollection() {
        return new ArrayList<>();
    }

    @Bean
    public Collection<Answer> questionsAnswerCollection(){
        return new ArrayList<>();
    }
    //endregion

    @Bean
    public Map map() {
        return new HashMap<>();
    }

    @Bean
    public UserInput input() {
        return new ConsoleInput();
    }

    @Bean
    public UserOutput output() {
        return new ConsoleOutput();
    }

    @Bean
    public Database database() {
        return new models.Database(this.usersCollection(),
                this.currentlyLoggedUsersCollection(),
                this.questionsCollection(),
                this.postsCollection());
    }

    @Bean
    public CommandManager commandManager() {
        return new models.CommandManager();
    }

    // Engine with no setter injection for the command manager ! ! !

    public Engine engineWithNoInjection() {
        return new models.Engine(this.input(), this.output(), this.database(), this.commandManager());
    }

    @Bean
    public Engine engine(){
        Engine injectedEngine = this.engineWithNoInjection();
        injectedEngine.setCommandManager(this.commandManager());
        injectedEngine.getCommandManager().setEngine(this.engineWithNoInjection());
        return injectedEngine;
    }

    @Bean
    @Scope("prototype")
    public Question question(String title, String body){
        return new models.Question(title,body,this.questionsAnswerCollection());
    }
}
