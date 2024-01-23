<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE softpkg PUBLIC "-//OMG//DTD CORBA Software Descriptor 3.0//EN" "http://openccm.objectweb.org/dtd/ccm/softpkg.dtd">
<softpkg version="0,8,0,0" name="Observateur">
  <title>Observateur</title>
  <pkgtype version="">CORBA Component</pkgtype>
  <author>
    <name>ENIS</name>
    <company>ENIS</company>
    <webpage href="http://openccm.objectweb.org/" xml:link="SIMPLE"></webpage>
  </author>
  <description>Le composant Observateur de l'application Diner des philosophes</description>
  <license href="http://openccm.objectweb.org/license.html" xml:link="SIMPLE"></license>
  <idl homeid="IDL:DinerDesPhilosophes/ObservateurHome:1.0" id="IDL:DinerDesPhilosophes/Observateur:1.0">
    <link href="DinerDesPhilosophes.idl3" xml:link="SIMPLE"></link>
  </idl>
  <descriptor type="CORBA Component">
    <fileinarchive name="META-INF/observateur.ccd">
    </fileinarchive>
  </descriptor>
  <implementation variation="" id="ObservateurImpl">
    <code type="Java Class">
      <fileinarchive name="archives/observateur.jar">
      </fileinarchive>
      <entrypoint>DinerDesPhilosophes.ObservateurServiceComposition.ObservateurHomeImpl.create_home</entrypoint>
    </code>
    <compiler version="" name="JDK"/>
    <programminglanguage version="" name="Java"/>
    <os version="" name="Windows XP"/>
    <os version="2,4,20,0" name="Linux"/>
    <processor name="x86"/>
    <runtime version="1,3,1,0" name="Java VM"/>
  </implementation>
</softpkg>
