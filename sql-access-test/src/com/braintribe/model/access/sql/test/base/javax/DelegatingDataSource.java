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
package com.braintribe.model.access.sql.test.base.javax;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @author peter.gazdik
 */
public class DelegatingDataSource implements DataSource {

	protected DataSource delegate;

	public DelegatingDataSource(DataSource delegate) {
		this.delegate = delegate;
	}

	public void setDelegate(DataSource delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return delegate.getLogWriter();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return delegate.unwrap(iface);
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		delegate.setLogWriter(out);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return delegate.isWrapperFor(iface);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return delegate.getConnection();
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		delegate.setLoginTimeout(seconds);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return delegate.getConnection(username, password);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return delegate.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return delegate.getParentLogger();
	}
	
	
	
}
