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

let text = document.getElementById('training_text');
console.log(text);