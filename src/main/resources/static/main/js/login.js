window.onload = function() {
    let preloader = document.getElementById('preloader');
    preloader.classList.add('hide-preloader');
    setInterval(function() {
          preloader.classList.add('preloader-hidden');
    }, 990);
}


let images = ['main/images/image1.jpg', 'main/images/image2.jpg', 'main/images/image3.jpg']



// let images = ['main/images/image1.jpg', 'main/images/image2.jpg'];


let index = 0;
const imgElement = document.querySelector('#mainPhoto');

function change() {
   imgElement.src = images[index];
   index > 1 ? index = 0 : index++;
}
    setInterval(change, 3000);


