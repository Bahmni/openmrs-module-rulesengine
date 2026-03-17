/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.rulesengine;

import org.junit.Before;
import org.junit.Ignore;
import org.openmrs.module.rulesengine.util.RulesEngineProperties;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;

import java.util.Properties;

@Ignore
public class RulesEngineBaseIT extends BaseModuleWebContextSensitiveTest {
    private Properties properties;

    @Before
    public void beforeEach() {
        properties = new Properties();
        RulesEngineProperties.initialize(properties);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void getProperty(String key) {
        properties.getProperty(key);
    }
}
