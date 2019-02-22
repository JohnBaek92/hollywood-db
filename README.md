# The Hollywood DB

The Hollywood DB is an Android application where users can search for all things Hollywood, encompassing TV shows, people, and movies.
This application is built in Java using the Model View Presenter architecture. The app pulls its data from The Movie DB for all the latest information.

### Landing Page

Upon opening the app, users will be able to search for a particular topic using the categorized search in the middle of the landing page, enter the browse page, or view their favorites.

### Browse Page

If the user clicks “Browse Hollywood DB" they will be taken to a screen which will show movie results as “Top Rated Movies”, “Upcoming Movies”and, “Now playing movies” in a horizontal scrolling fashion.

### Search Page

This is the activity that opens when using the categorized search on the landing page. Unless the user has specified to search for a movie, TV show, or actor, all results for the search term will be displayed. A label for the search will also be displayed on this page. For movie and TV show results, the search page will render the title, the poster, and the average rating. For people, the full name and an image of the person will be shown.

### Detail Page

This page opens when clicking on a result from either the browse page or the search page. For "people" results, this page will show an image, full name, and details of the person. For "TV shows" and "movie", the title, poster, synopsis, trailer (if available), and average rating will be displayed.

### Favorites

Users will see hearts on results when visiting the search or detail page. Tapping on a heart will add the result to the users favorites list. This information is stored locally using Room.
