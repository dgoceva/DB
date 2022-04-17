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
  var sql = "SELECT * FROM cars WHERE price > 50000";

  con.query(sql, function (err, result) {
    if (err) throw err;
    console.log(result);
    con.close();
  });
});
