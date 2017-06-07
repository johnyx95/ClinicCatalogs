package com.goldenie.devs.clinics_catalog.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.goldenie.devs.clinics_catalog.CatalogApplication;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.response.ClinicResponse;
import com.goldenie.devs.clinics_catalog.services.webservice.CatalogWebService;
import com.goldenie.devs.clinics_catalog.ui.BaseActivity;
import com.goldenie.devs.clinics_catalog.ui.adapter.ImageGalleryAdapter;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kobec on 04.05.2017.
 */

public class ClinicPage extends BaseActivity {

    private Integer clinicId;
    private String name;


    @Inject
    protected CatalogWebService catalogWebService;

    @BindView(R.id.logo_full)
    AppCompatImageView logoFull;
    @BindView(R.id.name_full)
    AppCompatTextView nameFull;
    @BindView(R.id.rate)
    AppCompatTextView rate;
    @BindView(R.id.full)
    AppCompatTextView full;
    @BindView(R.id.services)
    AppCompatTextView services;
    @BindView(R.id.district_logo)
    AppCompatImageView districtLogo;
    @BindView(R.id.district)
    AppCompatTextView district;
    @BindView(R.id.logo_address)
    AppCompatImageView logoAddress;
    @BindView(R.id.address)
    AppCompatTextView address;
    @BindView(R.id.metro_logo)
    AppCompatImageView metroLogo;
    @BindView(R.id.metro)
    AppCompatTextView metro;
    @BindView(R.id.time_logo)
    AppCompatImageView timeLogo;
    @BindView(R.id.time)
    AppCompatTextView time;
    @BindView(R.id.time2)
    AppCompatTextView time2;

    @Override
    protected int getContentView() {
        return R.layout.clinic_page_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CatalogApplication.appComponent().inject(this);
        Intent intent = getIntent();
        clinicId = intent.getIntExtra("ClinicId",0);
        name = intent.getStringExtra("clinicName");
        ButterKnife.bind(this);
        loadClinicInfo(clinicId,false);
        setTitle(name);

    }


    private void getClinicInfo(ClinicResponse clinicResponse){

        String[] service = new String[clinicResponse.getServices().size()];

        Glide.with(this).load(clinicResponse.getClinic().getLogoUrl()).into(logoFull);
        nameFull.setText(clinicResponse.getClinic().getName());
        rate.setText(Integer.toString(clinicResponse.getClinic().getRating()));
        full.setText(android.text.Html.fromHtml(clinicResponse.getClinic().getShortDescription()).toString());
        for (int i = 0; i < clinicResponse.getServices().size(); i++) {
            service[i] = clinicResponse.getServices().get(i).getName();
        }
        services.setText("Услуги: " + Arrays.toString(service).replaceAll("\\[|\\]", ""));
        district.setText(clinicResponse.getDistrict().getName());
        address.setText(clinicResponse.getLocation().getAddress());
        metro.setText(clinicResponse.getMetro().getName());
        if(!clinicResponse.getWorktime().isEmpty()) {
            time.setText(clinicResponse.getWorktime().get(0).getDayInterval() + ":  " + clinicResponse.getWorktime().get(0).getTimeInterval());
            if(clinicResponse.getWorktime().size()>1) {
                time2.setText(clinicResponse.getWorktime().get(1).getDayInterval() + ":  " + clinicResponse.getWorktime().get(1).getTimeInterval());
            }
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ImageGalleryAdapter adapter = new ImageGalleryAdapter(this, clinicResponse.getGallery());
        recyclerView.setAdapter(adapter);
    }


    private void loadClinicInfo(Integer idClinic, boolean showDialog) {


        if (showDialog)
            showProgressDialog();

        catalogWebService.getClinicFullInfo(idClinic)
                .retry(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClinicResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Instructions complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onNext(ClinicResponse clinicResponse) {

                        getClinicInfo(clinicResponse);

                        if (showDialog)
                            hideProgressDialog();

                    }
                });
    }


}
