# Ülesanne 2

Kirjutage testidel põhinevalt klass LinkedSet (järjestatud hulk). See on kogumik,
milles pole dublikaate. See erineb tavalisest hulgast niipalju, et meeles peetakse
elementide sisestamise järjekorda. Käitumine on sama, mis java.util.LinkedHashSet-il.

## Meetodid:

* void add(Object o) - lisab elemendi hulka (kui seda seal enne ei olnud)
* void remove(Object o) - eemaldab elemendi hulgast
* boolean contains(Object o) - ütleb, kas antud element on juba hulgas
* boolean containsAll(List list) - ütleb, kas listi kõik elemendid on hulgas
* boolean removeAll(List list) - eemaldab hulgast kõik listis olevad elemendid
* boolean retainAll(List list) - eemaldab hulgast kõik elemendid, mida listis pole
* int size() - tagastab hulga suuruse
* List asList() - tagastab hulga elemendid sisestamise järjekorras
