package com.gold.InterviewTest.contoller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gold.InterviewTest.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.ui.IconGenerator;
import com.gold.InterviewTest.model.IDataCallback;
import com.gold.InterviewTest.model.PSIModel;
import com.gold.InterviewTest.model.data.PSIData;
import com.gold.InterviewTest.model.data.PSILocation;
import com.gold.InterviewTest.model.data.PSIReadings;
import com.gold.InterviewTest.model.data.PSIRegionMetaData;
import com.gold.InterviewTest.util.Util;
import java.util.List;


import static android.content.ContentValues.TAG;

public class GoogleMapController implements OnMapReadyCallback {

    private final Context context;
    private final LatLng singapore = new LatLng(1.35735, 103.82);
    private GoogleMap googleMap;
    private ProgressDialog progressDialog;
    final private PSIModel psiModel = new PSIModel();
    private PSIData psiData;

    public GoogleMapController(final Context context, final SupportMapFragment mapFragment) {
        this.context = context;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(false);
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(

                    MapStyleOptions.loadRawResourceStyle(
                            context, R.raw.styles));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");


            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                LinearLayout info = new LinearLayout(context.getApplicationContext());
                info.setOrientation(LinearLayout.VERTICAL);
                TextView title = new TextView(context.getApplicationContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.LEFT);
                title.setPadding(20, 0, 20, 0);
                title.setText(Html.fromHtml(marker.getTitle()));
                title.setLineSpacing(20.0f, 1.0f);

                TextView snippet = new TextView(context.getApplicationContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);
                return info;
            }
        });
        checkConnection();
    }

    public void showMap() {
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore, 10.5f));
            googleMap.clear();
            ClusterManager clusterManager = new ClusterManager<>(context, googleMap);
            googleMap.setOnCameraIdleListener(clusterManager);

            if (psiData != null) {
                PSIReadings psiReadings = psiData.getItems().get(psiData.getItems().size() - 1).getReadings();

                String psiTwentyFourHourlyLabel = "PSI 24 Hourly";
                String pm10SubIndexLabel = "PM<sub><small>10</small></sub> Sub Index";
                String pm10TwentyFourHourlyLabel = "PM<sub><small>10</small></sub> 24 Hourly";
                String pm25SubIndexLabel = "PM<sub><small>2.5</small></sub> Sub Index";
                String pm25TwentyFourHourlyLabel = "PM<sub><small>2.5</small></sub> 24 Hourly";
                String coSubIndexLabel = "CO Sub Index";
                String coEightHourMaxLabel = "CO Eight Hour Max";
                String so2SubIndexLabel = "SO<sub><small>2</small></sub> Sub Index";
                String so2TwentyFourHourlyLabel = "SO<sub><small>2</small></sub> 24 Hourly";
                String no2OneHourMaxLabel = "NO<sub><small>2</small></sub> 1 Hourly Max";
                String o3SubIndexLabel = "O<sub><small>3</small></sub> Sub Index";
                String o3EightHourMaxLabel = "O<sub><small>3</small></sub> 8 Hour Max";

                List<PSIRegionMetaData> regionMetaDataList = psiData.getRegionMetaDatas();

                for (int i = 0; i < regionMetaDataList.size(); i++) {
                    PSILocation location = regionMetaDataList.get(i).getLocation();
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    String name = regionMetaDataList.get(i).getName();
                    IconGenerator iconGenerator = new IconGenerator(context);
                    Util mapUtils = new Util();
                    Bitmap bitmap = mapUtils.GetBitmapMarker(context, R.drawable.marker, String.valueOf(psiReadings.getPsiTwentyFourHourly().getReadingItemByPath(name)));
                    String titleLabel =
                            psiTwentyFourHourlyLabel + ": " + String.valueOf(psiReadings.getPsiTwentyFourHourly().getReadingItemByPath(name)) + "<br>" +
                                    pm10SubIndexLabel + ": " + String.valueOf(psiReadings.getPm10SubIndex().getReadingItemByPath(name)) + "<br>" +
                                    pm10TwentyFourHourlyLabel + ": " + String.valueOf(psiReadings.getPm10TwentyFourHourly().getReadingItemByPath(name)) + "<br>" +
                                    pm25SubIndexLabel + ": " + String.valueOf(psiReadings.getPm25SubIndex().getReadingItemByPath(name)) + "<br>" +
                                    pm25TwentyFourHourlyLabel + ": " + String.valueOf(psiReadings.getPm25TwentyFourHourly().getReadingItemByPath(name)) + "<br>" +
                                    coSubIndexLabel + ": " + String.valueOf(psiReadings.getCoSubIndex().getReadingItemByPath(name)) + "<br>" +
                                    coEightHourMaxLabel + ": " + String.valueOf(psiReadings.getCoEightHourMax().getReadingItemByPath(name)) + "<br>" +
                                    so2SubIndexLabel + ": " + String.valueOf(psiReadings.getSo2SubIndex().getReadingItemByPath(name)) + "<br>" +
                                    so2TwentyFourHourlyLabel + ": " + String.valueOf(psiReadings.getSo2TwentyFourHourly().getReadingItemByPath(name)) + "<br>" +
                                    no2OneHourMaxLabel + ": " + String.valueOf(psiReadings.getNo2OneHourMax().getReadingItemByPath(name)) + "<br>" +
                                    o3SubIndexLabel + ": " + String.valueOf(psiReadings.getO3SubIndex().getReadingItemByPath(name)) + "<br>" +
                                    o3EightHourMaxLabel + ": " + String.valueOf(psiReadings.getO3EightHourMax().getReadingItemByPath(name));

                    googleMap.addMarker(
                            new MarkerOptions()
                                    .position(latLng)
                                    .snippet(name)
                                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                    .title(titleLabel)
                    );
                }
                clusterManager.cluster();
            }
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void checkConnection() {
        if (isNetworkConnected()) {
            displayProgressDialog();
            retrievePSIData();
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("No Internet Connection")
                    .setMessage("It looks like your internet connection is off. Please turn it on and try again")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkConnection();
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    private void displayProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

    private void retrievePSIData() {
        psiModel.getPSI(new IDataCallback<PSIData>() {
            @Override
            public void success(PSIData data) {
                psiData = data;

                System.out.println("data from api"+data);
                hideProgressDialog();
                showMap();
            }

            @Override
            public void error(Exception error) {
                hideProgressDialog();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
