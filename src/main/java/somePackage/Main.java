package main.java.somePackage;

import java.io.IOException;

import main.java.somePackage.SomeInterface;

public class Main{
   public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
       SomeBean someBean = (new Injector()).inject(new SomeBean());
       someBean.foo();
   }
}