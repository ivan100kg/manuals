describe("pow", function() {                    // описание тестир ф-ии
    console.log(her);
    it("возводит 2 степень 3", function() {     // тесты(1+)
        assert.equal(pow(2, 3), 8);
    });

    it("возводит 3 степень 11", function() {     // тесты(1+)
        assert.equal(pow(3, 11), 177147);
    });

});