package selenium.properties;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:test-data-${env}.properties"
})
public interface TestDataMapping extends Config {
    @Key("username") String username();
    @Key("password") String password();
}
