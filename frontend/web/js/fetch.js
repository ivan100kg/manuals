'use strict';

const url = 'https://httpbin.org/get';

console.log('hello 1');

// asyncGetFetch(url);
// syncGetFetch(url);

console.log('hello 2');

function asyncGetFetch(url) {
    fetch(url)                                          // url
        .then(response => response.json())              // ответ в JSON ковертируем в объект
        .then(obj => console.log('async ' + obj.origin))  // обрабатываем дальше наш объект
        .catch(err => console.log('Fuck!' + err));      // в случае ошибки
}

async function syncGetFetch(url) {
    const responce = await fetch(url);
    const data = await responce.json();
    console.log('sync ' + data);
}
