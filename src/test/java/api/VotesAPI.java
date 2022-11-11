package api;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;

public class VotesAPI {
    private EnvironmentVariables environmentVariables;

    @Step
    public String invoke_my_webservice() {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("api.base.endpoint");
    }
}
