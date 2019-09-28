import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.conf.StatementType;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class Db {

    private DSLContext dslContext;

    public Db(Connection connection) {
        this.dslContext = DSL.using(
                connection,
                SQLDialect.MYSQL,
                new Settings().withStatementType(StatementType.STATIC_STATEMENT));
    }

    public int getRecordCount(String tableName, Map<String, String> fields) {
        return this.dslContext.select()
                .from(table(tableName))
                .where(getConditions(fields))
                .execute();
    }

    private Set<Condition> getConditions(Map<String, String> fields) {
        return fields.entrySet().stream()
                .map(entry -> field(entry.getKey()).eq(entry.getValue()))
                .collect(Collectors.toSet());
    }

    public int executeQuery(String sqlQuery) {
        return this.dslContext.execute(sqlQuery);
    }

    public ResultQuery<Record> getQueryResult(String sqlQuery) {
        return this.dslContext.resultQuery(sqlQuery);
    }
}


