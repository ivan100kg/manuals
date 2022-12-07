describe("pow", function() {
    before(() => console.log("перед всеми"));
    after(() => console.log("после всех"));
    beforeEach(() => console.log("перед каждым"));
    afterEach(() => console.log("после каждого"));
    
    it("2 в степени 3 будет 8", () => assert.equal(pow(2, 3), 8));
    it("3 в степени 3 будет 27", function() {
        assert.equal(pow(3, 3), 27);
    });
    it("для отрицательных n возвращает NaN", function() {
        assert.isNaN(pow(2, -1));
    });
    it("для дробных n возвращает NaN", function() {
        assert.isNaN(pow(2, 1.5));
    });
});