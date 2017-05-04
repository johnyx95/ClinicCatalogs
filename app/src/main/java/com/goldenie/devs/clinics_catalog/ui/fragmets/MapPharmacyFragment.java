package com.goldenie.devs.clinics_catalog.ui.fragmets;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.goldenie.devs.clinics_catalog.CatalogApplication;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.event.LocationDisabledEvent;
import com.goldenie.devs.clinics_catalog.services.model.Pharmacy;
import com.goldenie.devs.clinics_catalog.services.model.response.PharmacyResponse;
import com.goldenie.devs.clinics_catalog.services.webservice.PharmacyWebService;
import com.goldenie.devs.clinics_catalog.utils.MapClusterRenderer;
import com.goldenie.devs.clinics_catalog.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import lombok.Getter;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kobec on 01.05.2017.
 */

public class MapPharmacyFragment extends BaseFragment implements OnMapReadyCallback, ClusterManager.OnClusterItemClickListener<Pharmacy.MapModel> {

    private static final String TAG = MapPharmacyFragment.class.getClass().getName();

    private ArrayList<Pharmacy> pharmacyArrayList = new ArrayList<>();

    public static MapPharmacyFragment newInstance() {

        Bundle args = new Bundle();

        MapPharmacyFragment fragment = new MapPharmacyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    protected LocationManager locationManager;
    @Inject
    protected PharmacyWebService pharmacyWebService;

    @Getter
    private boolean mapReady = false;

    private Location location;
    private ClusterManager<Pharmacy.MapModel> mClusterManager;

    @Nullable
    private GoogleMap googleMap;

    @Override
    protected int getContentView() {
        return R.layout.map_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CatalogApplication.appComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPlaces();
    }

    private void loadPharmacy(double longitude, double latitude) {
        showProgressDialog();
        pharmacyWebService.getPharmacyGeo(latitude, longitude)
                .retry(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PharmacyResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Instructions complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onNext(PharmacyResponse pharmacyResponse) {
                        hideProgressDialog();
                        if (!pharmacyResponse.getPharmacy().isEmpty()) {
                            pharmacyArrayList.addAll(pharmacyResponse.getPharmacy());
                            dropPlaces();
                        }
                    }
                });
    }

    private void loadPlaces() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (Utils.ifLocationEnabled(locationManager, getActivity())) {
                if (location == null) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                        @SuppressWarnings("MissingPermission")
                        @Override
                        public void onLocationChanged(Location location) {
                            if (location != null) {
                                loadPharmacy(location.getLatitude(), location.getLongitude());
                            } else {
                                Toast.makeText(getActivity(), R.string.we_cant_detect_your_location, Toast.LENGTH_SHORT).show();
                            }
                            locationManager.removeUpdates(this);
                            Log.i(TAG, "Provider changed");
                            Log.i(TAG, "Location changed");
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                            Log.i(TAG, "Provider changed");
                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                            Log.i(TAG, "Provider enabled");
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                            Toast.makeText(getActivity(), R.string.we_cant_detect_your_location, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Provider disabled");
                        }
                    });
                    Toast.makeText(getActivity(), R.string.we_cant_detect_your_location, Toast.LENGTH_SHORT).show();
                    return;
                }
                loadPharmacy(location.getLongitude(), location.getLatitude());
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        loadPlaces();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressWarnings("UnusedParameters")
    @Subscribe
    public void onEvent(LocationDisabledEvent event) {
        Toast.makeText(getActivity(), R.string.we_cant_detect_your_location, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        mClusterManager = new ClusterManager<>(getActivity(), googleMap);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setRenderer(new MapClusterRenderer(getActivity(), googleMap, mClusterManager));
        googleMap.setOnCameraChangeListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);

    }

    private void dropPlaces() {
        if (googleMap != null) {
            googleMap.clear();

            googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                            new LatLng(location.getLatitude(),
                                    location.getLongitude()), 13));
            googleMap.addCircle(new CircleOptions()
                    .center(new LatLng(location.getLatitude(), location.getLongitude()))
                    .radius(2000)
                    .strokeColor(Color.parseColor("#90FFC107"))
                    .fillColor(Color.parseColor("#2000BCD4")));

            Observable.from(pharmacyArrayList)
                    .map(Pharmacy::getLocationModel)
                    .toList()
                    .subscribe(m -> mClusterManager.addItems(m));

            mClusterManager.cluster();
        }
    }

    @Override
    public boolean onClusterItemClick(Pharmacy.MapModel mapModel) {

        Pharmacy pharmacy = Observable.from(pharmacyArrayList)
                .filter(i -> i.getId().equals(mapModel.getId()))
                .toBlocking()
                .first();

        BottomSheetDialogFragment bottomSheetDialogFragment = PharmacyBottomSheet.newInstance(pharmacy);
        bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getTag());

        return true;
    }
}
