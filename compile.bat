En Windows:

javac -source 1.8 -target 1.8 src\*.java -d bin
cd bin
java Main 10 ..\Hilillos_de_prueba\1.txt ..\Hilillos_de_prueba\2.txt
cd ..



                            En Linux Mint:

javac src/*.java -d bin
cd bin
java Main 5 ../Hilillos_de_prueba/1.txt ../Hilillos_de_prueba/2.txt
cd ..
