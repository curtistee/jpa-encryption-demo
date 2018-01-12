@TypeDefs({
        @TypeDef(
                name = "encryptedString",
                typeClass = EncryptedStringType.class,
                parameters = {
                        @Parameter(name = "encryptorRegisteredName", value = "hibernateEncryptor")
                }
        )
}) package com.example.demo.entity;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedStringType;
