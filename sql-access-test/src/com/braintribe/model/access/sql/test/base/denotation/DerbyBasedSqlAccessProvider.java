// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
package com.braintribe.model.access.sql.test.base.denotation;

import org.apache.derby.jdbc.EmbeddedDriver;

import com.braintribe.model.deployment.database.connector.GenericDatabaseConnectionDescriptor;
import com.braintribe.model.deployment.database.pool.DatabaseConnectionPool;

import tribefire.extension.sqlaccess.model.RdbmsDriver;

/**
 * @author peter.gazdik
 */
public class DerbyBasedSqlAccessProvider extends AbstractCustomizableSqlAccessDenotationProvider {

	static String databaseName = "res/junit/DerbyDb";
	static int derbyPort = 1527;
	static String user = null;
	static String password = null;

	public static RdbmsDriver newDriver() {
		return new DerbyBasedSqlAccessProvider().driver();
	}
	
	@Override
	protected String databaseName() {
		return databaseName;
	}

	@Override
	protected DatabaseConnectionPool connectionPool() {
		return singleThreadHikariCpConnectionPool(derbyConnectionDescriptor());
	}

	private static GenericDatabaseConnectionDescriptor derbyConnectionDescriptor() {
		GenericDatabaseConnectionDescriptor result = GenericDatabaseConnectionDescriptor.T.create();
		result.setDriver(EmbeddedDriver.class.getName());
		result.setUrl(String.format("jdbc:derby:%s;create=true", databaseName));
		result.setUser(user);
		result.setPassword(password);

		return result;
	}

}
