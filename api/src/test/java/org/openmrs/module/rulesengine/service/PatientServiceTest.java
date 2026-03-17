/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.rulesengine.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.module.rulesengine.RulesEngineBaseIT;

import static org.junit.Assert.assertEquals;

public class PatientServiceTest extends RulesEngineBaseIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        executeDataSet("doseRuleTestData.xml");
    }

    @Test
    public void shouldThrowPatientNotFoundExceptionWhenPatientDoesNotExist() {
        expectedException.expect(APIException.class);
        expectedException.expectMessage("Patient not found");
        PatientService.getPatientByUuid("random_uuid");
    }

    @Test
    public void shouldReturnPatientWhenPatientExist() {
        Patient patient = PatientService.getPatientByUuid("person_1055_uuid");
        assertEquals("person_1055_uuid", patient.getUuid());
    }

}
