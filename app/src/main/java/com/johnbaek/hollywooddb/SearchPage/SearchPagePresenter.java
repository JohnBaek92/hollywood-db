package com.johnbaek.hollywooddb.SearchPage;

import com.johnbaek.hollywooddb.Database.DatabaseInitializer;
import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Response;

public class SearchPagePresenter implements SearchPageContract.Presenter {

    private SearchPageContract.View view;
    private String searchSubject;
    private SearchPageContract.Model model;
    private static String NO_RESULTS = "No Results";
    private List<Favorites> favorites;

    public SearchPagePresenter(SearchActivity view) {
        this.view = view;
        this.searchSubject = "";
        this.model = new SearchPageModel(this);
    }

    @Override
    public void fetchResults(String searchSubject) {
        view.clearData();
        model.retrieveResults(searchSubject);
    }

    public String getSearchSubject() {
        return this.searchSubject;
    }

    public void setSearchSubject(String searchSubject) {
        this.searchSubject = searchSubject;
        view.displaySearchResultText(searchSubject);
    }

    public void onSearchResultsRetrievedSuccessful(Response<SearchListings> response) {
        SearchListings unformattedResults = response.body();
        ArrayList<SearchItem> searchItems = unformattedResults.getSearchItemListings();
        if (searchItems.size() >= 1) {
            new DatabaseInitializer.AsyncGetFavorites(favorites -> {
                HashSet<String> hashFavoriteNames = new HashSet<>();
                for (Favorites favorite : favorites) {
                    String identifier = favorite.getIdentifier();
                    hashFavoriteNames.add(identifier);
                }
                for (SearchItem searchItem : searchItems) {
                    String identifier = searchItem.getHollywoodName() != null ? searchItem.getHollywoodName() : searchItem.getHollywoodTitle();
                    searchItem.setFavorite(hashFavoriteNames.contains(identifier));
                }
                view.displayResults(searchItems);
            }).execute();
        } else {
            Util.showToastMessage(NO_RESULTS);
        }
    }

    public void onSearchResultsRetrievedFailed(Throwable throwable) {
        Util.showToastMessage(throwable.getMessage());
    }
}
