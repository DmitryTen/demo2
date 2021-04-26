package demo.project.vimpelcom.repositories;

import demo.project.vimpelcom.generated.jooq.tables.records.TableBRecord;
import demo.project.vimpelcom.generated.jooq.tables.records.TableCRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static demo.project.vimpelcom.generated.jooq.Tables.TABLE_C;

@Repository
public class TableCRepository {

    private DSLContext context;

    public TableCRepository(DSLContext context) {
        this.context = context;
    }


    public List<Long> flushOldRecords(LocalDateTime threshold) {
        if (threshold == null) {
            return Collections.emptyList();
        }

        Result<TableCRecord> result = context.selectFrom(TABLE_C)
                .where(TABLE_C.CDAT.lessThan(threshold))
                .fetch();

        List<Long> list = new ArrayList<>();
        result.stream().forEach(record -> {
            int cnt = deleteById(record.getTableCId());
            if (cnt == 1)
                list.add(record.getTableCId());
        });

        return list;
    }

    public int deleteById(Long tableAId) {
        return context.deleteFrom(TABLE_C)
                .where(TABLE_C.TABLE_C_ID.eq(tableAId))
                .execute();
    }

}
