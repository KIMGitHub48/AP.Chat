package apClient.Domain;

import apCommon.TestInterface;

public class TestInterfaceR implements TestInterface {
    @Override
    public void TestPrint() {
        System.out.println("Сервис работает №0");
    }

    @Override
    public void TestPrint1() {
        System.out.println("Сервис работает №1");
    }


}
