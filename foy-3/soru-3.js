const fs = require('fs');

fs.readFile('./assets/asal_sayi.txt', 'utf-8', function (err, data) {
  if (err) throw err;

  const list = [];

  data = data.split(',');
  for (var i = 0; i < data.length; i++) {
    if (Number(data[i])) {
      list.push(Number(data[i]));
    }
  }

  console.log('All of prime numbers: ' + list);

  for (var i = 0; i <= 90; i++) {
    if (list.includes(i)) {
      continue;
    } else {
      list.push(i);
    }
  }

  writeTxt(String(list));
});

function writeTxt(tum_sayilar) {
  console.log('All of numbers: ' + tum_sayilar);
  fs.writeFile('./assets/tum_sayilar.txt', tum_sayilar, function (err, data) {
    if (err) throw err;
    console.log('Saved!');
  });
}
