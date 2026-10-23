
package com.abes.hrms.model;

import com.abes.hrms.repository.EmpRepo;
import org.springframework.stereotype.Component;

@Component
public class DemoEmployee {

    private final EmpRepo emprepo;

    public DemoEmployee(EmpRepo emprepo) {
        this.emprepo = emprepo;
    }

    public int add(int a, int b) {
        return emprepo.add(a, b);
    }

    public int greaterNumber(int a, int b) {
        return emprepo.greaterNumber(a, b);
    }

    public double squareRoot() {
        return emprepo.squareRoot(2999);
    }
}
