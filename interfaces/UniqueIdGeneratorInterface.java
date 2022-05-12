package com.library.system.interfaces;

public interface UniqueIdGeneratorInterface {
    int randomNumberGenerator();
    int randomNumberGenerator(int upperBound);
    String getUniqueID();
}
