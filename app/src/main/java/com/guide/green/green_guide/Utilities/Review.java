package com.guide.green.green_guide.Utilities;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Review {
    public Location location = new Location();
    public WaterIssue waterIssue = new WaterIssue();
    public AirWaste airWaste = new AirWaste();
    public SolidWaste solidWaste = new SolidWaste();
    public int imageCount;
    public String id;

    /**
     * Parent class for all keys. Will be used to make "enums" for children.
     * Will contain static final variables and be un-instantiable in child classes through
     * private constructors.
     *
     * A null postName means that this argument should not be sent in a post request.
     */
    public static abstract class Key {
        public final String jsonName;
        public final String postName;
        public Key(String jsonName) {
            this(jsonName, jsonName);
        }
        public Key(String jsonName, String postName) {
            this.jsonName = jsonName;
            this.postName = postName;
        }
        public abstract Key[] getKeys();
        @Override
        public String toString() {
            return jsonName;
        }
    }

    /**
     * The parent of {@code Location}, {@code WaterIssue}, {@code SolidWaste}, and {@code AirWaste}.
     * Creates an object similar to a {@code map} where the key can only be from a specific
     * predefined set. Allows for setting and getting values.
     *
     * Provides a way to set the predefined set so each child can have its own keyset.
     */
    public static abstract class ReviewComponent {
        private HashMap<Key, String> attribLookup = new HashMap<>();

        /**
         * Default constructor uses STATIC {@code Key} variables from the realized
         * class through a call to {@code createAttribLookup} to create the
         * {@code attribLookup} map of this object.
         */
        public ReviewComponent(Key keySet) {
            for (Key k : keySet.getKeys()) {
                attribLookup.put(k, null);
            }
        }

        /**
         * Like a map, gets the value associated with a {@code Key}.
         *
         * @code key    The name of the attribute.
         */
         public String get(Key key) {
            return attribLookup.get(key);
        }

        /**
         * Like a map, associates a {@code Key} to a {@code value}.
         *
         * @code key    The name of the attribute.
         * @code value  The value to set it to.
         * @return      true if the value was set, else false
         */
        public boolean set(Key key, String value) {
            if (attribLookup.containsKey(key)) {
                attribLookup.put(key, value);
                return true;
            } else {
                return false;
            }
        }

        public abstract Key[] getKeys();
    }

    public static class Location extends ReviewComponent {
        /**
         * Default constructor. Insures the right keyset is used by this object.
         */
        public Location() {
            super(Key.keys[0]);
        }

        @Override
        public Review.Key[] getKeys() {
            return Key.keys;
        }

        /**
         * Key class containing keys that only work for the Location object.
         */
        public static class Key extends Review.Key {
            public static final Key SIZE = new Key("size", null);
            public static final Key REASON = new Key("reason", null);
            public static final Key STATUS = new Key("status", null);
            public static final Key MEASURE = new Key("measure", null);
            public static final Key EPA = new Key("epa", null);
            public static final Key REPORT = new Key("report", null);
            public static final Key HELP = new Key("help", null);
            public static final Key TIME = new Key("time", null);
            public static final Key PRODUCT = new Key("product");
            public static final Key INDUSTRY = new Key("industry");
            public static final Key NEWS = new Key("news");
            public static final Key OTHER = new Key("other");
            public static final Key LIVING = new Key("living");
            public static final Key LAND = new Key("land");
            public static final Key WASTE = new Key("waste");
            public static final Key AIR = new Key("air");
            public static final Key WATER = new Key("water");
            public static final Key RATING = new Key("rating");
            public static final Key LAT = new Key("lat");
            public static final Key LNG = new Key("lng");
            public static final Key REVIEW = new Key("review");
            public static final Key CITY = new Key("city");
            public static final Key ADDRESS = new Key("address");
            public static final Key COMPANY = new Key("company");
            public static final Key[] keys = new Key[] {Key.COMPANY, Key.ADDRESS, Key.CITY,
                    Key.REVIEW, Key.LNG, Key.LAT, Key.RATING, Key.WATER, Key.AIR, Key.WASTE,
                    Key.LAND, Key.LIVING, Key.OTHER, Key.NEWS, Key.INDUSTRY, Key.PRODUCT, Key.TIME,
                    Key.HELP, Key.REPORT, Key.EPA, Key.MEASURE, Key.STATUS, Key.REASON, Key.SIZE};

            /**
             * Private constructor to insure that no keys can be created outside of this
             * object. Effect: enum like structure when the enum values are the static final values.
             *
             * @param jsonName A unique key name. Uniqueness is not enforced.
             */
            public Key(String jsonName) {
                super(jsonName);
            }

            /**
             * Private constructor to insure that no keys can be created outside of this
             * object. Effect: enum like structure when the enum values are the static final values.
             *
             * @param jsonName A unique key name. Uniqueness is not enforced.
             * @param postName A the value that the REST API expects this to be named.
             */
            public Key(String jsonName, String postName) {
                super(jsonName, postName);
            }

            /**
             * @return  list of the keys unique to the parent object.
             */
            @Override
            public Key[] getKeys() { return keys; }
        }
    }


    public static class WaterIssue extends ReviewComponent {
        /**
         * Default constructor. Insures the right keyset is used by this object.
         */
        public WaterIssue() {
            super(Key.keys[0]);
        }

        @Override
        public Review.Key[] getKeys() {
            return Key.keys;
        }

        /**
         * Key class containing keys that only work for the  object.
         */
        public static class Key extends Review.Key {
            public static final Key ARSENIC = new Key("Arsenic", "As");
            public static final Key CD = new Key("Cd");
            public static final Key PB = new Key("Pb");
            public static final Key HG = new Key("Hg");
            public static final Key TP = new Key("TP");
            public static final Key NH4 = new Key("NH4");
            public static final Key TS = new Key("TS");
            public static final Key TOC = new Key("TOC");
            public static final Key COD = new Key("COD");
            public static final Key BOD = new Key("BOD");
            public static final Key TURB_PARAMS = new Key("TurbParams", "Turbidity");
            public static final Key PH = new Key("pH");
            public static final Key DO = new Key("DO");
            public static final Key FLOATS = new Key("Floats", "floatType");
            public static final Key CHECK_FLOAT = new Key("CheckFloat", "float");
            public static final Key TURB_SCORE = new Key("TurbScore", "turbRate");
            public static final Key ODOR = new Key("Odor", "WaterOdor");
            public static final Key WATER_COLOR = new Key("WaterColor");
            public static final Key WATER_TYPE = new Key("WaterType");
            public static final Key[] keys = new Key[] {Key.WATER_TYPE, Key.WATER_COLOR, Key.ODOR,
                    Key.TURB_SCORE, Key.CHECK_FLOAT, Key.FLOATS, Key.DO, Key.PH, Key.TURB_PARAMS,
                    Key.BOD, Key.COD, Key.TOC, Key.TS, Key.NH4, Key.TP, Key.HG, Key.PB, Key.CD,
                    Key.ARSENIC};

            /**
             * Private constructor to insure that no keys can be created outside of this
             * object. Effect: enum like structure when the enum values are the static final values.
             *
             * @param jsonName A unique key name. Uniqueness is not enforced.
             */
            public Key(String jsonName) {
                super(jsonName);
            }

            /**
             * Private constructor to insure that no keys can be created outside of this
             * object. Effect: enum like structure when the enum values are the static final values.
             *
             * @param jsonName A unique key name. Uniqueness is not enforced.
             * @param postName A the value that the REST API expects this to be named.
             */
            public Key(String jsonName, String postName) {
                super(jsonName, postName);
            }

            /**
             * @return  list of the keys unique to the parent object.
             */
            @Override
            public Key[] getKeys() { return keys; }
        }
    }

    public static class AirWaste extends ReviewComponent {
        /**
         * Default constructor. Insures the right keyset is used by this object.
         */
        public AirWaste() {
            super(Key.keys[0]);
        }

        @Override
        public Review.Key[] getKeys() {
            return Key.keys;
        }

        /**
         * Key class containing keys that only work for the  object.
         */
        public static class Key extends Review.Key {
            public static final Key CO = new Key("CO");
            public static final Key NOX = new Key("NOx");
            public static final Key SOX = new Key("SOx");
            public static final Key O3 = new Key("O3");
            public static final Key PM10 = new Key("PM10");
            public static final Key PM2_5 = new Key("PM2_5", "PM2.5");
            public static final Key SYMPTOMDESCR = new Key("symptomDescr");
            public static final Key SYMPTOM = new Key("Symptom");
            public static final Key SMOKE_COLOR = new Key("SmokeColor");
            public static final Key SMOKE_CHECK = new Key("Smoke_Check", "SmokeCheck");
            public static final Key ODOR = new Key("Odor", "AirOdor");
            public static final Key VISIBILITY = new Key("Visibility");
            public static final Key[] keys = new Key[] {Key.VISIBILITY, Key.ODOR, Key.SMOKE_CHECK,
                    Key.SMOKE_COLOR, Key.SYMPTOM, Key.SYMPTOMDESCR, Key.PM2_5, Key.PM10, Key.O3,
                    Key.SOX, Key.NOX, Key.CO};

            /**
             * Private constructor to insure that no keys can be created outside of this
             * object. Effect: enum like structure when the enum values are the static final values.
             *
             * @param jsonName A unique key name. Uniqueness is not enforced.
             */
            public Key(String jsonName) {
                super(jsonName);
            }

            /**
             * Private constructor to insure that no keys can be created outside of this
             * object. Effect: enum like structure when the enum values are the static final values.
             *
             * @param jsonName A unique key name. Uniqueness is not enforced.
             * @param postName A the value that the REST API expects this to be named.
             */
            public Key(String jsonName, String postName) {
                super(jsonName, postName);
            }

            /**
             * @return  list of the keys unique to the parent object.
             */
            @Override
            public Key[] getKeys() { return keys; }
        }
    }

    public static class SolidWaste extends ReviewComponent {
        /**
         * Default constructor. Insures the right keyset is used by this object.
         */
        public SolidWaste() {
            super(Key.keys[0]);
        }

        @Override
        public Review.Key[] getKeys() {
            return Key.keys;
        }

        /**
         * Key class containing keys that only work for the object.
         */
        public static class Key extends Review.Key {
            public static final Key MEASUREMENTS = new Key("Measurements", "WasteMeasure");
            public static final Key ODOR = new Key("Odor", "WasteOdor");
            public static final Key AMOUNT = new Key("Amount", "WasteAmount");
            public static final Key WASTE_TYPE = new Key("WasteType");
            public static final Key[] keys = new Key[] {Key.WASTE_TYPE, Key.AMOUNT, Key.ODOR,
                    Key.MEASUREMENTS};

            /**
             * Private constructor to insure that no keys can be created outside of this
             * object. Effect: enum like structure when the enum values are the static final values.
             *
             * @param jsonName A unique key name. Uniqueness is not enforced.
             */
            public Key(String jsonName) {
                super(jsonName);
            }

            /**
             * Private constructor to insure that no keys can be created outside of this
             * object. Effect: enum like structure when the enum values are the static final values.
             *
             * @param jsonName A unique key name. Uniqueness is not enforced.
             * @param postName A the value that the REST API expects this to be named.
             */
            public Key(String jsonName, String postName) {
                super(jsonName, postName);
            }

            /**
             * @return  list of the keys unique to the parent object.
             */
            @Override
            public Key[] getKeys() { return keys; }
        }
    }

    public interface Results {
        /**
         * Provided the returns of the query.
         *
         * @param reviews   non-null value.
         */
        public void onSuccess(ArrayList<Review> reviews);

        /**
         *  Provides the exception that messed up getting the results.
         *
         * @param e non-null value.
         */
        public void onError(Exception e);

        /**
         * Called every time more data is obtained from the webserver.
         *
         * @param current   the total amount of bytes read so far from the server.
         * @param total the expected total number of bytes that will be read from the server.
         *              if unknown, this value is set to -1.
         */
        public void onUpdate(long current, long total);

        /**
         * Called when the background task is canceled.
         */
        public void onCanceled();
    }

    /**
     * Gets the reviews stored for a specific point on the Green Guide database.
     *
     * @param lng longitude
     * @param lat latitude
     * @param callback non-null value where the results will be returned to.
     * @return  the object managing the background request.
     */
    public static AsyncJSONArray getReviewsForPlace(double lng, double lat,
                                                    @NonNull final Results callback) {

        final ReviewAsyncJSONArrayResult jsonCallback = new ReviewAsyncJSONArrayResult(callback);

        AsyncJSONArray aj = new AsyncJSONArray(jsonCallback) {
            @Override
            protected void onProgressUpdate(Long... values) {
                jsonCallback.REVIEWS_CALLBACK.onUpdate(values[0], values[1]);
            }
        };

        String url = "http://www.lovegreenguide.com/map_point_co_app.php?lng=" + lng + "&lat=" +lat;
        aj.execute(url);
        return  aj;
    }

    private static class ReviewAsyncJSONArrayResult implements AsyncJSONArray.OnAsyncJSONArrayResultListener {
        public final Results REVIEWS_CALLBACK;

        private static String decodeHTML(String htmlString) {
            if (Build.VERSION.SDK_INT >= 24) {
                return Html.fromHtml(htmlString , Html.FROM_HTML_MODE_LEGACY).toString();
            } else {
                return Html.fromHtml(htmlString).toString();
            }
        }

        ReviewAsyncJSONArrayResult(Results callback) {
            REVIEWS_CALLBACK = callback;
        }

        @Override
        public void onCanceled(ArrayList<JSONArray> jArray, ArrayList<Exception> exceptions) {
            REVIEWS_CALLBACK.onCanceled();
        }

        @Override
        public void onFinish(ArrayList<JSONArray> jArray, ArrayList<Exception> exceptions) {
            if (!exceptions.isEmpty() || jArray.isEmpty() || jArray.get(0) == null) {
                REVIEWS_CALLBACK.onError(exceptions.get(0));
                return;
            }

            ArrayList<Review> results = new ArrayList<>();
            JSONArray jArr = jArray.get(0);
            for (int i = jArr.length() - 1; i >= 0; i--) {
                Review review = new Review();
                try {
                    JSONObject jObj = jArr.getJSONObject(i);
                    review.imageCount = jObj.getInt("img_count");
                    JSONObject subJObj = null;
                    subJObj = jObj.getJSONObject("review");
                    for (Location.Key key : Location.Key.keys) {
                        review.location.set(key, decodeHTML(subJObj.getString(key.jsonName)));
                    }
                    review.id = subJObj.getString("id");
                    String waterKey = "water";
                    if (!jObj.isNull(waterKey)) {
                        subJObj = jObj.getJSONObject(waterKey);
                        for (WaterIssue.Key key : WaterIssue.Key.keys) {
                            review.waterIssue.set(key, decodeHTML(subJObj.getString(key.jsonName)));
                        }
                    }
                    String solidKey = "solid";
                    if (!jObj.isNull(solidKey)) {
                        subJObj = jObj.getJSONObject(solidKey);
                        for (SolidWaste.Key key : SolidWaste.Key.keys) {
                            review.solidWaste.set(key, decodeHTML(subJObj.getString(key.jsonName)));
                        }
                    }
                    String airKey = "air";
                    if (!jObj.isNull(airKey)) {
                        subJObj = jObj.getJSONObject(airKey);
                        for (AirWaste.Key key : AirWaste.Key.keys) {
                            review.airWaste.set(key, decodeHTML(subJObj.getString(key.jsonName)));
                        }
                    }
                    results.add(review);
                } catch (JSONException e) {
                    REVIEWS_CALLBACK.onError(e);
                    return;
                }
            }
            REVIEWS_CALLBACK.onSuccess(results);
        }
    }
}
