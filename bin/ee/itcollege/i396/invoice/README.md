Iseseisev töö 2
Tähtaeg 09. Dets (23:59) 

Kirjutage testidel põhinevalt klass InvoiceRowGenerator, mis genereerib
osamakseid. Sisendiks on summa ja periood, millele see summa jagada tuleb.

Jagamine toimub järgmiste reeglite alusel:
  - esimene osamakse tuleb perioodi esimesel päeval;
  - ülejäänud osamaksed tulevad kuu esimesel päeval;
  - kui summa ei jagu Euro täpsusega, siis läheb vahe viimasse osamaksesse;
  - kahte osamakset ühele päevale sattuda ei või;
  - miinimum oramakse summa on 3 EUR-i. Kui summa tuleb väiksem, võetakse need maksed kokku.
    nt. kui periood on kolm kuud aga makse on 2 EUR-i, siis tuleb vaid üks osamakse.
    Kui jagatav summa on alla 3 EUR-i, siis tuleb üks osamakse.

nt. kui summa on 10 ja periood on 2012-02-15 - 2012-04-02
Siis tulevad järgmised osamaksed (summa - kuupäev):

  3 - 2012-02-15
  3 - 2012-03-01
  4 - 2012-04-01 (jääk läheb viimasele osamaksele)

Vajalikud on vähemalt järgmised testid:

  - summa jagatakse õigesti;
  - kuupäevad on õiged;
  - ühele päevale ei tule kaks arvet;
  - osamake summa tuleks väksem kui 3 EUR-i.
    See tähendab, et võetakse teisega kokku.
    Kui kogu suumma on alle 3 EUR-i, siis jääb üks vastava summaga osamakse.

InoviceRowGenerator.generateRowsFor() jagab summa perioodile ära ja kutsub iga osamakse
kohta välja InvoiceRowDao.save() meetodit. Meetodi generateRowsFor() õigsuses saab
veenduda kontrollides save() meetodi väljakutseid.

Märkus:
Antud koodi parem disain oleks selline, et InvoiceRowGenerator ise salvestamisega ei
tegeleks ja tagastaks genereeritud osamaksed. Mock-imise harjutamise huvides on see
disain seekord selline.
