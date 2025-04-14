FROM quay.io/malam/devops/maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM image-registry.openshift-image-registry.svc:5000/openshift/java-runtime:openjdk-17-ubi8
WORKDIR /app

LABEL BASE_IMAGE="image-registry.openshift-image-registry.svc:5000/openshift/java-runtime:openjdk-17-ubi8"
LABEL JAVA_VERSION="17"

ENV JAVA_TOOL_OPTIONS="-XX:TieredStopAtLevel=1 -noverify -Xlog:gc*,safepoint=debug:file=/tmp/gc.log.%p:time,uptime:filecount=5,filesize=50M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/"
ENV GC_CONTAINER_OPTIONS="-XX:+UseShenandoahGC"
ENV TZ="Asia/Jakarta"

COPY target/*.jar /deployments/application.jar
