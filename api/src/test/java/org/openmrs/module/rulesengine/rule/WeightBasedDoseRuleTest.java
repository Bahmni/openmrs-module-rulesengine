/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.rulesengine.rule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.openmrs.Patient;
import org.openmrs.module.rulesengine.domain.DosageRequest;
import org.openmrs.module.rulesengine.domain.Dose;
import org.openmrs.module.rulesengine.service.ObservationService;
import org.openmrs.module.rulesengine.service.PatientService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class WeightBasedDoseRuleTest  {

    @InjectMocks
    WeightBasedDosageRule weightBasedDoseRule= new WeightBasedDosageRule();

    private MockedStatic<PatientService> patientServiceMockedStatic;
    private MockedStatic<ObservationService> observationServiceMockedStatic;

    @Before
    public void setUp() throws Exception {
        patientServiceMockedStatic = mockStatic(PatientService.class);
        observationServiceMockedStatic = mockStatic(ObservationService.class);
    }

    @After
    public void tearDown() {
        patientServiceMockedStatic.close();
        observationServiceMockedStatic.close();
    }

    @Test
    public void shouldReturnCalculatedDoseBasedOnWeight() throws Exception {
        Patient patient= mock(Patient.class);
        when(patient.getAge()).thenReturn(20);
        patientServiceMockedStatic.when(() -> PatientService.getPatientByUuid(anyString())).thenReturn(patient);
        observationServiceMockedStatic.when(() -> ObservationService.getLatestObsValueNumeric(any(Patient.class), any(ObservationService.ConceptRepo.class), eq(null)))
                .thenReturn(50.0);

        DosageRequest dosageRequest = new DosageRequest("paracetamol","person_1055_uuid", 10.0, "mg/kg","testorderset","mg/kg", null);

        Dose dose = weightBasedDoseRule.calculateDose(dosageRequest);
        assertEquals(500, dose.getValue(), 0.0);
        assertEquals(Dose.DoseUnit.mg, dose.getDoseUnit());
    }
}
