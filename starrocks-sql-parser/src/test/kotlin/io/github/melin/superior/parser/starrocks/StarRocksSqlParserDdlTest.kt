package io.github.melin.superior.parser.starrocks

import io.github.melin.superior.common.relational.create.CreateTable
import io.github.melin.superior.common.relational.drop.DropTable
import org.junit.Assert
import org.junit.Test

class StarRocksSqlParserDdlTest {

    @Test
    fun createTableTest() {
        val sql = """
            create table meta_role (
                id           int          not null,
                tenant_id    int          null comment '租户ID',
                code         varchar(255) null comment '角色code, 字母数字和下划线',
                name         varchar(255) null comment '角色名称，一般为中文',
                type         varchar(32)  not null comment '角色类型：superadmin、workspaceadmin、dataadmin、custom',
                order_index  int          null,
                creater      varchar(45)  null,
                modifier     varchar(45)  null,
                gmt_created  datetime     null,
                gmt_modified datetime     null
            ) ENGINE = olap PRIMARY KEY(id)
            DISTRIBUTED BY HASH (id) BUCKETS 10;
        """.trimIndent()

        val statement = StarRocksHelper.getStatementData(sql)
        
        if (statement is CreateTable) {
            val name = statement.tableId.tableName
            Assert.assertEquals("meta_role", name)
        } else {
            Assert.fail()
        }
    }

    @Test
    fun dropTableTest() {
        val sql = """
           DROP TABLE IF EXISTS example_db.My_table force;
        """.trimIndent()

        val statement = StarRocksHelper.getStatementData(sql)
        
        if (statement is DropTable) {
            Assert.assertEquals("example_db", statement.tableId?.schemaName)
            Assert.assertEquals("My_table", statement.tableId?.tableName)
            Assert.assertTrue(statement.force)
        } else {
            Assert.fail()
        }
    }
}