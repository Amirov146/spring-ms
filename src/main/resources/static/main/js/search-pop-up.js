function searchMovies() {
    const searchTerm = document.querySelector('.input-search').value; // get the search term from the input field

    if (searchTerm.length > 0) { // if the search term is not empty
        // send an Ajax request to the server to search for movies by title
        const xhr = new XMLHttpRequest();
        xhr.open('GET', '/search-movies?title=' + searchTerm);
        xhr.onload = function() {
            if (xhr.status === 200) {
                // if the search was successful, display the search results in the search popup
                const movies = JSON.parse(xhr.responseText);
                const searchResults = document.querySelector('#search-results');
                searchResults.innerHTML = ''; // clear the search results
                // movies.forEach(function(movie) {
                //     const li = document.createElement('li');
                //     li.textContent = movie.title;
                //     searchResults.appendChild(li);
                // });
                movies.forEach(function(movie) {
                    const li = document.createElement('li');
                    const a = document.createElement('a');
                    a.textContent = movie.title;
                    a.style.color = 'white';
                    a.style.padding = "20px";
                    a.style.fontWeight = 'bold';
                    a.addEventListener('mouseenter', function() {
                        // Change the text color to red on mouseenter
                        a.style.color = 'darkred';
                    });
                    a.addEventListener('mouseleave', function() {
                        // Change the text color back to white on mouseleave
                        a.style.color = 'white';
                    });
                    a.href = '/movie/' + movie.id; // set the href attribute to the movie's URL
                    li.appendChild(a);
                    searchResults.appendChild(a);
                    searchResults.appendChild(document.createElement('br'));
                });
            } else {
                // if the search was unsuccessful, display an error message in the search popup
                const searchResults = document.querySelector('#search-results');
                searchResults.innerHTML = ''; // clear the search results
                const li = document.createElement('li');
                li.textContent = 'An error occurred while searching for movies';
                searchResults.appendChild(li);
            }
        };
        xhr.send();
    } else {
        // if the search term is empty, clear the search results
        const searchResults = document.querySelector('#search-results');
        searchResults.innerHTML = '';
    }
}

// function to show the search popup when the user starts typing in the search field
function SearchFunction() {
    const searchPopup = document.querySelector('#search-popup');
    searchPopup.style.display = 'block'; // show the search popup

    // search for movies after a short delay to avoid sending too many requests to the server
    setTimeout(searchMovies, 300);
}
