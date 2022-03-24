package apCommon;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface TestInterface {
    void TestPrint();

    void TestPrint1();

    static List<TestInterface> getAll() {
        var serviceLoader = ServiceLoader.load(TestInterface.class);
        var serviceLoader1 = serviceLoader.stream().map(ServiceLoader.Provider::get);
        var serviceLoader2 = serviceLoader1.collect(Collectors.toList());
        serviceLoader2.get(0).TestPrint();
        serviceLoader2.get(0).TestPrint1();
        //        return ServiceLoader
//                .load(TestInterface.class)
//                .stream()
//                .map(ServiceLoader.Provider::get)
//                .toList();
        return serviceLoader2;
    }
}
