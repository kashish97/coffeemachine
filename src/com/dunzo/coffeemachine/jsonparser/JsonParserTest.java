package com.dunzo.coffeemachine.jsonparser;

import com.dunzo.coffeemachine.inventory.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @Test
    void parseJson() {

       String output =  JsonParser.parseJson("C:\\Users\\kashi\\Desktop\\input_test.json",new Inventory());
        Assertions.assertEquals("Json Keys/Format is incorrect...\nExiting ...",output);

    }

    @Test
    void fileNotFoundTest() {

        String output =  JsonParser.parseJson("C:\\Users\\kashi\\Desktop",new Inventory());
        Assertions.assertEquals("File not Found. Try again...",output);

    }
}