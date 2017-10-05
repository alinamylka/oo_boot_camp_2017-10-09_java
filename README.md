# oo_boot_camp_2017-10-09_java
Copyright 2015-17 by Fred George. May be copied with this notice, but not used in classroom training.

OO Boot Camp in Java for client on 9-13 October 2017

Java is the most difficult to setup since there is a broad variety of Java
development tools (and versions). The following instructions are for installing
the code in IntelliJ 2016 by JetBrains. Adapt as necessary for your environment.

## IntelliJ setup
Import the sample code:
- Choose "Import Project"
- Select the pom.xml file (Maven import)
- Accept all the defaults in subsequent dialog boxes

Now tag the source and test directories:
    - File/Project Structure...
    - Select "Modules"
        -- Tag src directory as Sources
        -- Tag test directory as Tests
        -- Click "OK"

Confirm that everything builds correctly (and necessary libraries exist)