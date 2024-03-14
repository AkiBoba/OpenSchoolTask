FROM openjdk:17-alpine
ADD SupplierService/target/SupplierService-0.0.1-SNAPSHOT.jar supplier.jar
ENTRYPOINT ["java", "-jar", "supplier.jar"]