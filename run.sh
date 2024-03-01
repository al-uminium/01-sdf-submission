mkdir -p build
javac -sourcepath ./src -d ./build ./src/*
java -cp build App $1