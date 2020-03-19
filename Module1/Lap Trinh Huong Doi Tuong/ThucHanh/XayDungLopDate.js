let MyDate = function(day, month, year) {
    this.day = day;
    this.month = month;
    this.year = year;
    
    this.getDay = function() {
        return this.day;
    };
    this.getMonth = function() {
        return this.month;
    };
    this.getYear = function() {
        return this.year;
    };

    this.setDay = function(day) {
        this.day  = day;
    };
    this.setMonth = function(month) {
        this.month  = month;
    };
    this.setYear = function(year) {
        this.year  = year;
    };
    this.toString = function () {
        this.string = this.day + "/" + this.month + "/" + this.year;
    }
};

let date = new MyDate(2, 2, 2007);

// Getter
let day = date.getDay(); // 2
let month = date.getMonth(); // 2
let year = date.getYear(); // 2007
let string = date.toString();
alert(date.string);

// // Setter
// date.setDay(10);
// date.setMonth(10);
// date.setYear(2019);
// alert(date.toString);