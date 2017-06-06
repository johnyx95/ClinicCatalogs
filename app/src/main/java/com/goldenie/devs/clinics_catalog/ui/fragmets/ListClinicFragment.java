package com.goldenie.devs.clinics_catalog.ui.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.goldenie.devs.clinics_catalog.CatalogApplication;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.response.ClinicSearchResponse;
import com.goldenie.devs.clinics_catalog.services.model.response.DistrictResponse;
import com.goldenie.devs.clinics_catalog.services.model.response.ServicesResponse;
import com.goldenie.devs.clinics_catalog.services.webservice.CatalogWebService;
import com.goldenie.devs.clinics_catalog.ui.adapter.ClinicListAdapter;
import com.goldenie.devs.clinics_catalog.ui.view.CustomSpinner;
import com.paginate.Paginate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kobec on 03.05.2017.
 */

public class ListClinicFragment extends BaseFragment {

    @BindView(R.id.no_info_layout_clinic)
    RelativeLayout noInfoLayoutClinic;
    @BindView(R.id.list_clinic)
    RecyclerView listClinic;

    @Inject
    protected CatalogWebService catalogWebService;
    @BindView(R.id.spinnerDis)
    CustomSpinner spinnerDis;
    @BindView(R.id.spinnerService)
    CustomSpinner spinnerService;
    Unbinder unbinder;
    private ClinicListAdapter clinicListAdapter;
    private boolean isLoading = false;
    private Integer lastPage = 1;
    private boolean isLastPage = false;

    private Integer districtId = null;
    private Integer serviceId = null;

    @Override
    protected int getContentView() {
        return R.layout.list_clinic;
    }

    public static ListClinicFragment newInstance() {

        Bundle args = new Bundle();

        ListClinicFragment fragment = new ListClinicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CatalogApplication.appComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clinicListAdapter = new ClinicListAdapter();
        listClinic.setAdapter(clinicListAdapter);
        listClinic.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadClinicList(null, null, lastPage, false);

        applyPaging();
    }

    private void applyPaging() {
        Paginate.with(listClinic, new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                loadClinicList(districtId, serviceId, lastPage, false);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return isLastPage;
            }
        })
                .setLoadingTriggerThreshold(5)
                .addLoadingListItem(true)
                .build();
    }

    private void loadDistrict(boolean showDialog) {

        catalogWebService.getDistricts()
                .retry(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DistrictResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Instructions complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onNext(DistrictResponse districtResponse) {
                        String[] district = new String[districtResponse.getDistricts().size()+1];
                        district [0] = "Выберите район";
                        for (int i = 0; i < districtResponse.getDistricts().size(); i++) {
                            district [i+1] = districtResponse.getDistricts().get(i).getName();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, district);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Вызываем адаптер
                        spinnerDis.setAdapter(adapter);
                        spinnerDis.setSelection(0);
                        spinnerDis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                districtId = position;
                                lastPage = 1;
                                loadClinicList(districtId,serviceId,lastPage,true);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                                districtId = null;

                            }
                        });
                        loadService(showDialog);
                    }
                });
    }

    private void loadService(boolean showDialog) {

        catalogWebService.getServices()
                .retry(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServicesResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Instructions complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onNext(ServicesResponse servicesResponse) {
                        String[] service = new String[servicesResponse.getSrvices().size()+1];
                        service [0] = "Выберите услугу";
                        for (int i = 0; i <servicesResponse.getSrvices().size(); i++) {
                            service[i+1] = servicesResponse.getSrvices().get(i).getName();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, service);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Вызываем адаптер
                        spinnerService.setAdapter(adapter);
                        spinnerService.setSelection(0);
                        spinnerService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                serviceId = position;
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                                serviceId = null;
                            }

                        });
                        if (showDialog)
                            hideProgressDialog();

                    }
                });
    }

    private void loadClinicList(Integer service, Integer district, Integer lastPage, boolean showDialog) {
        isLoading = true;

        if (showDialog)
            showProgressDialog();

        catalogWebService.getClinicForServicesDistricts(service, district, lastPage)
                .retry(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClinicSearchResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Instructions complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onNext(ClinicSearchResponse clinicSearchResponse) {


                        if (service != null && lastPage == 1)
                            clinicListAdapter.clear();

                        isLoading = false;

                        if (!clinicSearchResponse.getClinics().isEmpty()) {
                            noInfoLayoutClinic.setVisibility(View.GONE);
                        } else {
                            noInfoLayoutClinic.setVisibility(View.VISIBLE);
                        }

                        isLastPage = clinicSearchResponse.getClinics().size() < 20;

                        clinicListAdapter.addData(clinicSearchResponse.getClinics());
                        ListClinicFragment.this.lastPage++;
                        if(lastPage != 1){
                            if(showDialog)
                                hideProgressDialog();
                       }else
                        loadDistrict(showDialog);

                    }
                });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
