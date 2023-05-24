"use strict";

console.log("hello");

// body tag
let bodyElement = document.body;

// first element
console.log("firstElementChild:");
console.log(bodyElement.firstElementChild);

// has nodes
console.log("hasChildNodes:");
console.log(bodyElement.hasChildNodes());

// all nodes
if (document.hasChildNodes()) {
    for (let node of bodyElement.childNodes) {
        console.log(node);
    }
}

// all elemets
if (document.hasChildNodes()) {
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

if (images.length > 0) {
    for (let img of images) {
        console.log(img.currentSrc);
    }
}

// изменение элементов
const mainText = document.querySelector('.training__text');
const elementIText = mainText.innerHTML;
console.log(elementIText);
mainText.innerHTML =
    `<p>${elementIText}</p>
    <p><span class="yellow">FUUUUCK!!!</span></p>`;

// const elementOText = mainText.outerHTML;
// mainText.outerHTML = `<p>Deleted</p>`
// console.log(mainText.outerHTML);

const elementText = mainText.textContent;
mainText.textContent = `<p>hui</p>`;
console.log(mainText);

const getComment = mainText.nextSibling;
console.log(getComment);
console.log(getComment.data);
getComment.data = "fuck!";

// create element
const newElement = document.createElement('div');
console.log(newElement);
newElement.innerHTML = `Hello <span class="yellow">blya</span> !!!`;

const newText = document.createTextNode("Fuck you!");
console.log(newText);

mainText.before(newElement);
mainText.after(newText);
// mainText.prepend(newElement);
// mainText.append(newText);

mainText.insertAdjacentHTML(`afterbegin`, `<p>Fuck off</p>`);
mainText.insertAdjacentHTML(`afterend`, `<p>Bitch!!</p>`);

// move
const trainingHeader = document.querySelector('.training__header');
const trainingRow = document.querySelector('.training__row');

trainingHeader.before(trainingRow);

// copy
const cloneHeader = trainingHeader.cloneNode();
const cloneHeaderDeep = trainingHeader.cloneNode(true);
trainingRow.after(cloneHeaderDeep);
trainingRow.after(cloneHeader);

// remove
cloneHeaderDeep.remove();

const classNameElem = document.getElementById('123');
console.log(classNameElem.className);
console.log(classNameElem.classList);

classNameElem.className = 'item__loh';

classNameElem.classList.add('new-cls'); // добавить класс
classNameElem.classList.remove('cls');  // удалить класс
classNameElem.classList.toggle('cls');  // доб класс если его нет, если есть del
classNameElem.classList.contains('cl'); // проверка на наличие - true/false

// style
mainText.style.color = "red";
mainText.style.fontSize = "24px";

console.log(getComputedStyle(mainText));
console.log(getComputedStyle(mainText).fontFamily);

// size
const element = document.documentElement;
console.log(element);
console.log(element.clientWidth);
console.log(element.clientHeight);
console.log(window.innerWidth);
console.log(window.innerHeight);

let scrollWidth = Math.max(
    document.body.scrollWidth, document.documentElement.scrollWidth,
    document.body.offsetWidth, document.documentElement.offsetWidth,
    document.body.clientWidth, document.documentElement.clientWidth,
);
let scrollHeight = Math.max(
    document.body.scrollHeight, document.documentElement.scrollHeight,
    document.body.offsetHeight, document.documentElement.offsetHeight,
    document.body.clientHeight, document.documentElement.clientHeight,
);

console.log(scrollWidth);
console.log(scrollHeight);

const btn = document.querySelector('#btn');
console.log(btn);
btn.onclick = function setScrollBy() {
    window.scrollBy(0, 50);
    const windowScrollTop = window.pageYOffset;
    console.log(windowScrollTop);
};
function setScrollTo() {
    window.scrollBy(0, 50);
    window.scrollTo(0, 100);
}
function setScrollIntoView(top) {
    const elem = document.querySelector('.training__text');
    elem.setScrollIntoView(top);
}
function setScrollIntoViewOption(top) {
    const elem = document.querySelector('.training__text');
    elem.setScrollIntoView({
        block: "center",    // start end nearest 
        inline: "nearest",  // center start end
        behavior: "smooth"  // auto
    });
}


function promice() {

}

function main() {
    const header = document.querySelector('.training__header');
    const button = document.createElement('button');
    button.id = 'promice_btn';
    button.type = 'button';
    button.innerHTML = 'кнопка';
    header.append(button);

    button.addEventListener('click', (e, args) => {
        e.preventDefault();
        console.log(e);
        const promice = new Promise(function (resolve, reject) { // resolve - выполнилось правильно
            setTimeout(() => {
                console.log('hello');
                const obj = { name: 'loh' };                      // объект - имитация ответа с сервера
                if (false) { reject(); return; }                   // reject()
                resolve(obj);
            }, 2000);

            // <--------------------------+
        });
        promice.then((obj) => console.log('What a fuck! ' + obj.name));

    });
}

function fetchUser(id) {                        // функция возвращающая Promise
    return new Promise((resolve, reject) => {     // Promise
        setTimeout(() => {                          // имитация асинх ф-ии
            const user = { id: id, name: "John" };
            if (id <= 0) {
                reject(new Error("Invalid id"));
            } else {
                resolve(user);
            }
        }, 1000);
    });
}

fetchUser(1)
    .then((user) => console.log(user))            // в случ успеха
    .catch((err) => console.error(err))           // неудача
    .finally(() => console.log('f'))               // всегда


fetchUser(-1)
    .then((user) => console.log(user))            // в случ успеха
    .catch((err) => console.error(err))           // неудача
    .finally((f) => console.log(f))               // всегда

const test = time => new Promise(resolve => setTimeout(() => resolve(), time));
test(2000).then(() => console.log('ok!'));

fetch('https://httpbin.org/get')                // url
.then(response => response.json())              // ответ в JSON ковертируем в объект
.then(obj => console.log('IP: ' + obj.origin))  // обрабатываем дальше наш объект
.catch(err => console.log('Fuck!' + err));      // в случае ошибки

document.addEventListener('DOMContentLoaded', main);
