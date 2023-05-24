'use strict';

const url = 'https://httpbin.org/get';

console.log('hello 1');

asyncGetFetch(url);
syncGetFetch(url)
.then(res => console.log('2 IP: ' + res.origin))
.catch(err => console.log('Fuck!' + err));

console.log('hello 2');

// асинхронная ф-ия
function asyncGetFetch(url) {
    fetch(url)                                          // url
        .then(response => response.json())              // ответ в JSON ковертируем в объект
        .then(obj => console.log('1 IP: ' + obj.origin))  // обрабатываем дальше наш объект
        .catch(err => console.log('Fuck!' + err));      // в случае ошибки
}

// синхронная ф-я
async function syncGetFetch(url) {
    const responce = await fetch(url);
    if(!responce.ok) {
        throw new Error('Cant fetch loadOldBlank');
    }
    return await responce.json();
}
