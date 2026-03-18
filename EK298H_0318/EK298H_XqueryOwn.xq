xquery version "3.1";

(:  
 : Osszes auto kiiras
doc("/db/EK298H_XML1.xml")//auto
:)

(:  
 : Azok az autok, amelyeknek Kivalo a muszaki allapota
for $a in doc("/db/EK298H_XML1.xml")//auto
where $a/muszakiallapot = "Kiváló"
return
<auto>
    <Rendszam>{data($a/@autorendszam)}</Rendszam>
    <Szin>{data($a/szin)}</Szin>
    <Motor>{data($a/motor)}</Motor>
    <Allapot>{data($a/muszakiallapot)}</Allapot>
</auto>
:)

(:  
 : Azok a karbantartasok, amelyeknek a koltsége nagyobb mint 40000 Ft
for $k in doc("/db/EK298H_XML1.xml")//karbantartas
where $k/koltseg > 40000
return
<karbantartas>
    <SzervizID>{data($k/@a_k_sz)}</SzervizID>
    <Auto>{data($k/@a_k_a)}</Auto>
    <Koltseg>{data($k/koltseg)}</Koltseg>
    <Munkavallalo>{data($k/munkavallaloneve)}</Munkavallalo>
</karbantartas>
:)

(:  
 : Azok a tulajdonosok, akik Debrecenben laknak
for $t in doc("/db/EK298H_XML1.xml")//tulajdonos
where $t/lakcim/varos = "Debrecen"
return
<tulajdonos>
    <TulajID>{data($t/@tulajid)}</TulajID>
    <Nev>
        {data($t/nev/vezeteknev)} {data($t/nev/keresztnev)}
    </Nev>
    <Email>{data($t/emailcim)}</Email>
</tulajdonos>
:)

(:  
 : Tulajdonos neve és az autó vasarlasi ara (JOIN)
for $t in doc("/db/EK298H_XML1.xml")//tulajdonos
let $b := doc("/db/EK298H_XML1.xml")//birtokol[@a_b_t = $t/@tulajid]
for $x in $b
return
<adat>
    <Nev>
        {data($t/nev/vezeteknev)} {data($t/nev/keresztnev)}
    </Nev>
    <Ar>{data($x/vasarlasiar)}</Ar>
</adat>
:)

(:  
 : Noveli minden karbantartas koltseget 5000 Ft-tal (UPDATE)
for $k in doc("/db/EK298H_XML1.xml")//karbantartas
return
    update replace $k/koltseg
    with <koltseg>{ xs:integer($k/koltseg) + 5000 }</koltseg>
:)

(: 
 : Karbantartasok szama
count(doc("/db/EK298H_XML1.xml")//karbantartas)
:)

(:  
 : Osszes karbantartasi koltseg
sum(doc("/db/EK298H_XML1.xml")//karbantartas/koltseg)
:)

(:  
 : Atlagos karbantartasi koltseg
avg(doc("/db/EK298H_XML1.xml")//karbantartas/koltseg)
:)

