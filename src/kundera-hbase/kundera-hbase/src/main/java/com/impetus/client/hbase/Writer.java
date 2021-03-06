/*******************************************************************************
 * * Copyright 2012 Impetus Infotech.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 ******************************************************************************/
package com.impetus.client.hbase;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.metamodel.Attribute;

import org.apache.hadoop.hbase.client.HTableInterface;

import com.impetus.client.hbase.admin.HBaseDataHandler.HBaseDataWrapper;
import com.impetus.kundera.db.RelationHolder;

/**
 * HBase data writer.
 * 
 * @author impetus
 */
public interface Writer
{

    /**
     * Write column.
     * 
     * @param htable
     *            the htable
     * @param columnFamily
     *            the column family
     * @param rowKey
     *            the row key
     * @param column
     *            the column
     * @param columnObj
     *            the column obj
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    void writeColumn(HTableInterface htable, String columnFamily, Object rowKey, Attribute column, Object columnObj)
            throws IOException;

    /**
     * Writes a column family with name <code>columnFamily</code>, into a table
     * whose columns are <code>columns</code>.
     * 
     * @param htable
     *            the htable
     * @param columnFamily
     *            Column Family Name
     * @param rowKey
     *            Row Key
     * @param columns
     *            Columns for a given column family
     * @param values TODO
     * @param columnFamilyObj
     *            the column family obj
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    void writeColumns(HTableInterface htable, String columnFamily, Object rowKey, Map<String, Attribute> columns,
            Map<String, Object> values, Object columnFamilyObj) throws IOException;

    /**
     * Writes Columns <code>columns</code> into a given table. Each columns is
     * written in their own column family(name same as column name)
     * 
     * @param htable
     *            the htable
     * @param rowKey
     *            the row key
     * @param columns
     *            Columns of a given table (No column family given)
     * @param entity
     *            the entity
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    void writeColumns(HTableInterface htable, Object rowKey, Map<String, Attribute> columns, Object entity,
            String columnFamilyName) throws IOException;

    /**
     * Write relations.
     * 
     * @param htable
     *            the htable
     * @param rowKey
     *            the row key
     * @param containsEmbeddedObjectsOnly
     *            the contains embedded objects only
     * @param relations
     *            the relations
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    void writeRelations(HTableInterface htable, Object rowKey, boolean containsEmbeddedObjectsOnly,
            List<RelationHolder> relations, String columnFamilyName) throws IOException;

    /**
     * Writes foreign keys along with a database table. They are stored into a
     * column family named FKey-TO. Each column corresponds to foreign key field
     * name and values are actual foreign keys (separated by ~ if applicable)
     * 
     * @param hTable
     *            the h table
     * @param rowKey
     *            the row key
     * @param foreignKeyMap
     *            the foreign key map
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @deprecated
     */
    public void writeForeignKeys(HTableInterface hTable, String rowKey, Map<String, Set<String>> foreignKeyMap)
            throws IOException;

    /**
     * Writes columns data to HBase table, supplied as a map in Key/ value pair;
     * key and value representing column name and value respectively.
     * 
     * @param htable
     *            the htable
     * @param rowKey
     *            the row key
     * @param columns
     *            the columns
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    void writeColumns(HTableInterface htable, Object rowKey, Map<String, Object> columns, String columnFamilyName)
            throws IOException;

    /**
     * Delete.
     * 
     * @param hTable
     *            the h table
     * @param rowKey
     *            the row key
     * @param columnFamily
     *            the column family
     */
    void delete(HTableInterface hTable, Object rowKey, String columnFamily);

    /**
     * method to perform batch insert/update.
     * 
     * @param rows
     *            data rows
     * @throws IOException
     *             throws io exception.
     */
    void persistRows(Map<HTableInterface, List<HBaseDataWrapper>> rows) throws IOException;
}
