mvn clean install
docker build -t sctp-client:latest --file Dockerfile .
docker stop sctp-client
docker rm sctp-client
docker run -itd --name sctp-client --network host sctp-client:latest