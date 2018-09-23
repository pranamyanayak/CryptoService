# CryptoService Compute

Calculate the average and standard deviation using a not so worthy crypto service.

## Getting started

These instructions will help you get a preview of how to run this service.

### Using Docker.

Get the latest version of docker. The docker image is already pushed to my docker hub. Download the image and run he service.

1. docker pull pranamya/cryptoservicev1
2. docker images -> Should be able to see the docker image
3. docker run -p 8080:8080 pranamya/cryptoservicev1
4. Server is started

### Using the attached jar file.

1. tar -xvzf CryptoService.tar.gz
2. java -jar CryptoService-0.0.1-SNAPSHOT.jar server


### Run simple API tests

1. http://localhost:8080/crypto/pushAndRecalculate/4
2. http://localhost:8080/crypto/pushAndRecalculate/7
3. http://localhost:8080/crypto/pushAndRecalculate/6
4. http://localhost:8080/crypto/pushRecalculateAndEncrypt/9
5. http://localhost:8080/crypto/decrypt/PkFAfvpNuv3oZR6MmwHDXA== 
6. http://localhost:8080/crypto/decrypt/mospv1teGbZLQNPWNiNX%2FUnj2f%2FGyU5n%2BZbh3qcE9yI%3D
7. http://localhost:8080/crypto/pushAndRecalculate/1

Note: The decrypt url should take in the parameter which should be UTF-8 encoded.
