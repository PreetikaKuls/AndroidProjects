package Network;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.Map;

/**
 * Created by preetikataly on 9/13/14.
 */
public class RottenTomatoesClient {
    private final String API_KEY = "8m4s2jzav2nnycwyjhszbz95";
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient client = new AsyncHttpClient();
    public static final String TAG ="DEBUG";

    public RottenTomatoesClient() {
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    // http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=<key>
    public void getBoxOfficeMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("lists/movies/box_office.json");
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, handler);
    }

    public void getSimilarMovies(JsonHttpResponseHandler handler, String movieID) {
        String url = getApiUrl("movies/" + movieID + "/" + "similar.json");
        Log.v(TAG, url);
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, handler);
    }

    public void getMovieId(JsonHttpResponseHandler handler, String movieName){
        String url = getApiUrl("movies.json");
        RequestParams params = new RequestParams();
        params.put("apikey", API_KEY);
        params.put("q", movieName);
        //Log.v(TAG, movieName);
        client.get(url, params, handler);
    }
}

/*public class RottenTomatoesClientNew {
    private final String API_KEY = "8m4s2jzav2nnycwyjhszbz95";
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient asyncClient = new AsyncHttpClient();
    private DefaultHttpClient syncClient = new DefaultHttpClient();
    public static final String TAG ="DEBUG";

    public RottenTomatoesClientNew() {
        this.asyncClient = new AsyncHttpClient();
        this.syncClient = new DefaultHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    public void makeAsyncRequest(JsonHttpResponseHandler handler, String relativeUrl, RequestParams params) {
        client.get(getApiUrl(relativeUrl), params, handler);
    }

    public HttpResponse makeSyncRequest(HttpUriRequest request) {
            try {
                syncClient.execute(request);
            } catch (e ) {

            }
    }
}

public class MovieIDClient {
    public RottenTomatoesClientNew rtClient
    public String movieID;
    public Map<String, String>

    public RottenTomatoesClientNew() {
        this.rtClient = new RottenTomatoesClientNew;
    }
    public String getMovieID(String movieName) {

          Consult the cache, if exists, then serve from cache
          Otherwise,
          Create Http get object with movieName as tha parameter
          Invoke rtClient.makeSyncRequest(<movieIDURL>, <Http get object>)
          and then drop movieID into cache.

    }
}
*/