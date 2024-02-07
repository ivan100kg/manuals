'use strict';

const promise = new Promise((resolve, reject) => {
    console.log('in Promise before setTimeout');
    setTimeout(() => {
        console.log('in the beginning of setTimeout');
        const randomNumber = Math.random();
        if (randomNumber > 0.5) {
            resolve(randomNumber);
        } else {
            reject(new Error('Число меньше 0.5'));
        }
        console.log('in the end of setTimeout');
    }, 1000);
    console.log('in Promise after setTimeout');
});

console.log('hello 1');
promise.then(result => console.log('(P)Успешно:', result)).catch(error => console.error('(P)Ошибка:', error));
console.log('hello 2');