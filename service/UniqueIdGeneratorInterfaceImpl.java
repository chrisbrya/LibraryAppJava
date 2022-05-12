package com.library.system.service;

import com.library.system.interfaces.UniqueIdGeneratorInterface;

import java.util.Random;

public class UniqueIdGeneratorInterfaceImpl implements UniqueIdGeneratorInterface {

    public String iDcharacterList = "0123456789ABCDEFGHIJKLNOPQRSTUVWXYZ!@#";


    @Override
    public int randomNumberGenerator() {
        Random random = new Random();
        int upperBound = 37;
        return random.nextInt(upperBound);
    }

    public int randomNumberGenerator(int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound);
    }

    @Override
    public String getUniqueID() {
        String iDcharacterList = "0123456789ABCDEFGHIJKLNOPQRSTUVWXYZ!@#";
        StringBuilder uniqueId = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            uniqueId.append(iDcharacterList.charAt(randomNumberGenerator()));

        }
        return uniqueId.toString();
    }

}
