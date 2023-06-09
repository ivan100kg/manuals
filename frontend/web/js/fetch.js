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
