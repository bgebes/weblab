const fs = require('fs');

function findPrime() {
  const primeList = [];
  var i = 2;

  while (i < 100) {
    var timer = 0;
    for (var dividing = 1; dividing <= i; dividing++) {
      if (i % dividing == 0) {
        timer++;
      }
    }

    if (timer == 2) {
      primeList.push(String(i) + ',');
    }

    i++;
  }

  console.log(primeList);
  primeList.forEach(writeToFile);
}

function writeToFile(num) {
  fs.appendFile('./assets/asal.txt', num, function (err, data) {
    if (err) throw err;
  });
}

findPrime();
