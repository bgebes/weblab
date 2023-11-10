var mysql = require('mysql2');

var connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '123456789',
  database: 'weblab',
});

connection.connect(function (err) {
  if (err) throw err;
  console.log('Connected!');

  function createTable() {
    var sql =
      'CREATE TABLE Employee (EmployeId INT NOT NULL, FirstName VARCHAR(45) NOT NULL, LastName VARCHAR(45) NOT NULL, DepartmentName VARCHAR(45) NOT NULL, PRIMARY KEY (EmployeId) )';
    connection.query(sql, function (err, result) {
      if (err) throw err;

      console.log('Created Table!');
    });
  }

  function insert() {
    var insert_sql =
      'INSERT INTO Employee (EmployeId, FirstName, LastName, DepartmentName ) VALUES ? ';

    var values = [
      [1, 'Ken', 'Sanchez', 'Executive'],
      [2, 'Terri', 'Duffy', 'Engineering'],
      [3, 'Roberto', 'Tamburello', 'Engineering'],
      [4, 'Rob', 'Walters', 'Engineering'],
      [5, 'Gail', 'Erickson', 'Engineering'],
      [6, 'Jossef', 'Goldberg', 'Engineering'],
      [7, 'Dylan', 'Miller', 'Support'],
      [8, 'Diane', 'Margheim', 'Support'],
      [9, 'Gigi', 'Matthew', 'Support'],
      [10, 'Michael', 'Raheem', 'Support'],
    ];

    connection.query(insert_sql, [values], function (err, result) {
      if (err) throw err;
      console.log('Inserted records count:  ' + result.affectedRows);
    });
  }

  function search() {
    var search_sql =
      "SELECT * FROM Employee WHERE DepartmentName = 'Engineering'";

    connection.query(search_sql, function (err, result) {
      if (err) throw err;
      console.log(result);
    });
  }

  function update() {
    var update_sql =
      "UPDATE Employee SET DepartmentName = 'Executive' WHERE FirstName = 'Terri'";

    connection.query(update_sql, function (err, result) {
      if (err) throw err;
      console.log(result.affectedRows + ' records updated!');
    });
  }

  createTable();
  insert();
  search();
  update();
});
