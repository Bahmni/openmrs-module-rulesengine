/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.rulesengine.service;

import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;

public class PatientService {
    public static Patient getPatientByUuid(String patientUuid) {
        Patient patient = Context.getPatientService().getPatientByUuid(patientUuid);
        if(null == patient){
            throw new APIException("Patient not found");
        }
        return patient;
    }
}
