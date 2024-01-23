#! /bin/bash

# Arrêt préventif de toute instance de l'Interface Repository et
# suppression des répertoires

ir3_stop >/dev/null 2>&1
rm -rf generated
rm -rf src

# Création des répertoires nécessaires

mkdir -p ./generated/idl
mkdir -p ./generated/stubs
mkdir -p ./generated/skeletons
mkdir -p ./generated/dependencies
mkdir -p ./generated/classes

mkdir -p src

# Démarrer l'Interface Repository: élément essentiel qui sera utilisé
# tout le long du processus de construction et d'exécution de
# l'application

ir3_start

# Verification de la syntaxe du fichier IDL 3

idl3_check DinerDesPhilosophes.idl3

# Chargement de la spécification dans l'Interface Repository

ir3_feed -I"${OpenCCM_HOMEDIR}/idl" DinerDesPhilosophes.idl3

# Génération d'un fichier IDL3 à partir de ce qu'on a chargé dans l'IR

ir3_idl3 -o ./generated/idl/DinerDesPhilosophes_gen.idl3 ::DinerDesPhilosophes

# Génération des fichiers IDL2 à partir de ce qu'on a chargé dans l'IR

ir3_idl2 -o ./generated/idl/DinerDesPhilosophes.idl ::DinerDesPhilosophes

# Génération d'une partie du code du conteneur

cd ./generated/skeletons
ir3_java ::DinerDesPhilosophes
cd ../..

# Génération des exécuteurs

cidl_cif -o ./generated/idl/DinerDesPhilosophes_cif.idl \
    -d ./generated/skeletons \
    -dep ./generated/dependencies \
    -ipath DinerDesPhilosophes_local.idl \
           DinerDesPhilosophes.cidl

# Génération des souches JAVA

# Interfaces distantes

idl2java -d ./generated/stubs \
    -I./generated/idl/ \
    -I${OpenCCM_HOMEDIR}/idl \
        ./generated/idl/DinerDesPhilosophes.idl

# Interfaces locales

idl2java -d ./generated/stubs \
    -I./generated/idl/ \
    -I${OpenCCM_HOMEDIR}/idl \
        ./generated/idl/DinerDesPhilosophes_local.idl

# Interfaces CIF

idl2java -d ./generated/stubs \
    -I./generated/idl/ \
    -I${OpenCCM_HOMEDIR}/idl \
        ./generated/idl/DinerDesPhilosophes_cif.idl

# Compilation des souches

javac -d ./generated/classes \
    -sourcepath ./generated/stubs \
    ./generated/stubs/DinerDesPhilosophes/**/*.java

# Compilation des squelettes

javac -d ./generated/classes \
    -sourcepath ./generated/skeletons \
    ./generated/skeletons/DinerDesPhilosophes/**/*.java

# Génération des implantations

cif_jimpl -d src/ DinerDesPhilosophes.cidl

# Compilation des implantations

javac -d ./generated/classes \
    -sourcepath ./src \
    ./src/DinerDesPhilosophes/**/*.java

# Arrêt de l'interface repository

ir3_stop

exit 0;
