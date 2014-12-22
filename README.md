# De magische dierentuin

Oplossing voor een Java Magazine Code Challenge.

## Probleembeschrijving

Het probleem van de magische dierentuin kan formeel worden beschreven. Laat (g,w,l) het aantal geiten, wolven en leeuwen zijn
van de beginsituatie. Vervolgens kunnen er verschillende stappen worden gedaan op dit tupel:

- a: (g-1,w-1,l+1) als g>0 en w>0
- b: (g-1,w+1,l-1) als g>0 en l>0
- c: (g+1,w-1,l-1) als w>0 en l>0

Doel van code challenge is te berekenen wat het maximum aantal dieren van 1 soort dat kan overblijven door bovenstaande 
stappen toe te passen. De volgorde waarin de stappen worden toegepast spelen hierbij een grote rol!

## Analyse

Een recht-toe-recht-aan oplossing bestaat uit recursief alle mogelijke stappen toe te passen en dan het beste resultaat
te kiezen. Voor de voorbeeld input (2055, 2006, 2017) zou zo'n algoritme veel te veel tijd kosten. Zelfs als een techniek
als memoization wordt toegepast zouden nog een paar miljard (2055*2006*2017) situaties moeten worden nagegaan. 
Dat zijn er zo veel dat het niet in het geheugen van mijn laptop past.

Dan maar eens kijken of de oplossing niet anders kan worden berekend. Wat opvalt aan iedere stap is dat het totaal
aantal dieren (g+w+l) met 1 afneemt. Verder zorgt iedere stap ervoor dat een even getal wordt omgezet naar een oneven getal
en vice versa.

De geiten, wolven en leeuwen kunnen in twee groepen worden verdeeld: de dieren waarvan het aantal even is en de dieren
waarvan het aantal oneven is. Deze groepen kunnen niet even groot zijn omdat er het aantal soorten dieren oneven is. Een van de 
groepen is daardoor het grootste. Laten we deze groep met twee of drie diersoorten de meerderheidsgroep noemen. Het uitvoeren
van een stap wijzigt de meerderheidsgroep niet.

Stel nu dat de meerderheidsgroep uit twee diersoorten bestaat. In de eindsituatie moeten twee diersoorten het aantal nul hebben.
Deze twee diersoorten hebben dan allebei een even aantal. Ze zitten dus in de meerderheidsgroep! En als de meerderheidsgroep
uit alledrie de diersoorten bestaat geldt ook dat de diersoorten met aantal nul uit de meerderheidsgroep moet komen.

Kunnen we iets zeggen over het minimum aantal stappen dat moet worden uitgevoerd om van twee diersoorten het aantal
op nul te krijgen? Laat de beginsituatie (g, w, l) zijn. Stel dat geiten en wolven in de meerderheidsgroep zitten en de leeuwen niet.
We weten dat de eindsituatie (0, 0, n) moet zijn, waarbij n>0. Stel dat g <= w. Als we g keer stap a toepassen dan
is de situatie (g, w, l) overgegaan in (0, w-g, l+g). Als w-g > 0 dan is dit nog niet de eindoplossing. We weten wel dat
om bij de eindoplossing te komen er dus minimaal g stappen nodig zijn. Nu hoeven we alleen het aantal wolven nog op nul te krijgen.
Dit doen we door om de beurt stap c en a uit te voeren. Dit leidt namelijk tot de volgende situaties:
(0, w-g, l+g), (1, w-g-1, l+g-1), (0, w-g-2, l+g), (1, w-g-3, l+g-1), (0, w-g-4, l+g), ..., (0, 0, l+g).
Omdat de geiten en wolven in de meerderheidsgroep zitten weten we dat w-g een even getal is en dat we dus evenvaak stap c als stap a
kunnen uitvoeren. Het aantal wolven neemt hierbij iedere stap met 1 af. Er is geen snellere manier dan dit om het aantal wolven 
en het aantal geiten op nul te krijgen. We hebben dus het minimum aantal stappen gevonden om tot een eindoplossing te komen. 
Het aantal resterende leeuwen is maximaal en is gelijk aan l+g.

Wat nu als alledrie de dieren in de meerderheidsgroep zitten? We moeten dan in zo min mogelijk stappen het aantal van 
twee diersoorten op nul krijgen. Als we dit doen voor de twee diersoorten die het minst voorkomen dan vinden we weer een optimale
oplossing met het maximaal aantal resterende dieren.

## Implementaties

Ik heb twee implementaties gebouwd. Een recht-toe-recht-aan-implementatie die recursief de stappen toepast en de beste oplossing
van de stappen retourneert. Deze implementatie werkt redelijk snel voor enkele tientallen dieren. Voor meer dieren duurt het
rekenen veel te lang. De tweede implementatie bepaalt de meerderheidsgroep en rekent dan in O(1) de eindsituatie uit. 
Met automatische tests valideer ik een aantal eenvoudige scenario's. Ook valideer ik if voor alle mogelijkheden met maximaal tien
dieren per soort of beide implementaties hetzelfde resultaat geven.

## Oplossing voor (2055, 2006, 2017)

De oplossing voor de beginsituatie (2055, 2006, 2017) is (0, 4023, 0).