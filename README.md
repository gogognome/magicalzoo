Magical Zoo
===========

Oplossing voor Java Magazine Code Challenge.

Probleembeschrijving
--------------------

Het probleem van de magische dierentuin kan formeel worden beschreven. Laat (g,w,l) het aantal geiten, wolven en leeuwen zijn
van de beginsituatie. Vervolgens kunnen er verschillende wijzigingen worden gedaan op dit tupel waarbij de g+w+l bij iedere
wijziging met precies 1 afneemt. De mogelijke wijzigingen zijn:

a) (g-1,w-1,l+1) als g>0 en w>0
b) (g-1,w+1,l-1) als g>0 en l>0
c) (g+1,w-1,l-1) als w>0 en l>0

Doel van code challenge is te berekenen wat het maximum aantal dieren van 1 soort dat kan overblijven door bovenstaande 
wijzigingen toe te passen. De volgorde waarin de wijzigingen worden toegepast spelen hierbij een grote rol!

Analyse
-------

Een recht-toe-recht-aan oplossing bestaat uit recursief alle mogelijke wijzigingen toe te passen en dan het beste resultaat
te kiezen. Voor de voorbeeld input (2055, 2006, 2017) zou zo'n algoritme veel te veel tijd kosten. Zelfs als een techniek
als memoization wordt toegepast zouden nog een paar miljard situaties moeten worden nagegaan. Dat zijn er zo veel dat het
niet in het geheugen van mijn laptop past.

Dan maar eens kijken of de oplossing niet anders kan worden berekend. Wat opvalt aan iedere wijziging is dat het totaal
aantal dieren met 1 afneemt. En als je module twee gaat rekenen, dan zorgt iedere wijziging ervoor dat een 0 (modulo 2)
wijzigt in een 1 (modulo 2) en vice versa voor alle drie de elementen van het tupel. Als initeel g, w en l allemaal
even zijn, dan zal de eerste wijziging ze allemaal oneven maken en de volgende allemaal even enz. En als initieel alleen
g en w even zijn en l oneven, dan zal na de eerste wijziging g en w oneven en l even zijn en na de volgende wijziging
g en w weer even en l weer oneven enz.

Stel nu dat niet alledrie de getallen initieel modulo twee gelijk zijn. Ofwel initieel zijn g, w en l niet alledrie even
en niet alledrie oneven. Stel dat g en w even zijn en l oneven. Kunnen we dan iets zeggen over welk dier uiteindelijk
overleeft in de oplossing van de challenge? Iedere wijziging
zorgt ervoor dat modulo 2 de waarden van g, w en l worden geïnverteerd. In het begin waren g en w gelijk modulo 2 en
waren g en w allebei ongelijk aan l modulo 2. Dit geldt ook na iedere wijziging die plaatsvindt. In de eindsituatie moeten
twee van de drie getallen nul zijn. Ze moeten dus modulo 2 gelijk zijn. Dit kan dus alleen gebeuren voor g en w. Dus l
overleeft. Als alledrie de getallen even of alledrie oneven zijn aan het begin, dan kan ieder dier overleven. 

Kunnen we iets zeggen over het minimum aantal wijzigingen dat moet worden uitgevoerd om van twee diersoorten het aantal
op nul te krijgen? Stel dat g en w gelijk zijn modulo 2 en l ongelijk aan g modulo 2 (en l is daardoor ook ongelijk w
modulo 2). We weten dat de eindsituatie (0, 0, >0) moet zijn. Stel dat g <= w. Als we g keer wijziging a toepassen dan
is de situatie (g, w, l) overgegaan in (0, w-g, l+g). Nu hoeven we alleen het aantal wolven nog op nul te krijgen.
Dit doen we door om de beurt wijziging c en a uit te voeren. Dit leidt namelijk tot de volgende situaties:
(1, w-g-1, l+g-1), (0, w-g-2, l+g), (1, w-g-3, l+g-1), (0, w-g-4, l+g), ..., (0, 0, l+g).
