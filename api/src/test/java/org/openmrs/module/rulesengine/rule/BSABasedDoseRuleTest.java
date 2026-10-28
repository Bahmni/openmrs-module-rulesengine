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
import org.mockito.MockedStatic;
import org.openmrs.Patient;
import org.openmrs.module.rulesengine.domain.DosageRequest;
import org.openmrs.module.rulesengine.domain.Dose;
import org.openmrs.module.rulesengine.service.ObservationService;
import org.openmrs.module.rulesengine.service.PatientService;
import org.openmrs.module.rulesengine.util.BahmniMath;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


public class BSABasedDoseRuleTest  {

    private MockedStatic<PatientService> patientServiceMockedStatic;
    private MockedStatic<ObservationService> observationServiceMockedStatic;
    private MockedStatic<BahmniMath> bahmniMathMockedStatic;

    @Before
    public void setUp() throws Exception {
        patientServiceMockedStatic = mockStatic(PatientService.class);
        observationServiceMockedStatic = mockStatic(ObservationService.class);
        bahmniMathMockedStatic = mockStatic(BahmniMath.class);
    }

    @After
    public void tearDown() {
        patientServiceMockedStatic.close();
        observationServiceMockedStatic.close();
        bahmniMathMockedStatic.close();
    }

    @Test
    public void shouldReturnDoseWhenWeightObservationExistForLatestEncounter() throws Exception {
        Patient patient = mock(Patient.class);
        when(patient.getBirthdate()).thenReturn(new Date());

        patientServiceMockedStatic.when(() -> PatientService.getPatientByUuid(any(String.class))).thenReturn(patient);

        observationServiceMockedStatic.when(() -> ObservationService.getLatestObsValueNumeric(patient, ObservationService.ConceptRepo.HEIGHT, "visit_uuid")).thenReturn(189.0);
        observationServiceMockedStatic.when(() -> ObservationService.getLatestObsValueNumeric(patient, ObservationService.ConceptRepo.WEIGHT, "visit_uuid")).thenReturn(69.0);

        bahmniMathMockedStatic.when(() -> BahmniMath.ageInYears(any(Date.class), any(Date.class))).thenReturn(40);
        bahmniMathMockedStatic.when(() -> BahmniMath.getTwoDigitRoundUpValue(any(Double.class))).thenReturn(150.0);

        DosageRequest dosageRequest = new DosageRequest(
                "paracetamol",
                "person_1055_uuid",
                10.0,
                "mg/kg",
                "testorderset",
                "test",
                "visit_uuid"
        );

        Dose dose = new BSABasedDosageRule().calculateDose(dosageRequest);

        assertEquals(150, dose.getValue(), 0.0);
        assertEquals(Dose.DoseUnit.mg, dose.getDoseUnit());
    }

    @Test
    public void calculateBsaShouldReturnCorrectValue() throws Exception{
        Double bsa = BSABasedDosageRule.calculateBSA(170.0, 70.0, 23);
        assertEquals(1.809,bsa,0.001);

        bsa = BSABasedDosageRule.calculateBSA(120.0, 35.0, 10);
        assertEquals(1.080,bsa,0.001);

        bsa = BSABasedDosageRule.calculateBSA(120.0, 35.0, 20);
        assertEquals(1.047,bsa,0.001);

        bsa = BSABasedDosageRule.calculateBSA(120.0, 41.0, 15);
        assertEquals(1.119,bsa,0.001);
    }
}
