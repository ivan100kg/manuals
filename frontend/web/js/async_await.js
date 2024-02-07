function delay(ms) {
    return new Promise(resolve => {
        setTimeout(resolve, ms);
    });
}

async function exampleAsyncFunction() {
    console.log('Начало асинхронной функции');
    
    // Ожидаем 2 секунды
    await delay(2000);
    
    console.log('Прошло 2 секунды');
    
    // Ожидаем еще 1 секунду
    await delay(1000);
    
    console.log('Прошло еще 1 секунда');
    
    return 'Выполнение завершено';
}

async function runExample() {
    console.log('Старт выполнения примера');
    
    const result = await exampleAsyncFunction();
    
    console.log(result);
}

runExample();