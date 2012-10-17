# Ülesanne 3

Kirjutage testidel põhinevalt bowlingu punktide kalkulaator.

## Meetodid:

* void hit(int i)
* int getScore()

lihtsustus: arvutab ainult täis mänge (ei pea andma õiget tulemust, kui olen 
visanud nt. ainult 3 viset)

### Näide:

```java
Calculator c = new Calculator();
for (int i = 0; i < 20; i++) { // 20 viset iga kord üks maha
	c.hit(1);
}
assertThat(c.getScore(), is(20)); // selle tulemusena peab skoor olema 20
```

## Protsess:

1. Looge kalkulaatori klass (1. testmeetod)
2. 20 viset, iga kord üks maha (summa 20) (2. testmeetod)
3. 20 viset, iga kord kaks maha (summa 40) (3. testmeetod)
4. 21 viset, iga kord viis maha (summa 150) (4. testmeetod)
   * tuleb sisse frame-i mõiste
   * refaktoreerige testid (eemaldage duplakatsioon)
5. 21 viset, iga kord 10 maha (21 on lihtsuse mõttes. Tegelikult ei saa siin 21 viset. Sellega tegeleme hiljem) (5. testmeetod)
6. tavaline mäng (6. testmeetod)

So. tuleb teha vähemalt 6 testmeetodit. Kui vaja võib teha rohkem.

## Bowlingu skoori kalkuleerimine.

Mäng koosned 10 frame-st. Esimeses üheksas frame-s on kaks viset. Viimases frame-s
võib olla kolm viset. Lõppsumma on 10 frame-i summa. Viimases frame-is saab spare-i 
puhul ühe lisaviske ja strike puhul kaks lisaviset.

Frame - 2 (viimases 3) viset
Strike - esimese löögiga kõik kurikad maha
Spare - kahe löögiga kõik kurikad maha
näide punktitabelist: http://tralvex.com/pub/bowling/BSC.htm 
  (notatsioon: 0-9 or x or /) 
  |1, 2| tavaline (esimene vise üks, teine vise kaks kurikata maha)
  |5, /| spare (esimene vise 5, teine vise ülejäänud)
  |x,| Strike (esimene vise kõik maha. Teist viset ei saa)

## Punktide arvutamine: 
  * tavaline - frame-i summa (so. kahe viske summa)
  * spare - frame-i summa (10) + järgmise viske punktid
  * strike - frame-i summa (10, teist viset ei saa) + kahe järgmise viske punktid

# Näide tavalisest mängust

01. 3, 5, 
02. 5, 5,
03. 2, 5,
04. 10,
05. 2, 8,
06. 10,
07. 5, 2,
08. 7,1,
09. 3,7,
10. 10, 7, 3
= 139
