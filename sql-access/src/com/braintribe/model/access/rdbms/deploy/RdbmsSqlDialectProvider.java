// ============================================================================
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
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.model.access.rdbms.deploy;

import com.braintribe.model.access.sql.dialect.DefaultSqlDialect;
import com.braintribe.model.access.sql.dialect.DerbySqlDialect;
import com.braintribe.model.access.sql.dialect.SqlDialect;
import com.braintribe.model.deployment.database.connector.DatabaseConnectionDescriptor;
import com.braintribe.model.deployment.database.connector.GenericDatabaseConnectionDescriptor;
import com.braintribe.model.deployment.database.pool.ConfiguredDatabaseConnectionPool;
import com.braintribe.model.deployment.database.pool.DatabaseConnectionPool;

import tribefire.extension.sqlaccess.model.RdbmsDriver;

/**
 * @author peter.gazdik
 */
public class RdbmsSqlDialectProvider {

	public static SqlDialect provide(RdbmsDriver denotation) {
		DatabaseConnectionPool connectionPool = denotation.getConnectionPool();
		if (!(connectionPool instanceof ConfiguredDatabaseConnectionPool)) {
			return DefaultSqlDialect.INSTANCE;
		}

		DatabaseConnectionDescriptor cd = ((ConfiguredDatabaseConnectionPool) connectionPool).getConnectionDescriptor();
		if (cd instanceof GenericDatabaseConnectionDescriptor) {
			return provide((GenericDatabaseConnectionDescriptor) cd);
		}

		return DefaultSqlDialect.INSTANCE;
	}

	private static SqlDialect provide(GenericDatabaseConnectionDescriptor cd) {
		if (cd.getDriver().startsWith("org.apache.derby")) {
			return DerbySqlDialect.INSTANCE;
		}

		return DefaultSqlDialect.INSTANCE;
	}

}
