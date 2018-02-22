# feed-filter

 +	Pentru realizarea proiectului am folosit design pattern-urile Observer, 
 +Visitor si SingletonFactory. 
 +	Pentru realizarea comenzilor am creat clasa SubjectF cu SingletonFactory. 
 +Aceasta:
 +-> adauga noi observatori sau ii sterge din lista de observatori;
 +-> anunta fiecare observator cu privire la aparitia unui nou feed creeaza o 
 +lista cu toate feed-urile si un TreeMap;
 +	(Cum un feed se trece prin filtru atunci cand se apeleaza print si se 
 +folosesc ultimile valori pentru fiecare, aceste feed-uri le salvez intr-un 
 +TreeMap cu cheia name.)
 +-> afiseaza informatia din stock-ul unui observator.
 +	In cazul in care se creeaza un nou observator iar lista de feed-uri nu 
 +este goala(adica exista feed care a fost procesat de alti observatori anterior), 
 +adaug feed-urile in TreeMap.
 +	In clasa ObserverStock fiecare observator are:
 +-> un id si un filtru;
 +-> radacina unui arbore de parsare construit din filtru;
 +-> o lista de stock care contine toate feed-urile, chiar si acelea care nu trec 
 +prin filtru;
 +	(Aceasta lista este folosita pentru a determina nrOfChanges la fiecare 
 +afisare. Parcurg lista si verific care exista alte feed-uri cu acelasi name cu 
 +ultimul feed afisat din TreeMap, le contorizez si apoi le sterg din lista pentru 
 +a nu le mai lua in calcul la urmatoarea afisare.) 
 +-> 2 TreeMap-uri: 
 +	* stockTree(key: feed name, value: feed) - pentru a salva in ordine 
 +alfabetica feed-urile care trec de filtru; In acest TreeMap adaug feed-uri prin 
 +metoda update.
 +	* increase(key: feed name, value: feed value) - pentru a salva ultima 
 +valoarea afisata, value,  pentru fiecare feed. Pentru a calcula increase pentru 
 +un anumit stock o sa extrag din TreeMap lastvalue corespunzator stock-ului si 
 +calculez dupa formula: (value-lastvalue)/lastvalue*100.
 +
 +	Pentru a construi arborele de parsare pentru fiecare observator creez 
 +mai intai forma postfix din forma infix a filtrului: 
 +	Clasa InfixToPostfix contine metoda toPostfix(String infix) care 
 +transforma expresia filtrului(infix) trimis ca parametru intr-un vector de 
 +string-uri cu ajutorul caruia voi construi arborele de parsare.
 +	Pentru aplicarea alogritmului postfix:
 +-> elimin mai intai spatiile de dupa paranteze sau inaintea lor;
 +-> separ operatorii si operanzii filtrului dupa " " intr-un StringTokenizer, 
 +tokens;
 +-> creez o stiva cu ajutorul careia voi stabili ordinea operanzilor si 
 +operatorilor in vectorul rezultat
 +-> pentru fiecare token verific daca acesta contine paranteze si cate:
 +	* daca are '(' le elimin din string, si le adaug in stiva;
 +	* daca are ')' le elimin de asemenea din string, iar pentru fiecare ')' 
 +gasita scot elementele din stiva, adaugandu-le in vector, pana cand ii gasesc 
 +'(' corespunzatoare in stiva.
 +-> apoi determin cu un switch case daca token-ul este un operand sau un 
 +operator:
 +	* daca este operator, cum totii operatorii au aceeasi prioritate, scot 
 +toate elementele din stiva pana la prima '(' si le adaug in vector (dupa 
 +operanzii din vector vor urma operatorii din stiva), apoi adaug operatorul in 
 +stiva; Atfel, se va realiza noua ordine a operatorilor
 +	* daca este operand, il adaug in vector.
 +
 +	Arborele de parsare este construit in clasa ExpressionTree cu ajutorul 
 +design pattern-ului SingletonFactory, a carui metode getOperator/getOperand 
 +returneaza un nou nod operator/operand. Aceasta clasa are o singura metoda, 
 +create(String[] filter) si pentru fiecare element din vectorul filter va creea 
 +cate un nod si ii va stabili copiii, rezultand astfel un arbore.
 +
 +	Visitor este interfata pentru noduri ce pot vizita alte noduri si este 
 +folosita pentru parcurgerea si evaluarea arborelui de parsare.
 +	Fiecare arbore este evaluat prin metoda evaluate(Node root, String name, 
 +String value, String filter) din clasa ObserverStock. 		
 +	Arborele fiind unul generic, compatibil pentru mai multe filtre de 
 +evaluare trebuie inlocuite valorile din  nodurile care au stocate informatia 
 +"name"/"value" cu valorile din parametrii name sau value.
 +
 +	Informatia pentru un feed se gaseste in clasa Feed. 
 +
