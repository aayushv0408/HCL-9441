package org.example.constructordi;

public class Employee {
    private Address address;

    public Employee(Address address){
        this.address=address;
    }
    public void getEmpAddress(){
        address.getAddress();
    }
}
