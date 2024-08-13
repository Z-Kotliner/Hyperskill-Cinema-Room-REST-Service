import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.dynamic.input.DynamicTesting;
import org.hyperskill.hstest.mocks.web.response.HttpResponse;
import org.hyperskill.hstest.stage.SpringTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.expect.json.builder.JsonArrayBuilder;
import org.hyperskill.hstest.testing.expect.json.builder.JsonObjectBuilder;

import static org.hyperskill.hstest.testing.expect.Expectation.expect;
import static org.hyperskill.hstest.testing.expect.json.JsonChecker.isArray;
import static org.hyperskill.hstest.testing.expect.json.JsonChecker.isObject;

public class CinemaTests extends SpringTest {
    private static final int totalRows = 9;
    private static final int totalCols = 9;

    CheckResult testEndpoint() {

        HttpResponse response = get("/seats").send();

        if (response.getStatusCode() != 200) {
            return CheckResult.wrong("GET /seats should respond with " +
                "status code 200, responded: " + response.getStatusCode() + "\n\n" +
                "Response body:\n" + response.getContent());
        }

        return CheckResult.correct();
    }

    CheckResult testEndpointAvailableSeats() {
        HttpResponse response = get("/seats").send();

        JsonArrayBuilder arrayBuilder = isArray(totalRows * totalCols);
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalCols; j++) {
                JsonObjectBuilder objectBuilder = isObject()
                        .value("row", i)
                        .value("column", j);
                arrayBuilder = arrayBuilder.item(objectBuilder);
            }
        }
        expect(response.getContent()).asJson().check(
                isObject()
                        .value("available_seats", arrayBuilder)
                        .value("total_columns", totalCols)
                        .value("total_rows", totalRows)
        );

        return CheckResult.correct();
    }

    @DynamicTest
    DynamicTesting[] dynamicTests = new DynamicTesting[]{
        this::testEndpoint,
        this::testEndpointAvailableSeats
    };
}
