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

function writeTxt(all_of_numbers) {
  console.log('All of numbers: ' + all_of_numbers);
  fs.writeFile(
    './assets/tum_sayilar.txt',
    all_of_numbers,
    function (err, data) {
      if (err) throw err;
      console.log('Saved!');
    }
  );
}
