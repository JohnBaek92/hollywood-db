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
    private String mediaType;
    private SearchPageContract.Model model;
    private static String NO_RESULTS = "No Results";
    private List<Favorites> favorites;
    private final static String PERSON = "person";
    private final static String MOVIE = "movie";
    private final static String TV = "tv";
    private final static String ALL = "all";

    SearchPagePresenter(SearchActivity view, String searchSubject) {
        this.view = view;
        this.searchSubject = searchSubject;
        this.mediaType = "";
        this.model = new SearchPageModel(this);
        setSearchSubjectText();
    }

    public void fetchResults(){
        view.clearData();
        switch (mediaType) {
            case ALL:
                model.retrieveAllResults(searchSubject);
                break;
            case PERSON:
                model.retrievePeopleResults(searchSubject);
                break;
            case MOVIE:
                model.retrieveMovieResults(searchSubject);
                break;
            case TV:
                model.retrieveTvResults(searchSubject);
                break;
            default:
                break;
        }
    }

    private void setSearchSubjectText() {
        view.displaySearchResultText(searchSubject);
    }

    public void setMediaType(String mediaType){
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void onSearchResultsRetrievedSuccessful(ArrayList<SearchItem> searchItems) {
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
            view.showToastMessage(NO_RESULTS);
        }
    }

    public void onSearchResultsRetrievedFailed(Throwable throwable) {
        view.showToastMessage(throwable.getMessage());
    }
}
