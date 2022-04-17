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
  var sql =
    "CREATE TABLE IF NOT EXISTS cars (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), price INT)";
  con.query(sql, function (err, result) {
    if (err) throw err;
    console.log("Table created!");
    con.close();
  });
});
