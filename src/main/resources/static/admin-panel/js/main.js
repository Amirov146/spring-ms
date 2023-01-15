/*=============== LINK ACTIVE ===============*/
const linkColor = document.querySelectorAll('.nav__link')

function colorLink(){
    linkColor.forEach(l => l.classList.remove('active-link'))
    this.classList.add('active-link')
}

linkColor.forEach(l => l.addEventListener('click', colorLink))

/*=============== SHOW HIDDEN MENU ===============*/
const showMenu = (toggleId, navbarId) =>{
    const toggle = document.getElementById(toggleId),
    navbar = document.getElementById(navbarId)

    if(toggle && navbar){
        toggle.addEventListener('click', ()=>{
            /* Show menu */
            navbar.classList.toggle('show-menu')
            /* Rotate toggle icon */
            toggle.classList.toggle('rotate-icon')
        })
    }
}
showMenu('nav-toggle','nav')


function findMovieById() {
    var movieId = $('#movieIdInput').val();
    $.ajax({
        url: '/movie-title?id=' + movieId,
        type: 'GET',
        success: function(data) {
            $('#movieID').text('ID: ' + data.id);
            $('#movieTitle').text('Title: ' + data.title);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status == 404) {
                console.log("Movie not found");
            } else {
                console.log(textStatus, errorThrown);
            }
        }
    });
}

function findUserById() {
    var userId = $('#userIdInput').val();
    $.ajax({
        url: '/username?id=' + userId,
        type: 'GET',
        success: function(data) {
            $('#userID').text('ID: ' + userId);
            $('#username').text('Username: ' + data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status == 404) {
                console.log("User not found");
            } else {
                console.log(textStatus, errorThrown);
            }
        }
    });
}