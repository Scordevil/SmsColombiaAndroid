package co.com.app.android.smscolombia.smscolombia.cliente;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.com.app.android.smscolombia.smscolombia.R;
import co.com.app.android.smscolombia.smscolombia.cliente.maps.ManageGoogleRoutes;
import co.com.app.android.smscolombia.smscolombia.cliente.maps.OnTaskCompleted;
import co.com.app.android.smscolombia.smscolombia.cliente.maps.PlaceAutocompleteAdapter;
import co.com.app.android.smscolombia.smscolombia.cliente.maps.Posiciones;
import co.com.app.android.smscolombia.smscolombia.cliente.maps.Route_TO;
import co.com.app.android.smscolombia.smscolombia.config.UserLocalStore;
import co.com.app.android.smscolombia.smscolombia.models.Lugares_TO;
import co.com.app.android.smscolombia.smscolombia.models.Servicio_TO;
import co.com.app.android.smscolombia.smscolombia.sevice.CalcularTarifa;
import co.com.app.android.smscolombia.smscolombia.sevice.ConsultarLugar;
import co.com.app.android.smscolombia.smscolombia.sevice.ConsutarTarifa;
import co.com.app.android.smscolombia.smscolombia.sevice.ServerRequests;
import common.activities.SampleActivityBase;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Parte4 extends SampleActivityBase
        implements GoogleApiClient.OnConnectionFailedListener, OnTaskCompleted, OnMapReadyCallback {

    UserLocalStore userLocalStore;

    ServerRequests serverRequests = new ServerRequests();
    String ruta = serverRequests.BuscarRuta();

    String lugarInicio;
    static String puntoinicioLong;
    static String puntofinalLong;
    static String puntoinicioLat;
    static String puntofinalLat;
    String lugardestino;
    static int kilometros;

    Servicio_TO servicio = new Servicio_TO();

    private static GoogleMap mMap;
    private List<List<HashMap<String, String>>> routes;

/**
 * GoogleApiClient wraps our service connection to Google Play Services and provides access
 * to the user's sign in state as well as the Google's APIs.
 */
    protected GoogleApiClient mGoogleApiClient;

    private PlaceAutocompleteAdapter mAdapter;

    private AutoCompleteTextView autocomplete_places_desde, autocomplete_places_hasta;

    private TextView mPlaceDetailsText;

    private TextView mPlaceDetailsAttribution;

    private static TextView tvDistancia;

    Button btnAceptar, btnCancelarR, button_buscar, btnReset;

    String cc;

    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
        new LatLng(4.4166667, -74.01295555555555), new LatLng(4.8, -73.98704444444445));

    private static final LatLng bogota = new LatLng(4.71073676, -74.072270);

    private static int unidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userLocalStore = new UserLocalStore(this);

        ConsultarLugarInicio();

        cc = getIntent().getExtras().getString("cc");
        // Construct a GoogleApiClient for the {@link Places#GEO_DATA_API} using AutoManage
        // functionality, which automatically sets up the API client to handle Activity lifecycle
        // events. If your activity does not extend FragmentActivity, make sure to call connect()
        // and disconnect() explicitly.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();

        setContentView(R.layout.activity_parte4);


        mMap = null;
        setUpMapIfNeeded();

        android.util.Log.i("Gustavo66", "prueba." + Posiciones.POLILINEA);

        // Retrieve the AutoCompleteTextView that will display Place suggestions.
        autocomplete_places_desde = (AutoCompleteTextView)
                findViewById(R.id.autocomplete_places_desde);

        autocomplete_places_hasta = (AutoCompleteTextView)
                findViewById(R.id.autocomplete_places_hasta);


        // Register a listener that receives callbacks when a suggestion has been selected
      //  autocomplete_places_desde.setOnItemClickListener(mAutocompleteClickListener);
        autocomplete_places_hasta.setOnItemClickListener(mAutocompleteClickListener);

        // Retrieve the TextViews that will display details and attributions of the selected place.
        //   mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        //  mPlaceDetailsAttribution = (TextView) findViewById(R.id.place_attribution);

        // Set up the adapter that will retrieve suggestions from the Places Geo Data API that cover
        // the entire world.
        mAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, BOUNDS_GREATER_SYDNEY,
                null);
        autocomplete_places_desde.setAdapter(mAdapter);
        autocomplete_places_hasta.setAdapter(mAdapter);

        // Set up the 'clear text' button that clears the text in the autocomplete view
        tvDistancia = (TextView) findViewById(R.id.tvDistancia);
        btnAceptar  = (Button) findViewById(R.id.btnAceptar);;
      //  btnReset = (Button) findViewById(R.id.btnReset);
      //  btnCancelarR = (Button) findViewById(R.id.btnCancelarR);
        button_buscar = (Button) findViewById(R.id.button_buscar);

/*        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autocomplete_places_desde.setText("");
                autocomplete_places_hasta.setText("");
                tvDistancia.setText("Distancia / Duración");
                autocomplete_places_desde.setEnabled(true);
                autocomplete_places_hasta.setEnabled(true);
                btnAceptar.setEnabled(false);
                button_buscar.setEnabled(true);
                mMap.clear();
                pruebaMapa();
                Log.i("GustavoLimpiar", "limpiar");
            }
        });*/

        button_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String locationName = autocomplete_places_desde.getText().toString();
                String locationName2 = autocomplete_places_hasta.getText().toString();
                int maxResults = 5;


                try {
                    List<Address> result = new Geocoder(getApplicationContext()).getFromLocationName(locationName, maxResults);
                    List<Address> result2 = new Geocoder(getApplicationContext()).getFromLocationName(locationName2, maxResults);

                    Log.i("Rest ", result+"");
                    Log.i("Rest2 ", result2+"");
                    if (result.size() == 1 && result2.size() == 1) {
                        pintarRuta();
                        autocomplete_places_desde.setEnabled(false);
                        autocomplete_places_hasta.setEnabled(false);
                    } else {
                        if (result.size() > 1 || result2.size() > 1) {
                            pintarRuta();
                            autocomplete_places_desde.setEnabled(false);
                            autocomplete_places_hasta.setEnabled(false);
                            Toast.makeText(getApplicationContext(), "Se muestra la ruta mas corta",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Las direcciones deben ser mas especificas",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                lugardestino = autocomplete_places_hasta.getText().toString();

                servicio.setLugarInicio(lugarInicio);
                servicio.setLugarDestino(lugardestino);
                servicio.setPuntoInicioLat(puntoinicioLat);
                servicio.setPuntoInicioLong(puntoinicioLong);
                servicio.setPuntoFinalLat(puntofinalLat);
                servicio.setPuntoFinalLong(puntofinalLong);

                enviarPeticion();

            }
        });


/*        btnCancelarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Parte4.this)
                        .setTitle("Cancelar servicio")
                        .setMessage("¿Deseas Cancelar Este servicio?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                startActivity(new Intent(Parte4.this, MainActivity.class));
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bogota, 13));
        }

    public void pruebaMapa(){

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bogota, 13));
    }

    /**
     * Listener that handles selections from suggestions from the AutoCompleteTextView that
     * displays Place suggestions.
     * Gets the place id of the selected item and issues a request to the Places Geo Data API
     * to retrieve more details about the place.
     *
     * @see com.google.android.gms.location.places.GeoDataApi#getPlaceById(com.google.android.gms.common.api.GoogleApiClient,
     * String...)
     */
    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
            final AutocompletePrediction item = mAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            final CharSequence primaryText = item.getPrimaryText(null);

            Log.i(TAG, "Autocomplete item selected: " + primaryText);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
             details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

            Toast.makeText(getApplicationContext(), "Clicked: " + primaryText,
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
        }
    };

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);

            // Format details of the place for display and show it in a TextView.
//            mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
            //                place.getId(), place.getAddress(), place.getPhoneNumber(),
            //                  place.getWebsiteUri()));

            // Display the third party attributions if set.
            //     final CharSequence thirdPartyAttribution = places.getAttributions();
/*                if (thirdPartyAttribution == null) {
                    mPlaceDetailsAttribution.setVisibility(View.GONE);
                } else {
                    mPlaceDetailsAttribution.setVisibility(View.VISIBLE);
                    mPlaceDetailsAttribution.setText(Html.fromHtml(thirdPartyAttribution.toString()));
                }*/

            Log.i(TAG, "Place details received: " + place.getName());

            places.release();
        }
    };

    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));

    }

    /**
     * Called when the Activity could not connect to Google Play services and the auto manager
     * could resolve the error automatically.
     * In this case the API is not available and notify the user.
     *
     * @param connectionResult can be inspected to determine the cause of the failure
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }




    private void setUpMapIfNeeded() {
// Configuramos el objeto GoogleMaps con valores iniciales.
        if (mMap == null) {
            //Instanciamos el objeto mMap a partir del MapFragment definido bajo el Id "map"
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Chequeamos si se ha obtenido correctamente una referencia al objeto GoogleMap
            if (mMap != null) {
                // El objeto GoogleMap ha sido referenciado correctamente
                //ahora podemos manipular sus propiedades

                //Seteamos el tipo de mapa
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                //Activamos la capa o layer MyLocation
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                pruebaMapa();
                Log.i("GustavoMapa1", "nuevoDiferenteNull");


            }
        }else{
            pruebaMapa();
            Log.i("GustavoMapa1", "viejoDiferenteNull");
        }
    }

    private void pintarRuta() {

        //Obtenemos la dirección A y B obtenida por el usuario.
/*        String pointA = "calle 129d #59d-91, colombia";
        String pointB = "calle 68 #11-60, colombia";*/

        String pointA = lugarInicio;
        String pointB = autocomplete_places_hasta.getText().toString();


        if (pointA != null && pointA != "" && pointB != null && pointB != "") {
            //Disparamos la tarea asíncrona definida en la clase ManageGoogleRoutes
            //pasando los puntos A y B para el calculo de la ruta y la obtención de
            //las coordenadas que nos permitirán dibujar la ruta a seguir.
            new ManageGoogleRoutes(Parte4.this).execute(pointA, pointB);

            //    startActivity(new Intent(getActivity(), MapsActivity.class));
        } else {
            Toast.makeText(Parte4.this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
        }
    }

    public static void marcarPolilyne(List<LatLng> LatitudLongitud){
        for (int i=0; i<LatitudLongitud.size()-1; i++) {
            LatLng currentPoint = LatitudLongitud.get(i);
            LatLng nextPoint = LatitudLongitud.get(i+1);
            mMap.addPolyline(new PolylineOptions().add(currentPoint, nextPoint).
                    width(6).color(Color.RED));
        }
    };

    @Override
    public void onTaskCompleted(List<List<HashMap<String, String>>> listLatLong) {
        if (listLatLong != null && listLatLong.size() > 0) {
            //Instanciamos MyMapFragment y le pasamos el listado de Latitudes y Longitudes
            //necesarios para trazar la ruta entre el punto A y el B

            this.setRoutes(listLatLong);

            btnAceptar.setEnabled(true);
            button_buscar.setEnabled(false);

/*            //   startActivity(new Intent(this, MapsActivity.class));

            //Se hace el cambio de Fragment entre el Main y el Map empleando FragmentTransaction
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, myMapFrag);
            transaction.addToBackStack(null);
            transaction.commit();*/
        } else {
            Toast.makeText(this, " No se logro determinar ruta, intentelo nuevamente", Toast.LENGTH_LONG).show();
        }

    }

    public void setRoutes(List<List<HashMap<String, String>>> routes) {
        this.routes = routes;
    }

    private void drawRoutes(List<List<HashMap<String, String>>> result) {
        LatLng center = null;
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;

        //   setUpMapIfNeeded();

        // recorriendo todas las rutas
        for(int i=0;i<result.size();i++){
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();

            // Obteniendo el detalle de la ruta
            List<HashMap<String, String>> path = result.get(i);

            // Obteniendo todos los puntos y/o coordenadas de la ruta
            for(int j=0;j<path.size();j++){
                HashMap<String,String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                if (center == null) {
                    //Obtengo la 1ra coordenada para centrar el mapa en la misma.
                    center = new LatLng(lat, lng);
                }
                points.add(position);
            }

            // Agregamos todos los puntos en la ruta al objeto LineOptions
            lineOptions.addAll(points);
            //Definimos el grosor de las Polilíneas
            lineOptions.width(2);
            //Definimos el color de la Polilíneas
            lineOptions.color(Color.RED);
        }

        // Dibujamos las Polilineas en el Google Map para cada ruta
        mMap.addPolyline(lineOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center, 13));
    }

    public static void buscaDistancia(String result) {

        Route_TO ruta = new Route_TO();
        // turn the stream into a string
        try {
            //Tranform the string into a json object
            final JSONObject json = new JSONObject(result);
            //Get the route object
            final JSONObject jsonRoute = json.getJSONArray("routes").getJSONObject(0);
            //Get the leg, only one leg as we don't support waypoints
            final JSONObject leg = jsonRoute.getJSONArray("legs").getJSONObject(0);
            //Get the steps for this leg
            final JSONArray steps = leg.getJSONArray("steps");
            //Number of steps for use in for loop
            final int numSteps = steps.length();
            //Set the name of this route using the start & end addresses
            ruta.setName(leg.getString("start_address") + " to " + leg.getString("end_address"));
            //Get google's copyright notice (tos requirement)
            ruta.setCopyright(jsonRoute.getString("copyrights"));
            //Get the total length of the route.
            ruta.setLength(leg.getJSONObject("distance").getInt("value"));
            ruta.setDistance(leg.getJSONObject("distance").getString("text"));
            ruta.setDuration(leg.getJSONObject("duration").getString("text"));
            ruta.setLatInicio(leg.getJSONObject("start_location").getDouble("lat"));
            ruta.setLongInicio(leg.getJSONObject("start_location").getDouble("lng"));
            ruta.setLatFinal(leg.getJSONObject("end_location").getDouble("lat"));
            ruta.setLongFinal(leg.getJSONObject("end_location").getDouble("lng"));
            ruta.setDireccionInicio(leg.getString("start_address"));
            ruta.setDireccionFinal(leg.getString("end_address"));
            ruta.setValue(leg.getJSONObject("distance").getInt("value"));

            puntoinicioLong = ruta.getLongInicio()+"";
            puntofinalLong = ruta.getLongFinal()+"";
            puntoinicioLat = ruta.getLatInicio()+"";
            puntofinalLat = ruta.getLatFinal()+"";
            kilometros = ruta.getLength();

            LatLng direccionInicio = new LatLng(ruta.getLatInicio(),ruta.getLongInicio());
            LatLng direccionFinal = new LatLng(ruta.getLatFinal(),ruta.getLongFinal());

            addMarket(direccionInicio,"Desde: "+ ruta.getDireccionInicio());
            addMarket(direccionFinal,"Hasta: "+ruta.getDireccionFinal());
            rellenarDistancia(ruta);

            android.util.Log.i("distancia66", ruta.getDistance() + "-" + ruta.getDuration()+"-"+ruta.getLatInicio());
            //Get any warnings provided (tos requirement)
             /*
            if (!jsonRoute.getJSONArray("warnings").isNull(0)) {
                Route_TO.setWarning(jsonRoute.getJSONArray("warnings").getString(0));
            }*/
            /* Loop through the steps, creating a segment for each one and
             * decoding any polylines found as we go to add to the route object's
             * map array. Using an explicit for loop because it is faster!
             */
        /*    for (int i = 0; i < numSteps; i++) {
                //Get the individual step
                final JSONObject step = steps.getJSONObject(i);
                //Get the start position for this step and set it on the segment
                final JSONObject start = step.getJSONObject("start_location");
                final LatLng position = new LatLng(start.getDouble("lat"), start.getDouble("lng"));
                segment.setPoint(position);
                //Set the length of this segment in metres
                final int length = step.getJSONObject("distance").getInt("value");
                distance += length;
                segment.setLength(length);
                segment.setDistance(distance/1000);
                //Strip html from google directions and set as turn instruction
                segment.setInstruction(step.getString("html_instructions").replaceAll("<(.*?)*>", ""));
                //Retrieve & decode this segment's polyline and add it to the route.
                route.addPoints(decodePolyLine(step.getJSONObject("polyline").getString("points")));
                //Push a copy of the segment to the route
                route.addSegment(segment.copy());
            }*/
        } catch (JSONException e) {
            android.util.Log.e(e.getMessage(), "Google JSON Parser - ");
        }

    }

    public static void rellenarDistancia(Route_TO ruta){

        unidad = (ruta.getValue()/100);

        tvDistancia.setText("Distancia: " + ruta.getDistance() + " / Duración: " + ruta.getDuration() + " / Unidades: " + unidad);
    }

    /**
     * @param position
     * @param label
     */
    private static void addMarket(LatLng position, String label) {

        Log.i("mapa", position + "-" + label);
        mMap.addMarker(new MarkerOptions().position(position).title(label));
    }

    /**
     * Responde al evento OnClick del Boton Aceptar
     */
    private void enviarPeticion() {

        Intent intent = new Intent(Parte4.this, Parte5.class);
        intent.putExtra("cc", cc);
        intent.putExtra("kilometros", kilometros);
        intent.putExtra("servicio", servicio);
        startActivity(intent);
    }

    private void ConsultarLugarInicio(){

        int idLugar = userLocalStore.getLoggedInUser().getIdLugar().getIdLugares();

        Log.i("idlugar: ", idLugar + "");

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        ConsultarLugar servicio = restAdapter.create(ConsultarLugar.class);

        servicio.consultarLugar(idLugar, new Callback<Lugares_TO>() {
            @Override
            public void success(Lugares_TO lugar, Response response) {

                if (lugar.getIdLugares() > 0) {
                    //   tvConvenio.setText("Tiene convenio con " + usuario.getNombre());
                    Log.i("LatLong ", lugar.getLatitud() + " - " + lugar.getLongitud());
                    Log.i("Descripcion: ", lugar.getNombre());

                    autocomplete_places_desde.setText(lugar.getNombre());

                    lugarInicio = lugar.getDescripcion();

                }

            }

            @Override
            public void failure(RetrofitError retrofitError) {

                Log.i("Error: ", retrofitError.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (userLocalStore.getUserLoggedIn() == true) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_main2, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerrar_Sesion) {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            startActivity(new Intent(this, MainActivity.class));
        }

        if (id == R.id.iniciar_Sesion) {

            startActivity(new Intent(this, InicioSesion.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
