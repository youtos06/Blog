package so.tribe.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertyResolver {
    // retrieve our application props
    @Autowired
    private Environment environment;

    public String getPropertyValue(String property){
        return environment.getProperty(property);
    }
}
