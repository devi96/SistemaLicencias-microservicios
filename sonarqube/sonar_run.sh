#!/bin/bash



# Nombre del microservicio = nombre de carpeta y projectKey
MICROSERVICIO=$1

# Leer variables
# 🔐 Cargar variables desde .env si existe
if [ -f .env ]; then
  echo "🔐 Cargando variables desde .env"
  source .env
fi

if [ -z "$MICROSERVICIO" ]; then
  echo "❌ Debes pasar el nombre del microservicio como argumento."
  echo "Ejemplo: ./sonar_run.sh microservicio_usuario_noreactivo"
  exit 1
fi

# Ejecutar análisis
echo "🔍 Ejecutando análisis SonarQube para $MICROSERVICIO..."

cd ..

mvn -f $MICROSERVICIO/pom.xml clean verify sonar:sonar \
  -Dsonar.projectKey=$MICROSERVICIO \
  -Dsonar.host.url=$SONAR_HOST \
  -Dsonar.login=$SONAR_TOKEN \
  -Dsonar.coverage.jacoco.xmlReportPaths=$MICROSERVICIO/target/site/jacoco/jacoco.xml