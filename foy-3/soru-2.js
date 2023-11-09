const fs = require('fs');

fs.readFile('./assets/quiz_6.txt', 'utf-8', function (err, data) {
  if (err) throw err;

  function A(x, a, b, c) {
    return a * x * x + b * x + c;
  }

  var list = data.split(/\r\n|\n/);
  var x1 = Number(list[0][0]);
  var a1 = Number(list[0][2]);
  var b1 = Number(list[0][4]);
  var c1 = Number(list[0][6]);

  var A1 = A(x1, a1, b1, c1);
  console.log('Equation 1: ' + A1);

  var A2 = A(
    Number(list[1][0]),
    Number(list[1][2]),
    Number(list[1][4]),
    Number(list[1][6])
  );

  var A3 = A(
    Number(list[2][0]),
    Number(list[2][2]),
    Number(list[2][4]),
    Number(list[2][6])
  );

  console.log('Equation 2: ' + A2);
  console.log('Equation 3: ' + A3);
});
