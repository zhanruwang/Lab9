import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Sample code showing usage of the Microsoft Cognitive Services API.
 */
public class APITest {

    /** Subscription key. Replace with your API key. */
    private static final String SUBSCRIPTION_KEY =
            "13hc77781f7e4b19b5fcdd72a8df7156";

    /**
     * Region to use.
     *
     * You must use the same region in your REST API call as you used to obtain your subscription
     * key. For example, if you obtained your subscription keys from the westus region, replace
     * "westcentralus" in the URI below with "westus".
     *
     * NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are
     * using a free trial subscription key, you should not need to change this region.
     */
    private static final String URI_BASE =
            "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";


    /**
     * Make an API call using the Microsoft Cognitive Services API and display the results.
     *
     * @param unused unused input arguments
     */
    public static void main(final String[] unused) {
        HttpClient httpclient = new DefaultHttpClient();

        try {
            URIBuilder builder = new URIBuilder(URI_BASE);

            // Request parameters. All of them are optional.
            builder.setParameter("visualFeatures", "Categories,Description,Color");
            builder.setParameter("language", "en");

            // Prepare the URI for the REST API call.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY);

            // Request body.
            StringEntity reqEntity =
                    new StringEntity("{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/"
                            + "1/12/Broadway_and_Times_Square_by_night.jpg\"}");
            request.setEntity(reqEntity);

            // Execute the REST API call and get the response entity.
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                System.out.println("REST Response:\n");
                System.out.println(json.toString(2));
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
    }
}
