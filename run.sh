#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
JAR="jvm-unix-restart-jar-with-dependencies.jar"

function test_with_docker() {
  IMAGE="${1}"

  if docker run --rm -t -v "${DIR}/target:/app" -w "/app" "${IMAGE}" java -jar "${JAR}" ; then
    echo "... ${IMAGE} works as expected"
  else
    echo "... ${IMAGE} has failed"
  fi
}

function test_local() {
  if java -jar "${DIR}/target/${JAR}" ; then
    echo "... local machine works as expected"
  else
    echo "... local machine failed"
  fi
}

echo "... test on local machine"
test_local

echo "... test on debian"
test_with_docker adoptopenjdk/openjdk11:x86_64-debian-jre-11.0.6_10

echo "... test on centos"
test_with_docker adoptopenjdk/openjdk11:x86_64-centos-jre-11.0.6_10

echo "... test on alpine"
test_with_docker adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.6_10

