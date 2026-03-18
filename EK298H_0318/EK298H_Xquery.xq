xquery version "3.1";

(: Task 1: doc("/db/EK298H_XML.xml")//vendeg :)

(: 
 : Task 2
for $s in doc("/db/EK298H_XML.xml")//szakacs
where $s/vegzettseg = "Szakközépiskola"
return
<szakacs>  <SzakacsID>{data($s/@id)}</SzakacsID>
    <Nev>{data($s/nev)}</Nev>
    <Reszleg>{data($s/reszleg)}</Reszleg>
    <Vegzettsegek>{$s/vegzettseg}</Vegzettsegek>
</szakacs>
:)

(: 
 :  Task 3
for $e in doc("/db/EK298H_XML.xml")//etterem
where $e/csillag = 5
return
<etterem>  
    <EtteremID>{data($e/@id)}</EtteremID>
    <Nev>{data($e/nev)}</Nev>  
    <Cim>{$e/cim}</Cim>  
    <Csillag>{data($e/csillag)}</Csillag>
</etterem>
:)
(:
 : Task 4
for $g in doc("/db/EK298H_XML.xml")//gyakornok
where $g/muszak = "Délután"
return
<gyakornok>  
    <GyakornokID>{data($g/@id)}</GyakornokID>
    <Nev>{data($g/nev)}</Nev>
    <GyakorlatKezdete>{data($g/gyakorlat/kezdete)}
    </GyakorlatKezdete>
    <Muszak>{data($g/muszak)}</Muszak>
</gyakornok>
:)

(: 
 : Task 5
for $v in doc("/db/EK298H_XML.xml")//vendeg
let $rendelesek :=  
    doc("/db/EK298H_XML.xml")//rendeles[@vkod= $v/@vkod]
for $r in $rendelesek
return
<adat>
    <nev>{$v/nev}</nev>
    <osszeg>{$r/osszeg}</osszeg>
</adat>
:)

(:
 : Task 6
for $r in doc("/db/EK298H_XML.xml")//rendeles
return
    update replace $r/osszeg
    with <osszeg>{ xs:integer($r/osszeg) + 1000 }</osszeg>
:)

(: 
 : Task 7 
count(doc("/db/EK298H_XML.xml")//rendeles)
:)

(: 
 : Task 7 
sum(doc("/db/EK298H_XML.xml")//rendeles/osszeg)
:)

(: 
 : 
avg(doc("/db/EK298H_XML.xml")//rendeles/osszeg)
:)
