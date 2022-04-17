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

knex.schema
  .createTable("cars", (table) => {
    table.increments("id");
    table.string("name");
    table.integer("price");
  })
  .then(() => console.log("table created"))
  .catch((err) => {
    console.log(err);
    throw err;
  })
  .finally(() => {
    knex.destroy();
  });
