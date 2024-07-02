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
package com.braintribe.model.access.sql.dialect;

/**
 * @author peter.gazdik
 */
public class DefaultSqlDialect implements SqlDialect {

	public static final DefaultSqlDialect INSTANCE = new DefaultSqlDialect();

	protected DefaultSqlDialect() {
	}

	// @formatter:off
	@Override public String booleanType()        {return StandardSqls.SQL_BOOLEAN;}
	@Override public String stringType()         {return StandardSqls.SQL_VARCHAR_255;}
	@Override public String longStringType()     {return StandardSqls.SQL_CLOB_64K;}
	@Override public String integerType()        {return StandardSqls.SQL_INT;}
	@Override public String longType()           {return StandardSqls.SQL_BIGINT;}
	@Override public String floatType()          {return StandardSqls.SQL_FLOAT;}
	@Override public String doubleType()         {return StandardSqls.SQL_DOUBLE;}
	@Override public String decimalType()        {return StandardSqls.SQL_DECIMAL_31_10;}
	@Override public String dateType()           {return StandardSqls.SQL_TIMESTAMT;}

	@Override public String enumType()           {return stringType();}
	@Override public String entityType()         {return stringType();}
	// @formatter:on

	@Override
	public String showDatabasesLike(String databaseName) {
		return StandardSqls.showDatabasesLike(databaseName);
	}

	@Override
	public String createDatabase(String databaseName) {
		return StandardSqls.createDatabase(databaseName);
	}

	@Override
	public String showTablesLike(String tableName) {
		return StandardSqls.showTablesLike(tableName);
	}

	@Override
	public String createTable(String tableName, String columns) {
		return StandardSqls.createTable(tableName, columns);
	}

	@Override
	public String dropTable(String tableName) {
		return StandardSqls.dropTable(tableName);
	}

	@Override
	public String createMetaTable() {
		return StandardSqls.createMetaTable(this);
	}

	@Override
	public String selectFromMetaTable() {
		return StandardSqls.selectFromMetaTable();
	}

	
	
	@Override
	public String insertIntoPsTemplate(String tableName, String joinedColumnNames, String valuesWildcards) {
		return StandardSqls.insertIntoPsTemplate(tableName, joinedColumnNames, valuesWildcards);
	}

	
}
