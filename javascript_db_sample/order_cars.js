const options = {
  client: "mysql2",
  connection: {
    host: "127.0.0.1",
    user: "user11",
    password: "s$cret",
    database: "mydb",
  },
};

const knex = require("knex")(options);

knex
  .from("cars")
  .select("name", "price")
  .orderBy("price", "desc")
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
