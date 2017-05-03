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
import com.goldenie.devs.clinics_catalog.services.model.response.PharmacyResponse;
import com.goldenie.devs.clinics_catalog.services.webservice.PharmacyWebService;
import com.goldenie.devs.clinics_catalog.ui.adapter.PharmacyListAdapter;
import com.paginate.Paginate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnEditorAction;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kobec on 01.05.2017.
 */

public class ListPharmacyFragment extends BaseFragment {
    @Inject
    protected PharmacyWebService pharmacyWebService;

    @BindView(R.id.search_field)
    AppCompatEditText searchField;
    @BindView(R.id.no_info_layout)
    RelativeLayout noInfoLayout;
    @BindView(R.id.list)
    RecyclerView list;
    private PharmacyListAdapter pharmacyListAdapter;

    private boolean isLoading = false;
    private Integer lastPage = 1;
    private boolean isLastPage = false;

    private String searchQuerry = null;

    @Override
    protected int getContentView() {
        return R.layout.list_pharmacy;
    }

    public static ListPharmacyFragment newInstance() {

        Bundle args = new Bundle();

        ListPharmacyFragment fragment = new ListPharmacyFragment();
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
        pharmacyListAdapter = new PharmacyListAdapter();
        list.setAdapter(pharmacyListAdapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadPharmacyList(null, lastPage, false);

        applyPaging();
    }

    private void applyPaging() {
        Paginate.with(list, new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                loadPharmacyList(searchQuerry, lastPage, false);
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

    private void loadPharmacyList(String name, Integer lastPage, boolean showDialog) {
        isLoading = true;

        if (showDialog)
            showProgressDialog();

        pharmacyWebService.getPharmacy(name, lastPage)
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
                        if (showDialog)
                            hideProgressDialog();

                        if (name != null && lastPage == 1)
                            pharmacyListAdapter.clear();

                        isLoading = false;

                        if (!pharmacyResponse.getPharmacy().isEmpty()) {
                            noInfoLayout.setVisibility(View.GONE);
                        } else {
                            noInfoLayout.setVisibility(View.VISIBLE);
                        }

                        isLastPage = pharmacyResponse.getPharmacy().size() < 20;

                        pharmacyListAdapter.addData(pharmacyResponse.getPharmacy());
                        ListPharmacyFragment.this.lastPage++;
                    }
                });
    }

    @OnEditorAction(R.id.search_field)
    protected boolean onSendSearchRequest(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            lastPage = 1;

            searchQuerry = searchField.getText().toString();
            loadPharmacyList(searchQuerry, lastPage, true);
            return true;
        }

        return false;
    }
}

