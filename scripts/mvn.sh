#!/usr/bin/env bash
# Ejecuta Maven en la imagen oficial, sin instalar nada y sin root.
# Uso: scripts/mvn.sh [argumentos de mvn]. Por defecto, JDK 25; JDK=27 scripts/mvn.sh test para probar con el 27.
set -euo pipefail
raiz=$(git rev-parse --show-toplevel)
cache=${MAVEN_CACHE:-$raiz/.cache-maven}
mkdir -p "$cache"
exec docker run --rm -u "$(id -u):$(id -g)" \
  -v "$raiz":/app -w /app \
  -v "$cache":/var/maven/.m2 \
  -e MAVEN_CONFIG=/var/maven/.m2 -e HOME=/var/maven \
  "maven:3.10.0-eclipse-temurin-${JDK:-25}" \
  mvn -q -Duser.home=/var/maven "${@:-test}"
