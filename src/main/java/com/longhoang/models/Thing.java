package com.longhoang.models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Table(name = "things")
public class Thing implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String magicScript;

    private int manaNeeded;

    public Thing(String name, String magicScript, int manaNeeded) {
        this.name = name;
        this.magicScript = magicScript;
        this.manaNeeded = manaNeeded;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMagicScript() {
        return magicScript;
    }

    public void setMagicScript(String magicScript) {
        this.magicScript = magicScript;
    }

    public int getManaNeeded() {
        return manaNeeded;
    }

    public void setManaNeeded(int manaNeeded) {
        this.manaNeeded = manaNeeded;
    }

    public Thing() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Thing.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Thing thing = (Thing) target;
        String magicScript = thing.magicScript;
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");

        if (magicScript.length() < 10) {
            errors.rejectValue("magicScript", "magicScript.length");
        }

        String[] result = magicScript.split(" ");
        if (result.length > 1000) {
            errors.rejectValue("magicScript", "magicScript.length");
        }

        int manaNeeded = thing.manaNeeded;

        if (manaNeeded < 0) {
            errors.rejectValue("manaNeeded", "manaNeeded.value");
        }
    }

}
