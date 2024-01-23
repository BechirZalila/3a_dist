#! /bin/bash

ant application_clean

rm -rf DinerDesPhilosophes
mkdir DinerDesPhilosophes

for i in build.xml diner.properties deploy stop META-INF resources src; do
    svn export --force "$i" "DinerDesPhilosophes/$i" || exit 1
done

tar czvvf DinerDesPhilosophes.tar.gz DinerDesPhilosophes

rm -rf DinerDesPhilosophes

exit 0;
