#!/bin/bash

VERSION=${1:-1.0}

SERVICES=(
  authentication-server-jw
  config-server
  cloud-gateway
  discovery-server
  microservicio_licencia_noreactivo
  microservicio_usuario_noreactivo
  microservicio_vehiculo_noreactivo
)

for SERVICE in "${SERVICES[@]}"
do
  echo "🚀 Empaquetando y construyendo $SERVICE..."

  cd $SERVICE || exit
  ./mvnw clean package -DskipTests

  docker build -t devi96/$SERVICE:$VERSION -t devi96/$SERVICE:latest .
  docker push devi96/$SERVICE:$VERSION
  docker push devi96/$SERVICE:latest

  cd ..
done