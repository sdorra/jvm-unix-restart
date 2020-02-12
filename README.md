# JVM Unix Restart

Restart the JVM by using the Unix C Library.
This project is a proof of concept to test jvm restarts.
The procedure is mainly copied from the awesome [Jenkins](https://github.com/jenkinsci/jenkins/blob/master/core/src/main/java/hudson/lifecycle/UnixLifecycle.java) project.

## Test local and with docker

```bash
mvn package
./run.sh 
```

The snippet above will test the restart with the local java version and with the following docker images:

* adoptopenjdk/openjdk11:x86_64-debian-jre-11.0.6_10
* adoptopenjdk/openjdk11:x86_64-centos-jre-11.0.6_10
* adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.6_10
