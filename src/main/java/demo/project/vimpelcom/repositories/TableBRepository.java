package demo.project.vimpelcom.repositories;

import demo.project.vimpelcom.generated.jooq.tables.records.TableARecord;
import demo.project.vimpelcom.generated.jooq.tables.records.TableBRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static demo.project.vimpelcom.generated.jooq.Tables.TABLE_B;

@Repository
public class TableBRepository {

    private DSLContext context;

    public TableBRepository(DSLContext context) {
        this.context = context;
    }


    public List<String> flushOldRecords(LocalDateTime threshold) {
        if (threshold == null) {
            return Collections.emptyList();
        }

        Result<TableBRecord> result = context.selectFrom(TABLE_B)
                .where(TABLE_B.CREATE_DATE.lessThan(threshold))
                .fetch();

        List<String> list = new ArrayList<>();
        result.stream().forEach(record -> {
            int cnt = deleteById(record.getTableBId());
            if (cnt == 1)
                list.add(record.getTableBId());
        });

        return list;
    }

    public int deleteById(String tableAId) {
        return context.deleteFrom(TABLE_B)
                .where(TABLE_B.TABLE_B_ID.eq(tableAId))
                .execute();
    }

}
