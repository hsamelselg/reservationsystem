<H1>Restorani broneerimissüsteem</H1>
See projekt on valminud proovitöö raames, et hallata resotrani lauda broneerimist reaalajas saaliplaani abil.

<H3>Rakenduse käivitamine</H3>
<b>Eeldused:</b> Veendu, et on installeeritud Java 17+ ja Maven
<b>Andmebaas:</b> Rakendus kasutab H2 <i>in-memory</i> andmebaasi
<b>Käivitamine:</b> ```bash mvn spring-boot:run

<b>Kasutamine:</b> Ava brauseris http://localhost:8080

<h3>Tehniline teostus ja tehtud tööd</h3>
<b>Back-end:</b> Spring Boot (Java), Sptring Data JPA

<b>Frnot-end:</b> HTML5, Thymeleaf, JavaScript

<b>Andmebaas: </b> H2 (andmed lähtestatakse igal käivitusel)

<b>Põhifunktsionaalsus:</b>
* Interaktiivne saaliplaan: Kuvab vabu laudu vastavalt valitud ajale
  
* Nutikas laudade leidmine: Süsteem pakub esmalt laudu, mis vastavad täpselt seltskonna suurusele, et vältida suurte laudade raiskamist väikeste gruppide peale.

* Parim valiku soovitus: kliendile tehakse nähtavaks parim tema soovidele vastav laud.

* Reaalajas valideerimine: Kontrollitakse, et broneeringuid ei tehtaks minevikku ega väljapoole restorani lahtiolekuaegu (10:00–20:00).

* Dünaamilised veateated: Kasutaja saab tagasisidet läbi spetsiaalsete Modal-akende

<h3>Ajakulu ja protsess</h3>

* Planeerimine ja andmemudeli loomine: ~2 tundi.

* Backendi loogika (broneeringute kattuvus jne): ~5 tundi.

* Frontendi arendus ja saaliplaani visualiseerimine: ~6 tundi.

* Testimine ja silumine: ~3 tundi.

<b>Kokku: ~16 tundi.</b>

<h3>Keerulised kohad ja probleemi lahendused</h3>
1. Modal-akende ilumine ja asukoht ekraanil: Algul oli keeruline saavutada akende korrektset keskjoondust ja dünaamilist kuvamist vastavalt serveri vastusele.

  * Lahendus: Kasutasin CSS Flexboxi (align-items: center; justify-content: center;) ja Thymeleaf th:if ning th:utext märgendeid, mis võimaldasid kuvada serverist tulnud veateateid koos HTML-reavahetustega.
    
2. Back-endi sidumine HTML-iga: Algul oli keeruline mõista, kuidas saata andmeid JavaScriptist Controllerisse ja sealt tagasi nii, et saaliplaan reageeriks.

  * Lahendus: Kasutasin vormi parameetrite (@RequestParam) ja mudeli atribuutide kombinatsiooni. Õppisin kasutama RedirectAttributes ja addFlashAttribute meetodeid, et info säiliks ka pärast lehe ümbersuunamist.
    
3. Projekti setup:Kuna polnud varem Spring Boot'i kasutanud, võttis algus aega.

  * Lahendus: Kasutasin Spring Initializr-i projekti põhja loomiseks ning viisin end kurssi Spring Data JPA ja H2 seadistustega läbi ametliku dokumentatsiooni.

<h3>AI kasutus</h3>
Kasutasin AI (Gemini) abi järgmistes kohtades:

* JavaScript: Abi modal-akende sulgemise loogika ja disain.

* Thymeleaf: Süntaksiabi, et kuvada dünaamiliselt erinevaid veateateid.

* Veatuvastus: AI aitas mõista Optional tüübi kasutamist andmebaasi päringutes, et vältida NullPointerException-eid.

* Päringud: Päringute koostamine andmebaasist
