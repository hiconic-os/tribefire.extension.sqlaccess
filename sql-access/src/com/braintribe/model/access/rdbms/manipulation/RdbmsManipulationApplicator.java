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
package com.braintribe.model.access.rdbms.manipulation;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.braintribe.model.access.rdbms.context.RdbmsManipulationContext;
import com.braintribe.model.access.sql.SqlManipulationReport;
import com.braintribe.model.accessapi.ManipulationResponse;
import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.EntityType;

/**
 * @author peter.gazdik
 */
public class RdbmsManipulationApplicator {

	private final RdbmsManipulationContext context;
	private final SqlManipulationReport localReport;

	private final ManipulationResponse response = ManipulationResponse.T.create();
	private Connection connection;

	public RdbmsManipulationApplicator(RdbmsManipulationContext context, SqlManipulationReport localReport) {
		this.context = context;
		this.localReport = localReport;
	}

	public ManipulationResponse apply() {
		try {
			try (Connection connection = context.getConnection()) {
				this.connection = connection;

				applyHelper();
			}

			return response;

		} catch (Exception e) {
			throw new RuntimeException("Error while applying manipulations.", e);
		}
	}

	private void applyHelper() throws Exception {
		storeNewEntities();
	}

	private void storeNewEntities() throws Exception {
		Map<EntityType<?>, List<GenericEntity>> collect = localReport.getNewEntities().stream()
				.collect(Collectors.groupingBy(GenericEntity::entityType));

		for (Entry<EntityType<?>, List<GenericEntity>> entry : collect.entrySet()) {
			EntityType<?> entityType = entry.getKey();
			List<GenericEntity> entities = entry.getValue();

			NewEntityInserter inserter = new NewEntityInserter(entityType, context);

			inserter.doBulkInsert(connection, entities);
		}
	}

}
