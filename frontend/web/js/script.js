console.log("hello")

// body tag
let bodyElement = document.body;

// first element
console.log("firstElementChild:");
console.log(bodyElement.firstElementChild)

// has nodes
console.log("hasChildNodes:");
console.log(bodyElement.hasChildNodes());

// all nodes
if(document.hasChildNodes()) {
    for (let node of bodyElement.childNodes) {
        console.log(node);
    }
}

// all elemets
if(document.hasChildNodes()) {
    for (let node of bodyElement.children) {
        console.log(node);
    }
}

// поиск по class
const icons = document.querySelectorAll('.item-about__icon');
console.log(icons);

icons.forEach(element => {
    console.log(element);
});

// поиск по тэгу
const images = document.querySelectorAll('img');
console.log(images);

if (images.length > 0){
    for (let img of images) {
        console.log(img.currentSrc);
    }
}

// изменение элементов
const mainText = document.querySelector('.training__text');
const elementText = mainText.innerHTML;
console.log(elementText);
mainText.innerHTML =
    `<p>${elementText}</p>
    <p><span class="yellow">FUUUUCK!!!</span></p>`;

const elementObject = mainText.outerHTML;
mainText.outerHTML = `<p>Deleted</p>`
console.log(mainText.outerHTML);