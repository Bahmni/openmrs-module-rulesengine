/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.rulesengine.domain;

public class Dose {
    private double value;
    private DoseUnit doseUnit;
    private String drugName;

    public Dose(String drugName, double value, DoseUnit doseUnit) {
        this.drugName=drugName;
        this.value = value;
        this.doseUnit = doseUnit;
    }

    public double getValue() {
        return value;
    }

    public DoseUnit getDoseUnit() {
        return doseUnit;
    }

    public enum DoseUnit {
        mg
    }

    public String getDrugName() {
        return drugName;
    }
}
