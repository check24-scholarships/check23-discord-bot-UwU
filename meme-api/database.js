const mysql = require("mysql2")
const env = require("dotenv").config().parsed
const connection = mysql.createPool({
  host: env.host,
  user: env.user,
  password: env.password,
  database: env.database,
  connectionLimit: 5
});
exports.query = (query, data) => {
  return new Promise((resolve) => {
    connection.execute(query, data, (err, results) => {
      resolve({ err: err, rows: results });
    });
  });
};