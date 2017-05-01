package com.goldenie.devs.clinics_catalog.utils;

import android.content.Context;

import com.goldenie.devs.clinics_catalog.services.model.Pharmacy;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MapClusterRenderer extends DefaultClusterRenderer<Pharmacy.MapModel> {

    public MapClusterRenderer(Context context, GoogleMap map, ClusterManager<Pharmacy.MapModel> clusterManager) {
        super(context, map, clusterManager);
    }
    @Override
    protected void onBeforeClusterItemRendered(Pharmacy.MapModel item, MarkerOptions markerOptions) {
        markerOptions.position(item.getPosition())
                .title(item.getTitle());
    }
}