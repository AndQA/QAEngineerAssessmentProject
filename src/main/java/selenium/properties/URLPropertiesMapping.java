package selenium.properties;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:test-urls-${env}.properties"
})
public interface URLPropertiesMapping extends Config {
    @Key("base.url")
    String baseUrl();

    @Key("careers.qa.url")
    String careersQaUrl();
}
