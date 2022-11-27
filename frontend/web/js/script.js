console.log("hello")

let bodyElement = document.body;

console.log("firstElementChild:");
console.log(bodyElement.firstElementChild)

console.log("hasChildNodes:");
console.log(bodyElement.hasChildNodes());

if(document.hasChildNodes()) {
    for (let node of bodyElement.childNodes) {
        console.log(node);
    }
}

if(document.hasChildNodes()) {
    for (let node of bodyElement.children) {
        console.log(node);
    }
}