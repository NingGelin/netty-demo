FROM openjdk:8
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        lksctp-tools curl
WORKDIR /app
COPY ./target/sctp-client-1.0-SNAPSHOT-jar-with-dependencies.jar /app/sctp-clien.jar
CMD exec java -jar /app/sctp-clien.jar