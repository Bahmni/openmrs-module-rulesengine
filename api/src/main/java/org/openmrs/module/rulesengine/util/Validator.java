/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.rulesengine.util;

import org.openmrs.module.rulesengine.service.ObservationService;

public class Validator {
    public static void validate(Double obsValue, ObservationService.ConceptRepo conceptName) throws Exception {
        if (obsValue == null) {
            throw new Exception(String.format("Please enter %s to calculate dose.", conceptName));
        }
    }

    public static void validateHeightAndWeight(Double heightObsValue, Double weightObsValue, ObservationService.ConceptRepo heightConceptName, ObservationService.ConceptRepo weightConceptName) throws Exception {
        if (heightObsValue == null && weightObsValue == null) {
            throw new Exception(String.format("Please enter %s and %s to calculate dose.", heightConceptName, weightConceptName));
        }
        validate(heightObsValue, heightConceptName);
        validate(weightObsValue, weightConceptName);
    }
}