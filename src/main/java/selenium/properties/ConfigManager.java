package selenium.properties;

import org.aeonbits.owner.ConfigFactory;
import java.util.Map;

import static selenium.properties.EnvironmentManager.envConf;

public final class ConfigManager {

    private static final String DEFAULT_ENV = System.getProperty("env");
    private static final String ENV;

    static {
        String systemEnv = System.getProperty("env");
        String managerEnv = EnvironmentManager.getEnvName();

        if (systemEnv != null && !systemEnv.isBlank()) {
            ENV = systemEnv;
        } else if (managerEnv != null && !managerEnv.isBlank()) {
            ENV = managerEnv;
            System.setProperty("env", managerEnv);
        } else {
            ENV = DEFAULT_ENV;
            System.setProperty("env", DEFAULT_ENV);
        }
    }

    private ConfigManager() {
        // Utility class — no instances allowed
    }

    public static final EnvironmentManager.EnvironmentConfig env =
            envConf;

    public static final TestDataMapping testData =
            ConfigFactory.create(
                    TestDataMapping.class,
                    System.getProperties(),
                    System.getenv(),
                    Map.of("env", ENV)
            );

    public static final URLPropertiesMapping urls =
            ConfigFactory.create(
                    URLPropertiesMapping.class,
                    System.getProperties(),
                    System.getenv(),
                    Map.of("env", ENV)
            );
}
