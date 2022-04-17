var mysql = require("mysql2");

var con = mysql.createConnection({
  host: "localhost",
  user: "user12",
  password: "34klq*",
  database: "testdb",
});

con.connect(function (err) {
  if (err) throw err;
  console.log("Connected!");
  var sql = "INSERT INTO cars (name, price) VALUES ?";
  var values = [
    ["Audi", 52642],
    ["Mercedes", 57127],
    ["Volvo", 29000],
    ["Bentley", 350000],
    ["Citroen", 21000],
    ["Hummer", 41400],
    ["Volkswagen", 21600],
  ];
  con.query(sql, [values], function (err, result) {
    if (err) throw err;
    console.log("Number of records inserted: " + result.affectedRows);
    process.exit();
  });
});
