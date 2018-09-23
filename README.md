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


## Design Consideration

1. Calculation of average and standard deviation is inplace. Current average and standard deviations are dependent on previous average and standard deviation and doesn't store all the input's received so far.
2. Welford's online algorithm is used to calculate the streaming average and standard deviations.
          https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance
3. The above algorithm is O(1) and uses O(1) space.
4. Dropwizard is the server of choice as suggested in the instructions.
5. Guice is used as a dependency Injection framework.
6. CryptoImp and ComputeServiceImpl are singleton. This is achieved by the use of Guice annotation. Ensures single copy is used throught the application.
7. AES symmetric algorithm is the algorithm of choice.
8. I tried to use Atomic variables instead of singleton. But since there were multiple mathematical operation involved, computation was never thread safe even after using atomic variables. Some Atomic variables that I tried were AtomicInteger, LongAdders. But none provided thread safety as none of these supported divison in an atomic fashion.
9. (8) was not successful hence resorted to the use of synchronized block in computing.
10. Good read on the performance of Java concurrency modules. https://blog.takipi.com/java-8-longadders-the-fastest-way-to-add-numbers-concurrently/. Very tempting to use LongAdders everytime, but unfortunately, the problem is unable to use any of the Atomic variables.
11. Thought of using SecureRandom to generate new keys each time after the server boots, but this would prevent decryption of already encrypted data especially the ones encrypted before the server boots. Hence a choice was made to use single key throught.
12. As mentioned in the instructions, Base64 encoder is being used. 
13. BouncyCastle crypto provider is chosen. Although on a second thought JCE would be sufficient for this problem as well.
14. CyclicBarrier is used for one of the test to simulate behavior of multiple threads trying to compute average and standard deviation at the same time.
15. Junit is used to set up unit tests.
16. Maven for dependency management.


### Classes

| Class         | Description |          
| ------------- |-------------|
| CryptoModule      | Guice module to initialize the bindings | 
| ComputeServiceController      | Controller where all the routes are defined      |   
| CryptoProvider | Singleton interface to expose all the crypto operation.       | 
| CryptoImpl | Implements the encrypt and decrypt operation. This also encapsulates all the crypto related logic, like algorithm, key generation and creation of cipher for enc/dec operations. |
| ComputeService | Interface which exposes all the external facing compute operations. This is injected in the controller, which then calls the utility methods from this interface. |
| ComputeServiceImpl | Implements ComputeService |
  

