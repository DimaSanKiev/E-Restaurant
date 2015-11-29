package com.bionic.edu.bean;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.io.Serializable;

@Named
@Scope("session")
public class DeliveryBean implements Serializable {
    private static final long serialVersionUID = 525430134521610706L;


}
