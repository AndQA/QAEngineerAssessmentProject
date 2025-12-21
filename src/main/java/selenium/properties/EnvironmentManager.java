package selenium.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public class EnvironmentManager {

    private static final String DEFAULT_ENV = "prod";
    private static final String ENV;

    static {
        String systemEnv = System.getProperty("env");

        if (systemEnv != null && !systemEnv.isBlank()) {
            ENV = systemEnv;
        } else {
            ENV = DEFAULT_ENV;
            System.setProperty("env", DEFAULT_ENV);
        }

        System.out.println("ENV = " + ENV);
    }

    @Config.Sources({
            "classpath:environment.properties"
    })
    public interface EnvironmentConfig extends org.aeonbits.owner.Config {
        @Key("browser") String browser();
        @Key("headless") boolean headless();
        @Key("explicit.wait") int explicitWait();

    }

    public static final EnvironmentConfig envConf =
            ConfigFactory.create(EnvironmentConfig.class, System.getProperties());

    public static String getEnvName() {
        return ENV;
    }
}
