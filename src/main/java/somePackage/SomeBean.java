package main.java.somePackage;

import main.java.somePackage.SomeInterface;
import main.java.somePackage.SomeOtherInterface;

public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo(){
        field1.doSomething();
        field2.doSomeOther();
    }
}
