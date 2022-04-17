const options = {
  client: "mysql2",
  connection: "mysql://user11:s$cret@localhost:3306/mydb",
};

const knex = require("knex")(options);

knex
  .from("cars")
  .select("name", "price")
  .where("price", ">", "50000")
  .then((rows) => {
    for (row of rows) {
      console.log(`${row["name"]} ${row["price"]}`);
    }
  })
  .catch((err) => {
    console.log(err);
    throw err;
  })
  .finally(() => {
    knex.destroy();
  });
