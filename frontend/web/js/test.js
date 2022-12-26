"use strict";

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

let user = {};
user.name = 'John';
user.surname = 'Smith';
user.name = 'Pete';
delete user.name;
console.log(user);

let calculator = {
    read(a, b) {
        this.a = a;
        this.b = b; 
    },
    sum() {
        return this.a + this.b;
    },
    mul() {
        return this.a * this.b;
    }
};

calculator.read(2,3);
console.log(calculator.sum());
console.log(calculator.mul());

function createPDF() {
    var sTable = document.getElementById('tab').innerHTML;

    var style = "<style>";
    style = style + "table {width: 100%;font: 17px Calibri;}";
    style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
    style = style + "padding: 2px 3px;text-align: center;}";
    style = style + "</style>";

    // CREATE A WINDOW OBJECT.
    var win = window.open('', '', 'height=700,width=700');

    win.document.write('<html><head>');
    win.document.write('<title>Profile</title>');   // <title> FOR PDF HEADER.
    win.document.write(style);          // ADD STYLE INSIDE THE HEAD TAG.
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write(sTable);         // TABLE CONTENTS INSIDE THE BODY TAG.
    win.document.write('</body></html>');

    win.document.close(); 	// CLOSE THE CURRENT WINDOW.

    win.print();    // PRINT THE CONTENTS.
}