#!/bin/bash

echo "🔄 Updating package lists..."
sudo apt update -y

echo "☕ Installing Java JDK..."
sudo apt install default-jdk -y

echo "✅ Java installed successfully!"

echo "🔍 Checking installed Java versions..."
update-java-alternatives --list

echo "🔄 Setting Java alternatives..."
sudo update-alternatives --config java

echo "🔍 Verifying Java installation..."
which java
ls -l "$(which java)"
java -version

echo "🔄 Setting JAVA_HOME..."
export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
echo "export JAVA_HOME=$JAVA_HOME" >> ~/.bashrc
echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc

echo "✅ JAVA_HOME set to: $JAVA_HOME"

echo "🔍 Checking Ant installation..."
which ant
ls -l "$(which ant)"

echo "🔄 Running Ant diagnostics..."
ant -diagnostics | grep 'java.home'

echo "🚀 Trying to build using Ant..."
ant clean && ant make-jar

echo "✅ Setup complete!"
