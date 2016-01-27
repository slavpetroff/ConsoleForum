import configFiles.MainConfig;
import interfaces.Engine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Stanislav Petrov on 25.1.2016 г..
 */
public class MainEntry {

    public static void main(String[] args) throws OperationNotSupportedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Engine myEngine = context.getBean(models.Engine.class);
        myEngine.run();
    }
}
