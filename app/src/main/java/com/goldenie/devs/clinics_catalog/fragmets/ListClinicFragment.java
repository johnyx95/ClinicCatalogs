package com.goldenie.devs.clinics_catalog.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;

import com.goldenie.devs.clinics_catalog.CatalogApplication;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.response.ClinicSearchResponse;
import com.goldenie.devs.clinics_catalog.services.webservice.CatalogWebService;
import com.goldenie.devs.clinics_catalog.ui.adapter.ClinicListAdapter;
import com.paginate.Paginate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnEditorAction;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kobec on 03.05.2017.
 */

public class ListClinicFragment extends BaseFragment{

    @BindView(R.id.search_field_clinic)
    AppCompatEditText searchFieldClinic;
    @BindView(R.id.no_info_layout_clinic)
    RelativeLayout noInfoLayoutClinic;
    @BindView(R.id.list_clinic)
    RecyclerView listClinic;

    @Inject
    protected CatalogWebService catalogWebService;
    private ClinicListAdapter clinicListAdapter;
    private boolean isLoading = false;
    private Integer lastPage = 1;
    private boolean isLastPage = false;

    private Integer searchQuerry = null;
    private Integer searchQuerry1 = null;

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
                loadClinicList(searchQuerry, searchQuerry1, lastPage, false);
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
                        if (showDialog)
                            hideProgressDialog();

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
                    }
                });
    }

    @OnEditorAction(R.id.search_field_clinic)
    protected boolean onSendSearchRequest(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            lastPage = 1;

  //          searchQuerry = searchFieldClinic.getText().toString();
   //         loadClinicList(searchQuerry, lastPage, true);
            return true;
        }

        return false;
    }
}
