FROM gradle:jdk13 AS build
COPY . /build
WORKDIR /build
RUN gradle assemble

FROM openjdk:12-jdk-alpine AS final
COPY --from=build /build/build/libs/*.jar /app/
WORKDIR /app
CMD  java -jar -XX:+UseSerialGC -XX:MaxRAM=400m $(ls)
EXPOSE 8080