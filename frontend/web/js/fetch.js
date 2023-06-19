'use strict';

const url = 'https://httpbin.org/get';

console.log('hello 1');

getFetch(url);

console.log('hello 2');


async function getFetch(url) {
    const responce = await fetch(url);
    const data = await responce.json();
    console.log(data.origin);
}

let body = document.body;
while(body.firstChild) {
    body.removeChild(body.firstChild);
}
let img = document.createElement('img');
img.style.width = '600px';
img.src = 'https://www.eminem'+'.pro/wp-content/uploads/2014/10/1081966131.jpg'
body.append(img);











function selectAuthor() {
    let select = document.getElementById("select");
    // let value = select.value;
    let selectedOption = select.selectedOptions[0];
    console.log(selectedOption.value);
    console.log(selectedOption.text);
    // let tableAuthor = document.getElementById('tableAuthor');
    // let nameTr = document.createElement('tr');
    // tableAuthor.append(nameTr);
    // let nameTd = document.createElement('td');
    // nameTd.classList.add('text-primary');
    // nameTd.textContent = value;
    // nameTr.append(nameTd);
    // let checkTd = document.createElement('td');
    // nameTr.append(checkTd);
    // let radioBtn = document.createElement('input');
    // radioBtn.setAttribute('type','radio');
    // radioBtn.setAttribute('name','flexRadioDefault');
    // radioBtn.classList.add('form-check-input');
    // checkTd.append(radioBtn);
    // let removeTd = document.createElement('td');
    // nameTr.append(removeTd);
    // let close = document.createElement('i');
    // close.classList.add('fas', 'fa-times', 'text-danger', 'ml-10','mt-m5');
    // close.setAttribute('onclick', 'removeTr(this)');
    // checkTd.append(close);

  }










