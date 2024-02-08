'use strict';

function sleep(time) {
    return new Promise((resolve, reject) => {
        if (time <= 500) {
            reject(`${time} is too litle!`);
        }
        setTimeout(() => resolve(`поспал ${time}`), time);
    });
}

// sleep(1600).then(res => console.log(res));  

// sleep(1500).then(res => {
//     console.log(res)
//     return sleep(1000)
// }).then(res => {
//     console.log(res)
//     return sleep(500)
// }).then(res => {
//     console.log(res)
//     return sleep(200)
// }).then(res => 
//     console.log(res)
// ).catch(err => console.log(`Ошибка ${err}`));


// имитация синхронного кода на async await:
const sleepFoo = async () => {
    try {
        const sleep1 = await sleep(1500);
        console.log(sleep1);
        const sleep2 = await sleep(1000);
        console.log(sleep2);
        const sleep3 = await sleep(500);
        console.log(sleep3);
        const sleep4 = await sleep(300);
        console.log(sleep4);
        const sleep5 = await sleep(200);
        console.log(sleep5);
    } catch (err) {
        console.log(err);
    }
};

sleepFoo();